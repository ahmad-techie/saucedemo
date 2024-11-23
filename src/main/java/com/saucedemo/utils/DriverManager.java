package com.saucedemo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            initializeDriver(browser);
        }
        return driver.get();
    }

    private static void initializeDriver(String browser) {
        String mode = ConfigReader.get("mode");
        boolean isHeadless = mode.contains("headless");
        if (browser == null) {
            browser = "chrome"; // Defaults to Chrome browser if browser is not provided in the config file
        }
        if (browser.equals(Browsers.FIREFOX)) {
            driver.set(createFirefoxDriver(isHeadless));
        } else if (browser.equals(Browsers.EDGE)) {
            driver.set(createEdgeDriver(isHeadless));
        } else if (browser.equals(Browsers.CHROME)) {
            driver.set(createChromeDriver(isHeadless));
        } else if (browser.equals(Browsers.SAFARI)) {
            driver.set(createSafariDriver());
        }else {
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
