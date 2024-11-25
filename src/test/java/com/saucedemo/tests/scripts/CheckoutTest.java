package com.saucedemo.tests.scripts;

import com.saucedemo.constants.SharedConstants;
import com.saucedemo.pages.pages.LoginPage;
import com.saucedemo.tests.base_test.BaseTest;
import com.saucedemo.pages.pages.CartPage;
import com.saucedemo.pages.pages.Checkout;
import com.saucedemo.pages.pages.InventoryPage;
import com.saucedemo.util.TestDataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CheckoutTest extends BaseTest implements SharedConstants {

    private LoginPage loginPage;

    @Test(dataProvider = "customer_data", dataProviderClass = TestDataProvider.class)
    public void verify_order_is_placed(String firstName, String lastName, String zipCode){
        CartPage cartPage = goToCartPage();
        cartPage.isProductInCart(PRODUCT_ONE);
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
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        inventoryPage.addProductToCart(PRODUCT_TWO);
        return inventoryPage.gotoCart();
    }

}
