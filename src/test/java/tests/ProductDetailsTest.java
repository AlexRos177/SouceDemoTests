package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("Product Display")
@Feature("Product Details")
@Story("Verify product details are displayed correctly on inventory page")
@Severity(SeverityLevel.NORMAL)

public class ProductDetailsTest extends BaseTest {

    @Test(description = "Verify that product name, price, and image are displayed for each item")
    public void verifyProductDetailsDisplayed() {

        Allure.step("Step 1: Log in with a valid user", () -> {
            new LoginPage(driver).login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Locate the first product in the inventory list", () -> {
            WebElement firstProduct = driver.findElement(By.className("inventory_item"));

            Allure.step("Step 3: Retrieve product name", () -> {
                String productName = firstProduct.findElement(By.className("inventory_item_name")).getText();
                Assert.assertNotNull(productName, "Product name is missing");
            });

            Allure.step("Step 4: Retrieve and validate product price format", () -> {
                String productPrice = firstProduct.findElement(By.className("inventory_item_price")).getText();
                Assert.assertTrue(productPrice.startsWith("$"), "Price format is invalid");
            });

            Allure.step("Step 5: Verify product image is displayed", () -> {
                boolean imageDisplayed = firstProduct.findElement(By.className("inventory_item_img")).isDisplayed();
                Assert.assertTrue(imageDisplayed, "Product image not displayed");
            });
        });

        Allure.step("Test completed: Product details (name, price, image) verified successfully.");
    };
};
