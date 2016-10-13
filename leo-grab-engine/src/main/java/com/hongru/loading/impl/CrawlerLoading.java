package com.hongru.loading.impl;

import com.hongru.domain.WebHtml;
import com.hongru.loading.Loading;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class CrawlerLoading implements Loading{


    @Override
    public WebHtml loading(String url, int statusCode, Object htmlParseData) {

        if (StringUtils.isBlank(url)) {
            logger.warn("Loading html fault,Because url = " + url);
            return null;
        }

        if (htmlParseData == null) {
            logger.warn("Loading html fault,Because htmlParseData = " + htmlParseData);
            return null;
        }

        HtmlParseData parseData = (HtmlParseData)htmlParseData;

        WebHtml webHtml = null;
        try {
            webHtml = new WebHtml();
            webHtml.setUrl(url);
            webHtml.setCrawlTime(new Date());
            //TODO 这个页面更新时间最好采集页面最近更新的时间
            webHtml.setPageUpdateTime(new Date());
            webHtml.setHtml(parseData.getHtml());
            webHtml.setHtmlLength(parseData.getHtml().length());
            webHtml.setMetaDescription(parseData.getMetaTags().get("description"));
            webHtml.setMetaKeyword(parseData.getMetaTags().get("keywords"));
            webHtml.setMetaTitle(parseData.getMetaTags().get("dc:title"));
            webHtml.setNumberOfOutgoingLinks(parseData.getOutgoingUrls().size());
            webHtml.setStatusCode(statusCode);
            webHtml.setContentType(parseData.getMetaTags().get("content-type"));
            webHtml.setEncoding(parseData.getMetaTags().get("content-encoding"));
            webHtml.setAuthor(parseData.getMetaTags().get("author"));
        } catch (Exception e) {
            logger.error("web url = " + url + " | " + e.getMessage());
            return null;
        }

        return webHtml;
    }

    private static final Logger logger = LogManager.getLogger(CrawlerLoading.class);
}
