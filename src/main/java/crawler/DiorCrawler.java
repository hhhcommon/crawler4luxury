package crawler;

import base.BaseCrawler;
import com.google.common.base.Strings;
import common.DbUtil;
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

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            ProductCrawler productCrawler = new ProductCrawler();
            String reg = "\"productPrice\":\"(.*?)\",";
            //获取价格
            String price = RegexUtil.getDataByRegex(reg, document.outerHtml());
            //商品销售状态
            String statusLabel = document.select("p[class=status-label]").text();

            if (page.getUrl().toString().contains("zh_hk")) {
                //香港价格
                productCrawler.setLanguage("zh_hk");
                if (Strings.isNullOrEmpty(statusLabel)) {
                    productCrawler.setHkPrice(price);
                } else {
                    productCrawler.setHkPrice(statusLabel);
                }

            }
            if (page.getUrl().toString().contains("zh_cn")) {
                productCrawler.setLanguage("zh_CN");
                if (Strings.isNullOrEmpty(statusLabel)) {
                    productCrawler.setPrice(price);
                } else {
                    productCrawler.setPrice(statusLabel);
                }
            }
            if (page.getUrl().toString().contains("de_de")) {
                productCrawler.setLanguage("de_de");
                if (Strings.isNullOrEmpty(statusLabel)) {
                    productCrawler.setEurPrice(price);
                } else {
                    productCrawler.setEurPrice(statusLabel);
                }
            }
            if (page.getUrl().toString().contains("en_gb")) {
                productCrawler.setLanguage("en_gb");
                if (Strings.isNullOrEmpty(statusLabel)) {
                    productCrawler.setEnPrice(price);
                } else {
                    productCrawler.setEnPrice(statusLabel);
                }
            }
            //商品名
            String name = document.select("meta[name=gsa-subtitle]").attr("content");
            //唯一码
            String ref = document.select("meta[name=gsa-productcode]").attr("content");
            //描述
            String desc = document.select("div[class=productCrawler-description-content]").text();
            //照片
            String img = document.select("meta[name=gsa-image]").attr("content");
            //商品类别
            String classf = document.select("meta[name=gsa-univers-label]").attr("content") + "-" + document.select("meta[name=gsa-title]").attr("content");
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
