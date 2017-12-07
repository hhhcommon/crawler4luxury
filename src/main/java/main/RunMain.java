package main;

import common.DbUtil;
import compone.Component;
import crawler.BurberryCrawler;
import crawler.DiorCrawler;
import crawler.SinaCrawler;
import crawler.AlexanderMcQueenCrawler;
import factory.CrawlerComponent;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:17
 * @desc 测试
 */


public class RunMain {

    public static void main(String[] args) {
        DbUtil.init();
        Component component = CrawlerComponent.create("luxury job");
        component.AddJob(new DiorCrawler(1))
                .setInterval(60 * 60 * 24)
                .run();
    }

}
