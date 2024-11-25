package com.saucedemo.constants;

import com.saucedemo.util.DataReader;

public interface SharedConstants {
    String BASE_URL = DataReader.get("baseUrl");

    String CHECKOUT_URL = DataReader.get("checkoutUrl");

    String VALID_USERNAME = DataReader.get("valid_username");
    String VALID_PASSWORD = DataReader.get("valid_password");
    String INVALID_USERNAME = DataReader.get("invalid_username");
    String INVALID_PASSWORD = DataReader.get("invalid_password");

    String PRODUCT_ONE = DataReader.get("product1");
    String PRODUCT_TWO = DataReader.get("product2");

    String EXCEL_FILE_PATH = "src/test/resources/test-data/data.xlsx";

    int TIMEOUT = 5;
}

