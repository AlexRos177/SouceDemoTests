package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

@Epic("E-Commerce Platform")
@Feature("Checkout Process")
public class CheckoutTest extends BaseTest {

    @Test(description = "Verify that a user can complete the full checkout flow successfully")
    @Story("Complete purchase of an item")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in, adds an item to the cart, enters checkout information, and verifies successful checkout completion.")
    public void checkoutFlowTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Allure.step("Step 1: Log in with valid credentials", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Add first item to the cart", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            inventory.addFirstItemToCart();
            inventory.goToCart();
        });

        Allure.step("Step 3: Proceed to checkout", () -> {
            CartPage cart = new CartPage(driver);
            cart.checkout();
        });

        Allure.step("Step 4: Enter checkout information", () -> {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            CheckoutPage checkout = new CheckoutPage(driver);
            checkout.enterCheckoutInfo("John", "Doe", "12345");
        });

        Allure.step("Step 5: Finish checkout and verify success", () -> {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
            CheckoutPage checkout = new CheckoutPage(driver);
            checkout.finishCheckout();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
            Assert.assertTrue(checkout.isCheckoutComplete(), "Checkout was not completed");
        });
    }
}
