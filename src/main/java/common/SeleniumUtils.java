package common;

import base.BaseCrawler;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/13.14:35
 * @Desc: SeleniumUtils for Selenium
 */
public class SeleniumUtils {

    protected static Logger logger = Logger.getLogger(String.valueOf(SeleniumUtils.class));


    /**
     * 针对下拉分页的网站
     *
     * @param page
     * @return
     */
    public static Document getNextPager(Page page, WebDriver webDriver) {
        try {
            Html html;
            try {
                //设置超时时间
                webDriver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
                webDriver.get(page.getUrl().toString());
            } catch (Exception e) {
                //设置超时时间
                webDriver = new ChromeDriver();
                webDriver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
                webDriver.get(page.getUrl().toString());
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
            return html.getDocument();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static Boolean checkIsFlipPages(WebDriver webDriver) {

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

        if (ScrollTop >= ScrollHeight - WindowHeight) {
            return true;
        } else return false;

    }


    public static void rollDown(WebDriver webDriver) {

        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    public static void click(WebDriver driver, String clickText) {
        try {
            WebElement element = driver.findElement(By.linkText(clickText));
            element.click();
        } catch (Exception e) {
            logger.info("该页面无【" + clickText + "】关键词>>>>");
        }
    }

}
