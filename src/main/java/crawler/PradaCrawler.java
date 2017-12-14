package crawler;

import base.BaseCrawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

/**
 * @Author: yang
 * @Date: 2017/12/13.16:53
 * @Desc: PradaCrawler  都没有价格
 */
public class PradaCrawler extends BaseCrawler {
    public PradaCrawler(int threadDept) {
        super(threadDept);
    }

    @Override
    public void run() {

    }

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.miumiu.com")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
