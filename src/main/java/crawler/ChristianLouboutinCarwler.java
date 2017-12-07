package crawler;

import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.JsonParseUtil;
import common.RegexUtil;
import core.model.Product;
import io.netty.util.internal.ObjectUtil;
import model.ChristianPrize;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/7.10:13
 * @Desc: 中国网站没有商品
 */
public class ChristianLouboutinCarwler extends BaseCrawler {

    private static final String reg = "http://.*?.christianlouboutin.com/.*?/catalogsearch/.*?";

    public ChristianLouboutinCarwler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        new ChristianLouboutinCarwler(1).run();
    }

    @Override
    public void run() {
        logger.info("============ ChristianLouboutinCarwler Crawler start=============");
        List<String> urls = new ArrayList<>();
        urls.add("http://asia.christianlouboutin.com/hk_en/catalogsearch/result/?q=*:*");
        urls.add("http://eu.christianlouboutin.com/uk_en/catalogsearch/result/?q=*");
        urls.add("http://eu.christianlouboutin.com/de_en/catalogsearch/result/?q=*");
        Spider spider = Spider.create(new ChristianLouboutinCarwler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(new CrawlerPipeline())
                .thread(threadDept);
        setSpider(spider);
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info("process>>>>>>>>>>>" + page.getUrl());
        if (page.getUrl().regex(reg).match()) {
            Elements elements = page.getHtml().getDocument().select("#category-main > div > a");
            if (elements.size() > 0) {
                for (Element element : elements) {
                    page.addTargetRequest(element.attr("href"));
                }
            }
        } else {
            Product product = analyticalData(page);
            ObjectUtil.checkNotNull(product, "ChristianLouboutinCarwler product is null");
            page.putField("product", product);
        }

    }

    private Product analyticalData(Page page) {
        Product product = new Product();
        String json = RegexUtil.getDataByRegex("<script type=\"application.ld.json\">\\s+(.*?)</script>", page.getHtml().toString());
        List<String> imgList = new ArrayList<String>();
        // 图片获取
        Elements elements = page.getHtml().getDocument().getElementsByClass("product-main").first().getElementsByTag("img");
        ObjectUtil.checkNotNull(elements, "element is null in chirstianlouboutincarwler  on line 78");
        for (Element element : elements) {
            String img = element.getElementsByTag("img").attr("data-src");
            if (!Strings.isBlank(img)) {
                imgList.add(img);
            }
        }
        if (!Strings.isBlank(json)) {
            product.setBrand(JsonParseUtil.getString(json, "brand"));
            product.setUrl(page.getUrl().toString());
            product.setName(JsonParseUtil.getString(json, "name"));
            product.setIntroduction(JsonParseUtil.getString(json, "description"));
            product.setImg(Joiner.on("|").join(imgList));
            product.setClassification(JsonParseUtil.getString(json, "category"));
            product.setRef(JsonParseUtil.getString(json, "sku"));
            product.setColor(JsonParseUtil.getString(json, "color"));
        }

        ChristianPrize christianPrize = null;
        try {
            String jsonPrize = RegexUtil.getDataByRegex("var optionsPrice = new Product.OptionsPrice.(.*?).;", page.getHtml().toString());
            christianPrize = JSON.parseObject(jsonPrize, ChristianPrize.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (christianPrize != null) {
            if (page.getUrl().toString().contains("de_en")) {
                product.setEurPrice(String.valueOf(christianPrize.getProductPrice()));
                product.setLanguage("de_en");
            }
            if (page.getUrl().toString().contains("hk_en")) {
                product.setHkPrice(String.valueOf(christianPrize.getProductPrice()));
                product.setLanguage("hk_en");
            }
            if (page.getUrl().toString().contains("uk_en")) {
                product.setEnPrice(String.valueOf(christianPrize.getProductPrice()));
                product.setLanguage("uk_en");
            }
        }
        return product;
    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .setSleepTime(3000);
        return site;
    }
}
