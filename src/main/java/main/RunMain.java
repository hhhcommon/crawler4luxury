package main;

import compone.Component;
import crawler.SinaCrawler;
import crawler.SinaCrawler2;
import factory.CrawlerComponent;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:17
 * @desc 测试
 */


public class RunMain {

    public static void main(String[] args) {
        Component component = CrawlerComponent.create("mmpic job");
        component.AddJob(new SinaCrawler())
                .AddJob(new SinaCrawler2())
                .setInterval(60 * 60 * 24)
                .run();
    }

}
