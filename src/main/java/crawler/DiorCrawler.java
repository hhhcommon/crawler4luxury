package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Strings;
import common.DbUtil;
import common.HttpRequestUtil;
import common.RegexUtil;
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
import java.util.*;

/**
 * @Author: yang
 * @Date: 2017/12/7.11:17
 * @Desc: DiorCrawler
 * <p>
 * dior 亚洲 好像无法网上购买 只能 店里购买
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
        new DiorCrawler(5).run();
    }

    @Override
    public void run() {
        logger.info("============ DiorCrawler Crawler start=============");
        urls.add("https://www.dior.cn/home/zh_cn");
//        urls.add("https://www.dior.com/home/de_de");
//        urls.add("https://www.dior.com/home/en_gb");
//        urls.add("https://www.dior.com/home/zh_hk");
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
                    logger.info("deepUrls size " + deepUrls.size());
                } catch (Exception e) {
                }
            }
        }
        if (deepUrls.contains(page.getUrl().toString())) {
            ProductCrawler productCrawler = new ProductCrawler();
            //唯一码
            String ref = document.select("meta[name=gsa-sku]").attr("content");
//            String reg = "\"productPrice\":\"(.*?)\",";
//            String price = RegexUtil.getDataByRegex(reg, document.outerHtml());
            String otherPrice = document.select("meta[name=gsa-prices]").attr("content");
            //商品销售状态
            String statusLabel = document.select("p[class=status-label]").text();
            //商品名
            String name = document.select("meta[name=gsa-subtitle]").attr("content");
            //价格
            if (page.getUrl().toString().contains("zh_hk")) {
                //香港价格
                productCrawler.setLanguage("zh_hk");
                productCrawler.setHkPrice(otherPrice);
            }
            if (page.getUrl().toString().contains("zh_cn")) {
                productCrawler.setLanguage("zh_CN");
                productCrawler.setPrice(otherPrice);
                //设置英文名
                try {
                    String gbUrl = document.select("link[hreflang=en-GB]").attr("href");
                    String engName = new Html(HttpRequestUtil.sendGet(gbUrl, Site.me().setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36"))).getDocument().select("meta[name=gsa-subtitle]").attr("content");
                    productCrawler.setEngName(engName);
                } catch (Exception e) {
                    logger.info("设置英文名称发生错误");
                }
            }
            if (page.getUrl().toString().contains("de_de")) {
                productCrawler.setLanguage("de_de");
                productCrawler.setEurPrice(otherPrice);
            }
            if (page.getUrl().toString().contains("en_gb")) {
                productCrawler.setLanguage("en_gb");
                productCrawler.setEnPrice(otherPrice);
                productCrawler.setEngName(name);
            }
            //描述product-description-content
            String desc = document.select("div[class=product-description-content]").text();
            //照片
            String img = document.select("meta[name=gsa-image]").attr("content");
            //商品类别
            String classf = null;
            try {
                classf = document.select("meta[name=gsa-univers-label]").attr("content") + document.select("li[data-reactid=8]").first().text();
            } catch (Exception e) {
                classf = document.select("meta[name=gsa-univers-label]").attr("content");
            }
            productCrawler.setClassification(classf);
            productCrawler.setBrand("dior");
            productCrawler.setUrl(page.getUrl().toString());
            productCrawler.setName(name);
            productCrawler.setIntroduction(desc);
            productCrawler.setTags(statusLabel);
            productCrawler.setImg(img);
            productCrawler.setRef(ref);
            logger.info("商品信息" + productCrawler.toString());
            page.putField("productCrawler", productCrawler);
            //是否重新更新类别
            page.putField("isClassf", true);
        }
    }

    private String getPrice(String priceUrl, String ref) {
        Site site = Site.me()
                .setCharset("utf-8")
                .setTimeOut(5000)
                .setRetryTimes(3)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        String json = "{\"items\":[{\"sku\":\"" + ref + "\"}]}";
        return RegexUtil.getDataByRegex(" \"price\": \"(.*?)\",", HttpRequestUtil.sendPost(priceUrl, site, json));
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
