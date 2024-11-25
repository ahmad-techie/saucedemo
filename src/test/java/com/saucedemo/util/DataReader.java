package com.saucedemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataReader {

    private static Logger logger = LoggerFactory.getLogger("Data Reader");

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/properties/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            logger.error(e.getStackTrace().toString());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
