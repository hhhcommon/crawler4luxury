package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.Commons;
import common.DbUtil;
import common.RegexUtil;
import componentImpl.WebDriverManager;
import core.model.ProductCrawler;
import download.SeleniumDownloader;
import download.SeleniumDownloader2;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/8.15:31
 * @Desc: GucciCrawler
 * 没有香港
 */
public class GucciCrawler extends BaseCrawler {
    /**
     * urls 存储入口urls
     */
    protected static List<String> urls = new ArrayList<>();
    /**
     * 存储 获取下一页的 点击文字
     */
    static ArrayList<String> getNextPagerTextList = new ArrayList(Arrays.asList("加载更多", "加载全部", "LOAD ALL", "浏览所有"));

    public GucciCrawler(int threadDept) {
        super(threadDept);
    }

    private static SeleniumDownloader2 seleniumDownloader2 = new SeleniumDownloader2();

    public static void main(String[] args) {
        DbUtil.init();
        new GucciCrawler(4).run();
    }

    @Override
    public void run() {
        try {
//            urls.add("https://www.gucci.cn/zh/ca/women");
            urls.add("https://www.gucci.com/uk/en_gb/");
            urls.add("https://www.gucci.com/de/de/");
            spider = Spider.create(new GucciCrawler(threadDept))
                    .addUrl((String[]) urls.toArray(new String[urls.size()]))
                    .addPipeline(CrawlerPipeline.getInstall())
                    .thread(threadDept);
            spider.setDownloader(seleniumDownloader2);
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(Page page) {
        logger.info("开始处理 page " + page.getUrl().get());
        //获取navs
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().get())) {

            Elements elementsNav = null;
            try {
                elementsNav = document.select("a[class=header-nav-item]");
                if (elementsNav.size() == 0) {
                    elementsNav = document.getElementsByClass("navi_seo").first().getElementsByTag("ul").first().getElementsByTag("li");
                    for (Element element : elementsNav) {
                        //获取到nav的link
                        String navLink = element.getElementsByTag("a").attr("href");
                        if (!Strings.isBlank(navLink) && !navLink.contains("runway")) {
                            if (page.getUrl().toString().contains("/zh") && RegexUtil.checkHttp(page.getUrl().toString())) {
                                navLink = "https://www.gucci.cn" + navLink;
                            } else {
                                navLink = "https://www.gucci.com" + navLink;
                            }
                            navList.add(navLink);
                            //加到爬取队列
                            page.addTargetRequest(navLink);
                            logger.info("加入到导航采集队列 " + navLink);
                        }
                    }
                } else {
                    for (Element element : elementsNav) {
                        //获取到nav的link
                        String navLink = element.attr("href");
                        if (!Strings.isBlank(navLink) && !navLink.contains("runway")) {
                            if (page.getUrl().toString().contains("/zh") && RegexUtil.checkHttp(page.getUrl().toString())) {
                                navLink = "https://www.gucci.cn" + navLink;
                            } else {
                                navLink = "https://www.gucci.com" + navLink;
                            }
                            navList.add(navLink);
                            //加到爬取队列
                            page.addTargetRequest(navLink);
                            logger.info("加入到采集队列 " + navLink);
                        }
                    }
                }

                logger.info("navLink  " + navList.size());
            } catch (Exception e) {

            }

        }
        //获取详情页
        if (navList.contains(page.getUrl().toString())) {
            seleniumDownloader2.setGetNextPagerTextList(getNextPagerTextList);
            Elements elements = document.getElementsByClass("product-tiles-grid-item-link");
            if (elements.size() == 0) {
                elements = document.getElementsByClass("spice-item-hover");
                for (Element element : elements) {
                    String navLink = element.getElementsByTag("a").first().attr("href");
                    if (page.getUrl().toString().contains("/zh") && RegexUtil.checkHttp(page.getUrl().toString())) {
                        navLink = "https://www.gucci.cn" + navLink;
                    } else {
                        navLink = "https://www.gucci.com" + navLink;
                    }
                    if (!Strings.isBlank(navLink)) {
                        logger.info("加入到采集队列 " + navLink);
                        detailList.add(navLink);
                        page.addTargetRequest(navLink);
                    }
                }
            } else {
                for (Element element : elements) {
                    String navLink = element.attr("href");
                    if (page.getUrl().toString().contains("/zh") && RegexUtil.checkHttp(page.getUrl().toString())) {
                        navLink = "https://www.gucci.cn" + navLink;
                    } else {
                        navLink = "https://www.gucci.com" + navLink;
                    }
                    if (!Strings.isBlank(navLink)) {
                        logger.info("加入到采集队列 " + navLink);
                        detailList.add(navLink);
                        page.addTargetRequest(navLink);
                    }
                }
            }
            logger.info("detailList  " + detailList.size());
        }
        //分析详情页
        if (detailList.contains(page.getUrl().get()) && !navList.contains(page.getUrl().get())) {
            logger.info("处理详情页>>>>" + page.getUrl().get());
            seleniumDownloader2.setGetNextPagerTextList(null);
            //处理国内的网站
            if (page.getUrl().get().contains("https://www.gucci.cn/zh/pr/")) {
                logger.info("处理详情页>>>>" + page.getUrl().toString());
                String pname = null;
                try {
                    pname = document.getElementsByClass("spice-product-name").first().text();
                } catch (Exception e) {
                    pname = document.getElementsByTag("title").text().split("\\|")[0];
                }
                String prize = null;
                try {
                    prize = document.select("span[class=goods-price]").first().text();
                } catch (Exception e) {
                    prize = document.select("span[id=markedDown_full_Price]").first().text();
                }
                String desc = null;
                try {
                    desc = document.select("div[class=spice-product-detail]").first().text();
                } catch (Exception e) {
                    desc = document.select("div[class=accordion-drawer]").first().text();
                }
                String ref = null;
                try {
                    ref = document.select("div[class=spice-style-number-title]").first().getElementsByTag("span").text().trim();
                } catch (Exception e) {
                    ref = document.select("div[class=style-number-title]").first().getElementsByTag("span").text().trim();
                }
                Elements imgs = document.select("div[class=spice-carsoul-wrapper] div[class=spice-standard-image]");
                List<String> imgList = new ArrayList<>();
                for (Element element : imgs) {
                    String img = element.getElementsByTag("img").attr("data-cloudzoom");
                    if (!Strings.isBlank(img)) {
                        imgList.add(img);
                    }
                }
                //分类
                String classf = document.select("ul[class=spice-list-inline]").text();
                ProductCrawler p = new ProductCrawler();
                p.setBrand("gucci");
                p.setImg(Joiner.on("|").join(imgList));
                p.setName(pname);
                p.setClassification(classf);
                p.setUrl(page.getUrl().get());
                p.setPrice(prize);
                p.setLanguage("zh_CN");
                p.setIntroduction(desc);
                p.setRef(ref);
                page.putField(Commons.cwJob, p);
                page.putField(Commons.cfName, true);
                page.putField(Commons.imgName, true);

            } else {
                //处理国外的网站
                String pname = null;
                try {
                    pname = document.getElementsByClass("spice-product-name").first().text();
                } catch (Exception e) {
                    pname = document.getElementsByClass("product-name product-detail-product-name").first().text();
                }
                String prize = null;
                try {
                    prize = RegexUtil.getDataByRegex("impression.price=\"(.*?)\";", document.outerHtml().toString());
                } catch (Exception e) {
                    prize = document.select("span[id=markedDown_full_Price]").first().text();
                }

                String desc = null;
                try {
                    desc = document.select("div[class=spice-product-detail]").first().text();
                } catch (Exception e) {
                    desc = document.select("div[class=accordion-drawer]").first().text();
                }
                String ref = null;
                try {
                    ref = document.select("div[class=spice-style-number-title]").first().getElementsByTag("span").text().trim();
                } catch (Exception e) {
                    ref = document.select("div[class=style-number-title]").first().getElementsByTag("span").text().trim();
                }
                Elements imgs = document.select("section[class=product-detail-images] img");
                List<String> imgList = new ArrayList<>();
                for (Element element : imgs) {
                    String img = element.attr("src");
                    if (!Strings.isBlank(img)) {
                        imgList.add(img);
                    }
                }
                String classf = null;
                try {
                    classf = RegexUtil.getDataByRegex("impression.category=\"(.*?)\";", document.outerHtml().toString());
                    classf = classf.replaceAll("/", "-").replaceAll("'s", "");
                } catch (Exception e) {
                    logger.info("类别错误");
                }
                ProductCrawler p = new ProductCrawler();
                p.setBrand("gucci");
                p.setImg(Joiner.on("|").join(imgList));
                p.setName(pname);
                p.setUrl(page.getUrl().toString());
                if (page.getUrl().toString().contains("uk/en_gb")) {
                    try {
                        prize = prize.replace(",", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    p.setEnPrice(prize);
                    p.setEngName(pname);
                    p.setLanguage("en_gb");
                }
                if (page.getUrl().toString().contains("de/de/pr")) {
                    try {
                        prize = prize.replace(".", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    p.setEurPrice(prize);
                    p.setLanguage("de_de");
                }
                p.setIntroduction(desc);
                p.setClassification(classf);
                p.setRef(ref);
                page.putField(Commons.cwJob, p);
                page.putField(Commons.imgName, true);
                page.putField(Commons.EngName, true);
                page.putField(Commons.cfName, true);
            }
        }
    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setCycleRetryTimes(4)
                .setCharset("UTF-8")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
