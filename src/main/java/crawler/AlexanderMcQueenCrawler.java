package crawler;

import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import common.HttpRequestUtil;
import common.RegexUtil;
import model.Alexandermcqueen;
import model.ColorAlexander;
import core.model.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;


import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/1.16:57
 * @desc: AlexanderMcQueenCrawler  is ok'
 */
public class AlexanderMcQueenCrawler extends BaseCrawler {

    private static String reg = "http://www.alexandermcqueen.cn/.*?/alexandermcqueen/.*.html#dept=\\w+";

    private static String hkreg = "http://www.alexandermcqueen.com/.*?/alexandermcqueen/.*.html#dept=\\w+";

    public AlexanderMcQueenCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        new AlexanderMcQueenCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ Alexandermcqueen Crawler start=============");
        //添加中国的链接
        urls.add("http://www.alexandermcqueen.cn/cn");
        urls.add("http://www.alexandermcqueen.com/hk");
        urls.add("http://www.alexandermcqueen.com/de");
        urls.add("http://www.alexandermcqueen.com/gb");
        spider = Spider.create(new AlexanderMcQueenCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .thread(threadDept)
                .addPipeline(new CrawlerPipeline());
        try {
            SpiderMonitor.instance().register(spider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        spider.start();
    }

    public void process(Page page) {

        logger.info("process start>>>>" + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            Elements elements = document.select("ul[class=level-1] li");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href");
                if (!Strings.isNullOrEmpty(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }
        }

        if (navList.contains(page.getUrl().toString())) {
            logger.info("process>>>>>>>>>>>" + page.getUrl());
            document = getNextPager(page);
            //每页获取 每个详情页
            Elements elements = document.select("article");
            if (elements.size() > 0) {
                for (Element element : elements) {
                    String url = element.select("a").attr("href");
                    logger.info("加入到采集队列>>>>>>>" + url);
                    detailList.add(url);
                    page.addTargetRequest(url);
                }
            }
        }
        /**
         * 这里是分析详情页的地方
         */
        if (detailList.contains(page.getUrl().toString())) {
            destroy();
            Product product = analyticalData(page);
            //加入数据
            if (!Objects.isNull(product) && !Strings.isNullOrEmpty(product.getName())) {
                page.putField("product", product);
            }
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
            colorUrl = "http://www.alexandermcqueen.cn/yTos/api/Plugins/ItemPluginApi/GetCombinationsAsync/?siteCode=ALEXANDERMCQUEEN_CN&code10="
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


}
