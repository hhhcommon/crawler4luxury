package crawler;

import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.HttpRequestUtil;
import common.JsonParseUtil;
import common.RegexUtil;
import core.model.ProductCrawler;
import io.netty.util.internal.ObjectUtil;
import model.ChannelJson;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yang
 * @Date: 2017/12/5.18:02
 * @Desc: 香奈儿 爬虫
 * <p>
 * 问题： 除了中国 其他的价格 需要采集
 * 解决方案：vpn 需要切换不同国家 用代理模式
 */
public class ChannelCrawler extends BaseCrawler {

    private static String reg = "http://www.chanel.com/(.*?)/fashion/products/(.*?)/.*[A-Z]{1}\\d{5}[A-Z]{1}\\d{5}\\w{5}.*";
    private List requestUrlFinal = new ArrayList();

    public ChannelCrawler(int dept) {
        super(dept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new ChannelCrawler(5).run();
    }

    @Override
    public void run() {
        logger.info("============ ChannelCrawler Crawler start=============");
        /**
         * 配置代理
         */
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        spider = Spider.create(new ChannelCrawler(threadDept))
                .addUrl("http://www.chanel.com/zh_CN/fashion.html#products")
                .addPipeline(CrawlerPipeline.getInstall())
                .thread(threadDept);
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1", 1080)));
        spider.setDownloader(httpClientDownloader);
        try {
            SpiderMonitor.instance().register(spider);
        } catch (JMException e) {
            e.printStackTrace();
        }
        spider.run();
    }

    @Override
    public void process(Page page) {
        logger.info("process>>>>>>" + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        if (page.getUrl().regex("http://www.chanel.com/zh_CN/fashion.html#products").match()) {
            Elements requestEl = document.getElementsByClass("fs-navigation-secondary-menu__list fs-menu-product-lines").first().getElementsByTag("ul").first().getElementsByTag("li");
            if (Objects.nonNull(requestEl)) {
                for (Element element : requestEl) {
                    String link = "http://www.chanel.com" + element.getElementsByTag("a").attr("href");
                    logger.info("爬虫获取到导航链接 " + link);
                    navList.add(link);
                }
                page.addTargetRequests(navList);
            }
        }
        if (navList.contains(page.getUrl().toString())) {
            try {
                String url = "http://www.chanel.com" + document.select("div.center-collection").first().getElementsByTag("a").first().attr("href");
                if (!Strings.isBlank(url)) {
                    logger.info("加入到采集队列 " + url);
                    detailList.add(url);
                    page.addTargetRequest(url);
                }
            } catch (Exception e) {
                //各种系列
                Elements elements = document.getElementsByClass("no-select nav-item");
                ObjectUtil.checkNotNull(elements, "不需要点击的链接 no-select nav-item ");
                for (Element element : elements) {
                    String url = "http://www.chanel.com" + element.getElementsByTag("a").attr("href");
                    logger.info("加入到采集队列 " + url);
                    page.addTargetRequest(url);
                    detailList.add(url);
                }
            }

        }

        /*
         获取 每页的各个商品
         */
        if (detailList.contains(page.getUrl().toString())) {

            Elements elements = document.select("a.product-link");
            ObjectUtil.checkNotNull(elements, "requestUrlContent elements");
            for (Element element : elements) {
                String url = "http://www.chanel.com" + element.attr("href");
                logger.info("加入到采集队列 " + url);
                page.addTargetRequest(url);
                requestUrlFinal.add(url);
            }

        }
        if (requestUrlFinal.contains(page.getUrl().toString()) || page.getUrl().regex(reg).match()) {

            String name = document.select("h1[itemprop=name]").first().text();
            List<String> img = new ArrayList<String>();
            Elements elements = document.select("img[class=lazyloaded]");
            String language = "zh_CN";
            for (Element element : elements) {
                img.add("http://www.chanel.com" + element.attr("data-src"));
            }

            try {
                String json = RegexUtil.getDataByRegex(".\"detailsGridJsonUrl\": \"(.*?)\".", page.getHtml().toString());
                if (!Objects.isNull(json)) {
                    String jsonUrl = "http://www.chanel.com" + json;
                    String html = HttpRequestUtil.sendGet(jsonUrl);
                    String jsonData = JsonParseUtil.getString(html, page.getUrl().toString().replaceAll("http://www.chanel.com", ""));
                    ChannelJson channelJsons = JSON.parseObject(jsonData, ChannelJson.class);
                    String tag = channelJsons.getData().getCollectionLabel();
                    List<ChannelJson.DataBeanX.DetailsBean.InformationBean> list = channelJsons.getData().getDetails().getInformation();
                    //高级成衣的附属商品标志
                    String code = "";
                    List<ProductCrawler> productCrawlerList = new ArrayList<>();
                    for (ChannelJson.DataBeanX.DetailsBean.InformationBean listData : list) {
                        for (ChannelJson.DataBeanX.DetailsBean.InformationBean.DatasBean l : listData.getDatas()) {
                            if (listData.getTitle().contains("高级成衣")) {
                                ProductCrawler productCrawler = new ProductCrawler();
                                productCrawler.setName(l.getTitle());
                                productCrawler.setColor(l.getColor());
                                productCrawler.setMaterial(l.getMaterial());
                                productCrawler.setRef(l.getData().get(0).getRef());
                                code = l.getData().get(0).getRef();
                                productCrawler.setLanguage(language);
                                productCrawler.setUrl(page.getUrl().toString());
                                productCrawler.setTags(tag);
                                //请求价格
                                productCrawler.setEurPrice(getRefPrice("zh_HK", l.getData().get(0).getRefPrice()));
                                //同一个系列的图片放到相同的图片
                                productCrawler.setImg(Joiner.on("|").join(img));
                                productCrawler.setClassification(listData.getTitle());
                                productCrawler.setBrand("chanel");
                                productCrawlerList.add(productCrawler);
                            } else {
                                ProductCrawler productCrawler = new ProductCrawler();
                                productCrawler.setName(l.getTitle());
                                productCrawler.setColor(l.getColor());
                                productCrawler.setMaterial(l.getMaterial());
                                productCrawler.setRef(l.getData().get(0).getRef());
                                productCrawler.setCode(code);
                                productCrawler.setTags(tag);
                                productCrawler.setLanguage(language);
                                productCrawler.setUrl(page.getUrl().toString());
                                //请求价格
                                productCrawler.setEurPrice(getRefPrice("zh_HK", l.getData().get(0).getRefPrice()));
                                //同一个系列的图片放到相同的图片
                                productCrawler.setImg(Joiner.on("|").join(img));
                                productCrawler.setClassification(listData.getTitle());
                                productCrawler.setBrand("chanel");
                                productCrawlerList.add(productCrawler);
                            }

                        }
                    }
                    page.putField("productCrawlerList", productCrawlerList);
                } else {
                    String price = null;
                    String ref = null;
                    try {
                        ref = document.select("div[class=ref info] p").first().text();
                        price = getRefPrice("zh_CN", replaceBlank(ref.substring(0, 13)));
                    } catch (Exception e) {
                        logger.info("获取ref 出现问题" + e.toString());
                    }
                    String size = null;
                    try {
                        size = RegexUtil.getDataByRegex("<p class=\"size info\">(.*?)</p>", page.getHtml().toString());
                    } catch (Exception e) {
                        logger.info("size 出现问题" + e.toString());
                    }
                    String classification = "";
                    List<String> arr = RegexUtil.matchGroup(reg, page.getUrl().toString());
                    if (arr.size() == 2) {
                        language = arr.get(0);
                        classification = arr.get(1);
                    }
                    List<String> tagarr = RegexUtil.matchGroup("<span itemprop=\"title\">(.*?)</span>", page.getHtml().toString());
                    String tags = tagarr.get(2);

                    String desc = null;
                    try {
                        desc = document.select("p[class=description info matCol ]").first().getElementsByTag("span").first().text();
                    } catch (Exception e) {
                        logger.info("desc 出现问题" + e.toString());
                    }
                    String color = null;
                    try {
                        color = document.select("div[class=product-scroll-detail-wrapper]").first().getElementsByTag("div").eq(2).text();
                    } catch (Exception e) {
                        logger.info("color 出现问题" + e.toString());
                    }
                    ProductCrawler p = new ProductCrawler();
                    p.setBrand("chanel");
                    p.setClassification(classification);
                    p.setColor(color);
                    p.setImg(Joiner.on("|").join(img));
                    p.setName(name);
                    p.setUrl(page.getUrl().toString());
                    p.setLanguage(language);
                    p.setIntroduction(desc);
                    p.setRef(ref);
                    p.setSize(size);
                    p.setEurPrice(price);
                    p.setTags(tags);
                    page.putField("product", p);
                }

            } catch (Exception e) {
                logger.info("该链接不支持json方式！");
            }


        }
    }

    private String getRefPrice(String local, String refPrice) {
        String price = null;
        try {
            String priceUrl = "http://ws.chanel.com/pricing/pricing_db/" + local + "/fashion/" + refPrice + "/?i_division=FSH&i_project=fsh_v3&i_client_interface=fsh_v3_misc&i_locale=" + local + "&format=json&callback=localJsonpPricingCallbackde59ebceb39f738952933882ef45e6ed";
            Site site = Site.me().setDomain("www.chanel.com")
                    .setCharset("utf-8")
                    .addHeader("Cookie", "cookie_consent=consent; hp_lang=" + local + "; country=CN; _cs_v=0; CH_ROUTE=373336256.20480.0000; mt.s-lbx=1; WT_FPC=id=07cca411-6bba-4805-9b14-9b1ea9ffe0b7:lv=1514919268274:ss=1514937213831; __zlcmid=kIh1BK45JL2WeP; mt.v=2.169822375.1514966018378; __ag_cm_=1; device=desktop; ag_fid=YTEyOFUXykuZUpgF; _ga=GA1.2.1317918075.1512469626; _gid=GA1.2.142845839.1515378297; chanelmwhishlist=N4IghgxhIFwNoF0A0IBOAXA7rRBfIA%3D%3D; _cs_id=20a542ad-effc-a2d5-bc1e-7f0084c96951.1512469625.36.1515380948.1515380948.1.1546633625822")
                    .setTimeOut(5000)
                    .setRetryTimes(3)
                    .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
            String jsonPrice = HttpRequestUtil.sendGet(priceUrl, site, new Proxy("127.0.0.1", 1080));
            price = RegexUtil.getDataByRegex("\"amount\":\"(.*?)\"", jsonPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }


    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.chanel.com")
                .setCharset("utf-8")
                .addCookie("hp_lang", "zh_HK")
                .setTimeOut(5000)
                .setRetryTimes(3)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
