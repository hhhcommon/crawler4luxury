package scheduler;

import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/2.22:04
 */
public class CrawlerRedisScheduler extends us.codecraft.webmagic.scheduler.RedisScheduler {

    public CrawlerRedisScheduler(String host) {
        super(host);
    }
}
