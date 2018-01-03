package main;

import base.BaseCrawler;
import common.DbUtil;
import absCompone.Component;
import crawler.*;
import componentImpl.CrawlerComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:17
 * @desc 运行主入口
 */


public class RunMain {
    public static void main(String[] args) {
        DbUtil.init();
        Component component = CrawlerComponent.create("《《《《【luxury job start】》》》》");
        List<BaseCrawler> crawlerList = new ArrayList<>();
//        crawlerList.add(new LVCrawler(1));
//        crawlerList.add(new GucciCrawler(1));
        crawlerList.add(new DolcegabbanaCrawler4GB(1));
        crawlerList.add(new DolcegabbanaCrawler4DE(1));
        component.AddJob(crawlerList)
                .run();
    }
}
