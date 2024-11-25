package com.saucedemo.tests.base_test;

import com.saucedemo.util.DataReader;
import com.saucedemo.pages.pages.LoginPage;
import com.saucedemo.util.TestUtils;
import com.saucedemo.util.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;

public class BaseTest extends TestUtils {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected WebDriver driver;

    protected int timeoutDuration = Integer.parseInt(DataReader.get("timeout"));

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        logger.debug("********** setup method started ***********");
        driver = BrowserManager.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutDuration));
        driver.manage().window().maximize();
        logger.debug("********** setup method executed ***********");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        logger.debug("********** tearDown method started ***********");
        if (!result.isSuccess()){
            takeScreenshot(driver);
        }
        BrowserManager.quitDriver();
        logger.debug("********** tearDown method executed ***********");
    }
}
