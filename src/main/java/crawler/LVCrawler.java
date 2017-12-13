package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import core.model.Product;
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

    public LVCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        new LVCrawler(5).run();
    }

    @Override
    public void run() {
        logger.info(">>>>LVCrawler start<<<<");
        urls.add("http://www.louisvuitton.cn/zhs-cn/homepage");
        urls.add("http://hk.louisvuitton.com/eng-hk/homepage");
        urls.add("http://fr.louisvuitton.com/fra-fr/homepage");
        urls.add("http://uk.louisvuitton.com/eng-gb/homepage");
        spider = Spider.create(new LVCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(new CrawlerPipeline())
                .thread(threadDept);
        try {
            SpiderMonitor.instance().register(spider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        spider.start();
    }

    @Override
    public void process(Page page) {
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            logger.info("LVCrawler page is starting>>>" + page.getUrl().toString());
            //获取navs
            Elements elements = document.select("li[class=mm-block]");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href").trim();
                if (!Strings.isBlank(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }
        }
        // navsList
        if (navList.contains(page.getUrl().toString())) {
            logger.info("nav page is starting>>>" + page.getUrl().toString());
            Document document1 = null;
            try {
                document1 = getNextPager(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Elements elements = document1.getElementsByClass("product-item tagClick tagClick");
            for (Element element : elements) {
                String link = element.attr("href");
                if (!Strings.isBlank(link)) {
                    page.addTargetRequest("www.louisvuitton.cn" + link);
                    detailList.add("www.louisvuitton.cn" + link);
                }
            }
        }

        if (detailList.contains(page.getUrl().toString())) {
            logger.info("detail page is starting>>>" + page.getUrl().toString());
            String pname = document.getElementsByClass("productName title").text();
            String ref = document.select("div[class=sku reading-and-link-text]").text();
            String prize = document.select("td[class=priceValue price-sheet]").text();
            String desc = document.select("div[class=productDescription description-push-text onlyML ppTextDescription]").first().text();
            String classification = document.select("meta[name=page_name]").text();
            List<String> imgLs = new ArrayList<>();
            Elements elements = document.select("ul[class=bigs]").first().getElementsByTag("li");
            for (Element element : elements) {
                String img = element.getElementsByTag("img").attr("data-src").split("\\?")[0];
                if (!Strings.isBlank(img)) {
                    imgLs.add(img);
                }
            }
            Product p = new Product();
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
            if (page.getUrl().toString().contains("fra-fr")) {
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
