package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.RegexUtil;
import core.model.ProductCrawler;
import download.SeleniumDownloader;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/8.15:31
 * @Desc: GucciCrawler
 */
public class GucciCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public GucciCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new GucciCrawler(1).run();
    }

    @Override
    public void run() {
        try {
            init();
            List<String> listText = new ArrayList<>();
            listText.add("加载更多");
            listText.add("加载全部");
            listText.add("LOAD ALL");
            urls.add("https://www.gucci.cn/zh/");
//            urls.add("https://www.gucci.com/uk/en_gb/");
//            urls.add("https://www.gucci.com/de/de/");
            spider = Spider.create(new GucciCrawler(threadDept))
                    .addUrl((String[]) urls.toArray(new String[urls.size()]))
                    .addPipeline(CrawlerPipeline.getInstall())
                    .setDownloader(new SeleniumDownloader(webDriver, driverComponent, listText))
                    .thread(threadDept);
            try {
                SpiderMonitor.instance().register(spider);
            } catch (JMException e) {
                e.printStackTrace();
            }
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(Page page) {
        logger.info("开始处理 page " + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        //获取navs
        if (urls.contains(page.getUrl().toString())) {
            Elements elementsNav = document.getElementsByClass("navi_seo").first().getElementsByTag("ul").first().getElementsByTag("li");
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
                    logger.info("加入到采集队列" + navLink);
                }
            }
        }
        //获取详情页
        if (navList.contains(page.getUrl().toString())) {
            Elements elements = document.getElementsByClass("spice-item-hover");
            for (Element element : elements) {
                String navLink = element.getElementsByTag("a").first().attr("href");
                if (page.getUrl().toString().contains("/zh") && RegexUtil.checkHttp(page.getUrl().toString())) {
                    navLink = "https://www.gucci.cn" + navLink;
                } else {
                    navLink = "https://www.gucci.com" + navLink;
                }
                if (!Strings.isBlank(navLink)) {
                    logger.info("加入到采集队列" + navLink);
                    detailList.add(navLink);
                    page.addTargetRequest(navLink);
                }
            }
        }
        //分析详情页
        if (detailList.contains(page.getUrl().toString()) && !navList.contains(page.getUrl().toString())) {

            logger.info("处理详情页>>>>" + page.getUrl().toString());
            String pname = null;
            try {
                pname = document.getElementsByClass("spice-product-name").first().text();
            } catch (Exception e) {
                pname = document.getElementsByClass("product-name product-detail-product-name").first().text();
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
            Elements imgs = document.select("div[class=spice-standard-image]");
            if (imgs.size() < 0) {
                imgs = document.getElementsByClass("item product-detail-image-slide zoom-in");
            }
            List<String> imgList = new ArrayList<>();
            for (Element element : imgs) {
                String img = element.getElementsByTag("img").attr("src");
                if (!Strings.isBlank(img)) {
                    imgList.add(img);
                }
            }
            ProductCrawler p = new ProductCrawler();
            p.setBrand("gucci");
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pname);
            p.setUrl(page.getUrl().toString());
            if (page.getUrl().toString().contains("uk/en_gb")) {
                p.setEnPrice(prize);
                p.setLanguage("en_gb");
            }
            if (page.getUrl().toString().contains("zh/pr")) {
                p.setPrice(prize);
                p.setLanguage("zh_CN");
            }
            if (page.getUrl().toString().contains("de/de/pr")) {
                p.setEurPrice(prize);
                p.setLanguage("de_de");
            }
            p.setIntroduction(desc);
            p.setRef(ref);
            page.putField("product", p);
        }
    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.gucci.cn")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
