package com.hongru.recrawl.impl;

import com.google.gson.Gson;
import com.hongru.common.redis.JedisPoolUtil;
import com.hongru.domain.WebHtml;
import com.hongru.recrawl.Recrawl;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.ShardedJedis;

import java.util.List;

/**
 * 蜘蛛重爬
 * Created by chenhongyu on 2016/11/2.
 */
public class CrawlerRecrawl implements Recrawl{

    private static final int SPACING_MILLSECONDS = 500;  //每插入一个重爬任务的间隔时间毫秒
    public static final String RECRAWL_QUEUE = "recrawl_queue";  //重爬队列的名字

    @Override
    public void recrawl(WebHtml webHtml) {
        if (webHtml == null) return;

        //插入redis队列中
        // 加入发布队列
        ShardedJedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();
            Gson g = new Gson();
            jedis.lpush(CrawlerRecrawl.RECRAWL_QUEUE, g.toJson(webHtml));

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if (jedis!=null) {
                JedisPoolUtil.returnRes(jedis);
            }
        }
    }

    /**
     * 从redis队列中获取
     */
    @Override
    public void recrawlFromRedisQueue() {
        logger.info("redis recraw queue start ...");

        ShardedJedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedis();

            //读取发布队列
            while (true)
            {
                Gson g = new Gson();
                List<String> taskJsones = jedis.brpop(0, CrawlerRecrawl.RECRAWL_QUEUE);
                if (taskJsones != null && taskJsones.size() >= 2) {
                    WebHtml task = null;
                    try {
                        task = g.fromJson(taskJsones.get(1), WebHtml.class);
                        doRecrawl(task);

                        //停一会是害怕重入的数量大把新抓取的队列给冲了，导致更新太多，无法再爬新的页面
                        Thread.sleep(SPACING_MILLSECONDS);
                    } catch (Exception e) {
                        logger.error("doRecrawl Exception("+ e.getMessage() +")");
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("redis recraw queue stop.Exception("+ e.getMessage() +")");
            e.printStackTrace();
        } finally{
            if (jedis!=null) {
                JedisPoolUtil.returnRes(jedis);
            }

        }
    }

    /**
     * 执行重爬
     * @param webHtml
     */
    private void doRecrawl(WebHtml webHtml) {
        controller.addRepeatVisitSeed(webHtml.getUrl(), webHtml.getDocId());
    }

    private CrawlController controller;

    private CrawlerRecrawl(){}

    public CrawlerRecrawl(CrawlController controller) {
        this.controller = controller;
    }
    private static final Logger logger = LogManager.getLogger(CrawlerRecrawl.class);
}
