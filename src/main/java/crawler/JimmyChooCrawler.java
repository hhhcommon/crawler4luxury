package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.RegexUtil;
import core.model.ProductCrawler;
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
 * @Date: 2017/12/8.17:47
 * @Desc: JimmyChooCrawler
 */
public class JimmyChooCrawler extends BaseCrawler {

    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();
    private static String reg = "http://row.jimmychoo.com/.*?/.*?/.*?/.*?";

    public JimmyChooCrawler(int threadDept) {
        super(threadDept);
    }

    @Override
    public void run() {
        logger.info(">>>>JimmyChooCrawler start<<<<");
        urls.add("http://row.jimmychoo.com/en_CN/home");
        spider = Spider.create(new JimmyChooCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(CrawlerPipeline.getInstall())
                .thread(threadDept);
        try {
            SpiderMonitor.instance().register(spider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info(">>>>process start<<<<" + page.getUrl().toString());
        init();
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            //获取navs
            Elements elements = document.getElementsByClass("js-main-navigation level-1-list clearfix js-link-wrapper").first().getElementsByTag("li");
            for (Element element : elements) {
                if (element.hasClass("level-1-item") || element.hasClass("level-2-item") || element.hasClass("level-3-item")) {
                    String navLink = element.getElementsByTag("a").attr("href").trim();
                    if (!Strings.isBlank(navLink)) {
                        if (navLink.matches(reg)) {
                            page.addTargetRequest(navLink);
                            logger.info("加入到采集队列  " + navLink);
                            navList.add(navLink);
                        }

                    }
                }
            }
        }
        //获取详情页面
        if (navList.contains(page.getUrl().toString())) {
            init();
            Document document1 = driverComponent.getNextPager(page, webDriver);
            Elements elements = document1.getElementsByClass("js-producttile_link");
            for (Element element : elements) {
                String link = element.attr("href");
                if (!Strings.isBlank(link)) {
                    link = "http://row.jimmychoo.com" + link;
                    page.addTargetRequest(link);
                    logger.info("加入到采集队列  " + link);
                    detailList.add(link);
                }
            }
        }
        //解析详情页面
        if (detailList.contains(page.getUrl().toString())) {
            document = driverComponent.getPage(page.getUrl().toString(), webDriver);
            String pname = document.select("h1.productCrawler-name").text();
            String prize = document.getElementsByClass("text-uppercase").attr("content");
            String desc = document.getElementById("tab2").text();
            String ref = RegexUtil.getDataByRegex("\"sku\":\"(.*?)\",\"sku_code\":", page.getHtml().toString());
            String Classification = document.select("span[itemprop=name]").text();
            List<String> colorLs = new ArrayList<>();
            Elements elements = null;
            try {
                elements = document.select("ul[class=js-menu-swatches Color js-menu-color menu-horz-block clearfix]").first().getElementsByTag("li");
            } catch (Exception e) {

            }
            try {
                for (Element element : elements) {
                    String color = element.getElementsByTag("a").attr("title");
                    if (!Strings.isBlank(color)) {
                        colorLs.add(color);
                    }
                }
            } catch (Exception e) {
            }
            List<String> sizeLs = new ArrayList<>();
            Elements sizeEl = null;
            try {
                sizeEl = document.select("ul[class=js-menu-swatches menu-horz-block clearfix size]").first().getElementsByTag("li");
            } catch (Exception e) {

            }
            try {
                for (Element element : sizeEl) {
                    String size = element.select("span[class=js-swatch-value]").text().split(" ")[0];
                    if (!Strings.isBlank(size)) {
                        sizeLs.add(size);
                    }
                }
            } catch (Exception e) {

            }
            List<String> imgLs = new ArrayList<>();
            Elements imgEl = document.select("img[class=js-producttile_image js-productCrawler-image productCrawler-image]");
            for (Element element : imgEl) {
                String img = element.attr("src").trim();
                if (!Strings.isBlank(img)) {
                    imgLs.add(img);
                }
            }
            ProductCrawler productCrawler = new ProductCrawler();
            productCrawler.setUrl(page.getUrl().toString());
            productCrawler.setRef(ref);
            productCrawler.setName(pname);
            productCrawler.setImg(Joiner.on("|").join(imgLs));
            productCrawler.setColor(Joiner.on("|").join(colorLs));
            productCrawler.setIntroduction(desc);
            productCrawler.setEnPrice(prize);
            productCrawler.setClassification(Classification);
            productCrawler.setBrand("jimmychoo");
            productCrawler.setLanguage("en_CN");
            page.putField("productCrawler", productCrawler);
        }


    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.jimmychoo.com")
                .addCookie("dwgeoip", "jchgb#en_GB#GB")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }

    public static void main(String[] args) {
        DbUtil.init();
        new JimmyChooCrawler(1).run();
    }
}
