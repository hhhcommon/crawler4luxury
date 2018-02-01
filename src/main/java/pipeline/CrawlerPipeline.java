package pipeline;

import common.Commons;
import common.RegexUtil;
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
    //是否重新更新类别
    private static boolean isCf = false;
    //是否重新更新img
    private static boolean isImg = false;
    //是否重新tag
    private static boolean isTag = false;
    //是否重新更新ref
    private static boolean isRef = false;
    //是否重新更新英文名
    private static boolean isEngName = false;
    //是否不采集入库
    private static boolean isDataInput = false;

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
        ProductCrawler productCrawler = resultItems.get(Commons.cwJob);
        //初始化 参数
        initMoudle(resultItems);
        List<ProductCrawler> productCrawlerList = resultItems.get(Commons.cwJobList);
        if (productCrawler != null) {
            this.showProductInfo(productCrawler);
            this.dataInput(productCrawler);
        } else if (Objects.nonNull(productCrawlerList)) {
            for (ProductCrawler p : productCrawlerList) {
                this.dataInput(p);
            }
        }
    }

    private void initMoudle(ResultItems resultItems) {
        Boolean isCf = resultItems.get(Commons.cfName);
        Boolean isimg = resultItems.get(Commons.imgName);
        Boolean istag = resultItems.get(Commons.tagName);
        Boolean isref = resultItems.get(Commons.REfName);
        Boolean isengName = resultItems.get(Commons.EngName);
        Boolean isDataInput = resultItems.get(Commons.ISDATAINPUT);
        if (!Objects.isNull(isCf)) {
            this.isCf = isCf;
        }
        if (!Objects.isNull(isimg)) {
            this.isImg = isimg;
        }
        if (!Objects.isNull(istag)) {
            this.isTag = istag;
        }
        if (!Objects.isNull(isref)) {
            this.isRef = isref;
        }
        if (!Objects.isNull(isengName)) {
            this.isEngName = isengName;
        }
        if (!Objects.isNull(isDataInput)) {
            this.isDataInput = isDataInput;
        }

    }

    private void showProductInfo(ProductCrawler productCrawler) {
        System.out.println("==================================================");
        System.out.println("【商品品牌名】" + productCrawler.getBrand());
        System.out.println("【商品URL】" + productCrawler.getUrl());
        System.out.println("【商品中文名】" + productCrawler.getName());
        System.out.println("【商品英文名】" + productCrawler.getEngName());
        System.out.println("【商品SKU】" + productCrawler.getRef());
        System.out.println("【商品中国价格】" + productCrawler.getPrice());
        System.out.println("【商品香港价格】" + productCrawler.getHkPrice());
        System.out.println("【商品英国价格】" + productCrawler.getEnPrice());
        System.out.println("【商品德国价格】" + productCrawler.getEurPrice());
        System.out.println("【商品类别】" + productCrawler.getClassification());
        System.out.println("【商品Tag】" + productCrawler.getTags());
        System.out.println("【商品简介】" + productCrawler.getIntroduction());
        System.out.println("==================================================");
    }


    private void dataInput(ProductCrawler productCrawler) {
        if (Strings.isBlank(productCrawler.getRef())) {
            logger.info("suk 唯一码 未知");
            return;
        }
        ProductCrawler productCrawlerFromDb = ProductCrawler.dao.findByCode(productCrawler.getRef().trim(), productCrawler.getBrand());
        if (productCrawlerFromDb == null) {
            if (isDataInput) {
                logger.info("忽略入库【" + productCrawler.getName() + "】");
                return;
            }
            //处理下html标签
            if (!com.google.common.base.Strings.isNullOrEmpty(productCrawler.getName())) {
                productCrawler.setName(RegexUtil.delHTMLTag(productCrawler.getName()));
            }
            if (!com.google.common.base.Strings.isNullOrEmpty(productCrawler.getEngName())) {
                productCrawler.setEngName(RegexUtil.delHTMLTag(productCrawler.getEngName()));
            }
            if (!com.google.common.base.Strings.isNullOrEmpty(productCrawler.getBrand())) {
                productCrawler.setBrand(RegexUtil.delHTMLTag(productCrawler.getBrand()));
            }
            if (!com.google.common.base.Strings.isNullOrEmpty(productCrawler.getClassification())) {
                productCrawler.setClassification(RegexUtil.delHTMLTag(productCrawler.getClassification()));
            }
            if (!com.google.common.base.Strings.isNullOrEmpty(productCrawler.getIntroduction())) {
                productCrawler.setIntroduction(RegexUtil.delHTMLTag(productCrawler.getIntroduction()));
            }
            //没有该商品 添加
            productCrawler.setRef(productCrawler.getRef().trim());
            boolean b = productCrawler.save();
            if (b) {
                logger.info("成功采集【" + productCrawler.getName().trim() + "】");
            }
        } else {

            //更新价格
            if (!Strings.isBlank(productCrawler.getPrice()) && !Objects.isNull(productCrawler.getPrice())) {
                productCrawlerFromDb.setPrice(productCrawler.getPrice());
            }
            if (!Strings.isBlank(productCrawler.getHkPrice()) && !Objects.isNull(productCrawler.getHkPrice())) {
                productCrawlerFromDb.setHkPrice(productCrawler.getHkPrice());
            }
            if (!Strings.isBlank(productCrawler.getEnPrice()) && !Objects.isNull(productCrawler.getEnPrice())) {
                productCrawlerFromDb.setEnPrice(productCrawler.getEnPrice());
            }
            if (!Strings.isBlank(productCrawler.getEurPrice()) && !Objects.isNull(productCrawler.getEurPrice())) {
                productCrawlerFromDb.setEurPrice(productCrawler.getEurPrice());
            }
            //更新类别
            if (isCf) {
                if (!Strings.isBlank(productCrawler.getClassification())) {
                    productCrawlerFromDb.setClassification(productCrawler.getClassification());
                }
            }
            //更新图片
            if (isImg) {
                if (!Strings.isBlank(productCrawler.getImg())) {
                    productCrawlerFromDb.setImg(productCrawler.getImg());
                }
            }
            //更新tag
            if (isTag) {
                if (!Strings.isBlank(productCrawler.getTags())) {
                    productCrawlerFromDb.setTags(productCrawler.getTags());
                }
            }
//            //更新ref
            if (isRef) {
                if (!Strings.isBlank(productCrawler.getRef())) {
                    productCrawlerFromDb.setRef(productCrawler.getRef());
                }
            }
            if (isEngName) {
                //更新英文名
                if (!com.google.common.base.Strings.isNullOrEmpty(productCrawler.getEngName())) {
                    productCrawlerFromDb.setEngName(RegexUtil.delHTMLTag(productCrawler.getEngName()));
                }
            }

            //updata
            boolean b = productCrawlerFromDb.update();
            if (b) {
                logger.info("更新【" + productCrawlerFromDb.getName() + "】成功！！！");
            }
        }
    }
}
