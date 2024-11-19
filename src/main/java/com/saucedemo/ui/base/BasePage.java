package com.saucedemo.ui.base;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
    }

    public void navigateTo(String url){
        driver.get(url);
    }

    protected WebElement find(By locator){
        return driver.findElement(locator);
    }

    public void enterText(WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public void click(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getText(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void waitForVisibilityOfElement(WebElement element, int timeout) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
