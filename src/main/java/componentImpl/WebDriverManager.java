package componentImpl;

import absCompone.DriverComponent;
import common.SeleniumUtils;
import common.WebDriverPool;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yang
 * @Date: 2017/12/20.14:57
 * @Desc: 创建一个webDriver 对象
 */
public class WebDriverManager extends DriverComponent {


    private WebDriverManager() {
    }

    private static int capacity = 1;

    private static WebDriverPool webDriverPool = null;

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private WebDriver webDriver = null;
    /**
     * store webDrivers created
     */
    private static List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<WebDriver>());

    /**
     * store webDrivers available
     */
    private static BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

    private static WebDriverManager instance;


    public synchronized static WebDriverManager getInstall() {
        if (instance == null) {                         //Single Checked
            synchronized (WebDriverManager.class) {
                if (instance == null) {                 //Double Checked
                    instance = new WebDriverManager();
                }
            }
        }
        return instance;
    }

    /**
     * 创建一个webderive
     *
     * @param sec 设置下超时时间
     * @return webDriver
     */
    @Override
    public WebDriver create(int sec, WebDriver webDriver) {
        try {

//                ChromeOptions options = new ChromeOptions();
//                DesiredCapabilities caps = DesiredCapabilities.chrome();
//                options.setBinary(WebDriverManager.class.getClassLoader().getResource("chromedriver.exe").getPath()); //注意chrome和chromeDirver的区别
            System.getProperties().setProperty("webdriver.chrome.driver", WebDriverManager.class.getClassLoader().getResource("chromedriver.exe").getPath());
//
            HashMap<String, Object> images = new HashMap<String, Object>();
            images.put("images", 2);

            HashMap<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values", images);

            ChromeOptions chrome_options = new ChromeOptions();
            chrome_options.setExperimentalOption("prefs", prefs);

            DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
            chromeCaps.setCapability(ChromeOptions.CAPABILITY, chrome_options);

            webDriver = new ChromeDriver(chromeCaps);
            //设置超时时间3s
            webDriver.manage().timeouts().setScriptTimeout(sec, TimeUnit.SECONDS);

            return webDriver;
        } catch (Exception e) {
            throw new IllegalArgumentException("创建WebDriver发生错误了!" + e.toString());
        }
    }

    @Override
    public WebDriver get(int sec) throws InterruptedException {
        if (webDriverPool == null) {
            synchronized (this) {
                webDriverPool = new WebDriverPool(sec);
            }
        }
        return webDriverPool.get();
    }

    public WebDriverPool getWebDriverPool(int thired) {
        if (webDriverPool == null) {
            synchronized (this) {
                webDriverPool = new WebDriverPool(thired);
            }
        }
        return webDriverPool;
    }

    @Override
    public void returnToPool(WebDriver webDriver) {
        if (webDriverPool != null) {
            webDriverPool.returnToPool(webDriver);
        }
    }

    /**
     * 销毁 WebDriver
     */
    @Override
    public WebDriver destoty(WebDriver webDriver) {
        if (webDriver != null) {
            webDriver.close();
            webDriver = null;
        }
        return webDriver;
    }

    @Override
    public void closeAll() {
        if (webDriverPool != null) {
            webDriverPool.closeAll();
        }
    }

    @Override
    public WebDriver quit(WebDriver webDriver) {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
        return webDriver;
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
                    Thread.sleep(1000);
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
                    Thread.sleep(1000);
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
            webDriver = WebDriverManager.getInstall().create(3, webDriver);
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
                    Thread.sleep(1000);
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
                        Thread.sleep(1000);
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
