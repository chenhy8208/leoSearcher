package com.hongru;

import com.hongru.config.LoadAppConfigFromFiles;
import com.hongru.recrawl.Recrawl;
import com.hongru.recrawl.impl.CrawlerRecrawl;
import com.hongru.spider.SpiderLauncher;
import com.hongru.spider.XHRCrawler;
import com.hongru.spider.impl.CrawlerLauncher;
import com.hongru.spider.impl.ReCrawlerLauncher;
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

        SpiderLauncher spiderLauncher = null;
        if (args.length >= 0 && args[0].equals("-r")) {
            //爬虫重入
            spiderLauncher = new ReCrawlerLauncher(XHRCrawler.class);
        } else {
            //启动爬虫
            spiderLauncher = new CrawlerLauncher(XHRCrawler.class);
        }

        spiderLauncher.spiderLaunch();
        final SpiderLauncher sl = spiderLauncher;

        //关闭爬虫
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                sl.shutdown();
            }
        });
    }
}
