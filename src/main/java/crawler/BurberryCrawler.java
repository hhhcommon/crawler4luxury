package crawler;


import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.HttpRequestUtil;
import common.RegexUtil;
import core.model.ProductCrawler;
import org.assertj.core.util.Strings;
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
import java.util.LinkedList;
import java.util.List;

/**
 * @Author cyy
 * @Date 2017/12/15 18:35
 * @Description BurberryCrawler is ok
 * 跑一次 就能直接把所有的价格全部显示出来
 * 先跑中文网站的 然后其他国家的网站可以一起跑
 */
public class BurberryCrawler extends BaseCrawler {
    //用链接式的
    private static List<String> urls = new LinkedList<>();

    public BurberryCrawler(int crawlPath) {
        super(crawlPath);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new BurberryCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ BurberryCrawler Crawler start=============");

        //中国
        urls.add("https://cn.burberry.com/mens-trench-coats/");
        //德国
//        urls.add("https://de.burberry.com/damen/");
//        //英国
//        urls.add("https://uk.burberry.com/men/");
//        //香港
//        urls.add("https://hk.burberry.com/men/");

        spider = Spider.create(new BurberryCrawler(threadDept))
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
        //获取 navlist
        if (urls.contains(page.getUrl().toString())) {
            if (page.getUrl().toString().contains("https://cn.burberry.com")) {
                Elements elementsNavs = document.select("div[class=nav-level3_item nav-level3_item-empty]");
                for (Element element : elementsNavs) {
                    String link = element.getElementsByTag("a").attr("href");
                    if (page.getUrl().toString().contains("https://cn.burberry.com")) {
                        link = "https://cn.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到导航采集队列 " + link);
                            navList.add(link);
                        }
                    }
                }
            } else {
                Elements elements = document.select("li[class=center-nav-bar-element]");
                for (Element element : elements) {
                    String link = element.getElementsByTag("a").attr("href");
                    if (page.getUrl().toString().contains("https://de.burberry.com")) {
                        link = "https://de.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到导航采集队列 " + link);
                            navList.add(link);
                        }
                    }
                    if (page.getUrl().toString().contains("https://uk.burberry.com")) {
                        link = "https://uk.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到导航采集队列 " + link);
                            navList.add(link);
                        }
                    }
                    if (page.getUrl().toString().contains("https://hk.burberry.com")) {
                        link = "https://hk.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到导航采集队列 " + link);
                            navList.add(link);
                        }

                    }
                }
            }
            logger.info("navList 个数 " + navList.size());
            page.addTargetRequests(navList);
        }
        //获取detail 页面
        if (navList.contains(page.getUrl().toString())) {
            if (page.getUrl().toString().contains("https://cn.burberry.com")) {
                Elements detailEls = document.select("a[class=product_link]");
                for (Element element : detailEls) {
                    String link = element.attr("href");
                    link = "https://cn.burberry.com" + link;
                    if (RegexUtil.checkHttp(link)) {
                        logger.info("添加到采集队列 " + link);
                        detailList.add(link);
                    }
                }
            } else {
                Elements detailEls = document.select("a[class=js-asset-content-link asset-content-link js-product-internal-link ga-product-item esiLink]");
                for (Element element : detailEls) {
                    String link = element.getElementsByTag("a").attr("href");
                    if (page.getUrl().toString().contains("https://uk.burberry.com")) {
                        link = "https://uk.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到采集队列 " + link);
                            detailList.add(link);
                        }
                    } else if (page.getUrl().toString().contains("https://hk.burberry.com")) {
                        link = "https://hk.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到采集队列 " + link);
                            detailList.add(link);
                        }
                    } else if (page.getUrl().toString().contains("https://de.burberry.com")) {
                        link = "https://de.burberry.com" + link;
                        if (RegexUtil.checkHttp(link)) {
                            logger.info("添加到采集队列 " + link);
                            detailList.add(link);
                        }
                    }
                }
            }
            page.addTargetRequests(detailList);
            logger.info("detailList 的个数 " + detailList.size());
        }
        if (detailList.contains(page.getUrl().toString())) {
            try {
                ProductCrawler productCrawler = analyticalData(page);
                if (productCrawler != null) {
                    page.putField("productCrawler", productCrawler);
                }
            } catch (Exception e) {
                logger.info("异常信息" + e.toString());
                logger.info("转化json失败>>>>>>>" + page.getUrl());
            }
        }
    }

    /**
     * 解析数据
     *
     * @param page
     * @return
     */
    public ProductCrawler analyticalData(Page page) {
        ProductCrawler productCrawler = new ProductCrawler();
        Document document = page.getHtml().getDocument();
        if (page.getUrl().toString().contains("https://cn.burberry.com")) {
            productCrawler.setBrand("burberry");
            productCrawler.setLanguage("zh_CN");
            productCrawler.setUrl(page.getUrl().toString());
            productCrawler.setName(document.getElementsByClass("product-purchase_name").text());
            List<String> temp = new ArrayList<String>();
            //获取图片
            Elements imgEl = document.select("div[class=product-carousel_item] picture");
            if (imgEl != null && imgEl.size() > 0) {
                for (Element element : imgEl) {
                    String alt = element.getElementsByTag("img").attr("alt");
                    if (alt != null && !"".equals(alt)) {
                        String img = element.getElementsByTag("img").attr("data-src");
                        if (!Strings.isNullOrEmpty(img)) {
                            temp.add(img);
                        }
                    }
                }
            }
            productCrawler.setImg(Joiner.on("|").join(temp));
            try {
                productCrawler.setPrice(document.select("meta[name=og:price:amount]").attr("content"));
            } catch (Exception e) {
                logger.info("--------------价格转化 发生错误----------------");
            }
            productCrawler.setRef(RegexUtil.getDataByRegex("(\\d+)", page.getUrl().toString()));
            productCrawler.setIntroduction(document.getElementsByClass("accordion-tab_content").first().text());
            //类别
            try {
                String classF = document.select("div[class=nav-level1_item nav-level1_item-selected]").first().getElementsByTag("a").first().text() + "-";
                classF += document.select("div[class=nav-level2_item nav-level2_item-selected]").first().getElementsByTag("a").first().text() + "-";
                classF += document.select("div[class=nav-level3_item nav-level3_item-selected nav-level3_item-empty]").first().getElementsByTag("a").first().text();
                productCrawler.setClassification(classF);
            } catch (Exception e) {
                logger.info("类别获取失败");
                //类别获取失败的是彩妆
                productCrawler.setClassification("彩妆");
                productCrawler.setRef(document.select("input[name=product]").attr("value"));
            }

            try {
                Elements sizeEl = document.select("label.productCrawler-purchase_option");

                if (sizeEl != null && sizeEl.size() > 0) {
                    List<String> list = new ArrayList<String>();
                    for (Element element : sizeEl) {
                        list.add(element.text());
                    }
                    productCrawler.setSize(Joiner.on("|").join(list));

                }

                Element colorEl = document.select("div.productCrawler-purchase_options").first();
                if (colorEl != null) {
                    List<String> list = new ArrayList<String>();
                    Elements colors = colorEl.getElementsByTag("span");
                    for (Element element : colors) {
                        String color = element.getElementsByTag("a").first().getElementsByTag("img").attr("title");
                        list.add(color);
                    }
                    productCrawler.setColor(Joiner.on("|").join(list));
                } else {
                    String color = document.select("span.product-purchase_selected").first().text();
                    productCrawler.setColor(color);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 获取香港价格
            String hkUrl = productCrawler.getUrl().replace("cn", "hk");
            try {
                String html = HttpRequestUtil.sendGet(hkUrl);
                productCrawler
                        .setHkPrice(RegexUtil.getDataByRegex("<meta property=\"og:price:amount\" content=\"(.*?)\"/>", html));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 获取德国价格

            String deUrl = productCrawler.getUrl().replace("cn", "de");
            try {
                String html = HttpRequestUtil.sendGet(deUrl);
                productCrawler
                        .setEurPrice(RegexUtil.getDataByRegex("<meta property=\"og:price:amount\" content=\"(.*?)\"/>", html));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 获取英国价格

            String enUrl = productCrawler.getUrl().replace("cn", "uk");
            try {
                String html = HttpRequestUtil.sendGet(enUrl);
                productCrawler.setEnPrice(RegexUtil.getDataByRegex("<meta property=\"og:price:amount\" content=\"(.*?)\"/>", html));
                String engName = null;
                try {
                    engName = new Html(html).getDocument().select("meta[property=og:title]").attr("content").split("\\|")[0];
                } catch (Exception e) {
                }
                productCrawler.setEngName(engName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //分析国外的网站================================================================================================
        else {
            productCrawler.setBrand("burberry");
            productCrawler.setUrl(page.getUrl().toString());
            try {
                productCrawler.setName(document.getElementsByClass("product-title transaction-title ta-transaction-title type-h6").first().text());
                productCrawler.setEngName(document.getElementsByClass("product-title transaction-title ta-transaction-title type-h6").first().text());
            } catch (Exception e) {
                logger.info("解析【name】字段发生错误！");
            }
            List<String> temp = new ArrayList<String>();
            //获取图片
            Elements imgEl = document.getElementsByClass("asset-container js-asset-container gallery-asset-item-container");
            if (imgEl != null && imgEl.size() > 0) {
                for (Element element : imgEl) {
                    String img = element.attr("data-src");
                    if (!Strings.isNullOrEmpty(img)) {
                        temp.add(img.split("\\?")[0]);
                    }
                }
            }
            productCrawler.setImg(Joiner.on("|").join(temp));
            try {
                if (page.getUrl().toString().contains("https://uk.burberry.com")) {
                    productCrawler.setLanguage("en_gb");
                    Elements elements = document.select("span[class=price-amount ta-price-amount price-amount-without-monogramming]");
                    if (elements.size() == 2) {
                        productCrawler.setEnPrice(elements.get(1).text());
                    } else {
                        productCrawler.setEnPrice(elements.get(0).text());
                    }
                }
            } catch (Exception e) {
                logger.info("--------------价格转化 发生错误----------------");
            }
            productCrawler.setRef(RegexUtil.getDataByRegex("(\\d+)", page.getUrl().toString()));
            productCrawler.setIntroduction(document.getElementsByClass("cell-text ta-cell-text").text());
        }
        return productCrawler;
    }


    @Override
    public Site getSite() {
        site = Site.me()
//                .setDomain("de.burberry.com")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("referer", "https://cn.burberry.com/womens-new-arrivals-new-in/")
                .addHeader("x-csrf-token", getCode())
                .setRetryTimes(3)
                .setTimeOut(5000);
        return site;
    }

    /**
     * 获取网站的 x-csrf-token
     *
     * @return
     */

    private String getCode() {
        String html = HttpRequestUtil.sendGet("https://cn.burberry.com/short-sleeve-soutache-lace-tulle-dress-p45458641");
        return RegexUtil.getDataByRegex("<input class=\"csrf-token\" type=\"hidden\" value=\"(.*?)\">", html);
    }
}
