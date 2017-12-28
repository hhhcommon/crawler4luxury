package absCompone;

import base.BaseCrawler;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:06
 */
public abstract class Component {
    protected String jobName;
    protected long delay = 1;
    protected long interval;
    protected static Logger logger = Logger.getLogger(String.valueOf(Component.class));

    public Component(String jobName) {
        this.jobName = jobName;
        logger.info("=========jobName===" + jobName + "===start=========");
    }

    /**
     * 添加一个爬虫任务
     *
     * @param c
     */
    public abstract Component AddJob(BaseCrawler c);

    /**
     * 添加一个爬虫list任务
     *
     * @param list
     */
    public abstract Component AddJob(List<BaseCrawler> list);

    /**
     * 移除一个爬虫任务
     *
     * @param c
     */
    public abstract Component RemoveJob(BaseCrawler c);

    /**
     * 执行爬虫任务
     */
    protected abstract void RunCrawler(long delay,
                                       long interval);

    /**
     * 执行爬虫任务
     */
    protected abstract void RunCrawler();

    public abstract void AddStopJob(BaseCrawler c);

    public abstract void stopJob();

    public abstract void run();

    public abstract Component setDelay(long delay);

    public abstract Component setInterval(long interval);
}
