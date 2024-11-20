package com.saucedemo.tests;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.ui.pages.InventoryPage;
import com.saucedemo.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verify_login_with_valid_credentials() {
        logger.debug("verify_login_with_valid_credentials started");
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(inventoryPage.isLoginSuccessful(), "Login was not successful");
        logger.debug("verify_login_with_valid_credentials completed");
    }

    @Test
    public void verify_login_with_invalid_credentials() {
        logger.debug("verify_login_with_invalid_credentials started");
        loginPage.loginWith(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertTrue(loginPage.isPasswordMismatchMessagePresent());
        logger.debug("verify_login_with_invalid_credentials completed");
    }

}
