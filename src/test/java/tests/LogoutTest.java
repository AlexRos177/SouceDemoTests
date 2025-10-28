package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.MenuPage;

public class LogoutTest extends BaseTest {

    @Test
    public void logoutFlowTest() {
        // Step 1: Login
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        Assert.assertTrue(inventory.isInventoryVisible(), "Inventory page is not visible after login");

        // Step 2: Open menu and logout
        MenuPage menu = new MenuPage(driver);
        menu.openMenu();
        menu.logout();

        // Step 3: Verify logout by checking if login button reappears
        boolean isBackAtLogin = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(isBackAtLogin, "User was not logged out successfully");
    }
}
