package crawler;

import base.BaseCrawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: yang
 * @Date: 2017/12/1.16:57
 * @desc: 新浪爬虫测试
 */
public class SinaCrawler2 extends BaseCrawler implements PageProcessor {


    public void process(Page page) {

    }

    public Site getSite() {
        return null;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("测试222啊");
        }
    }
}
