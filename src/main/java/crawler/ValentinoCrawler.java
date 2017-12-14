package crawler;

import base.BaseCrawler;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import common.HttpRequestUtil;
import common.RegexUtil;
import core.model.Product;
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
 * @Date: 2017/12/13.17:03
 * @Desc: ValentinoCrawler
 */
public class ValentinoCrawler extends BaseCrawler {


    public ValentinoCrawler(int threadDept) {
        super(threadDept);
    }

    @Override
    public void run() {
        logger.info(">>>>ValentinoCrawler start<<<<");
        urls.add("https://www.valentino.cn/cn");
//        urls.add("http://uk.louisvuitton.com/eng-gb/homepage");
        spider = Spider.create(new ValentinoCrawler(threadDept))
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
        logger.info("process start>>>>" + page.getUrl().toString());
        Document document = page.getHtml().getDocument();
        if (urls.contains(page.getUrl().toString())) {
            Elements elements = document.select("li[class=hasChildren categories menuItem] ul li");

            for (Element element : elements) {
                String link = element.getElementsByTag("a").attr("href").trim();

                if (!Strings.isNullOrEmpty(link)) {
                    navList.add(link);
                    page.addTargetRequest(link);
                }
            }

        }

        if (navList.contains(page.getUrl().toString())) {
            logger.info("nav>>>>" + page.getUrl().toString());

            Document document1 = getNextPager(page, "加载更多");
            Elements elements = document1.select("article[class=search-item   ]");
            for (Element element : elements) {
                String detailLink = element.getElementsByTag("a").attr("href");
                if (!Strings.isNullOrEmpty(detailLink)) {
                    page.addTargetRequest(detailLink);
                    detailList.add(detailLink);
                }
            }


        }

        if (detailList.contains(page.getUrl().toString())) {
            logger.info("detail>>>" + page.getUrl().toString());
            String pname = null;
            String dePri = null;
            try {
                pname = document.select("div[class=attributesUpdater title ]").text().split(" ")[0];
            } catch (Exception e) {
            }
            String classification = document.select("ul[class=level-2]").first().getElementsByTag("li").first().select("span[class=text]").first().text();
            String ref = document.select("div[class=modelfabricolor]").text();
            String price = null;
            if (page.getUrl().toString().contains("https://www.valentino.cn/cn")) {
                price = document.select("span[class=price]").eq(1).select("span[class=value]").text();
            }
            String color = document.select("ul[class=hasOneSelection] div[class=description]").text();
            Elements size = document.select("div[class=selectSize sizeWSelection dropdown] ul li");
            List<String> sizeLs = new ArrayList<>();
            for (Element element : size) {
                String sizeTxt = element.text();
                if (!Strings.isNullOrEmpty(sizeTxt)) {
                    sizeLs.add(sizeTxt);
                }
            }
            String desc = document.select("div[class=editorial]").text();

            Elements elementsImg = document.select("ul[class=alternativeImages] img");
            List<String> imgList = new ArrayList<>();
            for (Element element : elementsImg) {
                String img = element.attr("src").trim();
                if (!Strings.isNullOrEmpty(img)) {
                    imgList.add(img);
                }
            }

            try {
                // 德国价格
                String deUrl = document.select("link[hreflang=en-BE]").attr("href");
                if (deUrl != null) {
                    dePri = HttpRequestUtil.sendGet(deUrl);
                    Html html = new Html(dePri);
                    dePri = html.getDocument().select("span[class=price]").eq(1).select("span[class=value]").text();
                }
            } catch (Exception e) {
                logger.info("请求德国网址出错 错误原因" + e.toString());
            }
            String hkPri = null;
            try {
                // 香港价格
                String hkUrl = document.select("link[hreflang=en-HK]").attr("href");
                hkPri = "";
                if (hkUrl != null) {
                    hkPri = HttpRequestUtil.sendGet(hkUrl);
                    Html html = new Html(hkPri);
                    hkPri = html.getDocument().select("span[class=price]").eq(1).select("span[class=value]").text();
                }
            } catch (Exception e) {
                logger.info("请求香港网址出错 错误原因" + e.toString());
            }
            String enPri = null;

            try {
                // 英国价格
                String enUrl = document.select("link[hreflang=en-GB]").attr("href");
                enPri = "";
                if (enUrl != null) {
                    enPri = HttpRequestUtil.sendGet(enUrl);
                    Html html = new Html(enPri);
                    enPri = html.getDocument().select("span[class=price]").eq(1).select("span[class=value]").text();
                }
            } catch (Exception e) {
                logger.info("请求英国网址出错 错误原因" + e.toString());
            }
            Product p = new Product();
            p.setBrand("valentino");
            p.setClassification(classification);
            p.setImg(Joiner.on("|").join(imgList));
            p.setName(pname);
            p.setColor(color);
            p.setSize(Joiner.on("|").join(sizeLs));
            p.setUrl(page.getUrl().toString());
            p.setLanguage("zh_CN");
            p.setPrice(price);
            p.setHkPrice(hkPri);
            p.setEnPrice(enPri);
            p.setEurPrice(dePri);
            p.setIntroduction(desc);
            p.setRef(ref.trim());
            page.putField("product", p);
        }

    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.valentino.cn")
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                .setRetryTimes(3);

        return site;
    }

    public static void main(String[] args) {
        new ValentinoCrawler(1).run();
    }
}
