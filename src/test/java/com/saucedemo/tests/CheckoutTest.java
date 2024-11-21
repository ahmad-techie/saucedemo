package com.saucedemo.tests;

import com.saucedemo.testBase.BaseTest;
import com.saucedemo.ui.pages.CartPage;
import com.saucedemo.ui.pages.Checkout;
import com.saucedemo.ui.pages.InventoryPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest {

    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String zipCode = "12345";

    private InventoryPage inventoryPage;

    @Test(priority = 0)
    public void verify_order_is_placed(){
        CartPage cartPage = goToCartPage();
        cartPage.isProductInCart(product1);
        Checkout checkout = cartPage.checkout();
        checkout.fillOutInformation(firstName, lastName, zipCode);
        checkout.clickContinue();
        checkout.finish();
        assertTrue(checkout.isOrderPlaced());
    }

    @Test
    public void verify_error_at_checkout_when_missing_information(){
        CartPage cartPage = goToCartPage();
        Checkout checkout = cartPage.checkout();
        checkout.fillOutInformation("", "", "");
        checkout.clickContinue();
        assertTrue(checkout.isErrorMessagePresent());
    }

    private CartPage goToCartPage() {
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        inventoryPage.addProductToCart(product1);
        CartPage cartPage = inventoryPage.gotoCart();
        return cartPage;
    }


}
