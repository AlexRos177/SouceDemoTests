package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

@Epic("Product Display")
@Feature("Sorting Functionality")
@Story("Verify product sorting order by name")
@Test(description = "Verify that products are sorted A→Z/Z→A correctly")
@Severity(SeverityLevel.NORMAL)

public class ProductSortTest extends BaseTest {

    public void sortProductsAZ() {

        Allure.step("Step 1: Log in as standard user", () -> {
            new LoginPage(driver).login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Select sorting option A→Z", () -> {
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.cssSelector("option[value='az']")).click();
        });

        Allure.step("Step 3: Retrieve product names after sorting", () -> {
            List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
            List<String> displayedNames = names.stream().map(WebElement::getText).collect(Collectors.toList());
            List<String> sortedNames = displayedNames.stream().sorted().collect(Collectors.toList());

            Allure.step("Step 4: Compare displayed order with expected A→Z order");
            Assert.assertEquals(displayedNames, sortedNames, "Products are not sorted A→Z");
        });

        Allure.step("Test completed: Verified A→Z product sorting successfully.");
    };

    public void sortProductsZA() {

        Allure.step("Step 1: Log in as standard user", () -> {
            new LoginPage(driver).login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Select sorting option Z→A", () -> {
            driver.findElement(By.className("product_sort_container")).click();
            driver.findElement(By.cssSelector("option[value='za']")).click();
        });

        Allure.step("Step 3: Retrieve product names after sorting", () -> {
            List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
            List<String> displayedNames = names.stream().map(WebElement::getText).collect(Collectors.toList());
            List<String> sortedNames = displayedNames.stream()
                    .sorted((a, b) -> b.compareTo(a))
                    .collect(Collectors.toList());

            Allure.step("Step 4: Compare displayed order with expected Z→A order");
            Assert.assertEquals(displayedNames, sortedNames, "Products are not sorted Z→A");
        });

        Allure.step("Test completed: Verified Z→A product sorting successfully.");
    };
};
