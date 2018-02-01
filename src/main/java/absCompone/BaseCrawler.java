package absCompone;

import componentImpl.WebDriverManager;
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

    protected WebDriver webDriver;
    /**
     * 基类爬虫对象
     */
    protected Spider spider;

    protected Site site;

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

    protected void removeList(String link) {
        if (detailList.size() > 0) {
            detailList.remove(link);
            logger.info("成功移除detailList " + link);
        }
        if (navList.size() > 0) {
            navList.remove(link);
            logger.info("成功移除navList " + link);
        }
        logger.info("navList size " + navList.size());
        logger.info("detailList size " + detailList.size());
    }

    protected void removeListAndClose(String link) {
        if (detailList.size() > 0) {
            detailList.remove(link);
            logger.info("成功移除detailList " + link);
        }
        if (navList.size() > 0) {
            navList.remove(link);
            logger.info("成功移除navList " + link);
        }
        logger.info("navList size " + navList.size());
        logger.info("detailList size " + detailList.size());
        closeWebDriver();
    }

    protected void closeWebDriver() {
        if (detailList.size() == 0) {
            WebDriverManager.getInstall().closeAll();
        }
    }
}
