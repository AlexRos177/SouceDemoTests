package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("Shopping Cart")
@Feature("Remove Items from Cart")
@Story("Verify that user can remove item from the cart successfully")
@Test(description = "Verify that an item can be removed from the cart and cart becomes empty")
@Severity(SeverityLevel.CRITICAL)

public class RemoveFromCartTest extends BaseTest {

    public void removeItemFromCart() {

        Allure.step("Step 1: Log in with a valid user", () -> {
            new LoginPage(driver).login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Add the first available product to the cart", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.addFirstItemToCart();
        });

        Allure.step("Step 3: Navigate to the cart page", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.goToCart();
        });

        Allure.step("Step 4: Remove item from the cart", () -> {
            driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        });

        Allure.step("Step 5: Verify the cart is empty after removal", () -> {
            boolean isEmpty = driver.findElements(By.cssSelector(".inventory_item_name")).isEmpty();
            Assert.assertTrue(isEmpty, "Cart is not empty after removing item");
        });

        Allure.step("Test completed: Cart item removed successfully and verified as empty.");
    };
};
