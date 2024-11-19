package com.saucedemo.ui.pages;

import com.saucedemo.ui.base.BasePage;
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
        enterText(usernameField, username);
        enterText(passwordField, password);
        click(loginButton);
        return new InventoryPage(driver);
    }

    public boolean isPasswordMismatchMessagePresent(){
        return !errorMessage.getText().contains("password do not match");
    }


}
