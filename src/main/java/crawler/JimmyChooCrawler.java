package crawler;

import base.BaseCrawler;
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
    /**
     * urls
     */
    private static List<String> urls = new ArrayList<>();
    /**
     * navs
     */
    private static List<String> navList = new ArrayList<>();
    /**
     * detailList
     */
    private static List<String> detailList = new ArrayList<>();

    public JimmyChooCrawler(int threadDept) {
        super(threadDept);
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

        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            //获取navs
            Elements elements = document.getElementsByClass("js-main-navigation level-1-list clearfix js-link-wrapper").first().getElementsByTag("li");
            for (Element element : elements) {
                if (element.hasClass("level-1-item") || element.hasClass("level-2-item") || element.hasClass("level-3-item")) {
                    String navLink = element.getElementsByTag("a").attr("href").trim();
                    if (!Strings.isBlank(navLink)) {
                        page.addTargetRequest(navLink);
                        navList.add(navLink);
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
                    page.addTargetRequest(link);
                    detailList.add(link);
                }
            }
        }
//解析详情页面
        if (detailList.contains(page.getUrl().toString())) {


        }


    }

    private Document getNextPager(Page page) {
        System.getProperties().setProperty("webdriver.chrome.driver",
                "D:\\java\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get(page.getUrl().toString());

            while (true) {
                //获取滚动条的位置  document.body.scrollTop
                Object javascriptExecutor = ((JavascriptExecutor) webDriver).executeScript("return document.documentElement.clientHeight");
                Long clientHeightdoc = (Long) javascriptExecutor;

                Object clientHeight = ((JavascriptExecutor) webDriver).executeScript("return  document.body.clientHeight");
                Long clientHeightLong = (Long) clientHeight;

                Object sorcll = ((JavascriptExecutor) webDriver).executeScript("return  document.documentElement.scrollTop+document.body.scrollTop");
                Long scorcllhight = (Long) sorcll;

                if (Math.abs(clientHeightLong - clientHeightdoc) <= (scorcllhight)) {
                    break;
                }
                ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(3000);
            }
            Thread.sleep(3000);
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            Html html = new Html(webElement.getAttribute("outerHTML"));
            return html.getDocument();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            webDriver.close();
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
