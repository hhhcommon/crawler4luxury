package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import com.sun.deploy.net.HttpUtils;
import core.model.Product;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/8.15:31
 * @Desc: GucciCrawler
 */
public class GucciCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public GucciCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        new GucciCrawler(1).run();
    }

    @Override
    public void run() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("127.0.0.1", 1080)
                , new Proxy("102.102.102.102", 8888)));
        urls.add("https://www.gucci.cn/zh/");
        urls.add("https://www.gucci.com/uk/en_gb/");
        urls.add("https://www.gucci.com/de/de/");
        spider = Spider.create(new GucciCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(new CrawlerPipeline())
                .setDownloader(httpClientDownloader)
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
        //获取navs
        if (urls.contains(page.getUrl().toString())) {
            Elements elementsNav = document.getElementsByClass("sub-");
            for (Element element : elementsNav) {
                //获取到nav的link
                String navLink = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(navLink)) {
                    navList.add("https://www.gucci.com" + navLink);
                    //加到爬取队列
                    page.addTargetRequest(navLink);
                    logger.info("GucciCrawler navs link is added>>>>" + "https://www.gucci.com" + navLink);
                }
            }
        }
        //获取详情页
        if (navList.contains(page.getUrl().toString())) {
            Elements elements = document.getElementsByClass("product-tiles-grid-item-link");
            for (Element element : elements) {
                String link = "https://www.gucci.com" + element.attr("href");
                if (!Strings.isBlank(link)) {
                    detailList.add(link);
                    page.addTargetRequest(link);
                }

            }

        }
        //分析详情页
        if (detailList.contains(page.getUrl().toString())) {
            logger.info("detail page is ready>>>>");
            String pname = document.getElementsByClass("product-name product-detail-product-name").text();
            String prize = document.getElementById("markedDown_full_Price").text().split(" ")[1];
            String desc = document.getElementsByClass("accordion-drawer").text();
            String ref = document.getElementsByClass("style-number-title").first().getElementsByTag("span").text().trim();
            Elements imgs = document.getElementsByClass("item-content product-detail-carousel-image zoom-item");
            List<String> imgList = new ArrayList<>();
            for (Element element : imgs) {
                String img = element.getElementsByTag("img").attr("data-src_small_retina");
                if (!Strings.isBlank(img)) {
                    imgList.add(img);
                }
            }
            List<String> colorList = new ArrayList<>();
            Elements elements = document.getElementsByClass("container-carousel-style-selector").first().getElementsByClass("carousel-inner").first().getElementsByTag("div");
            for (Element element : elements) {
                String color = element.attr("data-color-material");
                if (!Strings.isBlank(color)) {
                    colorList.add(color);
                }
            }

            Product p = new Product();
            p.setBrand("gucci");
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pname);
            p.setColor(Joiner.on("|").join(colorList));
            p.setUrl(page.getUrl().toString());

            if (page.getUrl().toString().contains("uk/en_gb")) {
                p.setEnPrice(prize);
                p.setLanguage("en_gb");
            }
            if (page.getUrl().toString().contains("zh/pr")) {
                p.setPrice(prize);
                p.setLanguage("zh_CN");
            }
            if (page.getUrl().toString().contains("de/de/pr")) {
                p.setEurPrice(prize);
                p.setLanguage("de_de");
            }
            p.setIntroduction(desc);
            p.setRef(ref);
            page.putField("product", p);
        }


    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.gucci.com")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .addHeader("Referer", "https://www.gucci.cn/zh/ca/women")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
