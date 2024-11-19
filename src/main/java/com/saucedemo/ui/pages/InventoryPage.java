package com.saucedemo.ui.pages;

import com.saucedemo.ui.base.BasePage;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginSuccessful(){
        return driver.getCurrentUrl().equals(INVENTORY_PAGE_URL);
    }
}
