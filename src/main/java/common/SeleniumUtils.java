package common;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/13.14:35
 * @Desc: SeleniumUtils for Selenium
 */
public class SeleniumUtils {

    protected static Logger logger = Logger.getLogger(String.valueOf(SeleniumUtils.class));


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


    public static void click(WebDriver driver, List<String> clickText) {
        WebElement element = null;
        for (String list : clickText) {
            try {
                element = driver.findElement(By.linkText(list));

            } catch (Exception e) {
                logger.info("该页面无【" + list + "】关键词>>>>");
                continue;
            }
        }
        if (!Objects.isNull(element)) {
            element.click();
        }
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
