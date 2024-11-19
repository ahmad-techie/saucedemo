package com.saucedemo.testBase;

import com.saucedemo.utils.Constants;
import com.saucedemo.ui.pages.LoginPage;
import com.saucedemo.util.TestUtils;
import com.saucedemo.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

public class BaseTest extends TestUtils {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        logger.debug("********** setup method started ***********");
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Constants.BASE_URL);
        loginPage = new LoginPage(driver);
        logger.debug("********** setup method executed ***********\n");

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        logger.debug("********** tearDown method started ***********");
        if (!result.isSuccess()){
            takeScreenshot(driver);
        }
        DriverManager.quitDriver();
        logger.debug("********** tearDown method executed ***********\n");

    }
}
