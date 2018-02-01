package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.Commons;
import common.DbUtil;
import common.RegexUtil;
import componentImpl.WebDriverManager;
import core.model.ProductCrawler;
import org.apache.logging.log4j.util.Strings;
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
 * @Author: yang
 * @Date: 2017/12/12.13:58
 * @Desc: LVCrawler
 */
public class LVCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();
    //测试详细页数据
    private static final String detailReg = "https://(de|www|hk).louisvuitton.(com|cn)/(zht-hk|deu-de|eng-hk|zhs-cn|eng-gb)/(produkte|products)/.*";

    public LVCrawler(int threadDept) {
        super(threadDept);
    }

    public static void main(String[] args) {
        DbUtil.init();
        new LVCrawler(1).run();
    }

    @Override
    public void run() {
        logger.info(">>>>LVCrawler start<<<<");
//        urls.add("http://www.louisvuitton.cn/zhs-cn/homepage");
        urls.add("http://hk.louisvuitton.com/eng-hk/homepage");
        urls.add("http://de.louisvuitton.com/deu-de/homepage");
        urls.add("http://uk.louisvuitton.com/eng-gb/homepage");

        spider = Spider.create(new LVCrawler(threadDept))
                .addUrl((String[]) urls.toArray(new String[urls.size()]))
                .addPipeline(CrawlerPipeline.getInstall())
                .thread(threadDept);
        spider.start();
    }

    @Override
    public void process(Page page) {
        logger.info("开始处理url> " + page.getUrl().get());
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            //获取navs
            Elements elements = document.select("li[class=mm-block]");
            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href").trim();
                if (!Strings.isBlank(link) && RegexUtil.checkHttp(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                    logger.info("获取导航条 " + link);
                }
            }
            logger.info("navList 个数 " + navList.size());
        }
        // 获取详情页
        if (navList.contains(page.getUrl().toString())) {
            logger.info("获取详情页开始 " + page.getUrl().get());
            Document doc = null;
            try {
                //创建一个driver 超时时间设置为3
                webDriver = WebDriverManager.getInstall().create(3, webDriver);
                doc = WebDriverManager.getInstall().getNextPager(page, webDriver);
//                doc = page.getHtml().getDocument();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Elements elements = doc.getElementsByClass("product-item tagClick tagClick");
            if (elements.size() > 0) {
                for (Element element : elements) {
                    String link = element.attr("href");
                    link = RegexUtil.getDomin("https://", page.getUrl().get()) + link;
                    if (!Strings.isBlank(link) && RegexUtil.checkHttp(link)) {
                        logger.info("加入到采集队列 " + link);
                        page.addTargetRequest(link);
                        detailList.add(link);
                    }
                }
            } else {
                elements = doc.select("li[class=listing rtwproduct]");
                for (Element element : elements) {
                    String link = element.getElementsByTag("a").attr("href");
                    link = RegexUtil.getDomin("https://", page.getUrl().get()) + link;
                    if (!Strings.isBlank(link) && RegexUtil.checkHttp(link)) {
                        logger.info("加入到采集队列 " + link);
                        page.addTargetRequest(link);
                        detailList.add(link);
                    }
                }
            }

            logger.info("detailList 的个数 " + detailList.size());
        }
// || page.getUrl().regex(detailReg).match()
        if (detailList.contains(page.getUrl().toString())) {
            logger.info("开始处理详情页" + page.getUrl().get());
//            WebDriverManager.getInstall().quit(webDriver);
            String pname = document.getElementsByClass("productName title").text();
            String ref = document.select("div[class=sku reading-and-link-text]").text();
            if (Strings.isBlank(ref)) {
                ref = RegexUtil.getDataByRegex("DEFAULT_SKU = '(.*?)';", page.getHtml().get());
            }
            //priceValue price-sheet
            String prize = document.select("td[class=priceValue price-sheet]").text();
            if (Strings.isNotBlank(prize) && (!page.getUrl().toString().contains("deu-de") || !page.getUrl().get().contains("eng-gb"))) {
                prize = prize.replace(",", "");
            }
            String desc = null;
            try {
                desc = document.select("div[class=productDescription description-push-text onlyML ppTextDescription]").first().text();
            } catch (Exception e) {
            }
            String classification = null;

            List<String> imgLs = new ArrayList<>();
            Elements elements = document.select("ul[class=bigs]").first().getElementsByTag("li");
            try {
                classification = elements.first().getElementsByTag("img").attr("alt").split("\\|")[0].split("-")[2];
            } catch (Exception e) {
                classification = document.select("meta[name=page_name]").attr("content");
                classification = dealClassification(classification);
            }
            for (Element element : elements) {
                String img = element.getElementsByTag("img").attr("data-src").split("\\?")[0];
                if (!Strings.isBlank(img)) {
                    imgLs.add(img);
                }
            }
            ProductCrawler p = new ProductCrawler();
            p.setBrand("louisvuitton");
            p.setClassification(classification);
            p.setImg(Joiner.on("|").join(imgLs));
            p.setName(pname);
            p.setUrl(page.getUrl().toString());
            p.setRef(ref);
            if (page.getUrl().toString().contains("www.louisvuitton.cn/zhs-cn")) {
                p.setPrice(prize);
                p.setLanguage("zh_cn");
            }
            if (page.getUrl().toString().contains("hk.louisvuitton")) {
                p.setHkPrice(prize);
                p.setEngName(pname);
                p.setLanguage("eng-hk");
            }
            if (page.getUrl().toString().contains("de.louisvuitton")) {
                p.setEurPrice(prize.replace(".", "").replace(",", "."));
                p.setEngName(pname);
                p.setLanguage("de-de");
            }
            if (page.getUrl().toString().contains("uk.louisvuitton.com")) {
                p.setEnPrice(prize);
                p.setLanguage("eng-gb");
                p.setEngName(pname);
            }
            p.setIntroduction(desc);
            //是否重新更新英文名
            page.putField(Commons.EngName, true);
            page.putField(Commons.cwJob, p);
        }

    }

    //处理类别
    private String dealClassification(String classification) {

        if (Strings.isNotBlank(classification)) {

            if (classification.equals("women/personalization/mon-monogram")) {
                return "LV女士臻礼之选";
            }

        }
        return "";
    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.louisvuitton.cn")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }

}
