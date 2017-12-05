package crawler;


import base.BaseCrawler;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import common.HttpRequestUtil;
import common.RegexUtil;
import core.model.Product;
import model.BurFinal;
import model.Burberry;
import pipeline.AlexanderMcQueenCrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by yang on 2017/6/21.
 */
public class BurberryCrawler extends BaseCrawler implements PageProcessor {

    private final static Logger logger = Logger.getLogger(String.valueOf(BurberryCrawler.class));

    static String fireg2 = "https://cn.burberry.com/service/product-listing/search/.query=.*?offset=.*\\d+";

    static String reg = "https://cn.burberry.com/.*p\\d+";

    static String finalReg = "https://cn.burberry.com/service/products/.*?id=.*cat.*";

    public BurberryCrawler(int crawlPath) {
        this.threadDept = crawlPath;
    }


    @Override
    public void run() {
        logger.info("============ BurberryCrawler Crawler start=============");
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i <= 6000; i = i + 40) {
            String url =
                    "https://cn.burberry.com/service/product-listing/search/?query=*%3A*&order_by=numprop%3Adescending%3Aprice&limit=40&offset="
                            + i + "&_=1512443746131";
            urlList.add(url);
        }
        Spider spider = Spider.create(new BurberryCrawler(threadDept))
                .addUrl((String[]) urlList.toArray(new String[urlList.size()]))
                .addPipeline(new AlexanderMcQueenCrawlerPipeline())
                .thread(threadDept);
        setSpider(spider);
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info("process>>>>>>>>>>>" + page.getUrl());
        try {
            if (page.getUrl().regex(fireg2).match()) {
                Burberry burberry = JSON.parseObject(page.getHtml().toString(), Burberry.class);
                if (burberry.getShelves().size() > 0) {
                    List<Burberry.ShelvesBean.ShelfElementsBean> shelfElementsBeans =
                            burberry.getShelves().get(0).getShelfElements();
                    for (Burberry.ShelvesBean.ShelfElementsBean shelfElementsBean : shelfElementsBeans) {
                        page.addTargetRequest("https://cn.burberry.com" + shelfElementsBean.getLink());
                    }
                }
            }

            if (page.getUrl().regex(reg).match()) {
                String catCode =
                        RegexUtil.getDataByRegex("data-atg-category-id=\"(.*?)\" data-currency-code=\"CNY", page.getHtml().toString());
                String shortUrl = RegexUtil.getDataByRegex("data-default-url=\"(.*?)\"", page.getHtml().toString());
                String id = RegexUtil.getDataByRegex("data-product-id=\"(.*?)\"", page.getHtml().toString());
                String url = "https://cn.burberry.com/service/products" + shortUrl + "?id=" + id + "&categoryId="
                        + catCode + "&_=1498022990033";
                logger.info("加入到下一列===" + url);
                page.addTargetRequest(url);
            }

            if (page.getUrl().regex(finalReg).match()) {// 处理页
                Product product = analyticalData(page);
                if (product != null) {
                    page.putField("product", product);
                }
            }

        } catch (Exception e) {
            logger.info("转化json失败=====" + page.getUrl() + e.toString());
            logger.info("转化json失败的json字符串====" + page.getHtml());
        }
    }

    public Product analyticalData(Page page) {
        Product product = new Product();
        BurFinal burFinal = JSON.parseObject(page.getHtml().toString(), BurFinal.class);
        product.setBrand("burberry");
        product.setLanguage("zh_CN");
        product.setUrl("https://cn.burberry.com" + burFinal.getDefaultUrl());
        product.setName(burFinal.getName());

        List<String> temp = new ArrayList<String>();
        if (burFinal.getHdCarousel() != null && burFinal.getHdCarousel().size() > 0) {
            for (BurFinal.HdCarouselBean b : burFinal.getHdCarousel()) {
                temp.add("http:" + b.getImg().getSrc().toString());
            }
        }
        product.setImg(Joiner.on("|").join(temp));

        product.setPrice(String.valueOf(burFinal.getPrice()));
        product.setRef(burFinal.getItemNumber());
        product.setIntroduction(burFinal.getDescription().getContent());
        if (burFinal.getFindInStore().getSize() != null) {
            if (burFinal.getFindInStore().getSize().getItems().size() > 0) {
                List<String> list = new ArrayList<String>();
                for (BurFinal.FindInStoreBean.SizeBean.ItemsBean itemsBean : burFinal.getFindInStore()
                        .getSize()
                        .getItems()) {
                    if (itemsBean.isIsAvailable()) {
                        list.add(itemsBean.getLabel());
                    }
                }
                product.setSize(Joiner.on("|").join(list));
            }
        }

        if (burFinal.getOptions().size() > 0) {
            List<String> list = new ArrayList<String>();
            for (BurFinal.OptionsBean itemsBean : burFinal.getOptions()) {
                if (itemsBean.getLabel().equals("颜色")) {
                    for (BurFinal.OptionsBean.ItemsBeanX itemsBeanX : itemsBean.getItems()) {
                        if (itemsBeanX.isIsAvailable()) {
                            list.add(itemsBeanX.getLabel());
                        }
                    }
                }
            }
            product.setColor(Joiner.on("|").join(list));
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
        System.out.println(product.toString());
        return product;
    }


    @Override
    public Site getSite() {
        site = Site.me().setDomain("cn.burberry.com")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("x-newrelic-id", "VwIOVFFUGwIJVldQBAQA")
                .addHeader("referer", "https://cn.burberry.com/womens-new-arrivals-new-in/")
                .addHeader("x-csrf-token", getCode())
                .setSleepTime(3000);
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
