package com.saucedemo.constants;

import com.saucedemo.util.CSVReader;
import com.saucedemo.util.PropertiesReader;

import java.util.List;

public interface SharedConstants {
    String APPLICATION_PROPERTIES_FILE_PATH = "./src/test/resources/properties/application.properties";
    String TEST_DATA_PROPERTIES_FILE_PATH = "./src/test/resources/test-data/data.properties";
    String EXCEL_FILE_PATH = "src/test/resources/test-data/data.xlsx";
    String CSV_FILE_PATH = "src/test/resources/test-data/sorted_products.csv";

    String BASE_URL = PropertiesReader.get(APPLICATION_PROPERTIES_FILE_PATH, "base_url");
    String INVENTORY_PAGE_URL = PropertiesReader.get(APPLICATION_PROPERTIES_FILE_PATH, "inventory_url");
    String CHECKOUT_URL = PropertiesReader.get(APPLICATION_PROPERTIES_FILE_PATH, "checkout_url");

    String VALID_USERNAME = PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"valid_username");
    String VALID_PASSWORD = PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"valid_password");
    String INVALID_USERNAME = PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"invalid_username");
    String INVALID_PASSWORD = PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"invalid_password");

    String PRODUCT_ONE = PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"product1");
    String PRODUCT_TWO = PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"product2");

    int TIMEOUT_DURATION = Integer.parseInt(PropertiesReader.get(TEST_DATA_PROPERTIES_FILE_PATH,"timeout"));
    boolean HEADLESS_MODE = Boolean.parseBoolean(PropertiesReader.get(APPLICATION_PROPERTIES_FILE_PATH, "headless_mode"));
    boolean DEMO_MODE = Boolean.parseBoolean(PropertiesReader.get(APPLICATION_PROPERTIES_FILE_PATH, "demo_mode"));
    String BROWSER = PropertiesReader.get(APPLICATION_PROPERTIES_FILE_PATH, "browser");

    List<String> SORTED_PRICES = CSVReader.readCSV(CSV_FILE_PATH);
}

