package crawler;

import absCompone.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.JsonParseUtil;
import common.RegexUtil;
import componentImpl.WebDriverManager;
import core.model.ProductCrawler;
import model.ChristianPrize;
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
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/7.10:13
 * @Desc: 中国网站没有商品
 */
public class ChristianLouboutinCarwler extends BaseCrawler {

    private static List<String> urls = new LinkedList<>();

    public ChristianLouboutinCarwler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new ChristianLouboutinCarwler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ ChristianLouboutinCarwler Crawler start=============");
        //香港
        //  urls.add("http://asia.christianlouboutin.com/hk_tc/");
        //德国
        urls.add("http://eu.christianlouboutin.com/de_en/");
        //英国
//        urls.add("http://eu.christianlouboutin.com/uk_en/");
        spider = Spider.create(new ChristianLouboutinCarwler(threadDept))
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
        logger.info("process>>>>>>>>>>>" + page.getUrl());
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            //获取navList
            Elements elements = document.select("nav li");
            for (Element el : elements) {
                String url = el.getElementsByTag("a").attr("href");
                if (Strings.isNotBlank(url) && RegexUtil.checkHttp(url)) {
                    logger.info("加入到导航list " + url);
                    navList.add(url);
                    page.addTargetRequest(url);
                }
            }
            logger.info("navList 的个数为 " + navList.size());
        }
        //开始获取详情页地址
        if (navList.contains(page.getUrl().toString())) {
            //这里有下拉分页 启用sem
            Document doc = null;
            try {
                webDriver = WebDriverManager.getInstall().create(3, webDriver);
                doc = WebDriverManager.getInstall().getNextPager(page, webDriver);
            } catch (Exception e) {
                logger.info("driverComponent 发生错误");
            }
            Elements elements = doc.select("div[class=product]");
            if (elements.size() > 0) {
                for (Element element : elements) {
                    String url = element.getElementsByTag("a").first().attr("href");
                    if (Strings.isNotBlank(url) && RegexUtil.checkHttp(url)) {
                        logger.info("加入采集队列 " + url);
                        detailList.add(url);
                        page.addTargetRequest(url);
                    }
                }
                logger.info("detailList 的个数为 " + detailList.size());
            }
        }
        //开始采集
        if (detailList.contains(page.getUrl().toString())) {
            WebDriverManager.getInstall().destoty(webDriver);
            ProductCrawler productCrawler = analyticalData(page);
            page.putField("productCrawler", productCrawler);
        }

    }

    private ProductCrawler analyticalData(Page page) {
        ProductCrawler productCrawler = new ProductCrawler();
        String json = RegexUtil.getDataByRegex("<script type=\"application.ld.json\">\\s+(.*?)</script>", page.getHtml().toString());
        List<String> imgList = new ArrayList<String>();
        // 图片获取
        Elements elements = null;
        try {
            elements = page.getHtml().getDocument().getElementsByClass("product-main").first().getElementsByTag("img");
        } catch (Exception e) {
            logger.info("获取图片错误》》》》");
        }
        for (Element element : elements) {
            String img = element.getElementsByTag("img").attr("data-src");
            if (!Strings.isBlank(img)) {
                imgList.add(img);
            }
        }
        if (!Strings.isBlank(json)) {
            productCrawler.setBrand("Christian Louboutin");
            productCrawler.setUrl(page.getUrl().toString());
            productCrawler.setName(JsonParseUtil.getString(json, "name"));
            productCrawler.setIntroduction(JsonParseUtil.getString(json, "description"));
            productCrawler.setImg(Joiner.on("|").join(imgList));
            productCrawler.setClassification(JsonParseUtil.getString(json, "category"));
            productCrawler.setRef(JsonParseUtil.getString(json, "sku"));
            productCrawler.setColor(JsonParseUtil.getString(json, "color"));
        }

        ChristianPrize christianPrize = null;
        try {
            String jsonPrize = RegexUtil.getDataByRegex("var optionsPrice = new Product.OptionsPrice.(.*?).;", page.getHtml().toString());
            christianPrize = JSON.parseObject(jsonPrize, ChristianPrize.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        productCrawler.setEngName(JsonParseUtil.getString(json, "name"));
        if (christianPrize != null) {
            if (page.getUrl().toString().contains("de_en")) {
                productCrawler.setEurPrice(String.valueOf(christianPrize.getProductPrice()));
                productCrawler.setLanguage("de_en");
            }
            if (page.getUrl().toString().contains("hk_tc")) {
                productCrawler.setHkPrice(String.valueOf(christianPrize.getProductPrice()));
                productCrawler.setLanguage("hk_en");
            }
            if (page.getUrl().toString().contains("uk_en")) {
                productCrawler.setEnPrice(String.valueOf(christianPrize.getProductPrice()));
                productCrawler.setLanguage("uk_en");
            }
        }
        return productCrawler;
    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .setTimeOut(5000)
                .setRetryTimes(3)
                .setCharset("utf-8");
        return site;
    }
}
