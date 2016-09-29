package com.hongru.common.lucene;

import com.hongru.domain.WebHtml;
import com.hongru.loading.impl.CrawlerLoading;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;

import java.util.Date;

public class HTMLDocumentUtils {


	public static Document htmlToDocument(WebHtml html) {

		if (html == null) {
			logger.warn("htmlToDocument fault,Because html = " + html);
			return null;
		}

		Document doc = new Document();

		doc.add(new Field("url", html.getUrl(), StringField.TYPE_STORED));
		doc.add(new SortedNumericDocValuesField("crawlTime", html.getCrawlTime().getTime()));
		doc.add(new SortedNumericDocValuesField("pageUpdateTime", html.getPageUpdateTime().getTime()));
		doc.add(new Field("MetaTitle", html.getUrl(), TextField.TYPE_STORED));
		doc.add(new Field("MetaKeyword", html.getUrl(), TextField.TYPE_STORED));
		doc.add(new Field("MetaDescription", html.getUrl(), TextField.TYPE_STORED));
		doc.add(new Field("html", html.getUrl(), TextField.TYPE_STORED));
		doc.add(new Field("text", html.getUrl(), TextField.TYPE_STORED));
		doc.add(new NumericDocValuesField("statusCode", html.getStatusCode()));
		doc.add(new NumericDocValuesField("htmlLength", html.getHtmlLength()));
		doc.add(new NumericDocValuesField("numberOfOutgoingLinks", html.getNumberOfOutgoingLinks()));

		return doc;
	}


	public static WebHtml documentToHtml(Document doc) {

		if (doc == null) {
			logger.warn("documentToHtml fault,Because html = " + doc);
			return null;
		}

		WebHtml html = new WebHtml();
		html.setPageUpdateTime(new Date(NumberUtils.toLong(doc.get("pageUpdateTime"))));
		html.setCrawlTime(new Date(NumberUtils.toLong(doc.get("crawlTime"))));
		html.setStatusCode(NumberUtils.toInt(doc.get("statusCode")));
		html.setHtmlLength(NumberUtils.toInt(doc.get("htmlLength")));
		html.setNumberOfOutgoingLinks(NumberUtils.toInt(doc.get("numberOfOutgoingLinks")));
		html.setHtml(doc.get("html"));
		html.setText(doc.get("text"));
		html.setMetaTitle(doc.get("MetaTitle"));
		html.setMetaKeyword(doc.get("MetaKeyword"));
		html.setMetaDescription(doc.get("MetaDescription"));
		html.setUrl(doc.get("url"));

		return html;
	}

	private static final Logger logger = LogManager.getLogger(HTMLDocumentUtils.class);
}
