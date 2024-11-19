package com.saucedemo.tests;

import com.saucedemo.utils.ConfigReader;
import com.saucedemo.ui.pages.InventoryPage;
import com.saucedemo.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private final String VALID_USERNAME = ConfigReader.get("username");
    private final String VALID_PASSWORD = ConfigReader.get("password");
    private final String INVALID_USERNAME = "fake@123";
    private final String INVALID_PASSWORD = "invalid_pass";

    @Test
    public void testValidLogin() {
        InventoryPage inventoryPage = loginPage.loginWith(VALID_USERNAME, VALID_PASSWORD);
        Assert.assertTrue(inventoryPage.isLoginSuccessful(), "Login was not successful");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.loginWith(INVALID_USERNAME, INVALID_PASSWORD);
        Assert.assertTrue(loginPage.isPasswordMismatchMessagePresent());
    }

}
