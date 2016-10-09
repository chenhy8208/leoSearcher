package com.hongru.spider;

import com.hongru.App;
import com.hongru.domain.WebHtml;
import com.hongru.filter.impl.LeoFilter;
import com.hongru.loading.Loading;
import com.hongru.loading.impl.CrawlerLoading;
import com.hongru.storage.HtmlPersistence;
import com.hongru.storage.impl.LuceneHtmlPersistence;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by chenhongyu on 16/9/28.
 *
 */
public class LeoCrawler extends WebCrawler {

    private static final Logger logger = LogManager.getLogger(LeoCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico"
            + "|png|tiff?|mid|mp2|mp3|mp4"
            + "|wav|avi|mov|mpeg|ram|m4v|pdf"
            + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();

        if (!LeoFilter.checkUrlIsHomePage(page)) {
            return; //不是首页就不入库
        }

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            logger.info("visit url:" + url);

            //数据装入
            Loading loading = new CrawlerLoading();
            WebHtml webHtml = loading.loading(url, page.getStatusCode(), htmlParseData);

            //过滤持久化
            HtmlPersistence htmlPersistence = new LuceneHtmlPersistence();
            htmlPersistence.filterAndSave(webHtml);

            //--------------------------------------------------------------
//            System.out.println("Url: " + url);
//            System.out.println("Text length: " + text.length());
//            System.out.println("Html length: " + html.length());
//            System.out.println("Number of outgoing links: " + links.size());
//            System.out.println("html: " + htmlParseData.getHtml());

        }
    }
}
