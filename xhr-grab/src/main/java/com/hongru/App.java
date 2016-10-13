package com.hongru;

import com.hongru.config.LoadAppConfigFromFiles;
import com.hongru.spider.SpiderLauncher;
import com.hongru.spider.XHRCrawler;
import com.hongru.spider.impl.CrawlerLauncher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * 启动类
 *
 */
public class App
{

    public static void main( String[] args )
    {
        //初始化加载
        AppInit.init();

        //启动爬虫
        SpiderLauncher spiderLauncher = new CrawlerLauncher(XHRCrawler.class);
        spiderLauncher.spiderLaunch();

        //关闭爬虫
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                spiderLauncher.shutdown();
            }
        });
    }
}
