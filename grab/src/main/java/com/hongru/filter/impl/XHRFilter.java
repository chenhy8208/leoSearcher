package com.hongru.filter.impl;

import com.hongru.domain.WebHtml;
import com.hongru.filter.GrabFilter;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class XHRFilter implements GrabFilter {

    @Override
    public boolean filter(WebHtml webHtml) {
        return false;
    }
}
