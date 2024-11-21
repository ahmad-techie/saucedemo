package com.saucedemo.tests;

import com.saucedemo.testBase.BaseTest;
import com.saucedemo.ui.pages.CartPage;
import com.saucedemo.ui.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {

    @Test
    public void verify_remove_product_from_cart(){
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        inventoryPage.addProductToCart(product1);
        inventoryPage.addProductToCart(product2);
        CartPage cartPage = inventoryPage.gotoCart();
        Assert.assertTrue(cartPage.removeItemFromCart());
    }
}
