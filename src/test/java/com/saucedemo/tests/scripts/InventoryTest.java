package com.saucedemo.tests.scripts;

import com.saucedemo.constants.SharedConstants;
import com.saucedemo.pages.pages.LoginPage;
import com.saucedemo.tests.base_test.BaseTest;
import com.saucedemo.pages.pages.CartPage;
import com.saucedemo.pages.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertTrue;

public class InventoryTest extends BaseTest implements SharedConstants {

    private LoginPage loginPage;

    private InventoryPage inventoryPage;

    @Test
    public void verify_products_are_sorted_by_price(){
        logger.debug("verify_products_are_sorted_by_price method started");
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        inventoryPage = login();
        assertTrue(inventoryPage.areProductsSortedByPriceFromLowToHigh());
        logger.debug("verify_products_are_sorted_by_price method completed");
    }

    @Test
    public void verify_product_added_to_cart(){
        logger.debug("verify_product_added_to_cart method started");
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        inventoryPage = login();
        inventoryPage.addProductToCart(PRODUCT_ONE);
        CartPage cartPage = inventoryPage.gotoCart();
        assertTrue(cartPage.isProductInCart(PRODUCT_ONE));
        logger.debug("verify_product_added_to_cart method completed");
    }

    @Test
    public void verify_cart_badge_count_display_correct_number(){
        logger.debug("verify_cart_badge_count_display_correct_number method started");
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
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
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        inventoryPage = login();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inventoryPage.isLoginSuccessful());
        inventoryPage.logout();
        softAssert.assertTrue(inventoryPage.isLogoutSuccessful());
        softAssert.assertAll();
    }

    @Test
    public void verify_product_details_is_displayed(){
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        inventoryPage = login();
        assertTrue(inventoryPage.productDetailsIsDisplayedWhenClickingOnTheProduct(PRODUCT_TWO));
    }

//    @Test(expectedExceptions = RuntimeException.class)
//    public void verifyCartBadgeCountFails(){
//        inventoryPage.throwExceptionTest();
//    }

    private InventoryPage login(){
        return loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
    }
}
