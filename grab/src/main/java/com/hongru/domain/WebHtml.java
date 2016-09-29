package com.hongru.domain;

import java.util.Date;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class WebHtml {
    private String url;
    private Date crawlTime;
    private Date pageUpdateTime;  //页面更新的时间
    private String MetaTitle;
    private String MetaKeyword;
    private String MetaDescription;
    private String html;
    private String text;  //有用的片段
    private int statusCode;  //网页返回的状态码
    private int htmlLength;  //网页字符长度
    private int numberOfOutgoingLinks;  //外链数量

    public Date getPageUpdateTime() {
        return pageUpdateTime;
    }

    public void setPageUpdateTime(Date pageUpdateTime) {
        this.pageUpdateTime = pageUpdateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(Date crawlTime) {
        this.crawlTime = crawlTime;
    }

    public String getMetaTitle() {
        return MetaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        MetaTitle = metaTitle;
    }

    public String getMetaKeyword() {
        return MetaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        MetaKeyword = metaKeyword;
    }

    public String getMetaDescription() {
        return MetaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        MetaDescription = metaDescription;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getHtmlLength() {
        return htmlLength;
    }

    public void setHtmlLength(int htmlLength) {
        this.htmlLength = htmlLength;
    }

    public int getNumberOfOutgoingLinks() {
        return numberOfOutgoingLinks;
    }

    public void setNumberOfOutgoingLinks(int numberOfOutgoingLinks) {
        this.numberOfOutgoingLinks = numberOfOutgoingLinks;
    }
}
