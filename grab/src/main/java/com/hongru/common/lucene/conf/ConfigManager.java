package com.hongru.common.lucene.conf;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

/**
 * Created by chenhongyu on 16/9/28.
 */
public class ConfigManager {

    private static IndexWriter indexWriter;
    private static final Version currentVersion = Version.LUCENE_6_2_1;
    private static final String storagePath = "./indexDir/";
    private static Directory directory;
    private static Analyzer analyzer;

    static {
        try {
            directory = FSDirectory.open(Paths.get(new URI(storagePath)));
            analyzer = new StandardAnalyzer(); //分词器
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Directory getDirectory() {
        return directory;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    public static IndexWriter getIndexWriter() {
        if (indexWriter != null) return indexWriter;

        synchronized (ConfigManager.class) {
            try {
                IndexWriterConfig config = new IndexWriterConfig(getAnalyzer());
                if (indexWriter == null) {
                    synchronized (ConfigManager.class) {
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
