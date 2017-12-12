package base;

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
        try {
            webDriver.get(page.getUrl().toString());
            while (true) {
                //休眠6秒 防止 没加载出来就退出了
                Thread.sleep(2000);

                //判断滚动条是否到达底部

                Object javascriptExecutor = ((JavascriptExecutor) webDriver).executeScript("var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;\n" +
                        "　　if(document.body){\n" +
                        "　　　　bodyScrollTop = document.body.scrollTop;\n" +
                        "　　}\n" +
                        "　　if(document.documentElement){\n" +
                        "　　　　documentScrollTop = document.documentElement.scrollTop;\n" +
                        "　　}\n" +
                        "　　scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;\n" +
                        "　　return scrollTop;");
                Long ScrollTop = (Long) javascriptExecutor;

                javascriptExecutor = ((JavascriptExecutor) webDriver).executeScript("var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;\n" +
                        "　　if(document.body){\n" +
                        "　　　　bodyScrollHeight = document.body.scrollHeight;\n" +
                        "　　}\n" +
                        "　　if(document.documentElement){\n" +
                        "　　　　documentScrollHeight = document.documentElement.scrollHeight;\n" +
                        "　　}\n" +
                        "　　scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;\n" +
                        "　　return scrollHeight;");
                Long ScrollHeight = (Long) javascriptExecutor;

                javascriptExecutor = ((JavascriptExecutor) webDriver).executeScript("var windowHeight = 0;\n" +
                        "　　if(document.compatMode == \"CSS1Compat\"){\n" +
                        "　　　　windowHeight = document.documentElement.clientHeight;\n" +
                        "　　}else{\n" +
                        "　　　　windowHeight = document.body.clientHeight;\n" +
                        "　　}\n" +
                        "　　return windowHeight;");
                Long WindowHeight = (Long) javascriptExecutor;


//                if (Math.abs(clientHeightLong - clientHeightdoc) <= (scorcllhight)) {
//                    break;
//                }

                if (ScrollTop >= ScrollHeight - WindowHeight) {
                    break;
                }

                ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Thread.sleep(5000);
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            Html html = new Html(webElement.getAttribute("outerHTML"));
            //如果跑完之后 关闭
            if (navList.size() == runTime) {
                webDriver.close();
            }
            return html.getDocument();
        } catch (InterruptedException e) {
            e.printStackTrace();
            webDriver.close();
            return null;
        }
    }

}
