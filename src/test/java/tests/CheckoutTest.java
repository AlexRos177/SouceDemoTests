package tests;

import base.BaseTest;
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

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutFlowTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        // Add first item to cart
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addFirstItemToCart();
        inventory.goToCart();

        // Proceed to Checkout
        CartPage cart = new CartPage(driver);
        cart.checkout();

        // Wait until first name input is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        // Enter checkout info
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterCheckoutInfo("John", "Doe", "12345");

        // Wait until Finish button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        checkout.finishCheckout();

        // Wait until checkout complete message appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        Assert.assertTrue(checkout.isCheckoutComplete(), "Checkout was not completed");
    }
}
