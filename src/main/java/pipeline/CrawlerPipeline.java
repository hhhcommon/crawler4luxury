package pipeline;

import common.WebDriverPool;
import core.model.ProductCrawler;
import org.apache.logging.log4j.util.Strings;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @Author: yang
 * @Date: 2017/12/4.17:58
 * @Desc: CrawlerPipeline 存储数据
 */
public class CrawlerPipeline implements Pipeline {

    private static Logger logger = Logger.getLogger(String.valueOf(CrawlerPipeline.class));

    private static CrawlerPipeline instance;

    private CrawlerPipeline() {
    }

    public static CrawlerPipeline getInstall() {
        if (instance == null) {                         //Single Checked
            synchronized (WebDriverPool.class) {
                if (instance == null) {                 //Double Checked
                    instance = new CrawlerPipeline();
                }
            }
        }
        return instance;
    }


    @Override
    public void process(ResultItems resultItems, Task task) {
        ProductCrawler productCrawler = resultItems.get("productCrawler");
        List<ProductCrawler> productCrawlerList = resultItems.get("productCrawlerList");
        if (productCrawler != null && !"".equals(productCrawler.getName())) {
            this.dataInput(productCrawler);
        }

        if (Objects.nonNull(productCrawlerList)) {

            for (ProductCrawler p : productCrawlerList) {
                this.dataInput(p);
            }
        }
    }


    private void dataInput(ProductCrawler productCrawler) {
        ProductCrawler productCrawlerFromDb = ProductCrawler.dao.findByCode(productCrawler.getRef());
        if (productCrawlerFromDb == null) {
            //没有该商品 添加
            boolean b = productCrawler.save();
            if (b) {
                logger.info("成功采集【" + productCrawler.getName().trim() + "】");
            }
        } else {
//                //更新价格
            if (!Strings.isBlank(productCrawler.getPrice())) {
                productCrawlerFromDb.setPrice(productCrawler.getPrice());
            }
            if (!Strings.isBlank(productCrawler.getHkPrice())) {
                productCrawlerFromDb.setHkPrice(productCrawler.getHkPrice());
            }
            if (!Strings.isBlank(productCrawler.getEnPrice())) {
                productCrawlerFromDb.setEnPrice(productCrawler.getEnPrice());
            }
            if (!Strings.isBlank(productCrawler.getEurPrice())) {
                productCrawlerFromDb.setEurPrice(productCrawler.getEurPrice());
            }
            //updata
            boolean b = productCrawlerFromDb.update();
            if (b) {
                logger.info("更新【" + productCrawlerFromDb.getName() + "】成功！！！");
            }
        }
    }
}
