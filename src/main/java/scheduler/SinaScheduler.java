package scheduler;

import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/2.22:04
 */
public class SinaScheduler extends RedisScheduler {
    public SinaScheduler(String host) {
        super(host);
    }

    public SinaScheduler(JedisPool pool) {
        super(pool);
        setDuplicateRemover(this);
    }
}
