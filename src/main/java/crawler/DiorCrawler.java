package crawler;

import base.BaseCrawler;
import com.google.common.base.Strings;
import common.JsonParseUtil;
import core.model.Product;
import io.netty.util.internal.ObjectUtil;
import model.DiorJson;
import org.jsoup.Jsoup;
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


    public static void main(String[] args) {
        new DiorCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ DiorCrawler Crawler start=============");

        urls.add("https://www.dior.com/home/zh_hk");
        urls.add("https://www.dior.cn/home/zh_cn");
        urls.add("https://www.dior.com/home/de_de");
        urls.add("https://www.dior.com/home/en_gb");

        spider = Spider.create(new DiorCrawler(threadDept))
                .addUrl("https://www.dior.cn/home/zh_cn")
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
        logger.info("process>>>>>" + page.getUrl().toString());
        if (urls.contains(page.getUrl().toString())) {
            Elements diorNavs = page.getHtml().getDocument().select("nav.main-nav li");
            ObjectUtil.checkNotNull(diorNavs, "dior Navs is null on line 71");
            for (Element element : diorNavs) {
                String url = element.getElementsByTag("a").attr("href");
                if (!Strings.isNullOrEmpty(url)) {
                    logger.info("navs url added>>>>>" + url);
                    page.addTargetRequest(url);
                    navList.add(url);
                }
            }
        }
        if (navList.contains(page.getUrl().toString())) {
            Elements diorNavsDeep = page.getHtml().getDocument().select("ul.js-subnav-items li");
            for (Element element : diorNavsDeep) {
                String url = element.getElementsByTag("a").attr("href");
                if (!Strings.isNullOrEmpty(url)) {
                    logger.info("diorNavsDeep url added>>>>>" + url);
                    page.addTargetRequest(url);
                    detailList.add(url);
                }
            }
        }
        if (detailList.contains(page.getUrl().toString())) {
            String reg = "j.*adior";
            String html = page.getHtml().toString().replaceAll(reg, "jadior");
            Document document = Jsoup.parse(html);
            Elements elements = document.getElementsByClass("push-pic push-pic--border");
            ObjectUtil.checkNotNull(elements, "elements is null on line 97");
            String json = "";
            try {
                for (Element element : elements) {
                    //json
                    json = element.getElementsByTag("a").attr("data-track-object");
//                    json = RegexUtil.getDataByRegex("data-track-object=\"{ (.*?)\\s*}\"", element.text().toString());
                    //图片
                    String img = element.getElementsByClass("push-pic push-pic--border").first().getElementsByTag("img").attr("src");
                    //diorJson的实体
                    DiorJson diorJson = JsonParseUtil.getBean(json, DiorJson.class);
                    Product product = new Product();
                    product.setClassification(diorJson.getEcommerce().getClick().getProducts().get(0).getCategory());
                    product.setUrl(page.getUrl().toString());
                    product.setBrand("dior");
                    product.setImg(img);
                    product.setName(diorJson.getEcommerce().getClick().getProducts().get(0).getName());
                    product.setIntroduction(diorJson.getEcommerce().getClick().getProducts().get(0).getVariant());
                    if (page.getUrl().toString().contains("zh_hk")) {
                        //香港价格
                        product.setLanguage("zh_hk");
                        product.setHkPrice(diorJson.getEcommerce().getClick().getProducts().get(0).getPrice());
                    }
                    if (page.getUrl().toString().contains("zh_cn")) {
                        product.setLanguage("zh_CN");
                        product.setPrice(diorJson.getEcommerce().getClick().getProducts().get(0).getPrice());
                    }
                    if (page.getUrl().toString().contains("de_de")) {
                        product.setLanguage("de_de");
                        product.setEurPrice(diorJson.getEcommerce().getClick().getProducts().get(0).getPrice());
                    }
                    if (page.getUrl().toString().contains("en_gb")) {
                        product.setLanguage("en_gb");
                        product.setEnPrice(diorJson.getEcommerce().getClick().getProducts().get(0).getPrice());
                    }
                    product.setRef(diorJson.getEcommerce().getClick().getProducts().get(0).getCode());
                    page.putField("product", product);
                }
            } catch (Exception e) {
                logger.info("转化错误>>>>>");
//                logger.info("转化错误的json>>>>" + json);
            }
        }
    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .setSleepTime(3000);
        return site;
    }
}
