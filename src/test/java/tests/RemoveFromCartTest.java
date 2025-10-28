package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class RemoveFromCartTest extends BaseTest {

    @Test
    public void removeItemFromCart() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addFirstItemToCart();
        inventory.goToCart();

        // Remove item
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        // Verify cart is empty
        boolean isEmpty = driver.findElements(By.cssSelector(".inventory_item_name")).isEmpty();
        Assert.assertTrue(isEmpty, "Cart is not empty after removing item");
    }
}

