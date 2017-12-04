package base;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import java.util.logging.Logger;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.16:59
 * @desc: 爬虫基类
 */
public abstract class BaseCrawler implements Runnable {

    protected static Logger logger = Logger.getLogger(String.valueOf(BaseCrawler.class));
    /**
     * 线程数
     */
    protected int threadDept = 1;
    /**
     * 基类爬虫对象
     */
    private Spider spider;

    protected Site site;

    public void setSpider(Spider spider) {
        this.spider = spider;
    }

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
}
