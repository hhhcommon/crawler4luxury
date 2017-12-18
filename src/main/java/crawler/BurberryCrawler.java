package crawler;


import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.HttpRequestUtil;
import common.RegexUtil;
import core.model.Product;
import model.BurBerryItem;
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

/**
 * @Author cyy
 * @Date 2017/12/15 18:35
 * @Description BurberryCrawler is ok
 */
public class BurberryCrawler extends BaseCrawler {
    private static String senced = "https://cn.burberry.com/service/\\w+/\\w+/\\w+/.*?.=\\d+";

    private static String reg = "https://cn.burberry.com/.*p\\d+";


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
        List<String> urls = new ArrayList<>();
        /**
         * 女士的
         */
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/coats/?_=1512464008454");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/jackets/?_=1512464008455");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/dresses/?_=1512464008456");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/knitwear-sweatshirts/?_=1512464008457");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/shirts-tops/?_=1512464008458");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/skirts-trousers/?_=1512464008459");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/ponchos-capes/?_=1512464008460");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/bags/?_=1512464008461");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/shoes/?_=1512464008462");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/scarves/?_=1512464008463");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/wallets/?_=1512464008464");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/small-accessories/?_=1512464008465");
        urls.add("https://cn.burberry.com/service/shelf/women/sale/all-sale/swimwear/?_=1512464008465");

        /**
         * 男士的
         */
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/coats/?_=1512464406668");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/jackets/?_=1512464406669");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/suits-blazers/?_=1512464406670");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/casual-shirts/?_=1512464406671");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/formal-shirts/?_=1512464406672");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/knitwear-sweatshirts/?_=1512464406673");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/polos-t-shirts/?_=1512464406674");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/jeans-trousers/?_=1512464406675");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/jeans-trousers/?_=1512464406675");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/scarves/?_=1512464406676");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/wallets/?_=1512464406677");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/ties/?_=1512464406678");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/shoes/?_=1512464406679");
        urls.add("https://cn.burberry.com/service/shelf/men/sale/all-sale/swimwear/?_=1512464008465");

        spider = Spider.create(new BurberryCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
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
        logger.info("process>>>>>>>>>>>" + page.getUrl());
        try {
            if (page.getUrl().regex(senced).match()) {
                List<BurBerryItem> burberry = JSON.parseArray(RegexUtil.getDataByRegex("<body>(.*?)</body>", page.getHtml().css("body").toString()), BurBerryItem.class);
                if (burberry != null) {
                    for (BurBerryItem burBerryItem : burberry) {
                        String link = "https://cn.burberry.com" + burBerryItem.getLink();
                        logger.info("加入采集队列>>>>>" + link);
                        page.addTargetRequest(link);
                    }
                }
            }
            if (page.getUrl().regex(reg).match()) {// 处理页
                Product product = analyticalData(page);
                if (product != null) {
                    page.putField("product", product);
                }
            }

        } catch (Exception e) {
            logger.info("异常信息" + e.toString());
            logger.info("转化json失败>>>>>>>" + page.getUrl());
        }
    }

    /**
     * 解析数据
     *
     * @param page
     * @return
     */
    public Product analyticalData(Page page) {
        Product product = new Product();
        Document document = page.getHtml().getDocument();
        product.setBrand("burberry");
        product.setLanguage("zh_CN");
        product.setUrl(page.getUrl().toString());
        product.setName(document.getElementsByClass("product-purchase_name").first().text());
        List<String> temp = new ArrayList<String>();
        //获取图片
        Elements imgEl = document.getElementsByClass("product-carousel_item");
        if (imgEl != null && imgEl.size() > 0) {
            for (Element element : imgEl) {
                String img = element.getElementsByTag("img").attr("src");
                temp.add(img);
            }
        }
        product.setImg(Joiner.on("|").join(temp));
        product.setPrice(document.getElementsByClass("product-purchase_price").first().text());
        product.setRef(RegexUtil.getDataByRegex("(\\d+)", document.getElementsByClass("product-purchase_item-number").first().text()));
        product.setIntroduction(document.getElementsByClass("accordion-tab_content").first().text());

        try {
            Elements sizeEl = document.select("label.product-purchase_option");

            if (sizeEl != null && sizeEl.size() > 0) {
                List<String> list = new ArrayList<String>();
                for (Element element : sizeEl) {
                    list.add(element.text());
                }
                product.setSize(Joiner.on("|").join(list));

            }

            Element colorEl = document.select("div.product-purchase_options").first();
            if (colorEl != null) {
                List<String> list = new ArrayList<String>();
                Elements colors = colorEl.getElementsByTag("span");
                for (Element element : colors) {
                    String color = element.getElementsByTag("a").first().getElementsByTag("img").attr("title");
                    list.add(color);
                }
                product.setColor(Joiner.on("|").join(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取香港价格
        String hkUrl = product.getUrl().replace("cn", "hk");
        try {
            String html = HttpRequestUtil.sendGet(hkUrl);
            product
                    .setHkPrice(RegexUtil.getDataByRegex("<meta property=\"og:price:amount\" content=\"(.*?)\"/>", html));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取德国价格

        String deUrl = product.getUrl().replace("cn", "de");
        try {
            String html = HttpRequestUtil.sendGet(deUrl);
            product
                    .setEurPrice(RegexUtil.getDataByRegex("<meta property=\"og:price:amount\" content=\"(.*?)\"/>", html));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取英国价格

        String enUrl = product.getUrl().replace("cn", "uk");
        try {
            String html = HttpRequestUtil.sendGet(enUrl);
            product
                    .setEnPrice(RegexUtil.getDataByRegex("<meta property=\"og:price:amount\" content=\"(.*?)\"/>", html));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }


    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("cn.burberry.com")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("x-newrelic-id", "VwIOVFFUGwIJVldQBAQA")
                .addHeader("referer", "https://cn.burberry.com/womens-new-arrivals-new-in/")
                .addHeader("x-csrf-token", getCode())
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setSleepTime(1000);
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
