package com.hongru.loading;

import com.hongru.domain.WebHtml;

/**
 * Created by chenhongyu on 16/9/28.
 */
public interface Loading {
    public WebHtml loading(String url, int statusCode, Object htmlParseData);
}
