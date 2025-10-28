package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    private By inventoryContainer = By.id("inventory_container");
    private By firstItemAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartButton = By.id("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isInventoryVisible() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }

    public void addFirstItemToCart() {
        driver.findElement(firstItemAddButton).click();
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }
}
