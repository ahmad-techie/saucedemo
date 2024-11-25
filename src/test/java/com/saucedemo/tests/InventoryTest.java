package com.saucedemo.tests;

import com.saucedemo.testBase.BaseTest;
import com.saucedemo.ui.pages.CartPage;
import com.saucedemo.ui.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;

public class InventoryTest extends BaseTest {

    private InventoryPage inventoryPage;


    @Test
    public void verify_products_are_sorted_by_price(){
        logger.debug("verify_products_are_sorted_by_price method started");
        inventoryPage = login();
        assertTrue(inventoryPage.areProductsSortedByPriceFromLowToHigh());
        logger.debug("verify_products_are_sorted_by_price method completed");
    }

    @Test
    public void verify_product_added_to_cart(){
        logger.debug("verify_product_added_to_cart method started");
        inventoryPage = login();
        inventoryPage.addProductToCart(product1);
        CartPage cartPage = inventoryPage.gotoCart();
        assertTrue(cartPage.isProductInCart(product1));
        logger.debug("verify_product_added_to_cart method completed");
    }

    @Test
    public void verify_cart_badge_count_display_correct_number(){
        logger.debug("verify_cart_badge_count_display_correct_number method started");
        inventoryPage = login();
        boolean result = false;
        String message = "";
        try {
            result = inventoryPage.shoppingCartBadgeDisplayCorrectCountOfAddedItems();
        } catch (RuntimeException e) {
            message = e.getLocalizedMessage();
        }
        Assert.assertTrue(result, message);
        logger.debug("verify_cart_badge_count_display_correct_number method completed");
    }

    @Test
    public void verify_logout_functionality(){
        logger.debug("verify_logout_functionality method started");
        inventoryPage = login();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inventoryPage.isLoginSuccessful());
        inventoryPage.logout();
        softAssert.assertTrue(inventoryPage.isLogoutSuccessful());
        softAssert.assertAll();
    }

    @Test
    public void verify_product_details_is_displayed(){
        inventoryPage = login();
        assertTrue(inventoryPage.productDetailsIsDisplayedWhenClickingOnTheProduct(product2));
    }

//    @Test(expectedExceptions = RuntimeException.class)
//    public void verifyCartBadgeCountFails(){
//        inventoryPage.throwExceptionTest();
//    }

    private InventoryPage login(){
        return loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
    }
}
