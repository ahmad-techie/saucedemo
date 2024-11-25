package com.saucedemo.tests.scripts;

import com.saucedemo.constants.SharedConstants;
import com.saucedemo.pages.pages.LoginPage;
import com.saucedemo.tests.base_test.BaseTest;
import com.saucedemo.pages.pages.CartPage;
import com.saucedemo.pages.pages.InventoryPage;
import com.saucedemo.util.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest implements SharedConstants {

    private LoginPage loginPage;

    @Test
    public void verify_remove_product_from_cart(){
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        inventoryPage.addProductToCart(PRODUCT_ONE);
        inventoryPage.addProductToCart(PRODUCT_TWO);
        CartPage cartPage = inventoryPage.gotoCart();
        Assert.assertTrue(cartPage.removeItemFromCart());
    }
}
