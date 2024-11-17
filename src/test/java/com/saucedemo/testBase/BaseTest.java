package com.saucedemo.testBase;

import com.saucedemo.config.Constants;
import com.saucedemo.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

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
}
