package com.saucedemo.testBase;

import com.saucedemo.ui.pages.InventoryPage;
import com.saucedemo.utils.ConfigReader;
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

    protected final String VALID_USERNAME = ConfigReader.get("username");
    protected final String VALID_PASSWORD = ConfigReader.get("password");
    protected final String INVALID_USERNAME = "fake@123";
    protected final String INVALID_PASSWORD = "invalid_pass";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
        logger.debug("********** setup method started ***********");
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(Constants.BASE_URL);
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
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
