package crawler;

import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.HttpRequestUtil;
import common.JsonParseUtil;
import common.RegexUtil;
import core.model.Product;
import io.netty.util.internal.ObjectUtil;
import model.ChannelJson;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/5.18:02
 * @Desc: 香奈儿 爬虫
 */
public class ChannelCrawler extends BaseCrawler implements PageProcessor {

    private static String reg = "http://www.chanel.com/(.*?)/fashion/products/(.*?)/.*[A-Z]{1}\\d{5}[A-Z]{1}\\d{5}\\w{5}.*";
    /**
     * 存储 http://www.chanel.com/zh_CN/fashion/products/eyewear.html 这样的链接
     */
    private static List<String> requestUrlDeep = new ArrayList<>();
    /**
     * 存储 http://www.chanel.com/zh_CN/fashion/products/ready-to-wear/g.cruise-2017-18.c.18C.html 这种类型的链接
     */
    private static List<String> requestUrlContent = new ArrayList<>();

    /**
     * 存储 最终的内容链接
     */
    private static List<String> requestUrlFinal = new ArrayList<>();

    public ChannelCrawler(int dept) {
        super(dept);
    }

    public static void main(String[] args) {
        new ChannelCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ ChannelCrawler Crawler start=============");
        /**
         * 配置代理
         */
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1", 1080, "username", "password")));
        Spider spider = Spider.create(new ChannelCrawler(threadDept))
                .addUrl("http://www.chanel.com/zh_CN/fashion.html#products")
//                .addPipeline(new CrawlerPipeline())
                .thread(threadDept);
        setSpider(spider);
        spider.setDownloader(httpClientDownloader);
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info("process>>>>>>" + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        List<String> requestUrl = new ArrayList<>();
        if (page.getUrl().regex("http://www.chanel.com/zh_CN/fashion.html#products").match()) {

            Elements requestEl = document.getElementsByClass("fs-navigation-secondary-menu__list fs-menu-product-lines").first().getElementsByTag("ul").first().getElementsByTag("li");
            ObjectUtil.checkNotNull(requestEl, "requestEl");
            requestUrl.clear();
            for (Element element : requestEl) {
                requestUrl.add("http://www.chanel.com" + element.getElementsByTag("a").attr("href"));
                requestUrlDeep.add("http://www.chanel.com" + element.getElementsByTag("a").attr("href"));
            }
            page.addTargetRequests(requestUrl);
        }

        if (requestUrlDeep.contains(page.getUrl().toString())) {
            try {
                String url = "http://www.chanel.com" + document.select("div.center-collection").first().getElementsByTag("a").first().attr("href");
                ObjectUtil.checkNotNull(url, "url");
                requestUrlContent.add(url);
                page.addTargetRequest(url);
            } catch (Exception e) {
                logger.info("处理不需要点击2次的链接>>>>>>>");
                //处理不需要2次点击的链接
                //各种系列
                Elements elements = document.getElementsByClass("no-select nav-item");
                ObjectUtil.checkNotNull(elements, "不需要点击的链接 no-select nav-item ");
                for (Element element : elements) {
                    String url = "http://www.chanel.com" + element.getElementsByTag("a").attr("href");
                    page.addTargetRequest(url);
                    //内容页的链接加到一个list 省去 正则去判断
                    requestUrlContent.add(url);
                }
            }

        }

        /*
         获取 每页的各个商品
         */
        if (requestUrlContent.contains(page.getUrl().toString())) {

            Elements elements = document.select("a.product-link");
            ObjectUtil.checkNotNull(elements, "requestUrlContent elements");
            for (Element element : elements) {
                String url = "http://www.chanel.com" + element.attr("href");
                page.addTargetRequest(url);
                requestUrlFinal.add(url);
            }

        }

            /*
            分析内容页
             */
        if (requestUrlFinal.contains(page.getUrl().toString())) {
            String name = document.select("h1[itemprop=name]").first().text();
            List<String> img = new ArrayList<String>();
            Elements elements = document.select("img[class=lazyloaded]");
            String language = "zh_CN";
            for (Element element : elements) {
                img.add("http://www.chanel.com" + element.attr("data-src"));
            }
            try {
                String json = RegexUtil.getDataByRegex(".\"detailsGridJsonUrl\": \"(.*?)\".", page.getHtml().toString());
                String jsonUrl = "http://www.chanel.com" + json;
                String html = HttpRequestUtil.sendGet(jsonUrl);
                String jsonData = JsonParseUtil.getString(html, page.getUrl().toString().replaceAll("http://www.chanel.com", ""));
                ChannelJson channelJsons = JSON.parseObject(jsonData, ChannelJson.class);
                Product product = new Product();
                List<ChannelJson.DataBeanX.DetailsBean.InformationBean> list = channelJsons.getData().getDetails().getInformation();
                for (ChannelJson.DataBeanX.DetailsBean.InformationBean listData : list) {
                    for (ChannelJson.DataBeanX.DetailsBean.InformationBean.DatasBean l : listData.getDatas()) {
                        product.setName(l.getTitle());
                        product.setColor(l.getColor());
                        product.setMaterial(l.getMaterial());
                        product.setRef(l.getData().get(0).getRef());
                        product.setLanguage(language);
                        //请求价格
                        product.setPrice(getRefPrice("zh_CN", l));
                        product.setHkPrice(getRefPrice("en_HK", l));
                        product.setEnPrice(getRefPrice("en_GB", l));
                        product.setEurPrice(getRefPrice("fr_BE", l));
                        //同一个系列的图片放到相同的图片
                        product.setImg(Joiner.on("|").join(img));
                        product.setClassification(listData.getTitle());
                        product.setBrand("chanel");
                        page.putField("product", product);
                        return;
                    }
                }


                ObjectUtil.checkNotNull(channelJsons, "JSON.parseArray is null");
            } catch (Exception e) {
                logger.info("该链接不支持json方式！");
            }
            //获取num 商品
            int num = Integer.parseInt(document.getElementsByClass("page-num no-select").text());

            String ref = document.getElementsByClass("ref info").get(num - 1).text();

            String size = RegexUtil.getDataByRegex("<p class=\"size info\">(.*?)</p>", page.getHtml().toString());
            String classification = "";

            List<String> arr = RegexUtil.matchGroup(reg, page.getUrl().toString());
            if (arr.size() == 2) {
                language = arr.get(0);
                classification = arr.get(1);
            }
            List<String> tagarr = RegexUtil.matchGroup("<span itemprop=\"title\">(.*?)</span>", page.getHtml().toString());
            String tags = tagarr.get(0);
            String price = "";


            Product p = new Product();
            p.setBrand("chanel");
            p.setClassification(classification);
//            p.setColor(color);
            p.setImg(Joiner.on("|").join(img));
            p.setName(name);
            p.setUrl(page.getUrl().toString());
            p.setLanguage(language);
//            p.setMaterial(material);
//            p.setRef(ref);
            p.setSize(size);
            p.setPrice(price);
            p.setTags(tags);
//            page.putField("product", p);
        }
    }

    private String getRefPrice(String local, ChannelJson.DataBeanX.DetailsBean.InformationBean.DatasBean l) {

        String price = null;
        try {
            String priceUrl = "http://ws.chanel.com/pricing/pricing_db/" + local + "/fashion/" + l.getData().get(0).getRefPrice() + "/?i_division=FSH&i_project=fsh_v3&i_client_interface=fsh_v3_misc&i_locale=" + local + "&format=json&callback=localJsonpPricingCallbackde59ebceb39f738952933882ef45e6ed";
            String jsonPrice = HttpRequestUtil.sendGet(priceUrl);
            price = RegexUtil.getDataByRegex("\"amount\":\"(.*?)\"", jsonPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    @Override
    public Site getSite() {

        site = Site.me()
                .setDomain("www.chanel.com")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .setSleepTime(3000);


        return site;
    }
}
