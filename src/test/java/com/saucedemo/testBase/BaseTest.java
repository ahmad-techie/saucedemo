package com.saucedemo.testBase;

import com.saucedemo.config.Constants;
import com.saucedemo.utils.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        logger.debug("********** setup method started ***********");
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Constants.BASE_URL);
        logger.debug("********** setup method executed ***********\n");

    }

    @AfterMethod
    public void tearDown() {
        logger.debug("********** tearDown method started ***********");
        DriverManager.quitDriver();
        logger.debug("********** tearDown method executed ***********\n");

    }

    public String takeScreenshot() throws IOException {
        // Create a timestamped filename
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath =  "./testOutput/screenshots/screenshot_" + timestamp + ".png";

        // Take screenshot and save to file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(filePath));
        logger.debug("Screenshot saved to: {}", filePath);
        return filePath;
    }
}
