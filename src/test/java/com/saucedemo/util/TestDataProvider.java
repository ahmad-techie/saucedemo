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

        try {
            ExcelReader reader = new ExcelReader(EXCEL_FILE_PATH);
            return reader.readData(sheetName);
        } catch (IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            return new Object[0][];
        }
    }
}
