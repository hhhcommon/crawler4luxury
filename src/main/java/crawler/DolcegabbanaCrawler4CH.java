package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.*;
import componentImpl.WebDriverManager;
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
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.selector.Html;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/7.17:23
 * @Desc: DolcegabbanaCrawler4Hk  多个site 的采集 改变header 才能更换国家
 */
public class DolcegabbanaCrawler4CH extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public DolcegabbanaCrawler4CH(int threadDept) {
        super(threadDept);
    }


    public static void main(String[] args) {
        DbUtil.init();
        new DolcegabbanaCrawler4CH(1).run();
    }

    @Override
    public void run() {
        logger.info("============ DolcegabbanaCrawler4Hk Crawler start=============");
        urls.add("https://store.dolcegabbana.com/zh/");
//        urls.add("https://us.dolcegabbana.com/fr/femme/nouveautes/robe-bustier-en-tulle-rose-F68A5TFLEAAF0372.html?cgid=newin-women#HP_BAN=BAN2_171205_NEWIN_W&start=1");
        spider = Spider.create(new DolcegabbanaCrawler4CH(threadDept))
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
        webDriver = WebDriverManager.getInstall().create(3, webDriver);
        logger.info("process>>>>>" + page.getUrl().toString());
        if (urls.contains(page.getUrl().toString())) {
            //获取navs的链接
            Elements elementNavs = page.getHtml().getDocument().select("li.b-menu_category-level_3-item");
            ObjectUtil.checkNotNull(elementNavs, "elementNavs is null");
            for (Element element : elementNavs) {
                String link = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(link) && RegexUtil.checkHttp(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                    logger.info("加入到导航采集队列 " + link);
                }
                logger.info("navList  " + navList.size());
            }
        }
        //获取详情页链接
        if (navList.contains(page.getUrl().toString())) {
            Document document = WebDriverManager.getInstall().getNextPager(page, webDriver);
            Elements elementDetail = document.select("a[class=js-producttile_link b-product_image-wrapper]");
            ObjectUtil.checkNotNull(elementDetail, "elementDetail is null");
            for (Element element : elementDetail) {
                String link = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(link) && RegexUtil.checkHttp(link)) {
                    detailList.add(link);
                    page.addTargetRequest(link);
                    logger.info("加入到详情页采集队列 " + link);
                }
            }
            logger.info("detailList  " + detailList.size());
        }
        //解析详情页
        if (detailList.contains(page.getUrl().toString())) {
            logger.info("deal detail page is start");
            Document document = WebDriverManager.getInstall().getPage(page.getUrl().get(), webDriver);
//            Document document = page.getHtml().getDocument();
            String size = "";
            String ref = document.getElementsByClass("b-product_master_id").text().split("：")[1].trim();
            String desc = document.getElementsByClass("b-product_long_description").text();
            String pName = null;
            try {
                pName = document.select("meta[property=og:title]").attr("content").split("-")[0];
            } catch (Exception e) {
            }
            String engName = null;
            try {
                //获取engName
                String engUrl = document.select("link[hreflang=en]").attr("href");
                engName = new Html(HttpRequestUtil.sendGet(engUrl)).getDocument().select("meta[property=og:title]").attr("content").split("-")[0];
            } catch (Exception e) {
            }
            String color = document.getElementsByClass("js_color-description").text();
            String prize = RegexUtil.getDataByRegex("\"productPrice\":\"(.*?)\"", document.outerHtml().toString());
            String classf = RegexUtil.getDataByRegex("\"productType\":\"(.*?)\"", page.getHtml().get());

            Elements sizeEl = document.getElementsByClass("b-swatches_size-item emptyswatch");
            ObjectUtil.checkNotNull(sizeEl, "sizeEl is null");
            List<String> sizeList = new ArrayList<>();
            for (Element element : sizeEl) {
                String elsize = element.getElementsByTag("a").attr("title");
                if (!Strings.isBlank(elsize)) {
                    sizeList.add(elsize);
                }
            }
            if (sizeList.size() > 0) {
                size = Joiner.on("|").join(sizeList);
            }
            Elements imgEl = document.getElementsByClass("js-thumbnail b-product_thumbnail");
            List<String> imgList = new ArrayList<>();
            for (Element element : imgEl) {
                String json = element.getElementsByTag("img").attr("data-zoomimg");
                String img = JsonParseUtil.getString(json, "url");
                imgList.add(img);
            }
            ProductCrawler p = new ProductCrawler();
            p.setBrand("dolcegabbana");
            p.setColor(color);
            p.setEngName(engName);
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pName);
            p.setClassification(classf);
            p.setUrl(page.getUrl().toString());
            p.setLanguage("zh_cn");
            p.setRef(ref);
            p.setSize(size);
            p.setEnPrice(prize);
            p.setIntroduction(desc);
            page.putField("productCrawler", p);
            //是否重新更新类别
            page.putField(Commons.cfName, false);
            page.putField(Commons.ISDATAINPUT, false);
        }


    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .setDomain("us.dolcegabbana.com")
                .addCookie("preferredCountry", "GB")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
