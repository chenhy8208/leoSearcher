package com.hongru.filter.impl;

import com.hongru.domain.WebHtml;
import com.hongru.filter.GrabFilter;
import edu.uci.ics.crawler4j.crawler.Page;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by chenhongyu on 16/9/28.
 *
 */
public class LeoFilter implements GrabFilter {

    //判断是否要收录
    @Override
    public boolean filter(WebHtml webHtml) {
        return true;
    }

    /**
     * 判断是否是首页
     * @param page
     * @return
     */
    public static boolean checkUrlIsHomePage(Page page) {
        String path = page.getWebURL().getPath();
        if (StringUtils.isBlank(path) ||
                StringUtils.equals(path, "/") ||
                StringUtils.indexOf(path, "/index.") == 0 ||
                StringUtils.indexOf(path, "/default.") == 0) {
            return true;
        }

        return false;
    }
}