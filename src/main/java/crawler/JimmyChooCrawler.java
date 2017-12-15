package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import common.RegexUtil;
import core.model.Product;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.selector.Html;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/8.17:47
 * @Desc: JimmyChooCrawler
 */
public class JimmyChooCrawler extends BaseCrawler {

    private static String reg = "http://row.jimmychoo.com/.*?/.*?/.*?/.*?";

    public JimmyChooCrawler(int threadDept) {
        super(threadDept);
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\java\\chromedriver.exe");
    }

    @Override
    public void run() {
        logger.info(">>>>JimmyChooCrawler start<<<<");
        urls.add("http://row.jimmychoo.com/en_CN/home");
        spider = Spider.create(new JimmyChooCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(new CrawlerPipeline())
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
                            navList.add(navLink);
                        }

                    }
                }
            }
        }
        //获取详情页面
        if (navList.contains(page.getUrl().toString())) {
            Document document1 = getNextPager(page);
            Elements elements = document1.getElementsByClass("js-producttile_link");
            for (Element element : elements) {
                String link = element.attr("href");
                if (!Strings.isBlank(link)) {
                    page.addTargetRequest("http://row.jimmychoo.com" + link);
                    detailList.add("http://row.jimmychoo.com" + link);
                }
            }
        }
        //解析详情页面
        if (detailList.contains(page.getUrl().toString())) {
            destroy();
            String pname = document.select("h1.product-name").text();
            String prize = document.getElementsByClass("text-uppercase").attr("content");
            String desc = document.getElementById("tab2").text();
            String ref = RegexUtil.getDataByRegex("\"sku\":\"(.*?)\",\"sku_code\":", page.getHtml().toString());
            String Classification = document.select("span[itemprop=name]").text();
            List<String> colorLs = new ArrayList<>();
            Elements elements = document.select("ul[class=js-menu-swatches Color js-menu-color menu-horz-block clearfix]").first().getElementsByTag("li");
            for (Element element : elements) {
                String color = element.getElementsByTag("a").attr("title");
                if (!Strings.isBlank(color)) {
                    colorLs.add(color);
                }
            }
            List<String> sizeLs = new ArrayList<>();
            Elements sizeEl = document.select("ul[class=js-menu-swatches menu-horz-block clearfix size]").first().getElementsByTag("li");
            for (Element element : sizeEl) {
                String size = element.select("span[class=js-swatch-value]").text().split(" ")[0];
                if (!Strings.isBlank(size)) {
                    sizeLs.add(size);
                }
            }
            List<String> imgLs = new ArrayList<>();
            Elements imgEl = document.select("img[class=js-producttile_image js-product-image product-image]");
            for (Element element : imgEl) {
                String img = element.attr("src").trim();
                if (!Strings.isBlank(img)) {
                    imgLs.add(img);
                }
            }
            Product product = new Product();
            product.setUrl(page.getUrl().toString());
            product.setRef(ref);
            product.setName(pname);
            product.setImg(Joiner.on("|").join(imgLs));
            product.setColor(Joiner.on("|").join(colorLs));
            product.setIntroduction(desc);
            product.setEnPrice(prize);
            product.setClassification(Classification);
            product.setBrand("jimmychoo");
            product.setLanguage("en_CN");
            page.putField("product", product);
        }


    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.jimmychoo.com")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }

    public static void main(String[] args) {
        new JimmyChooCrawler(1).run();
    }
}
