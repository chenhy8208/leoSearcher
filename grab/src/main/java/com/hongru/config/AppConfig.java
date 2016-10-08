package com.hongru.config;

import org.apache.lucene.util.Version;

/**
 * Created by chenhongyu on 16/9/30.
 */
public class AppConfig {

    //lucene数据保存路径
    public static final String storagePath = "/Volumes/Macintosh HD/Users/hongyu/workspace/indexDir/";

    //lucene版本
    public static final Version currentLuceneVersion = Version.LUCENE_6_2_1;

    //爬虫数据保存目录
    public static final String crawlStorageFolder = "./data/craw/root";

    //爬虫数量
    public static final int numberOfCrawlers = 10;

    /**
     * A -> B -> C -> D
     Since, "A" is a seed page, it will have a depth of 0.
     "B" will have depth of 1 and so on.
     You can set a limit on the depth of pages that crawler4j crawls.
     For example, if you set this limit to 2,
     it won't crawl page "D". To set the maximum depth you can use:
     */
    //抓取深度
    public static final int maxDepthOfCrawling = 200;

    //延迟毫秒数 Politeness delay in milliseconds (delay between sending two requests to the same host).默认200毫秒.
    public static final int politenessDelay = 200;

    //网站初始种子
    public static final String[] seeds = new String[]{
            "http://www.ccgp-shandong.gov.cn/sdgp2014/site/channelall.jsp?colcode=0301",
            "http://www.bjcz.gov.cn/zfcg/index.htm",
            "http://www.zbs365.com/zt-hnscg/",
            "http://bid.aited.cn/bid/index.html"
    };
}
