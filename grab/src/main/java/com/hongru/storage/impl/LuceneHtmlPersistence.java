package com.hongru.storage.impl;

import com.hongru.domain.WebHtml;
import com.hongru.filter.GrabFilter;
import com.hongru.filter.impl.XHRFilter;
import com.hongru.spider.LeoCrawler;
import com.hongru.storage.HtmlPersistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class LuceneHtmlPersistence implements HtmlPersistence{

    @Override
    public void filterAndSave(WebHtml webHtml) {
        if(webHtml == null) {
            logger.warn(this.getClass().getName() + " fault, webhtml = " + webHtml);
            return;
        }

        GrabFilter grabFilter = new XHRFilter();
        if (!grabFilter.filter(webHtml)) return; //没有通过过滤不保存

    }

    private static final Logger logger = LogManager.getLogger(LuceneHtmlPersistence.class);

}