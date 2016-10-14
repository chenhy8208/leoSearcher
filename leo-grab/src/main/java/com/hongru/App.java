package com.hongru;

import com.hongru.spider.LeoCrawler;
import com.hongru.spider.SpiderLauncher;
import com.hongru.spider.impl.CrawlerLauncher;

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
        SpiderLauncher spiderLauncher = new CrawlerLauncher(LeoCrawler.class);
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
