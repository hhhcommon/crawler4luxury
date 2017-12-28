package componentImpl;

import absCompone.DriverComponent;
import common.SeleniumUtils;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yang
 * @Date: 2017/12/20.14:57
 * @Desc: 创建一个webDriver 对象
 */
public class WebDriverComponent extends DriverComponent {

    /**
     * 创建一个webderive
     *
     * @param sec 设置下超时时间
     * @return webDriver
     */
    @Override
    public WebDriver create(int sec) {
        try {
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().setScriptTimeout(sec, TimeUnit.SECONDS);
            return webDriver;
        } catch (Exception e) {
            throw new IllegalArgumentException("创建WebDriver发生错误了!" + e.toString());
        }
    }

    /**
     * 销毁 WebDriver
     */
    @Override
    public void destoty() {
        if (webDriver != null) {
            webDriver.close();
            webDriver = null;
        }
    }

    @Override
    public Document getNextPager(Page page, WebDriver webDriver) {
        Html html;
        try {
            webDriver.get(page.getUrl().toString());
        } catch (Exception e) {
            throw new IllegalArgumentException("getNextPager发生错误了!" + e.toString());
        } finally {
            while (true) {
                try {
                    //休眠1秒 防止 没加载出来就退出了
                    Thread.sleep(1000);
                    //判断是否翻到底了
                    if (SeleniumUtils.checkIsFlipPages(webDriver)) {
                        break;
                    }
                    //向下翻页
                    SeleniumUtils.rollDown(webDriver);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            html = new Html(webElement.getAttribute("outerHTML"));
        }
        return html.getDocument();
    }

    @Override
    public Document getNextPager(String url, WebDriver webDriver) {
        Html html;
        try {
            webDriver.get(url);
        } catch (Exception e) {
            throw new IllegalArgumentException("getNextPager发生错误了!" + e.toString());
        } finally {
            while (true) {
                try {
                    //休眠1秒 防止 没加载出来就退出了
                    Thread.sleep(1000);
                    //判断是否翻到底了
                    if (SeleniumUtils.checkIsFlipPages(webDriver)) {
                        break;
                    }
                    //向下翻页
                    SeleniumUtils.rollDown(webDriver);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            html = new Html(webElement.getAttribute("outerHTML"));
        }
        return html.getDocument();
    }

    /**
     * 获取一个doc
     *
     * @param url
     * @param webDriver
     * @return
     */
    @Override
    public Document getPage(String url, WebDriver webDriver) {
        Html html;
        try {
            webDriver.get(url);
        } catch (Exception e) {
            throw new IllegalArgumentException("getNextPager发生错误了!" + e.toString());
        } finally {
            try {
                //休眠1秒 防止 没加载出来就退出了
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            html = new Html(webElement.getAttribute("outerHTML"));
        }
        return html.getDocument();
    }

    @Override
    public Document getNextPager(String url, WebDriver webDriver, List<String> text) {
        Html html;
        try {
            webDriver.get(url);
        } catch (Exception e) {
            throw new IllegalArgumentException("getNextPager发生错误了!" + e.toString());
        } finally {
            while (true) {
                try {
                    if (!Objects.isNull(text) && text.size() > 0) {
                        SeleniumUtils.click(webDriver, text);
                    }
                    //休眠1秒 防止 没加载出来就退出了
                    Thread.sleep(1000);
                    //判断是否翻到底了
                    if (SeleniumUtils.checkIsFlipPages(webDriver)) {
                        break;
                    }
                    //向下翻页
                    SeleniumUtils.rollDown(webDriver);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            WebElement webElement = webDriver.findElement(By.xpath("/html"));
            html = new Html(webElement.getAttribute("outerHTML"));
        }
        return html.getDocument();
    }

    @Override
    public Document getNextPager(Page page, WebDriver webDriver, String ClickText) {
        try {
            Html html;
            try {
                webDriver.get(page.getUrl().toString());
            } catch (Exception e) {
                throw new IllegalArgumentException("getNextPager发生错误了!" + e.toString());
            } finally {
                while (true) {
                    try {
                        if (!Strings.isBlank(ClickText)) {
                            SeleniumUtils.click(webDriver, ClickText);
                        }
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
                        break;
                    }
                }
                WebElement webElement = webDriver.findElement(By.xpath("/html"));
                html = new Html(webElement.getAttribute("outerHTML"));
            }
            return html.getDocument();
        } catch (Exception e) {
            return null;
        }
    }
}
