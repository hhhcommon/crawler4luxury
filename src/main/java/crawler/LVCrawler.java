package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import core.model.ProductCrawler;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/12.13:58
 * @Desc: LVCrawler
 */
public class LVCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public LVCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        new LVCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info(">>>>LVCrawler start<<<<");
        urls.add("http://hk.louisvuitton.com/eng-hk/homepage");
        urls.add("http://www.louisvuitton.cn/zhs-cn/homepage");
        urls.add("http://fr.louisvuitton.com/deu-de/homepage");
        urls.add("http://uk.louisvuitton.com/eng-gb/homepage");
        spider = Spider.create(new LVCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(CrawlerPipeline.getInstall())
                .thread(threadDept);
        try {
            SpiderMonitor.instance().register(spider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        spider.runAsync();
    }

    @Override
    public void process(Page page) {
        init();
        logger.info("page url>" + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            logger.info("LVCrawler page is starting>>>" + page.getUrl().toString());
            //获取navs
            Elements elements = document.select("li[class=mm-block]");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href").trim();
                if (!Strings.isBlank(link)) {
                    if (link.contains("women") || link.contains("men")) {
                        navList.add(link);
                        page.addTargetRequest(link);
                    }

                }
            }
        }
        // 获取详情页
        if (navList.contains(page.getUrl().toString())) {
            logger.info("nav page is starting>>>" + page.getUrl().toString());
            Document doc = null;
            try {
                doc = driverComponent.getNextPager(page, webDriver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Elements elements = doc.getElementsByClass("product-item tagClick tagClick");
            for (Element element : elements) {
                String link = element.attr("href");
                if (!Strings.isBlank(link)) {
                    link = "http://www.louisvuitton.cn" + link;
                    logger.info("加入到采集队列>>>>" + link);
                    page.addTargetRequest(link);
                    detailList.add(link);
                }
            }
        }

        if (detailList.contains(page.getUrl().toString())) {
            driverComponent.destoty();
            logger.info("detail page is starting>>>" + page.getUrl().toString());
            String pname = document.getElementsByClass("productName title").text();
            String ref = document.select("div[class=sku reading-and-link-text]").text();
            String prize = document.select("td[class=priceValue price-sheet]").text();
            String desc = null;
            try {
                desc = document.select("div[class=productDescription description-push-text onlyML ppTextDescription]").first().text();
            } catch (Exception e) {
            }
            String classification = document.select("meta[name=page_name]").text();
            List<String> imgLs = new ArrayList<>();
            Elements elements = document.select("ul[class=bigs]").first().getElementsByTag("li");
            for (Element element : elements) {
                String img = element.getElementsByTag("img").attr("data-src").split("\\?")[0];
                if (!Strings.isBlank(img)) {
                    imgLs.add(img);
                }
            }
            ProductCrawler p = new ProductCrawler();
            p.setBrand("lv");
            p.setClassification(classification);
            p.setImg(Joiner.on("|").join(imgLs));
            p.setName(pname);
            p.setUrl(page.getUrl().toString());
            p.setRef(ref);
            if (page.getUrl().toString().contains("zhs-cn")) {
                p.setPrice(prize);
                p.setLanguage("zh_cn");
            }
            if (page.getUrl().toString().contains("eng-hk")) {
                p.setHkPrice(prize);
                p.setLanguage("eng-hk");
            }
            if (page.getUrl().toString().contains("deu-de")) {
                p.setEurPrice(prize);
                p.setLanguage("fra-fr");
            }
            if (page.getUrl().toString().contains("eng-gb")) {
                p.setEnPrice(prize);
                p.setLanguage("eng-gb");
            }
            p.setIntroduction(desc);

            page.putField("product", p);
        }

    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.louisvuitton.cn")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
