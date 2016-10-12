package com.hongru;

import com.hongru.config.LoadAppConfigFromFiles;
import com.hongru.spider.SpiderLauncher;
import com.hongru.spider.impl.CrawlerLauncher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.IndexWriter;

import java.util.Scanner;

/**
 * 启动类
 *
 */
public class App
{

    public static void main( String[] args )
    {
        init();

        //启动爬虫
        SpiderLauncher spiderLauncher = new CrawlerLauncher();
        spiderLauncher.spiderLaunch();

        //关闭爬虫
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                spiderLauncher.shutdown();
            }
        });
    }

    private static void init() {
        while (!loadConfig()) {
            pathFault = true;
        }
    }

    private static boolean loadConfig() {
        if (pathFault) {
            System.out.print("路径无效,请重新输入info.properties的完整有效路径,结束用回车确认:");
        } else {
            System.out.print("请输入info.properties的完整有效路径,结束用回车确认:");
        }

        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        return LoadAppConfigFromFiles.load(filePath);
    }

    private static final Logger logger = LogManager.getLogger(App.class);
    private static boolean pathFault;
}
