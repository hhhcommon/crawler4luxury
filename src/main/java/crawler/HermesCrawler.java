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
 * @Date: 2017/12/8.17:16
 * @Desc: HermesCrawler https://www.hermes.com/us/en/
 */
public class HermesCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public HermesCrawler(int threadDept) {
        super(threadDept);
    }

    @Override
    public void run() {
        logger.info("HermesCrawler start>>>");
        urls.add("https://www.hermes.com/us/en/");
        spider = Spider.create(new HermesCrawler(threadDept))
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
        logger.info("process>>>>" + page.getUrl().toString());

        Document document = page.getHtml().getDocument();

        //获取分类

        if (urls.contains(page.getUrl().toString())) {
            Elements elements = document.getElementsByClass("product-item look-item");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href").trim();
                if (!Strings.isBlank(link)) {
                    link = link.split("#")[0];
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }

        }
        //获取详情 link
        if (navList.contains(page.getUrl().toString())) {
            Elements elements = document.getElementsByClass("product-item product-item-loading");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href").trim();
                link = "https://www.hermes.com" + link;
                detailList.add(link);
                page.addTargetRequest(link);
            }
        }

        if (detailList.contains(page.getUrl().toString())) {
            String pname = document.getElementById("variant-info").getElementsByTag("h1").text();
            String prize = document.getElementById("variant-info").getElementsByClass("field-type-commerce-price").text();
            String desc = document.select("h2.field-name-field-description").text();
            String ref = document.getElementsByClass("commerce-product-sku").first().getElementsByTag("span").text().trim();

            List<String> imgList = new ArrayList<>();
            Elements imgEl = document.getElementsByClass("product-image-wrap");
            for (Element element : imgEl) {
                String imgUrl = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(imgUrl)) {
                    imgList.add(imgUrl);
                }
            }

            List<String> colorList = new ArrayList<>();
            Elements colorEl = document.getElementsByClass("name");
            for (Element element : colorEl) {
                String color = element.text();
                if (!Strings.isBlank(color)) {
                    colorList.add(color);
                }
            }

            Product p = new Product();
            p.setBrand("hermes");
            p.setColor(Joiner.on("|").join(colorList));
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pname);
            p.setUrl(page.getUrl().toString());
            if (page.getUrl().toString().contains("us/en")) {
                p.setLanguage("Us_en");
                p.setEnPrice(prize);
            }
            p.setRef(ref);
            p.setIntroduction(desc);
            page.putField("product", p);
        }


    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.hermes.com")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }

    public static void main(String[] args) {
        new HermesCrawler(1).run();
    }
}
