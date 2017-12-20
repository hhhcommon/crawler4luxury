package base;

import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Page;

import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/20.14:59
 * @Desc: to do?
 */
public abstract class BaseDriver {

    protected static final Logger logger = Logger.getLogger(String.valueOf(BaseDriver.class));
    /**
     * webDriver的实例
     */
    protected WebDriver webDriver;

    /**
     * 初始化 chrome 位置信息
     */
    protected BaseDriver() {
        System.getProperties().setProperty("webdriver.chrome.driver", BaseDriver.class.getClassLoader().getResource("chromedriver.exe").getPath());
    }

    /**
     * 创建webDriver
     *
     * @return
     */
    public abstract WebDriver create(int sec);

    /**
     * 销毁webDriver
     */
    public abstract void destoty();

    /**
     * 获取下一页
     *
     * @return Document
     */
    public abstract Document getNextPager(Page page, WebDriver webDriver);

    /**
     * 找到下一页文本的 点击后 翻页
     *
     * @param page
     * @param ClickText 下一页的文本
     * @return Document
     */
    public abstract Document getNextPager(Page page, WebDriver webDriver, String ClickText);
}