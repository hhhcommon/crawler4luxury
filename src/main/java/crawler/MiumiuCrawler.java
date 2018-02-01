package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
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
import java.util.Objects;

/**
 * @Author: yang
 * @Date: 2017/12/13.11:23
 * @Desc: to MiumiuCrawler   需要用sem 去爬取 未完成
 */
public class MiumiuCrawler extends BaseCrawler {
    /**
     * urls
     */
    protected static List<String> urls = new ArrayList<>();

    public MiumiuCrawler(int threadDept) {
        super(threadDept);
    }

    @Override
    public void run() {
        logger.info(">>>>MiumiuCrawler start<<<<");
        urls.add("https://store.miumiu.com/zh-CN/miumiuww");
        urls.add("https://store.miumiu.com/en/miumiude");
        urls.add("https://store.miumiu.com/en/miumiugb");
//        urls.add("http://uk.louisvuitton.com/eng-gb/homepage");
        spider = Spider.create(new MiumiuCrawler(threadDept))
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
        //创建一个driver 超时时间设置为3s
        webDriver = WebDriverManager.getInstall().create(3, webDriver);
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            Elements elementsNav = document.getElementsByClass("lv0");
            for (Element element : elementsNav) {
                Elements elements = element.getElementsByTag("li");
                for (Element element1 : elements) {
                    String link = element1.getElementsByTag("a").attr("href");
                    if (!Strings.isBlank(link)) {
                        logger.info("加入到采集队列" + link);
                        navList.add(link);
                        page.addTargetRequest(link);
                    }
                }

            }

        }
        /**
         * navs
         */
        if (navList.contains(page.getUrl().toString())) {
            Document document1 = WebDriverManager.getInstall().getNextPager(page, webDriver, "查看更多");
            Elements elements = document1.select("div[class=nextItem discount col-lg-3 col-md-3 col-sm-4 col-xs-6]");
            for (Element element : elements) {
                String detailLink = element.getElementsByTag("a").attr("href");
                if (!Strings.isBlank(detailLink)) {
                    logger.info("加入到采集队列" + detailLink);
                    page.addTargetRequest(detailLink);
                    detailList.add(detailLink);
                }
            }
        }

        /**
         * 处理 详情页
         */
        if (detailList.contains(page.getUrl().toString())) {
            WebDriverManager.getInstall().destoty(webDriver);
            String pname = document.select("span[class=nameProduct]").text();
            String classification = document.select("li[class=lv1 selected]").text();
            String Introduction = document.select("div[class=descriptionTab descriptionContent]").text();
            String num = document.select("div[class=yourSelectionCode]").text();
            String priceInfo = document.select("span[class=price]").text();
            List<String> color = new ArrayList<>();
            Elements elementsColor = document.select("a[class=mobileOtherColorsItems]");
            for (Element element : elementsColor) {
                String colors = element.getElementsByTag("img").attr("alt");
                if (!Strings.isBlank(colors)) {
                    color.add(colors);
                }

            }
            ProductCrawler p = new ProductCrawler();
            p.setBrand("miumiu");
            p.setClassification(classification);
            List<String> img = RegexUtil.matchGroup("\"image_\\d+\" : \"(.*?)\"", page.getHtml().toString());
            if (!Objects.isNull(img)) {
                p.setImg(Joiner.on("|").join(img));
            }
            p.setName(pname);
            p.setColor(Joiner.on("|").join(color));
            p.setUrl(page.getUrl().toString());

            if (page.getUrl().toString().contains("zh-CN/miumiuww")) {
                p.setPrice(priceInfo);
                p.setLanguage("zh_CN");
            }
            if (page.getUrl().toString().contains("en/miumiude")) {
                p.setEurPrice(priceInfo);
                p.setLanguage("en/DE");
            }
            if (page.getUrl().toString().contains("en/miumiugb")) {
                p.setEnPrice(priceInfo);
                p.setLanguage("en/GB");
            }
            p.setIntroduction(Introduction);
            p.setRef(num);
            page.putField("product", p);
        }


    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.miumiu.com")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }

    public static void main(String[] args) {
        DbUtil.init();
        new MiumiuCrawler(1).run();
    }
}
