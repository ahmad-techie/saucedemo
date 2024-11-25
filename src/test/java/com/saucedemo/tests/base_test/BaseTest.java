package com.saucedemo.tests.base_test;

import com.saucedemo.util.DataReader;
import com.saucedemo.util.Constants;
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

    protected final String VALID_USERNAME = DataReader.get("username");
    protected final String VALID_PASSWORD = DataReader.get("password");
    protected final String INVALID_USERNAME = "fake@123";
    protected final String INVALID_PASSWORD = "invalid_pass";

    protected final String product1 = DataReader.get("product1");
    protected final String product2 = DataReader.get("product2");

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        logger.debug("********** setup method started ***********");
        driver = BrowserManager.getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
        loginPage = new LoginPage(driver);
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
