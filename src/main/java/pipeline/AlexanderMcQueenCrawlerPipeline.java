package pipeline;

import core.model.Product;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author: yang
 * @Date: 2017/12/4.17:58
 * @Desc: AlexanderMcQueenCrawlerPipeline 存储数据
 */
public class AlexanderMcQueenCrawlerPipeline implements Pipeline {


    @Override
    public void process(ResultItems resultItems, Task task) {
        Product product = (Product) resultItems.get("product");
        if (product != null && !"".equals(product.getName())) {


        }
    }
}
