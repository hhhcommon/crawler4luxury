package common;

import absCompone.BasePool;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yang
 * @Date: 2018/1/2.14:03
 * @Desc: 单例模式下 创建 WebDriverPool
 */
public class WebDriverPool implements BasePool {
    /**
     * 单例模式下 创建
     */
    private static WebDriverPool instance;
    private static Map<String, WebDriver> webDriverMap;

    private WebDriverPool() {
    }

    public synchronized static WebDriverPool getInstall() {
        if (instance == null) {                         //Single Checked
            synchronized (WebDriverPool.class) {
                if (instance == null) {                 //Double Checked
                    instance = new WebDriverPool();
                }
            }
        }
        if (webDriverMap == null) {
            synchronized (WebDriverPool.class) {
                if (webDriverMap == null) {                 //Double Checked
                    webDriverMap = new HashMap<>();
                }
            }
        }
        return instance;
    }

    @Override
    public void addDriver(String id, WebDriver webDriver) {
        webDriverMap.put(id, webDriver);
    }

    @Override
    public WebDriver getDriverById(String id) {
        return webDriverMap.get(id);
    }
}
