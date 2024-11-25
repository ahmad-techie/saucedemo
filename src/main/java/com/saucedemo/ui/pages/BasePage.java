package com.saucedemo.ui.pages;

import com.google.common.base.Function;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class BasePage {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected WebDriver driver;
    private final WebDriverWait wait;
    private final boolean isDemoMode = Boolean.parseBoolean(ConfigReader.get("demoMode"));

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
    }

    protected void customWait(int seconds){
        try {
            Thread.sleep(seconds*1000);
        }catch (Exception e){

        }
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
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

    public void handleCheckBox(By locator, boolean isCheck) {
        try {
            WebElement checkBoxElem = find(locator);
            highlightElement(checkBoxElem);

            boolean checkBoxState = checkBoxElem.isSelected();
            // If the desired state is to check the checkbox
            if (isCheck && !checkBoxState) {
                checkBoxElem.click();
            }
            // If the desired state is to uncheck the checkbox
            else if (!isCheck && checkBoxState) {
                checkBoxElem.click();
            }
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void selectDropDown(By locator, int index) {
        try {
            WebElement element = driver.findElement(locator);
            highlightElement(element);
            Select dropDown = new Select(element);
            dropDown.selectByIndex(index);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void selectDropDown(By locator, String visibleText) {
        try {
            WebElement element = driver.findElement(locator);
            highlightElement(element);
            Select dropDown = new Select(element);
            dropDown.selectByVisibleText(visibleText);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void selectDropDownByAttributeValue(By locator, String attributeValue) {
        try {
            WebElement element = driver.findElement(locator);
            highlightElement(element);
            Select dropDown = new Select(element);
            dropDown.selectByValue(attributeValue);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }


    public void clickHiddenElement(By locator) {
        try {
            WebElement elem = driver.findElement(locator);
            highlightElement(elem);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", elem);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }


    public void moveMouseToElement(WebElement targetElem) {
        try {
            highlightElement(targetElem);
            Actions action = new Actions(driver);
            action.moveToElement(targetElem).build().perform();
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void moveMouseToElement(WebElement firstElem, WebElement secElem) {
        try {
            highlightElement(firstElem);
            Actions action = new Actions(driver);
            action.moveToElement(firstElem).build().perform();
            highlightElement(secElem);
            action.moveToElement(secElem).build().perform();
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void highlightElement(WebElement element) {
        try {
            if (isDemoMode == true) {
                for (int i = 0; i < 4; i++) {
                    WrapsDriver wrappedElement = (WrapsDriver) element;
                    JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                            "color: red; border: 2px solid yellow");
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                }
            }
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void highlightElement(By locator) {
        WebElement element = find(locator);
        highlightElement(element);
    }

    public void scrollToElement(WebElement element) {
        try {
            highlightElement(element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void scrollByVertically(String verticalPixel) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(0," + verticalPixel + ")");
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void scrollByHorizontally(String horizontalPixel) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(" + horizontalPixel + ",0)");
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void sendKeyBoard(CharSequence... keysToSend) {
        try {
            WebElement webpage = driver.findElement(By.tagName("body"));
            highlightElement(webpage);
            webpage.sendKeys(keysToSend);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public String getCurrentTime() {
        int hour = LocalTime.now().getHour();
        int minute = LocalTime.now().getMinute();
        int sec = LocalTime.now().getSecond();
        return hour+":"+minute+":"+sec;
    }

    protected void waitForVisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.visibilityOf(find(locator)));
    }

    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement fluentWait(final By locator, int timeoutSeconds) {
        WebElement targetElem = null;
        try {
            @SuppressWarnings("deprecation")
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeoutSeconds))
                    .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
            targetElem = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return find(locator);
                }
            });
            highlightElement(targetElem);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return targetElem;
    }

    public WebDriver switchToWindow(int browserIndex) {
        try {
            Set<String> allBrowsers = driver.getWindowHandles();
            Iterator<String> iterator = allBrowsers.iterator();
            List<String> windowHandles = new ArrayList<>();
            while (iterator.hasNext()) {
                String window = iterator.next();
                windowHandles.add(window);
            }
            // switch to index N
            driver.switchTo().window(windowHandles.get(browserIndex));
            highlightElement(By.tagName("body"));
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return driver;
    }


    public void uploadFile(By by, String fileRelativePath) {
        try {
            WebElement fileUploadElem = driver.findElement(by);
            highlightElement(fileUploadElem);
            File tempFile = new File(fileRelativePath);
            String fullPath = tempFile.getAbsolutePath();
            logger.info("file uploading : {}", fullPath);
            fileUploadElem.sendKeys(fullPath);
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public Alert isAlertPresent() {
        Alert alert = null;
        try {
            alert = driver.switchTo().alert();
            logger.info("Popup Alert present: {{}}", alert.getText());
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return alert;
    }

    public void closeAlert(){
        try {
            Alert alert = driver.switchTo().alert();
            logger.info("Popup Alert closed: {{}}", alert.getText());
            alert.dismiss();
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }

    public void acceptAlert(){
        try {
            Alert alert = driver.switchTo().alert();
            logger.info("Popup Alert accepted: {{}}", alert.getText());
            alert.accept();
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
    }
}
