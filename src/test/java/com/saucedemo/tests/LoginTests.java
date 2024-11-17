package com.saucedemo.tests;

import com.saucedemo.config.ConfigReader;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.testBase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private final String VALID_USERNAME = ConfigReader.get("username");
    private final String VALID_PASSWORD = ConfigReader.get("password");

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
        InventoryPage inventoryPage = loginPage.clickLogin();

        Assert.assertTrue(inventoryPage.isLoginSuccessful(), "Login was not successful");
    }
}
