package com.hongru.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 将配置文件中的信息加载到全局的配置类中
 * Created by chenhongyu on 2016/10/12.
 */
public class LoadAppConfigFromFiles {
    public static boolean load(String filePath) {
        if (StringUtils.isBlank(filePath)) return false;

        Path configFile = Paths.get(filePath);
        if (!Files.exists(configFile)) return false;

        try {
            Configuration configuration = new PropertiesConfiguration(configFile.toFile());
            return initAppConfig(configuration);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return false;
    }

    private static boolean initAppConfig(Configuration configuration) throws Exception {
        if(configuration == null) return false;
        AppConfig.crawlStorageFolder = configuration.getString("crawlStorageFolder");
        AppConfig.maxDepthOfCrawling = configuration.getInt("maxDepthOfCrawling");
        AppConfig.mergePolicy_maxMergeDocs = configuration.getInt("mergePolicy_maxMergeDocs");
        AppConfig.mergePolicy_mergeFactor = configuration.getInt("mergePolicy_mergeFactor");
        AppConfig.numberOfCrawlers = configuration.getInt("numberOfCrawlers");
        AppConfig.politenessDelay = configuration.getInt("politenessDelay");
        AppConfig.storagePath = configuration.getString("storagePath");
        AppConfig.seeds = configuration.getStringArray("seeds");
        if (AppConfig.seeds == null || AppConfig.seeds.length <= 0) return false;

        return true;
    }

    private static final Logger logger = LogManager.getLogger(LoadAppConfigFromFiles.class);
}
