package crawler;

import base.BaseCrawler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.SinaPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/1.16:57
 * @desc: 新浪爬虫测试
 */
public class SinaCrawler extends BaseCrawler implements PageProcessor {
    public SinaCrawler(int threadDept) {
        this.threadDept = threadDept;
    }

    public SinaCrawler() {
    }

    @Override
    public void process(Page page) {
        List<String> requests = new ArrayList<>();
        Document root = page.getHtml().getDocument();
        Elements nextPages = root.getElementsByClass("nxt");
        if (nextPages.size() > 0) {
            String nextPageUrl = nextPages.get(0).attr("href").trim();
            requests.add(nextPageUrl);
        }
        page.addTargetRequests(requests);

        Elements pageElem = root.getElementsByClass("z");

        if (pageElem.size() > 0) {
            for (Element e : pageElem) {

                requests.add(e.attr("href"));
            }
            page.addTargetRequests(requests);
        }
        if (page.getUrl().regex("http://www.biread.net/thread-\\d+-\\d+-\\d+.html").match()) {

            Element element = root.getElementById("thread_subject");

            if (element != null && !"".equals(element.text())) {
                logger.info(element.text());
                String pic = "http://www.biread.net/" + root.getElementsByClass("zoom").get(0).getElementsByTag("img").get(0).attr("zoomfile");
                String id = root.getElementsByClass("zoom").get(0).getElementsByTag("img").get(0).attr("aid");
                logger.info(pic);
                logger.info(id);
                page.putField("title", element.text());
                page.putField("id", id);
                page.putField("pic", pic);
            }
        }


    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me()
                    .setDomain("www.biread.net")
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setCharset("gbk")
                    .setSleepTime(2000);
        }
        return site;
    }


    public void run() {
        logger.info("============SinaCrawler start=============");
        Spider spider = Spider.create(new SinaCrawler(threadDept))
                .addUrl("http://www.biread.net/forum-37-1.html")
                .addPipeline(new SinaPipeline());
        setSpider(spider);
        spider.start();
    }

}
