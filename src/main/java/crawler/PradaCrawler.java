package crawler;

import absCompone.BaseCrawler;
import com.google.common.base.Joiner;
import common.DbUtil;
import core.model.ProductCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pipeline.CrawlerPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author: yang
 * @Date: 2017/12/13.16:53
 * @Desc: PradaCrawler  都没有价格
 */
public class PradaCrawler extends BaseCrawler {
    private static ArrayList urls = new ArrayList();

    public static void main(String[] args) {
        DbUtil.init();
        new PradaCrawler(1).run();
    }

    public PradaCrawler(int threadDept) {
        super(threadDept);
    }

    @Override
    public void run() {
        logger.info(">>>>PradaCrawler start<<<<");
        urls.add("https://www.prada.com/cn/zh/man.html");
        urls.add("https://www.prada.com/cn/zh/woman.html");
        spider = Spider.create(new PradaCrawler(threadDept))
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

        Document document = page.getHtml().getDocument();
        //获取导航
        if (urls.contains(page.getUrl().toString())) {
            Elements elementsNav = document.select("div[class=col-xs-3 sub-link-group] ul li");
            if (Objects.nonNull(elementsNav)) {
                for (Element element : elementsNav) {
                    String navLink = element.getElementsByTag("a").attr("href");
                    navLink = "https://www.prada.com" + navLink;
                    logger.info("加入到采集队列 " + navLink);
                    navList.add(navLink);
                }
                //加入到采集队列
                page.addTargetRequests(navList);


            }


        }
        //获取详情页地址  https://www.prada.com/cn/zh/woman/bags/shoulder_bags/jcr:content/par/product-grid.4.sortBy_0.html
        if (navList.contains(page.getUrl().toString())) {
            //构造一个request url
            String temp = page.getUrl().toString().substring(0, page.getUrl().toString().length() - 5);
            for (int i = 1; i <= 100; i++) {
                String pagerLink = temp + "/jcr:content/par/product-grid." + i + ".sortBy_0.html";
                try {
                    URL url = new URL(pagerLink);
                    Document doc = Jsoup.parse(url, 5000);

                    Elements elementsDetails = doc.select("div[class=product-details]");

                    if (Objects.nonNull(elementsDetails)) {

                        for (Element element : elementsDetails) {

                            try {
                                String dtlink = element.getElementsByTag("a").first().attr("href");
                                dtlink = "https://www.prada.com" + dtlink;
                                detailList.add(dtlink);
                                logger.info("加入到采集队列 " + dtlink);
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }

                        }
                        if (Objects.nonNull(detailList)) {
                            page.addTargetRequests(detailList);
                        }
                    }

                } catch (MalformedURLException e) {
                    break;
                } catch (IOException e) {
                    break;
                }


            }


        }
        /**
         * 解析 终极页
         */
        if (detailList.contains(page.getUrl().toString())) {

            String pname = document.select("div[class=hidden-xs hidden-sm col-xs-12 col-sm-12 col-md-12 col-lg-12 pdp-name] h1").text();

            String Introduction = document.select("div[class=col-md-3 display-tcel text-material flex-start]").text();
            String num = null;
            try {
                num = document.select("div[class=hidden-xs hidden-sm col-xs-12 col-sm-12 col-md-12 col-lg-12 pdp-name] p").first().text();
            } catch (Exception e) {
            }
            String priceInfo = document.select("p[class=current-price]").text();
            String mate = document.select("div[class=pdp-tab-longdesc]").text();
            ProductCrawler p = new ProductCrawler();
            p.setBrand("prada");
            Elements img = document.select("div[class=row pdp-editorial-2 box-spacer-small hidden-sm hidden-xs] img");
            ArrayList list = new ArrayList();
            if (!Objects.isNull(img)) {
                for (Element element : img) {
                    list.add(element.attr("data-src"));
                }
                p.setImg(Joiner.on("|").join(list));
            }
            p.setName(pname);
            p.setMaterial(mate);
            p.setUrl(page.getUrl().toString());
            p.setPrice(priceInfo);
            p.setLanguage("zh_CN");
            p.setIntroduction(Introduction);
            p.setRef(num);
            page.putField("product", p);
        }


    }

    @Override
    public Site getSite() {
        site = Site.me()
                .setDomain("www.prada.com")
                .setRetryTimes(3)
                .setTimeOut(5000)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.9 Safari/537.36");
        return site;
    }
}
