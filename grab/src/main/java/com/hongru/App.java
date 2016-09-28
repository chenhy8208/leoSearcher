package com.hongru;

import com.hongru.spider.SpiderLauncher;
import com.hongru.spider.impl.CrawlerLauncher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.IndexWriter;

/**
 * 启动类
 *
 */
public class App 
{

    public static void main( String[] args )
    {
       //启动爬虫
        SpiderLauncher spiderLauncher = new CrawlerLauncher();
        spiderLauncher.spiderLaunch();
    }

    private static final Logger logger = LogManager.getLogger(App.class);

}
