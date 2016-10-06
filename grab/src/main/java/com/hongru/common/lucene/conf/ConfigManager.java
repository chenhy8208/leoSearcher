package com.hongru.common.lucene.conf;

import com.hongru.config.AppConfig;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
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

    private static IndexWriter indexWriter;
    private static final Version currentVersion = AppConfig.currentLuceneVersion;
    private static Directory directory;
    private static IKAnalyzer analyzer;

    static {
        try {
            Path dirctoryPath = Paths.get(AppConfig.storagePath);
            directory = FSDirectory.open(dirctoryPath);
            analyzer = new IKAnalyzer(true); //是否开启智能分词
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
