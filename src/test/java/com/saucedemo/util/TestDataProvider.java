package com.saucedemo.util;

import com.saucedemo.constants.SharedConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Arrays;

public class TestDataProvider implements SharedConstants {

    private static final Logger logger = LoggerFactory.getLogger("TestDataProvider");

    @DataProvider(name = "customer_data")
    public Object[][] provideCustomerData() {

        String sheetName = "customers";

        ExcelReader reader = new ExcelReader();
        return reader.readData(sheetName);
    }
}
