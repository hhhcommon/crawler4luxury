package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import common.DbUtil;
import common.JsonParseUtil;
import core.model.Product;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/7.17:23
 * @Desc: DolcegabbanaCrawler4Hk
 */
public class DolcegabbanaCrawler4DE extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public DolcegabbanaCrawler4DE(int threadDept) {
        super(threadDept);
    }


    public static void main(String[] args) {

        DbUtil.init();
        new DolcegabbanaCrawler4DE(1).run();
    }

    @Override
    public void run() {
        logger.info("============ DolcegabbanaCrawler4Hk Crawler start=============");
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("127.0.0.1", 1080, "", "")));
        urls.add("https://store.dolcegabbana.com/zh/");
//        urls.add("https://us.dolcegabbana.com/fr/femme/nouveautes/robe-bustier-en-tulle-rose-F68A5TFLEAAF0372.html?cgid=newin-women#HP_BAN=BAN2_171205_NEWIN_W&start=1");
        spider = Spider.create(new DolcegabbanaCrawler4DE(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(new CrawlerPipeline())
                .thread(threadDept);
        spider.setDownloader(httpClientDownloader);
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info("process>>>>>" + page.getUrl().toString());
        if (urls.contains(page.getUrl().toString())) {
            //获取navs的链接
            Elements elementNavs = page.getHtml().getDocument().select("li.b-menu_category-level_3-item");
            ObjectUtil.checkNotNull(elementNavs, "elementNavs is null");
            for (Element element : elementNavs) {
                String link = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }
        }
        //获取详情页链接
        if (navList.contains(page.getUrl().toString())) {
            Elements elementDetail = page.getHtml().getDocument().getElementsByClass("js-producttile_link b-product_image-wrapper");
            ObjectUtil.checkNotNull(elementDetail, "elementDetail is null");
            for (Element element : elementDetail) {
                String link = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(link)) {
                    detailList.add(link);
                    page.addTargetRequest(link);
                    logger.info("detail url is added>>>>" + link);
                }
            }
        }
        //解析详情页
        if (detailList.contains(page.getUrl().toString())) {
            logger.info("deal detail page is start");
            Document document = page.getHtml().getDocument();
            String size = "";
            String ref = document.getElementsByClass("b-product_master_id").text().split("：")[1].trim();
            String desc = document.getElementsByClass("b-product_long_description").text();
            String pName = document.getElementsByClass("b-product_name").text();
            String prize = document.getElementsByClass("b-product_price").text();
            String color = document.getElementsByClass("js_color-description").text();
            Elements classification = document.getElementsByClass("b-breadcrumb-link js-breadcrumb_refinement-link");
            List<String> claList = new ArrayList<>();
            for (Element e : classification) {
                String cla = e.text();
                claList.add(cla);
            }
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
            Product p = new Product();
            p.setBrand("dolcegabbana");
            p.setColor(color);
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pName);
            p.setClassification(Joiner.on("|").join(claList));
            p.setUrl(page.getUrl().toString());
            p.setLanguage("zh_cn");
            p.setRef(ref);
            p.setSize(size);
            p.setEurPrice(prize);
            p.setIntroduction(desc);
            page.putField("product", p);
        }


    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .setDomain("us.dolcegabbana.com")
                .addCookie("preferredCountry", "DE")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36")
                .setSleepTime(3000);
        return site;
    }
}
