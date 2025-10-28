package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By firstItemName = By.cssSelector(".inventory_item_name");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemInCart() {
        return driver.findElement(firstItemName).isDisplayed();
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }
}
