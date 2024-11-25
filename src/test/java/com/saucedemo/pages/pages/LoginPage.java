package com.saucedemo.pages.pages;

import com.saucedemo.pages.base_page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    @FindBy(tagName = "h3")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage loginWith(String username, String password){
        logger.debug("----------------- loginWith method started-------------------");
        enterText(usernameField, username);
        enterText(passwordField, password);
        click(loginButton);
        logger.debug("----------------- loginWith method completed-------------------");
        return new InventoryPage(driver);
    }

    public boolean isPasswordMismatchMessagePresent(){
        logger.debug("----------------- isPasswordMismatchMessagePresent method executed-------------------");
        return errorMessage.getText().contains("password do not match");

    }


}
