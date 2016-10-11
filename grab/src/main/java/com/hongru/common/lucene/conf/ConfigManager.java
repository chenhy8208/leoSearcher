package com.hongru.common.lucene.conf;

import com.hongru.common.lucene.HTMLDocumentUtils;
import com.hongru.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LogByteSizeMergePolicy;
import org.apache.lucene.index.LogMergePolicy;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static IndexWriter indexWriter;
    private static final Version currentVersion = AppConfig.currentLuceneVersion;
    private static Directory directory;
    private static IKAnalyzer analyzer;

    static {
        try {
            Path dirctoryPath = Paths.get(AppConfig.storagePath);
            directory = FSDirectory.open(dirctoryPath);
            analyzer = new IKAnalyzer(true); //是否开启智能分词
            logger.info("******* init directory = " + directory);
            logger.info("******* init directory path = " + dirctoryPath.toAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Directory getDirectory() {
        return directory;
    }

    public static IKAnalyzer getAnalyzer() {
        return analyzer;
    }

    public static IndexWriter getIndexWriter() {
        if (indexWriter != null) return indexWriter;

        synchronized (ConfigManager.class) {
            try {
                if (indexWriter == null) {
                    synchronized (ConfigManager.class) {
                        IndexWriterConfig config = new IndexWriterConfig(analyzer);
                        LogMergePolicy mergePolicy = new LogByteSizeMergePolicy();
                        //设置segment添加文档(Document)时的合并频率          //值较小,建立索引的速度就较慢          //值较大,建立索引的速度就较快,>10适合批量建立索引
                        mergePolicy.setMergeFactor(50);
                        //设置segment最大合并文档(Document)数
                        //值较小有利于追加索引的速度
                        //值较大,适合批量建立索引和更快的搜索
                        mergePolicy.setMaxMergeDocs(5000);
                        indexWriter = new IndexWriter(getDirectory(), config);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return indexWriter;
    }

    public static void closeIndexWriter() {
        if (indexWriter == null) return;

        try {
            indexWriter.close();
        } catch (IOException e) {
            try {
                indexWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
