package pipeline;

import accessor.IAccessor;
import common.ImageDownloadUtil;
import model.SinaEntity;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import util.ClientFactoryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @Author cyy
 * @Date 2017/12/4 14:32
 * @Description 存储pipeline
 */

public class SinaPipeline implements Pipeline {

    private static List<String> temp = new ArrayList<>();
    private static Logger logger = Logger.getLogger(String.valueOf(SinaPipeline.class));
    // 构造链接属性
    private static List list = new ArrayList<String>(Arrays.asList("127.0.0.1"));
    private IAccessor accessor = new ClientFactoryBuilder.builder().setCLUSTER_NAME("elasticsearch")
            .setCLIENT_PORT(9300)
            .setHOSTS(list)
            .setINIT(false)
            .create();

    @Override
    public void process(ResultItems resultItems, Task task) {
        if (resultItems.get("title") != null) {
            if (temp.contains(resultItems.get("title"))) return;
            temp.add(resultItems.get("title"));
            //下载图片
            String fileName = UUID.randomUUID() + ".jpg";
            try {
                ImageDownloadUtil.download(resultItems.get("pic").toString(), fileName, "/pic/data");
            } catch (Exception e) {
                e.printStackTrace();
            }
            SinaEntity sinaEntity = new SinaEntity();
            sinaEntity.setId(Integer.parseInt(resultItems.get("id")));
            sinaEntity.setTitle(resultItems.get("title"));
            sinaEntity.setPic(resultItems.get("pic"));
            sinaEntity.setFileName(fileName);
            boolean b = accessor.add(sinaEntity);
            if (b) {
                logger.info("==========添加成功=========");
            }
        }

    }
}
