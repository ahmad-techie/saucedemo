package com.saucedemo.tests;

import com.saucedemo.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class InventoryTest extends BaseTest {


    @Test
    public void verifyProductsAreSortedByPrice(){
        login();
        assertTrue(inventoryPage.areProductsSortedByPriceFromLowToHigh());
    }

    @Test
    public void verifyProductAdditionToCart(){
        login();
        assertTrue(inventoryPage.isProductAddedToCart());
    }

    @Test
    public void verifyCartBadgeCountDisplayCorrectNumber(){
        login();
        boolean result = false;
        String message = "";
        try {
            result = inventoryPage.shoppingCartBadgeDisplayCorrectCountOfAddedItems();
        } catch (RuntimeException e) {
            message = e.getLocalizedMessage();
        }
        Assert.assertTrue(result, message);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void verifyCartBadgeCountFails(){
        inventoryPage.throwExceptionTest();
    }

    private void login(){
        loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
    }
}
