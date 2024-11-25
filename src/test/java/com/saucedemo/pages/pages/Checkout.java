package com.saucedemo.pages.pages;

import com.saucedemo.pages.base_page.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout extends BasePage {

    private final String URL = "https://www.saucedemo.com/checkout-step-two.html";

    @FindBy(id = "first-name")
    private WebElement firstNameField;
    @FindBy(id = "last-name")
    private WebElement lastNameField;
    @FindBy(id = "postal-code")
    private WebElement zipCodeField;
    @FindBy(id = "continue")
    private WebElement continueBtn;
    @FindBy(className = "summary_subtotal_label")
    private WebElement subTotal;
    @FindBy(id = "finish")
    private WebElement finishButton;
    @FindBy(className = "title")
    private WebElement orderConfirmationMessage;
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public Checkout(WebDriver driver) {
        super(driver);
    }

    public boolean fillOutInformation(String firstName, String lastName, String zipCode){
        enterText(firstNameField, firstName);
        enterText(lastNameField, lastName);
        enterText(zipCodeField, zipCode);
        return getCurrentUrl().equals(URL);
    }

    public void clickContinue(){
        click(continueBtn);
    }

    public boolean verifySubTotal(){
        return subTotal.getText().equals("Item total: $7.99");
    }

    public void finish(){
        click(finishButton);
    }

    public boolean isOrderPlaced(){
        return orderConfirmationMessage.getText().equals("Checkout: Complete!");
    }

    public boolean isErrorMessagePresent(){
        return getText(errorMessage).contains("Error");
    }
}
