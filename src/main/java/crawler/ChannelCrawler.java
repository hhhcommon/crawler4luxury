package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.*;
import core.model.ProductCrawler;
import io.netty.util.internal.ObjectUtil;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Html;

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
 * 采集方法：
 * 跑一次 切换下代理 更换下 价格的字段
 */
public class ChannelCrawler extends BaseCrawler {

    private static String reg = "http://www.chanel.com/(.*?)/fashion/products/(.*?)/.*[A-Z]{1}\\d{5}[A-Z]{1}\\d{5}\\w{5}.*";
    private List requestUrlFinal = new ArrayList();

    public ChannelCrawler(int dept) {
        super(dept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new ChannelCrawler(3).run();
    }

    @Override
    public void run() {
        logger.info("============ ChannelCrawler Crawler start=============");
        /**
         * 配置代理
         */
        try {

            spider = Spider.create(new ChannelCrawler(threadDept))
                    .addUrl("http://www.chanel.com/zh_CN/fashion.html#products")
                    .addPipeline(CrawlerPipeline.getInstall())
                    .thread(threadDept);
            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
            httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1", 1080)));
            spider.setDownloader(httpClientDownloader);
            spider.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                logger.info("navList size " + navList.size());
            }
        }
        if (navList.contains(page.getUrl().toString())) {
            try {
                //各种系列
                Elements elements = document.getElementsByClass("no-select nav-item");
                if (elements.size() > 0) {
                    ObjectUtil.checkNotNull(elements, "不需要点击的链接 no-select nav-item ");
                    for (Element element : elements) {
                        String url = "http://www.chanel.com" + element.getElementsByTag("a").attr("href");
                        logger.info("加入到采集队列 " + url);
                        //如果是这样的 说明他自己就是这个链接 将链接加到详情页
                        if (!url.equals("http://www.chanel.com#")) {
                            page.addTargetRequest(url);
                            detailList.add(url);
                        }
                    }

                } else {
                    String url = "http://www.chanel.com" + document.select("div.center-collection").first().getElementsByTag("a").first().attr("href");
                    if (!Strings.isBlank(url) && RegexUtil.checkHttp(url)) {
                        logger.info("加入到采集队列 " + url);
                        navList.add(url);
                        detailList.add(url);
                        page.addTargetRequest(url);
                    }
                }
            } catch (Exception e) {
                String url = "http://www.chanel.com" + document.select("div.center-collection").first().getElementsByTag("a").first().attr("href");
                if (!Strings.isBlank(url) && RegexUtil.checkHttp(url)) {
                    logger.info("加入到采集队列 " + url);
                    navList.add(url);
                    detailList.add(url);
                    page.addTargetRequest(url);
                }
            }
            logger.info("detailList size " + detailList.size());
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
            logger.info("requestUrlFinal size " + requestUrlFinal.size());
        }
        if (requestUrlFinal.contains(page.getUrl().toString()) || page.getUrl().regex(reg).match()) {

            String name = null;
            try {
                name = document.select("h1[itemprop=name]").first().text();
            } catch (Exception e) {
                logger.info("获取名称错误");
            }
            List<String> img = new ArrayList<String>();
            Elements elements = document.select("img[class=lazyloaded]");
            String language = "zh_CN";
            if (elements.size() > 0) {
                for (Element element : elements) {
                    img.add("http://www.chanel.com" + element.attr("data-src"));
                }
            } else {
                try {
                    String imgOg = document.select("meta[property=og:image]").attr("content");
                    img.add(imgOg);
                } catch (Exception e) {
                }
            }
            try {
                String price = null;
                String ref = null;
                try {
                    ref = document.select("div[class=ref info] p").first().text();
                } catch (Exception e) {
                    ref = document.select("p[class=ref info]").first().text();
                } finally {
                    price = getRefPrice("zh_CN", replaceBlank(ref.substring(0, 13)));
                }
                String size = null;
                try {
                    size = RegexUtil.getDataByRegex("<p class=\"size info\">(.*?)</p>", page.getHtml().toString());
                } catch (Exception e) {
                    logger.info("size 出现问题" + e.toString());
                }
                List<String> arr = RegexUtil.matchGroup(reg, page.getUrl().toString());
                if (arr.size() == 2) {
                    language = arr.get(0);
                }
                List<String> tagarr = RegexUtil.matchGroup("<span itemprop=\"title\">(.*?)</span>", page.getHtml().toString());
                String tags = tagarr.get(3);
                String classf = tagarr.get(2);
                String desc = null;
                try {
                    desc = document.select("meta[property=og:description]").attr("content");
                } catch (Exception e) {
                    logger.info("desc 出现问题" + e.toString());
                }
                String color = null;
                try {
                    color = document.select("div[class=product-scroll-detail-wrapper]").first().getElementsByTag("div").eq(2).text();
                } catch (Exception e) {
                    color = document.select("p[class=description info]").eq(1).parents().first().text();
                }
                //请求英文网站 获取英文名称

                String engUrl = document.select("link[hreflang=en-GB]").attr("href");

                String engName = null;
                try {
                    Document doc = new Html(HttpRequestUtil.sendGet(engUrl)).getDocument();
                    engName = doc.select("h1[itemprop=name]").first().text();
                } catch (Exception e) {

                }
                ProductCrawler p = new ProductCrawler();
                p.setBrand("chanel");
                p.setClassification(classf);
                p.setEngName(engName);
                p.setColor(color);
                p.setImg(Joiner.on("|").join(img));
                p.setName(name);
                p.setUrl(page.getUrl().toString());
                p.setLanguage(language);
                p.setIntroduction(desc);
                p.setRef(ref);
                p.setSize(size);
                p.setPrice(price);
                p.setTags(tags);
                page.putField(Commons.cwJob, p);
//                page.putField(Commons.tagName, true);
            } catch (Exception e) {
                logger.info("该链接不支持json方式！");
            } finally {
                //移除link
                removeList(page.getUrl().get());
                closeWebDriver();
            }
        }

    }

    private String getRefPrice(String local, String refPrice) {
        String price = null;
        try {
            String priceUrl = "http://ws.chanel.com/pricing/pricing_db/" + local + "/fashion/" + refPrice + "/?i_division=FSH&i_project=fsh_v3&i_client_interface=fsh_v3_misc&i_locale=" + local + "&format=json&callback=localJsonpPricingCallbackde59ebceb39f738952933882ef45e6ed";
            Site site = Site.me().setDomain("www.chanel.com")
                    .setCharset("utf-8")
                    .addHeader("Cookie", "cookie_consent=consent; hp_lang=" + local + "; country=CN;")
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
                .setDomain("chanel.com")
                .setCharset("utf-8")
                .addCookie("country", "ZH")
                .setTimeOut(5000)
                .setRetryTimes(3)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
