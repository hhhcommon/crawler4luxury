package download;

import compone.DriverComponent;
import factory.WebDriverComponent;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

import java.util.List;

/**
 * @Author: yang
 * @Date: 2017/12/25.14:41
 * @Desc: to do?
 */
public class SeleniumDownloader implements Downloader {

    public WebDriver webDriver;

    public DriverComponent driverComponent;

    private List<String> text;

    public SeleniumDownloader(WebDriver webDriver, DriverComponent driverComponent, List<String> text) {
        this.webDriver = webDriver;
        this.driverComponent = driverComponent;
        this.text = text;
    }


    @Override
    public Page download(Request request, Task task) {

        Page page = null;
        try {
            Document doc = driverComponent.getNextPager(request.getUrl().toString(), webDriver, text);
            page = new Page();
            page.setRawText(doc.outerHtml());
            page.setHtml(new Html(doc.outerHtml(), request.getUrl()));
            page.setUrl(new PlainText(request.getUrl()));
            page.setRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public void setThread(int i) {

    }

    /**
     * 初始化
     */
    public void init() {
        //初始化的时候初始化 webdriver
        if (driverComponent == null) {

            driverComponent = new WebDriverComponent();
        }
        if (webDriver == null) {
            //创建一个driver 超时时间设置为3s
            webDriver = driverComponent.create(3);
        }
    }
}
