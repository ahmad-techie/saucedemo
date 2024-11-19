package com.saucedemo.tests;

import com.saucedemo.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {


    @Test
    public void verifyProductsAreSortedByPrice(){
        login();
        Assert.assertTrue(inventoryPage.areProductsSortedByPriceFromLowToHigh());
    }

    @Test
    public void verifyProductAdditionToCart(){
        login();
        Assert.assertTrue(inventoryPage.isProductAddedToCart());
    }

    private void login(){
        loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
    }
}
