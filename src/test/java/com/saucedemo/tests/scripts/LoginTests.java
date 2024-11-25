package com.saucedemo.tests.scripts;

import com.saucedemo.constants.SharedConstants;
import com.saucedemo.pages.pages.InventoryPage;
import com.saucedemo.pages.pages.LoginPage;
import com.saucedemo.tests.base_test.BaseTest;
import com.saucedemo.util.DataReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest implements SharedConstants {

    private LoginPage loginPage;

    @Test
    public void verify_login_with_valid_credentials() {
        logger.debug("verify_login_with_valid_credentials started");
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(inventoryPage.isLoginSuccessful(), "Login was not successful");
        logger.debug("verify_login_with_valid_credentials completed");
    }

    @Test
    public void verify_login_with_invalid_credentials() {
        logger.debug("verify_login_with_invalid_credentials started");
        loginPage = new LoginPage(driver);
        loginPage.navigateTo(BASE_URL);
        loginPage.loginWith(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertTrue(loginPage.isPasswordMismatchMessagePresent());
        logger.debug("verify_login_with_invalid_credentials completed");
    }

}
