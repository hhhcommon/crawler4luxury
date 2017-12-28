package base;

import absCompone.DriverComponent;
import componentImpl.WebDriverComponent;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

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
     * navs
     */
    protected List<String> navList = new ArrayList<>();
    /**
     * detailList
     */
    protected List<String> detailList = new ArrayList<>();


    public BaseCrawler(int threadDept) {
        this.threadDept = threadDept;
    }

    protected static final Logger logger = Logger.getLogger(String.valueOf(BaseCrawler.class));
    /**
     * 线程数
     */
    protected int threadDept = 1;
    /**
     * 基类爬虫对象
     */
    protected Spider spider;

    protected Site site;

    public WebDriver webDriver;

    public DriverComponent driverComponent;

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
     * 初始化
     */
    public void init() {
        //初始化的时候初始化 webdriver
        if (driverComponent == null) {

            driverComponent = new WebDriverComponent();
        }
        if (webDriver == null) {
            //创建一个driver 超时时间设置为3s
            webDriver = driverComponent.create(3);
        }
    }

}
