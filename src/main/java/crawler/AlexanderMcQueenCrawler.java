package crawler;

import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.HttpRequestUtil;
import common.RegexUtil;
import model.Alexandermcqueen;
import model.ColorAlexander;
import core.model.Product;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.processor.PageProcessor;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/1.16:57
 * @desc: AlexanderMcQueenCrawler
 */
public class AlexanderMcQueenCrawler extends BaseCrawler implements PageProcessor {
    private final static Logger logger = Logger.getLogger(String.valueOf(AlexanderMcQueenCrawler.class));

    private static String reg = "http://www.alexandermcqueen.cn/.*?/alexandermcqueen/.*.html#dept=\\w+";

    private static String hkreg = "http://www.alexandermcqueen.com/.*?/alexandermcqueen/.*.html#dept=\\w+";

    public AlexanderMcQueenCrawler(int threadDept) {
        super(threadDept);
        this.threadDept = threadDept;
    }

    public AlexanderMcQueenCrawler() {
        super(1);
    }

    public void process(Page page) {
        logger.info("process>>>>>>>>>>>" + page.getUrl());
        List<String> requestList = new ArrayList<>();
        //每页获取 每个详情页
        Elements elements = page.getHtml().getDocument().select("#llmnwmn > div > section > article");
        if (elements.size() > 0) {
            for (Element element : elements) {
                String url = element.select("a").attr("href");
                logger.info("加入到采集队列>>>>>>>" + url);
                requestList.add(url);
            }
            //将详情页 添加到 待爬取的request里
            page.addTargetRequests(requestList);
        }

        /**
         * 这里是分析详情页的地方
         */
        Product product = analyticalData(page);
        //加入数据
        if (product != null && !"".equals(product.getName())) {
            page.putField("product", product);
        }
    }

    /**
     * 分析数据
     *
     * @param page
     * @return
     */
    public Product analyticalData(Page page) {
        String Introduction = null;// 详细说明
        String imgUrl;// 商品大图
        String num;// 商品编号
        Product product = new Product();
        Alexandermcqueen alexandermcqueen;
        ColorAlexander colorAlexander;
        if (page.getUrl().regex(reg).match() || page.getUrl().regex(hkreg).match()) {
            String prizeUrl = "";
            String colorUrl = "";
            num = RegexUtil.getDataByRegex("\"Code10\":\"(.*?)\"", page.getHtml().toString());
            colorUrl =
                    "http://www.alexandermcqueen.cn/yTos/api/Plugins/ItemPluginApi/GetCombinationsAsync/?siteCode=ALEXANDERMCQUEEN_CN&code10="
                            + num;
            // 中国地址
            if (page.getUrl().toString().contains("http://www.alexandermcqueen.cn/cn")) {
                prizeUrl = "http://www.alexandermcqueen.cn/yTos/api/Plugins/SeoPluginApi/GetDataLayer?itemCode10=" + num
                        + "&itemSiteCode=ALEXANDERMCQUEEN_CN&controller=item&area=&action=index";

            }
            // 香港地址
            if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/hk")) {
                prizeUrl = "http://www.alexandermcqueen.com/yTos/api/Plugins/SeoPluginApi/GetDataLayer?itemCode10="
                        + num + "&itemSiteCode=ALEXANDERMCQUEEN_HK&controller=item&area=&action=index";

            }
            if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/de")) {
                // 德国地址
                prizeUrl = "http://www.alexandermcqueen.com/yTos/api/Plugins/SeoPluginApi/GetDataLayer?itemCode10="
                        + num + "&itemSiteCode=ALEXANDERMCQUEEN_DE&controller=item&area=&action=index";

            }
            if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/gb")) {
                // 英国地址
                prizeUrl = "http://www.alexandermcqueen.com/yTos/api/Plugins/SeoPluginApi/GetDataLayer?itemCode10="
                        + num + "&itemSiteCode=ALEXANDERMCQUEEN_GB&controller=item&area=&action=index";

            }
            Elements elements = page.getHtml().getDocument().select(
                    "#container > main > article > div.productInfoWrapper.item-is-not-fragrance > div > div.descriptionsContainer > div.attributesUpdater.EditorialDescription > span.value");
            if (elements.size() > 0) {
                Introduction = elements.first().text();
            }
            product.setIntroduction(Introduction);
            List<String> listImg = new ArrayList<String>();
            listImg = RegexUtil.matchGroup(
                    "<img alt=\".*?\" data-ytos-code10=\".*?\" data-ytos-image-shot=\".\" data-ytos-image-size=\".*?\" itemprop=\"image\" sizes=\".*?\" srcset=\"(.*?) 2400w",
                    page.getHtml().toString());
            imgUrl = Joiner.on("|").join(listImg);// 多图采集
            product.setImg(imgUrl);
            product.setUrl(page.getUrl().toString());
            product.setLanguage("zh_CN");
            product.setBrand("alexandermcqueen");
            try {
                // 颜色实体描述
                alexandermcqueen = JSON.parseObject(HttpRequestUtil.sendGet(prizeUrl), Alexandermcqueen.class);
                colorAlexander = JSON.parseObject(HttpRequestUtil.sendGet(colorUrl), ColorAlexander.class);
                if (alexandermcqueen != null) {
                    product.setName(alexandermcqueen.getProduct_title());
                    product.setClassification(alexandermcqueen.getProduct_category());
                    if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/hk")) {
                        product.setHkPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                    }
                    if (page.getUrl().toString().contains("http://www.alexandermcqueen.cn/cn")) {
                        product.setPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                    }
                    if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/de")) {
                        product.setEurPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                    }
                    if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/gb")) {
                        product.setEnPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                    }
                    product.setRef(alexandermcqueen.getProduct_cod10());
                }
                if (colorAlexander != null) {
                    List<ColorAlexander.SizesBean> list = colorAlexander.getSizes();
                    List<String> sizes = new ArrayList<String>();
                    if (list.size() > 0) {
                        for (ColorAlexander.SizesBean o : list) {
                            sizes.add(o.getDescription());
                        }
                    }
                    product.setSize(Joiner.on("|").join(sizes));
                    List<ColorAlexander.ColorsBean> list1 = colorAlexander.getColors();
                    List<String> colo = new ArrayList<String>();
                    if (list1.size() > 0) {
                        for (ColorAlexander.ColorsBean o : list1) {
                            colo.add(o.getDescription());
                        }
                    }
                    product.setColor(Joiner.on("|").join(colo));
                }
            } catch (Exception ex) {
                logger.info("请求接口发生错误");
                return product;
            }
            logger.info("product=========" + product.toString());
            return product;
        }

        return null;
    }


    public Site getSite() {
        if (site == null) {
            site = Site.me()
                    .setDomain("www.alexandermcqueen.com")
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setCharset("utf-8")
                    .setTimeOut(5000)
                    .setSleepTime(3000);
        }
        return site;
    }

    public void run() {
        logger.info("============ Alexandermcqueen Crawler start=============");
        List<String> urlList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String HkUrl =
                    "http://www.alexandermcqueen.com/Search/RenderProducts?ytosQuery=true&department=llmnwmn&gender=D%2CU%2CE&agerange=adult&page="
                            + i
                            + "&productsPerPage=50&suggestion=false&textSearch=*&totalPages=11&totalItems=532&partialLoadedItems=50&itemsToLoadOnNextPage=50&siteCode=ALEXANDERMCQUEEN_HK";
            String deUrl =
                    "http://www.alexandermcqueen.com/Search/RenderProducts?ytosQuery=true&department=llmnwmn&gender=D%2CU%2CE&agerange=adult&page="
                            + i
                            + "&productsPerPage=50&suggestion=false&textSearch=*&totalPages=16&totalItems=773&partialLoadedItems=50&itemsToLoadOnNextPage=50&siteCode=ALEXANDERMCQUEEN_DE";
            String gbUrl =
                    "http://www.alexandermcqueen.com/Search/RenderProducts?ytosQuery=true&department=llmnwmn&gender=D%2CU%2CE&agerange=adult&page="
                            + i
                            + "&productsPerPage=50&suggestion=false&textSearch=*&totalPages=16&totalItems=773&partialLoadedItems=50&itemsToLoadOnNextPage=50&siteCode=ALEXANDERMCQUEEN_GB";
            String cnurl =
                    "http://www.alexandermcqueen.cn/Search/RenderProducts?ytosQuery=true&department=llmnwmn&gender=D%2CU%2CE&agerange=adult&page="
                            + i
                            + "&productsPerPage=50&suggestion=false&textSearch=*%3A*&totalPages=8&totalItems=391&partialLoadedItems=50&itemsToLoadOnNextPage=50&siteCode=ALEXANDERMCQUEEN_CN";
            //将所有的url添加进去
            urlList.add(cnurl);
            urlList.add(HkUrl);
            urlList.add(deUrl);
            urlList.add(gbUrl);

        }
        logger.info("============ Alexandermcqueen Urls ready=============");
        Spider spider = Spider.create(new AlexanderMcQueenCrawler(threadDept))
                .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                .thread(threadDept)
                .addPipeline(new CrawlerPipeline());
        setSpider(spider);
        spider.start();
    }
}
