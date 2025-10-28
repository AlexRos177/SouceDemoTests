package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void verifyProductDetailsDisplayed() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        WebElement firstProduct = driver.findElement(By.className("inventory_item"));
        String productName = firstProduct.findElement(By.className("inventory_item_name")).getText();
        String productPrice = firstProduct.findElement(By.className("inventory_item_price")).getText();
        boolean imageDisplayed = firstProduct.findElement(By.className("inventory_item_img")).isDisplayed();

        Assert.assertNotNull(productName, "Product name is missing");
        Assert.assertTrue(productPrice.startsWith("$"), "Price format is invalid");
        Assert.assertTrue(imageDisplayed, "Product image not displayed");
    }
}
