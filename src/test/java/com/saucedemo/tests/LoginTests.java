package com.saucedemo.tests;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.ui.pages.InventoryPage;
import com.saucedemo.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void verifyLoginWithValidCredentials() {
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(inventoryPage.isLoginSuccessful(), "Login was not successful");
    }

    @Test
    public void verifyLoginWithInValidCredentials() {
        loginPage.loginWith(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertTrue(loginPage.isPasswordMismatchMessagePresent());
    }

}
