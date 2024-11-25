package com.saucedemo.util;

import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesReader extends Logger {

    private static final Properties properties;

    static {
        properties = new Properties();
    }

    public static String get(String filePath, String key) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return properties.getProperty(key);
    }
}
