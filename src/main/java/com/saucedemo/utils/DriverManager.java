package com.saucedemo.utils;

import com.saucedemo.config.Browsers;
import com.saucedemo.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    private static void initializeDriver() {
        String browser = ConfigReader.get("browser");
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
        } else {
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

//    private static void initializeDriver() {
//        String browser = ConfigReader.get("browser");
//        if (browser.equalsIgnoreCase("chrome")) {
//            driver.set(new ChromeDriver());
//        } else if (browser.equalsIgnoreCase("firefox")) {
//            driver.set(new FirefoxDriver());
//        }
//        driver.get().manage().window().maximize();
//    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}