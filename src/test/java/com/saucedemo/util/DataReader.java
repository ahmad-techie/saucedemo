package com.saucedemo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataReader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/properties/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
