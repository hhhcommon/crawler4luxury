package common;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import core.model._MappingKit;

/**
 * @Author: yang
 * @Date: 2017/12/4.18:35
 * @Desc: 数据库工具类
 */
public class DbUtil {


    /**
     * 初始化数据层
     */
    public static void init() {
        PropKit.use("a_little_config.txt");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        _MappingKit.mapping(arp);
        // 与web环境唯一的不同是要手动调用一次相关插件的start()方法
        dp.start();
        arp.start();
    }
}
