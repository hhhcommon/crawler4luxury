package componentImpl;

import absCompone.Component;
import base.BaseCrawler;
import io.netty.util.internal.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:08
 * @desc: 爬虫组件 用于增加爬虫 删除爬虫 运行爬虫
 */
public class CrawlerComponent extends Component {
    /**
     * 要执行的爬虫job
     */
    private List<BaseCrawler> crawlerList = new ArrayList<BaseCrawler>();
    /**
     * 需要停止的爬虫job
     */
    private List<BaseCrawler> crawlerStopList = new ArrayList<BaseCrawler>();

    public CrawlerComponent(String jobName) {
        super(jobName);
    }


    @Override
    public Component AddJob(BaseCrawler c) {
        ObjectUtil.checkNotNull(c, "BaseCrawler");
        crawlerList.add(c);
        return this;
    }

    @Override
    public Component AddJob(List<BaseCrawler> list) {
        ObjectUtil.checkNotNull(list, "BaseCrawlerlist");
        crawlerList = list;
        return this;
    }


    @Override
    public Component RemoveJob(BaseCrawler c) {
        ObjectUtil.checkNotNull(c, "RemoveJobBaseCrawler");
        crawlerList.remove(c);
        return this;
    }

    /**
     * @param delay    延迟多少时间执行
     * @param interval 多少时间之后再次执行
     */
    @Override
    public void RunCrawler(long delay,
                           long interval) {
        if (crawlerList.size() > 0) {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(crawlerList.size());
            for (BaseCrawler crawler : crawlerList) {
                //    线程池的方式去执行多线程任务
                service.scheduleAtFixedRate(
                        crawler, delay,
                        interval, TimeUnit.SECONDS);
            }
        }
    }

    /**
     * 只执行一次
     */
    @Override
    public void RunCrawler() {
        if (crawlerList.size() > 0) {
            for (BaseCrawler crawler : crawlerList) {
                new Thread(crawler).start();
            }
        }
    }

    /**
     * 添加需要停止的爬虫
     *
     * @param c
     */
    @Override
    public void AddStopJob(BaseCrawler c) {
        crawlerStopList.add(c);
    }

    /**
     * 停止爬虫
     */
    @Override
    public void stopJob() {

        if (crawlerStopList.size() > 0) {
            for (BaseCrawler crawler : crawlerStopList) {
                crawler.stop();
            }
        }

    }

    /**
     * 延迟多久开始执行爬虫
     *
     * @param delay 秒为单位
     * @return
     */
    @Override
    public Component setDelay(long delay) {
        this.delay = delay;
        return this;
    }

    /**
     * 多久后再次执行
     *
     * @param interval 秒为单位
     * @return
     */
    @Override
    public Component setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    /**
     * 爬虫任务start
     */
    @Override
    public void run() {
        if (interval > 0) {
            this.RunCrawler(delay, interval);
        } else {
            this.RunCrawler();
        }
    }

    /**
     * 创建一个爬虫component
     *
     * @param jobName
     * @return
     */
    public static Component create(String jobName) {
        return new CrawlerComponent(jobName);
    }
}
