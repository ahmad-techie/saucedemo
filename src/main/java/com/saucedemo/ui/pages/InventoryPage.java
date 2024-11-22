package com.saucedemo.ui.pages;

import com.saucedemo.ui.base.BasePage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private final List<String> SORTED_PRICES = List.of("$7.99", "$9.99", "$15.99", "$15.99", "$29.99", "$49.99");
    private final String product1 = ConfigReader.get("product1");
    private final String product2 = ConfigReader.get("product2");

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement sortDropDown;
    @FindBy(xpath = "//div[@class='pricebar']/div")
    private List<WebElement> prices;
    @FindBy(className = "inventory_item_description")
    private List<WebElement> inventory;
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;
    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburgerMenuButton;


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginSuccessful(){
        logger.debug("loginSuccessful method is called");
        String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
        return driver.getCurrentUrl().equals(INVENTORY_PAGE_URL);
    }

    public boolean areProductsSortedByPriceFromLowToHigh(){
        logger.debug("areProductsSortedByPriceFromLowToHigh method is called");
        Select select = new Select(sortDropDown);
        select.selectByValue("lohi");
        List<String> sorted = prices.stream().map(element -> element.getText()).collect(Collectors.toList());
        return SORTED_PRICES.equals(sorted);
    }

    public CartPage gotoCart(){
        click(cartIcon);
        return new CartPage(driver);
    }

    public void addProductToCart(String productToBeAdded) {
        for (WebElement product : inventory){
            String productName = product.findElement(By.className("inventory_item_name")).getText();
            if (productName.equals(productToBeAdded)){
                click(product.findElement(By.tagName("button")));
            }
        }
    }

    public boolean shoppingCartBadgeDisplayCorrectCountOfAddedItems(){
        int numberOfItemsInCart = 2;
        addProductToCart(product1);
        addProductToCart(product2);
        int actualItemsInCart = Integer.parseInt(cartBadge.getText());
        if (actualItemsInCart==numberOfItemsInCart){
            return true;
        }else {
            throw new RuntimeException("Cart items expected to be "+numberOfItemsInCart+" but was "+actualItemsInCart);
        }
    }

    public boolean productDetailsIsDisplayedWhenClickingOnTheProduct(String product){
        String partialUrl = "inventory-item.html";
        for (WebElement p : inventory){
            WebElement productTitle = p.findElement(By.className("inventory_item_name"));
            String productName = productTitle.getText();
            if (productName.equals(product)){
                Actions actions = new Actions(driver);
                actions.click(productTitle).build().perform();
                break;
            }
        }
        return getCurrentUrl().contains(partialUrl);
    }

    public boolean isLogoutSuccessful(){
        return getCurrentUrl().equals(ConfigReader.get("baseUrl"));
    }


    public void logout() {
        click(hamburgerMenuButton);
        click(logoutButton);
    }


    public void throwExceptionTest(){
        throw new RuntimeException("Testing exception handling");
    }
}
