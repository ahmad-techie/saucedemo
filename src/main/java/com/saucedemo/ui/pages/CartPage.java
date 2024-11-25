package com.saucedemo.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {


    @FindBy(className = "inventory_item_name")
    private WebElement productTitleInCart;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "cart_button")
    private WebElement removeButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String product){
        return getText(productTitleInCart).equals(product);
    }

    public Checkout checkout(){
        click(checkoutButton);
        return new Checkout(driver);
    }

    public boolean removeItemFromCart(){
        click(removeButton);
        int numberOfItemsInCart = 1;
        int actualItemsInCart = Integer.parseInt(cartBadge.getText());
        return actualItemsInCart==numberOfItemsInCart;
    }
}
