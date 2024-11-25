package com.saucedemo.tests.scripts;

import com.saucedemo.tests.base_test.BaseTest;
import com.saucedemo.pages.pages.CartPage;
import com.saucedemo.pages.pages.Checkout;
import com.saucedemo.pages.pages.InventoryPage;
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
