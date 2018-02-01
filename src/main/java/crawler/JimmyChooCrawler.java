package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.Commons;
import common.DbUtil;
import common.RegexUtil;
import componentImpl.WebDriverManager;
import core.model.ProductCrawler;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
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
 * 更换 cookie
 * <p>
 * 没有中文
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
        urls.add("http://row.jimmychoo.com/en_CN/women/shoes/pumps/");
        spider = Spider.create(new JimmyChooCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(CrawlerPipeline.getInstall())
                .thread(threadDept);
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info(">>>>process start<<<<" + page.getUrl().toString());
        if (urls.contains(page.getUrl().toString())) {
            Document document = page.getHtml().getDocument();
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
            logger.info("navList  " + navList.size());
        }
        //获取详情页面
        if (navList.contains(page.getUrl().toString())) {
            WebDriver webDriver1 = null;
            try {
                webDriver1 = WebDriverManager.getInstall().get(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Document document1 = WebDriverManager.getInstall().getNextPager(page, webDriver1);
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
            logger.info("detailList  " + detailList.size());
            WebDriverManager.getInstall().returnToPool(webDriver1);
        }
        //解析详情页面
        if (detailList.contains(page.getUrl().toString())) {
            WebDriver webDriver1 = null;
            try {
                webDriver1 = WebDriverManager.getInstall().get(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Document document = WebDriverManager.getInstall().getPage(page.getUrl().toString(), webDriver1);
            String pname = document.select("meta[itemprop=name]").attr("content");
            String prize = document.select("meta[property=og:price:amount]").attr("content");
            String desc = document.getElementById("tab2").text();
            String ref = RegexUtil.getDataByRegex("\"sku\":\"(.*?)\",\"sku_code\":", page.getHtml().toString());
            String Classification = RegexUtil.getDataByRegex("dw.ac.applyContext\\(\\{category: \"(.*?)\"\\}\\)", document.outerHtml().toString());
            List<String> imgLs = new ArrayList<>();
            Elements imgEl = document.select("img[class=productthumbnail]");
            for (Element element : imgEl) {
                String img = null;
                try {
                    img = element.attr("src").trim().split("\\?")[0];
                } catch (Exception e) {
                }
                if (!Strings.isBlank(img)) {
                    imgLs.add(img);
                }
            }
            ProductCrawler productCrawler = new ProductCrawler();
            productCrawler.setUrl(page.getUrl().toString());
            productCrawler.setRef(ref);
            //没有中文
            productCrawler.setName(pname);
            productCrawler.setEngName(pname);
            productCrawler.setImg(Joiner.on("|").join(imgLs));
            productCrawler.setIntroduction(desc);
            productCrawler.setPrice(prize);
            productCrawler.setClassification(Classification);
            productCrawler.setTags("中文没有");
            productCrawler.setBrand("jimmychoo");
            productCrawler.setLanguage("en_CN");
            page.putField(Commons.cwJob, productCrawler);
            WebDriverManager.getInstall().returnToPool(webDriver1);
            removeListAndClose(page.getUrl().get());
        }

    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.jimmychoo.com")
//                .addCookie("dwgeoip", "jchgb#en_GB#GB")
                .addCookie("dwgeoip", "jchrow#en_CN#CN")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }

    public static void main(String[] args) {
        DbUtil.init();
        new JimmyChooCrawler(3).run();
    }
}