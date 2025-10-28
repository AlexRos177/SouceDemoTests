package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSortTest extends BaseTest {

    @Test
    public void sortProductsAZ() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        // Select A→Z sort option
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='az']")).click();

        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        List<String> sortedNames = names.stream().map(WebElement::getText).sorted().collect(Collectors.toList());

        Assert.assertEquals(
                names.stream().map(WebElement::getText).collect(Collectors.toList()),
                sortedNames,
                "Products are not sorted A→Z"
        );
    }

    @Test
    public void sortProductsZA() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        // Select Z→A sort option
        driver.findElement(By.className("product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='za']")).click();

        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        List<String> sortedNames = names.stream().map(WebElement::getText).sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());

        Assert.assertEquals(
                names.stream().map(WebElement::getText).collect(Collectors.toList()),
                sortedNames,
                "Products are not sorted Z→A"
        );
    }
}
