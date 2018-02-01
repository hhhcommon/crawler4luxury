package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.RegexUtil;
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
 * @Date: 2017/12/8.13:54
 * @Desc: FendiCrawler  芬迪 这个 只有英国 德国的有价格 其他的没有价格
 */
public class FendiCrawler extends BaseCrawler {
    private String reg = "https://www.fendi.com/\\w{2}/.*?/p-.*?\\?from=search";

    public FendiCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new FendiCrawler(5).run();
    }

    @Override
    public void run() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            urls.add("https://www.fendi.com/gb/search-results?q=:relevance&page=" + i);
            urls.add("https://www.fendi.com/de/search-results?q=:relevance&page=" + i);
        }
        spider = Spider.create(new FendiCrawler(threadDept))
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
        logger.info("开始采集url " + page.getUrl().get());
        if (page.getUrl().regex(reg).match()) {
            Document document = page.getHtml().getDocument();
            String name = document.getElementsByClass("product-description").first().getElementsByTag("h1").text();
            Elements elementsimg = document.select("img[class=lazyload]");
            List<String> imgList = new ArrayList<>();
            for (Element element : elementsimg) {
                String img = element.attr("data-src").trim();
                if (!Strings.isBlank(img)) {
                    imgList.add(img);
                }
            }
            String introduction = document.select("p[itemprop=description]").text();
            String color = RegexUtil.getDataByRegex("itemprop=\"url\"/><meta content=\"(.*?)\" itemprop=\"color\"/>",
                    page.getHtml().toString());
            String ref = document.getElementsByClass("product-description").first().getElementsByClass("code").text();
            String price = document.select("meta[itemprop=lowPrice]").first().attr("content");
            String classification = document.getElementById("dropdown-main-category").getElementsByTag("meta").attr("content");
            ProductCrawler p = new ProductCrawler();
            p.setBrand("fendi");
            p.setEngName(name);
            p.setColor(color);
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(name);
            p.setClassification(classification);
            p.setUrl(page.getUrl().toString());

            try {
                p.setRef(ref.split(":")[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (page.getUrl().toString().contains("https://www.fendi.com/de")) {
                p.setEurPrice(price);
                p.setLanguage("de-de");
            }
            if (page.getUrl().toString().contains("https://www.fendi.com/gb")) {
                p.setEnPrice(price);
                p.setLanguage("gb");
            }
            p.setIntroduction(introduction);
            page.putField("productCrawler", p);
        } else {
            Elements elements = page.getHtml().getDocument().getElementsByClass("inner");
            for (Element e : elements) {
                String link = e.getElementsByTag("a").eq(2).attr("href");
                link = "https://www.fendi.com" + link;
                if (RegexUtil.checkHttp(link)) {
                    logger.info("加入到采集队列： " + link);
                    page.addTargetRequest(link);
                }

            }

        }
    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                .addCookie("www.fendi.com", "VISITOR=returning; VISITOR=returning; f_location=39.9289|116.3883; mf_9e16e19a-9df3-46a7-bdf3-2ccc5054083f=-1; f_cookiedisclaimer-v1=true; f_nl_count=1; f_nl_hide_session=true; f_countrydetection=fr; NEW_VISITOR=new; __atuvc=35%7C24; __atuvs=59425a1f5e91c987001; count_bs=12; _uetsid=_uet786668a0; _ga=GA1.2.1862722591.1497503758; _gid=GA1.2.240050887.1497503758; rr_rcs=eF4lyjEOgDAIAMDFyX84kgClBWYXv4GtTRzc1PfbxPlumq_3PttOyQqQuGYUY0QVSECLrgP8B0YXpDKaA9D01E28HBZ9RGIEqdqgm3YIzsphFsjyAUE5Fpo; JSESSIONID=7E377DBE4E3D7DED048CEFD417583CB1.pro-hybris-fendi-as2; AWSELB=19C3EFC718D4E46B0AFA9FAEC94E244A6B371387357C9B7DBE85352C18157AEC2C677552694079786BA6A90A4C7229321DE2C2062CD0ADA67C3121C3CF712279A93B90ACBF66A8CE41B09F6B3474B1187ADB1AE94F")
                .setRetryTimes(3)
                .setCharset("utf-8")
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
