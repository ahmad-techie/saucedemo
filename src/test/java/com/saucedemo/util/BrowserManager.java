package com.saucedemo.util;

import com.saucedemo.constants.Browsers;
import com.saucedemo.constants.SharedConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserManager implements SharedConstants {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            initializeDriver(browser);
        }
        return driver.get();
    }

    private static void initializeDriver(String browser) {

        if (browser == null || browser.isBlank() || browser.isEmpty()) {
            String browserName = System.getProperty("browser");
            browser = browserName != null ? browserName : BROWSER;
            if (browser==null) browser = "chrome";
        }

        Boolean headless = Boolean.parseBoolean(System.getProperty("headless"));
        boolean isHeadless = headless != null ? headless : HEADLESS_MODE;
        switch (browser) {
            case Browsers.FIREFOX:
                driver.set(createFirefoxDriver(isHeadless));
                break;
            case Browsers.EDGE:
                driver.set(createEdgeDriver(isHeadless));
                break;
            case Browsers.CHROME:
                driver.set(createChromeDriver(isHeadless));
                break;
            case Browsers.SAFARI:
                driver.set(createSafariDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
    }

    private static WebDriver createFirefoxDriver(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless) {
            options.addArguments("headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean isHeadless) {
        EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("headless");
        }
        return new EdgeDriver(options);
    }

    private static WebDriver createChromeDriver(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("headless");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver createSafariDriver() {
        return new SafariDriver();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
