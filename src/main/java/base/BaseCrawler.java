package base;

import common.SeleniumUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.16:59
 * @desc: 爬虫基类
 */
public abstract class BaseCrawler implements Runnable, PageProcessor {

    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();
    /**
     * navs
     */
    protected static List<String> navList = new ArrayList<>();
    /**
     * detailList
     */
    protected static List<String> detailList = new ArrayList<>();


    public BaseCrawler(int threadDept) {
        this.threadDept = threadDept;
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\java\\chromedriver.exe");
    }

    public BaseCrawler(Site site) {
        this.site = site;
    }

    protected static Logger logger = Logger.getLogger(String.valueOf(BaseCrawler.class));
    /**
     * 线程数
     */
    protected int threadDept = 1;
    /**
     * 基类爬虫对象
     */
    protected Spider spider;

    protected Site site;

    protected static WebDriver webDriver;

    protected int runTime = 0;

    public Spider getSpider() {
        return spider;
    }

    /**
     * 停止爬虫
     */
    public void stop() {
        if (spider != null) {
            spider.stop();
        }

    }

    /**
     * 针对下拉分页的网站
     *
     * @param page
     * @return
     */
    public Document getNextPager(Page page) {
        if (runTime == 0) {
            webDriver = new ChromeDriver();
        }
        runTime++;
        Html html;
        try {
            //设置超时时间
            webDriver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
            webDriver.get(page.getUrl().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            while (true) {
                try {
                    //休眠6秒 防止 没加载出来就退出了
                    Thread.sleep(1000);
                    //判断是否翻到底了
                    if (SeleniumUtils.checkIsFlipPages(webDriver)) {
                        break;
                    }
                    //向下翻页
                    SeleniumUtils.rollDown(webDriver);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    continue;
                }
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            html = new Html(webElement.getAttribute("outerHTML"));
        }
        //如果跑完之后 关闭
        if (navList.size() == runTime) {
            webDriver.close();
        }
        return html.getDocument();
    }

    /**
     * 翻页 +点击
     *
     * @param page
     * @param ClickText
     * @return
     */
    public Document getNextPager(Page page, String ClickText) {
        if (runTime == 0) {
            webDriver = new ChromeDriver();
        }
        runTime++;
        Html html;
        try {
            //设置超时时间
            webDriver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
            webDriver.get(page.getUrl().toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            while (true) {
                try {

                    if (!Strings.isBlank(ClickText)) {
                        SeleniumUtils.click(webDriver, ClickText);
                    }
                    //休眠6秒 防止 没加载出来就退出了
                    Thread.sleep(3000);
                    //判断是否翻到底了
                    if (SeleniumUtils.checkIsFlipPages(webDriver)) {
                        break;
                    }
                    //向下翻页
                    SeleniumUtils.rollDown(webDriver);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    continue;
                }
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            html = new Html(webElement.getAttribute("outerHTML"));
        }
        //如果跑完之后 关闭
        if (navList.size() == runTime) {
            webDriver.close();
        }
        return html.getDocument();
    }

}
