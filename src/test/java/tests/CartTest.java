package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("Shopping Cart")
@Feature("Add Items to Cart")
public class CartTest extends BaseTest {

    @Test(description = "Verify that an items can be added to the cart successfully")
    @Story("Add products to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in as a standard user, adds the items to the cart, navigates to the cart, and verifies that the items is present.")
    public void addToCartTest() {

        Allure.step("Step 1: Log in with valid credentials", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Add the first item to the cart", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.addFirstItemToCart();
        });

        Allure.step("Step 3: Add the second item to the cart", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.addSecondItemToCart();
        });

        Allure.step("Step 4: Add the third item to the cart", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.addThirdItemToCart();
        });

        Allure.step("Step 5: Navigate to the cart page", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.goToCart();
        });

        Allure.step("Step 6: Verify that the item is displayed in the cart", () -> {
            CartPage cart = new CartPage(driver);
            Assert.assertTrue(cart.isItemInCart(), "Item is not in the cart");
        });

    }
}
