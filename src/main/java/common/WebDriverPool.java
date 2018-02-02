package common;

import componentImpl.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author cyy
 * @Date 2018/2/2 10:40
 * @Description webdrive pool 管理webdriver
 */
public class WebDriverPool {
    private Logger logger = Logger.getLogger(getClass());

    private final static int DEFAULT_CAPACITY = 5;

    private final int capacity;

    private final static int STAT_RUNNING = 1;

    private final static int STAT_CLODED = 2;

    private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);

    /*
     * new fields for configuring phantomJS
     */
    private WebDriver mDriver = null;

    /**
     * Configure the GhostDriver, and initialize a WebDriver instance. This part
     * of code comes from GhostDriver.
     * https://github.com/detro/ghostdriver/tree/master/test/java/src/test/java/ghostdriver
     *
     * @throws IOException
     * @author bob.li.0718@gmail.com
     */
    public void configure() throws IOException {
        try {
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

            mDriver = new ChromeDriver(chromeCaps);
            //设置超时时间10s
            mDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new IllegalArgumentException("创建WebDriver发生错误了!" + e.toString());
        }
    }


    /**
     * store webDrivers created
     */
    private List<WebDriver> webDriverList = Collections
            .synchronizedList(new ArrayList<WebDriver>());

    /**
     * store webDrivers available
     */
    private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

    public WebDriverPool(int capacity) {
        this.capacity = capacity;
    }

    public WebDriverPool() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @return
     * @throws InterruptedException
     */
    public WebDriver get() throws InterruptedException {
        checkRunning();
        WebDriver poll = innerQueue.poll();
        if (poll != null) {
            return poll;
        }
        if (webDriverList.size() < capacity) {
            synchronized (webDriverList) {
                if (webDriverList.size() < capacity) {
                    // add new WebDriver instance into pool
                    try {
                        configure();
                        innerQueue.add(mDriver);
                        webDriverList.add(mDriver);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return innerQueue.take();
    }

    public void returnToPool(WebDriver webDriver) {
        checkRunning();
        innerQueue.add(webDriver);
    }

    protected void checkRunning() {
        if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
            logger.info("Already closed!");
            return;
        }
    }

    public void closeAll() {
        boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
        if (!b) {
            logger.info("Already closed!");
            return;
        }
        for (WebDriver webDriver : webDriverList) {
            logger.info("Quit webDriver" + webDriver);
            webDriver.close();
        }
        webDriverList.clear();
    }

}
