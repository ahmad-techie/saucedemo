package com.saucedemo.util;

import com.saucedemo.constants.SharedConstants;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider implements SharedConstants {

    @DataProvider(name = "customer_data")
    public Object[][] provideCustomerData() {

        String sheetName = "customers";

        try {
            ExcelReader reader = new ExcelReader(EXCEL_FILE_PATH);
            return reader.readData(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][];
        }
    }
}
