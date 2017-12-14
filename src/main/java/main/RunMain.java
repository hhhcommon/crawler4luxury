package main;

import common.DbUtil;
import compone.Component;
import crawler.*;
import factory.CrawlerComponent;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:17
 * @desc 运行主入口
 */


public class RunMain {

    public static void main(String[] args) {
        DbUtil.init();
        Component component = CrawlerComponent.create("luxury job");
        component.AddJob(new ValentinoCrawler(1))
                .run();
    }

}
