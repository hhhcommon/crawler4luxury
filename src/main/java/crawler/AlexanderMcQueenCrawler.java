package crawler;

import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import common.DbUtil;
import common.HttpRequestUtil;
import common.RegexUtil;
import core.model.ProductCrawler;
import model.Alexandermcqueen;
import model.ColorAlexander;
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
 * @Date: 2017/12/1.16:57
 * @desc: AlexanderMcQueenCrawler  is ok'
 * <p>
 * 先跑中文的 优先显示中文的数据 然后显示 其他的
 */
public class AlexanderMcQueenCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();
    private static String reg = "http://www.alexandermcqueen.cn/.*?/alexandermcqueen/.*.html#dept=\\w+";

    private static String hkreg = "http://www.alexandermcqueen.com/.*?/alexandermcqueen/.*.html#dept=\\w+";

    public AlexanderMcQueenCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new AlexanderMcQueenCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ Alexandermcqueen Crawler start=============");
        //添加中国的链接
        urls.add("http://www.alexandermcqueen.cn/cn");
//        urls.add("http://www.alexandermcqueen.com/hk");
//        urls.add("http://www.alexandermcqueen.com/de");
//        urls.add("http://www.alexandermcqueen.com/gb");
        spider = Spider.create(new AlexanderMcQueenCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .thread(threadDept)
                .addPipeline(CrawlerPipeline.getInstall());
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
                    logger.info("加入到采集队列 " + link);
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }
        }

        if (navList.contains(page.getUrl().toString())) {
            navList.remove(page.getUrl().toString());
            logger.info("navList的集合个数还有【" + navList.size() + "】个");
            init();
            logger.info("process>>>>>>>>>>>" + page.getUrl());
            document = driverComponent.getNextPager(page, webDriver);
            //每页获取 每个详情页
            Elements elements = document.select("article");
            if (elements.size() > 0) {
                for (Element element : elements) {
                    String url = element.select("a").attr("href");
                    logger.info("加入到采集队列 " + url);
                    detailList.add(url);
                    page.addTargetRequest(url);
                }
            }
        }
        /**
         * 这里是分析详情页的地方
         */
        if (detailList.contains(page.getUrl().toString())) {
            if (navList.size() == 0) {
                driverComponent.destoty();
            }
            ProductCrawler productCrawler = analyticalData(page);
            //加入数据
            if (!Objects.isNull(productCrawler) && !Strings.isNullOrEmpty(productCrawler.getName())) {
                page.putField("productCrawler", productCrawler);
            }
        }

    }

    /**
     * 分析数据
     *
     * @param page
     * @return
     */
    public ProductCrawler analyticalData(Page page) {
        String Introduction = null;// 详细说明
        String imgUrl;// 商品大图
        String num;// 商品编号
        ProductCrawler productCrawler = new ProductCrawler();
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
                try {
                    Introduction = elements.first().text();
                } catch (Exception e) {
                }
            }
            productCrawler.setIntroduction(Introduction);
            List<String> listImg = new ArrayList<String>();
            listImg = RegexUtil.matchGroup(
                    "<img alt=\".*?\" data-ytos-code10=\".*?\" data-ytos-image-shot=\".\" data-ytos-image-size=\".*?\" itemprop=\"image\" sizes=\".*?\" srcset=\"(.*?) 2400w",
                    page.getHtml().toString());
            imgUrl = Joiner.on("|").join(listImg);// 多图采集
            productCrawler.setImg(imgUrl);
            productCrawler.setUrl(page.getUrl().toString());
            productCrawler.setLanguage("zh_CN");
            productCrawler.setBrand("alexandermcqueen");
            try {
                // 颜色实体描述
                alexandermcqueen = JSON.parseObject(HttpRequestUtil.sendGet(prizeUrl), Alexandermcqueen.class);
                colorAlexander = JSON.parseObject(HttpRequestUtil.sendGet(colorUrl), ColorAlexander.class);
                if (alexandermcqueen != null) {
                    productCrawler.setName(alexandermcqueen.getProduct_title());
                    productCrawler.setClassification(alexandermcqueen.getProduct_category());
                    //价格有折扣的话 直接取折扣后的价格
                    try {
                        if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/hk")) {
                            if (alexandermcqueen.getProduct_discountprice() > 0) {
                                productCrawler.setHkPrice(String.valueOf(alexandermcqueen.getProduct_discountprice()).toString());
                            } else {
                                productCrawler.setHkPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                            }
                        }
                    } catch (Exception e) {
                        logger.info("获取价格发生错误");
                    }
                    try {
                        if (page.getUrl().toString().contains("http://www.alexandermcqueen.cn/cn")) {
                            if (alexandermcqueen.getProduct_discountprice() > 0) {
                                productCrawler.setPrice(String.valueOf(alexandermcqueen.getProduct_discountprice()).toString());
                            } else {
                                productCrawler.setPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                            }

                        }
                    } catch (Exception e) {
                        logger.info("获取价格发生错误");
                    }
                    if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/de")) {
                        try {
                            if (alexandermcqueen.getProduct_discountprice() > 0) {
                                productCrawler.setEurPrice(String.valueOf(alexandermcqueen.getProduct_discountprice()).toString());
                            } else {
                                productCrawler.setEurPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                            }
                        } catch (Exception e) {
                            logger.info("获取价格发生错误");
                        }

                    }
                    if (page.getUrl().toString().contains("http://www.alexandermcqueen.com/gb")) {
                        try {
                            if (alexandermcqueen.getProduct_discountprice() > 0) {
                                productCrawler.setEnPrice(String.valueOf(alexandermcqueen.getProduct_discountprice()).toString());
                            } else {
                                productCrawler.setEnPrice(String.valueOf(alexandermcqueen.getProduct_price()).toString());
                            }
                        } catch (Exception e) {
                            logger.info("获取价格发生错误");
                        }
                    }
                    productCrawler.setRef(alexandermcqueen.getProduct_cod10());
                }
                if (colorAlexander != null) {
                    List<ColorAlexander.SizesBean> list = colorAlexander.getSizes();
                    List<String> sizes = new ArrayList<String>();
                    if (list.size() > 0) {
                        for (ColorAlexander.SizesBean o : list) {
                            sizes.add(o.getDescription());
                        }
                    }
                    productCrawler.setSize(Joiner.on("|").join(sizes));
                    List<ColorAlexander.ColorsBean> list1 = colorAlexander.getColors();
                    List<String> colo = new ArrayList<String>();
                    if (list1.size() > 0) {
                        for (ColorAlexander.ColorsBean o : list1) {
                            colo.add(o.getDescription());
                        }
                    }
                    productCrawler.setColor(Joiner.on("|").join(colo));
                }
            } catch (Exception ex) {
                logger.info("请求接口发生错误");
                return productCrawler;
            }
            logger.info("productCrawler=========" + productCrawler.toString());
            return productCrawler;
        }

        return null;
    }


    public Site getSite() {
        site = Site.me()
                .setDomain("www.alexandermcqueen.com")
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                .setCharset("utf-8")
                .setTimeOut(5000)
                .setRetryTimes(3);
        return site;
    }


}
