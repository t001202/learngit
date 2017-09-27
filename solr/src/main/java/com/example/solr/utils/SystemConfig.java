package com.example.solr.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by WangYx on 2017/9/27.
 */
public class SystemConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfig.class);

    private static Config config;

    static {
        config = ConfigFactory.load();
        config.checkValid(ConfigFactory.defaultReference(), "simple-lib");

    }

    private static void init() {

    }


    /**
     * 系统变量优先
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        try {
            return System.getProperty(key, config.getString(key));
        } catch (ConfigException e) {
            LOGGER.warn("读取配置出错,key={}，异常：{}", key, e.getMessage());
            return null;
        }

    }

    public static List<Config> getConfigList(String key) {
        return (List<Config>) config.getConfigList(key);
    }

    public static Config getConfig(String key) {
        return config.getConfig(key);
    }

    public static int getInt(String key) {
        try {
            String str = System.getProperty(key, config.getString(key));
            return Integer.parseInt(str);
        } catch (Exception e) {
            LOGGER.info("根据key（" + key + "）获得value(Int)失败", e);
            throw new RuntimeException(e);
        }

    }

    public static Boolean getBoolean(String key) {
        try {
            String str = System.getProperty(key, config.getString(key));
            return Boolean.parseBoolean(str);
        } catch (Exception e) {
            LOGGER.info("根据key（" + key + "）获得value(Boolean)失败", e);
            throw new RuntimeException(e);
        }


    }
}
