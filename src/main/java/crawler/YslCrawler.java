package crawler;

import absCompone.BaseCrawler;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import common.HttpRequestUtil;
import common.RegexUtil;
import componentImpl.WebDriverManager;
import core.model.ProductCrawler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.selector.Html;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/14.13:53
 * @Desc: YslCrawler  价格有点问题
 */
public class YslCrawler extends BaseCrawler {
    public YslCrawler(int threadDept) {
        super(threadDept);
    }

    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    @Override
    public void run() {
        logger.info(">>>>YslCrawler start<<<<");
        urls.add("https://www.ysl.com/wy");
        spider = Spider.create(new YslCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(CrawlerPipeline.getInstall())
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
        logger.info("process start>>>>" + page.getUrl().toString());
        webDriver = WebDriverManager.getInstall().create(3, webDriver);
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            Elements elements = document.select("div[class=level-1] ul li");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href");
                if (!Strings.isNullOrEmpty(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }
        }

        if (navList.contains(page.getUrl().toString())) {
            logger.info("nav>>>>" + page.getUrl().toString());
            Document document1 = WebDriverManager.getInstall().getNextPager(page, webDriver);
            Elements elements = document1.select("article[class=item]");
            for (Element element : elements) {
                String detailLink = element.getElementsByTag("a").first().attr("href");
                if (!Strings.isNullOrEmpty(detailLink)) {
                    page.addTargetRequest(detailLink);
                    detailList.add(detailLink);
                }
            }
        }

        if (detailList.contains(page.getUrl().toString())) {
            logger.info("detail>>>" + page.getUrl().toString());
            WebDriverManager.getInstall().destoty(webDriver);
            String pname = document.select("h1[class=productName]").text();
            String classification = null;
            try {
                classification = document.select("li[class=hasChildren hasSelectedChild rtw menuItem]").first().select("span[class=text]").first().text();
            } catch (Exception e) {
            }
            String ref = RegexUtil.getDataByRegex("\"Cod10\":\"(.*?)\",\"LandingPartNumber\"", page.getHtml().toString());
            String price = document.select("div[class=infoPrice]").text();
            String desc = document.select("div[class=descriptionContent accordion-content]").text();
            Elements elementsImg = document.select("img[class=mainImage]");
            List<String> imgList = new ArrayList<>();
            for (Element element : elementsImg) {
                String img = element.attr("src").trim();
                if (!Strings.isNullOrEmpty(img)) {
                    imgList.add(img);
                }
            }
            String dePri = null;
            try {
                // 德国价格
                String deUrl = document.select("link[hreflang=de-DE]").attr("href");
                if (deUrl != null) {
                    dePri = HttpRequest.get(deUrl).body();
                    Html html = new Html(dePri);
                    dePri = html.getDocument().select("span[class=price] span[class=value]").text().split(" ")[0];
                }
            } catch (Exception e) {
                logger.info("请求德国网址出错 错误原因" + e.toString());
            }
            String hkPri = null;
            try {
                // 香港价格
                String hkUrl = document.select("link[hreflang=en-HK]").attr("href");
                hkPri = "";
                if (hkUrl != null) {
                    hkPri = HttpRequestUtil.sendGet(hkUrl);
                    Html html = new Html(hkPri);
                    hkPri = html.getDocument().select("span[class=price] span[class=value]").text().split(" ")[0];
                }
            } catch (Exception e) {
                logger.info("请求香港网址出错 错误原因" + e.toString());
            }
            String enPri = null;

            try {
                // 英国价格
                String enUrl = document.select("link[hreflang=en-GB]").attr("href");
                enPri = "";
                if (enUrl != null) {
                    enPri = HttpRequestUtil.sendGet(enUrl);
                    Html html = new Html(enPri);
                    enPri = html.getDocument().select("span[class=price] span[class=value]").text().split(" ")[0];
                }
            } catch (Exception e) {
                logger.info("请求英国网址出错 错误原因" + e.toString());
            }
            ProductCrawler p = new ProductCrawler();
            p.setBrand("valentino");
            p.setClassification(classification);
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pname);
            p.setUrl(page.getUrl().toString());
            p.setLanguage("zh_CN");
            p.setPrice(price);
            p.setHkPrice(hkPri);
            p.setEnPrice(enPri);
            p.setEurPrice(dePri);
            p.setIntroduction(desc);
            p.setRef(ref.trim());
            page.putField("product", p);
        }


    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.ysl.com")
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                .setRetryTimes(3);

        return site;
    }

    public static void main(String[] args) {
        new YslCrawler(1).run();
    }
}
