package main;

import compone.Component;
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
        Component component = CrawlerComponent.create("mmpic job");
        component.AddJob(new AlexanderMcQueenCrawler())
//                .setInterval(60 * 60 * 24)
                .run();
    }

}
