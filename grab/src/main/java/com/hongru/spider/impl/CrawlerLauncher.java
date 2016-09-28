package com.hongru.spider.impl;

import com.hongru.spider.LeoCrawler;
import com.hongru.spider.SpiderLauncher;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class CrawlerLauncher implements SpiderLauncher {

    //数据保存目录
    private static final String crawlStorageFolder = "./data/craw/root";
    //爬虫数量
    private static final int numberOfCrawlers = 7;
    /**
     * A -> B -> C -> D
     Since, "A" is a seed page, it will have a depth of 0.
     "B" will have depth of 1 and so on.
     You can set a limit on the depth of pages that crawler4j crawls.
     For example, if you set this limit to 2,
     it won't crawl page "D". To set the maximum depth you can use:
     */
    //抓取深度
    private static final int maxDepthOfCrawling = 7;
    //延迟毫秒数 Politeness delay in milliseconds (delay between sending two requests to the same host).
    private static final int politenessDelay = 1;

    //模拟User-Agent
    private static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";

    @Override
    public void spiderLaunch() {

        CrawlConfig config = new CrawlConfig();
        config.setResumableCrawling(true);  //可恢复
        config.setUserAgentString(userAgent);

        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setPolitenessDelay(politenessDelay);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            //构造出错,程序停止
            throw new RuntimeException(e);
        }

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("http://www.163.com/");
        controller.addSeed("http://www.csdn.com/");
        controller.addSeed("http://hongru.com/");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(LeoCrawler.class, numberOfCrawlers);
    }
}
