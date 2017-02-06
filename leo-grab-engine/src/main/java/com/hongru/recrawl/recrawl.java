package com.hongru.recrawl;

import com.hongru.domain.WebHtml;

/**
 * Created by chenhongyu on 2016/11/2.
 */
public interface Recrawl {

    /**
     *
     * @param webHtml
     */
    public void recrawl(WebHtml webHtml);

    /**
     * 开始从队列中获取信息进行重爬
     */
    public void recrawlFromRedisQueue();
}
