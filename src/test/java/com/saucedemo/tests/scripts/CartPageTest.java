package com.saucedemo.tests.scripts;

import com.saucedemo.tests.base_test.BaseTest;
import com.saucedemo.pages.pages.CartPage;
import com.saucedemo.pages.pages.InventoryPage;
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
