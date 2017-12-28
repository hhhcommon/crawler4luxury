package download;

import absCompone.DriverComponent;
import componentImpl.WebDriverComponent;
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

    private boolean isNotOnlyGetDoc = true;

    public SeleniumDownloader(WebDriver webDriver, DriverComponent driverComponent, List<String> text) {
        this.webDriver = webDriver;
        this.driverComponent = driverComponent;
        this.text = text;
    }

    public SeleniumDownloader(WebDriver webDriver, DriverComponent driverComponent, Boolean isNotOnlyGetDoc) {
        this.webDriver = webDriver;
        this.driverComponent = driverComponent;
        this.isNotOnlyGetDoc = isNotOnlyGetDoc;
    }

    @Override
    public Page download(Request request, Task task) {

        Page page = null;
        Document doc;
        try {
            if (isNotOnlyGetDoc) {
                doc = driverComponent.getNextPager(request.getUrl().toString(), webDriver, text);
            } else {
                doc = driverComponent.getPage(request.getUrl().toString(), webDriver);
            }
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

}
