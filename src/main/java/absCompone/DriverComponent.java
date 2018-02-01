package absCompone;

import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Page;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/20.14:59
 * @Desc: to do?
 */
public abstract class DriverComponent {

    protected static final Logger logger = Logger.getLogger(String.valueOf(DriverComponent.class));


    /**
     * 创建webDriver
     *
     * @return
     */
    public abstract WebDriver create(int sec, WebDriver webDriver);


    /**
     * 创建webDriver
     *
     * @return
     */
    public abstract WebDriver get(int sec) throws InterruptedException;

    /**
     * 返回可用的webdrive
     *
     * @param webDriver
     */
    public abstract void returnToPool(WebDriver webDriver);

    /**
     * 销毁webDriver
     */
    public abstract WebDriver destoty(WebDriver webDriver);

    public abstract void closeAll();

    /**
     * 销毁webDriver
     */
    public abstract WebDriver quit(WebDriver webDriver);

    /**
     * 获取下一页
     *
     * @return Document
     */
    public abstract Document getNextPager(Page page, WebDriver webDriver);

    /**
     * 获取下一页
     *
     * @return Document
     */
    public abstract Document getNextPager(String url, WebDriver webDriver);

    /**
     * 获取一个doc
     *
     * @param url
     * @param webDriver
     * @return
     */
    public abstract Document getPage(String url, WebDriver webDriver);

    /**
     * 找到下一页文本的 点击后 翻页
     *
     * @param page
     * @param ClickText 下一页的文本
     * @return Document
     */
    public abstract Document getNextPager(Page page, WebDriver webDriver, String ClickText);


    public abstract Document getNextPager(String url, WebDriver webDriver, List<String> text);
}
