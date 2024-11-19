package com.saucedemo.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public String takeScreenshot(WebDriver driver) throws IOException {
        // Create a timestamped filename
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath =  "./test-output/screenshots/regression-tests/screenshot_" + timestamp + ".png";

        // Take screenshot and save to file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(filePath));
        logger.debug("Screenshot saved to: {}", filePath);
        return filePath;
    }
}
