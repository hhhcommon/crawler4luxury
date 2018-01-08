package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import common.DbUtil;
import common.HttpRequestUtil;
import common.JsonParseUtil;
import common.RegexUtil;
import core.model.Product;
import io.netty.util.internal.ObjectUtil;
import model.DiorJson;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.selector.Html;

import javax.management.JMException;
import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/7.11:17
 * @Desc: DiorCrawler
 */
public class DiorCrawler extends BaseCrawler {

    public DiorCrawler(int threadDept) {
        super(threadDept);
    }

    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    protected List<String> deepUrls = new ArrayList<>();

    private static final String HTTP_REG = "[a-zA-z]+://[^\\s]*";


    public static void main(String[] args) {
        DbUtil.init();
        new DiorCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ DiorCrawler Crawler start=============");
        urls.add("https://www.dior.cn/home/zh_cn");
        urls.add("https://www.dior.com/home/de_de");
        urls.add("https://www.dior.com/home/en_gb");
        urls.add("https://www.dior.com/home/zh_hk");
        spider = Spider.create(new DiorCrawler(threadDept))
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
        logger.info("process>>>>>" + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            Elements diorNavs = page.getHtml().getDocument().select("nav.main-nav li");
            if (Objects.nonNull(diorNavs)) {
                for (Element element : diorNavs) {
                    String url = element.getElementsByTag("a").attr("href");
                    if (!Strings.isNullOrEmpty(url)) {
                        logger.info("加入到采集队列 " + url);
                        page.addTargetRequest(url);
                        navList.add(url);
                    }
                }
            }
        }
        if (navList.contains(page.getUrl().toString())) {
            Elements diorNavsDeep = page.getHtml().getDocument().select("ul.js-subnav-items li");
            for (Element element : diorNavsDeep) {
                String url = element.getElementsByTag("a").attr("href");
                if (!Strings.isNullOrEmpty(url)) {
                    if (url.matches(HTTP_REG)) {
                        logger.info("加入到采集队列 " + url);
                        page.addTargetRequest(url);
                        detailList.add(url);
                    }
                }
            }
        }
        if (detailList.contains(page.getUrl().toString())) {
            Elements elements = document.getElementsByClass("push-pic push-pic--border");
            if (!Objects.isNull(elements)) {
                try {
                    for (Element element : elements) {
                        String link = "https://www.dior.cn" + element.getElementsByTag("a").attr("href");
                        if (!Strings.isNullOrEmpty(link)) {
                            logger.info("加入到采集队列 " + link);
                            deepUrls.add(link);
                            page.addTargetRequest(link);
                        }

                    }
                } catch (Exception e) {
                }
            }
        }
        if (deepUrls.contains(page.getUrl().toString())) {
            Product product = new Product();
            String reg = "\"productPrice\":\"(.*?)\",";
            //获取价格
            String price = RegexUtil.getDataByRegex(reg, document.outerHtml());
            if (page.getUrl().toString().contains("zh_hk")) {
                //香港价格
                product.setLanguage("zh_hk");
                product.setHkPrice(price);
            }
            if (page.getUrl().toString().contains("zh_cn")) {
                product.setLanguage("zh_CN");
                product.setPrice(price);
            }
            if (page.getUrl().toString().contains("de_de")) {
                product.setLanguage("de_de");
                product.setEurPrice(price);
            }
            if (page.getUrl().toString().contains("en_gb")) {
                product.setLanguage("en_gb");
                product.setEnPrice(price);
            }
            String name = document.select("meta[name=gsa-subtitle]").attr("content");
            String ref = document.select("meta[name=gsa-productcode]").attr("content");
            String desc = document.select("div[class=product-description-content]").text();
            String img = document.select("meta[name=gsa-image]").attr("content");
            product.setBrand("dior");
            product.setUrl(page.getUrl().toString());
            product.setName(name);
            product.setIntroduction(desc);
            product.setImg(img);
            product.setRef(ref);
            page.putField("product", product);
        }
    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .setRetryTimes(3)
                .setTimeOut(5000);
        return site;
    }
}
