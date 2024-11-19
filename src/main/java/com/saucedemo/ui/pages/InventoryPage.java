package com.saucedemo.ui.pages;

import com.saucedemo.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private final List<String> SORTED_PRICES = List.of("$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99");
    private final String product1 = "Sauce Labs Onesie";

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement sortDropDown;
    @FindBy(xpath = "//div[@class='pricebar']/div")
    List<WebElement> prices;
    @FindBy(className = "inventory_item_description")
    List<WebElement> boxes;
    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;
    @FindBy(className = "inventory_item_name")
    WebElement productTitleInCart;


    private final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginSuccessful(){
        logger.debug("loginSuccessful method is called");
        return driver.getCurrentUrl().equals(INVENTORY_PAGE_URL);
    }

    public boolean areProductsSortedByPriceFromLowToHigh(){
        logger.debug("areProductsSortedByPriceFromLowToHigh method is called");
        Select select = new Select(sortDropDown);
        select.selectByValue("lohi");
        List<String> sorted = prices.stream().map(element -> element.getText()).collect(Collectors.toList());
        return SORTED_PRICES.equals(sorted);
    }

    public boolean isProductAddedToCart(){
        logger.debug("isProductAddedToCart method is called");
        for (WebElement productBox : boxes){
            WebElement productTitle = productBox.findElement(By.className("inventory_item_name"));
            if (productTitle.getText().equals(product1)){
                productBox.findElement(By.tagName("button")).click();

            }
        }
        click(cartIcon);
        return getText(productTitleInCart).equals(product1);
    }
}
