package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.MenuPage;

@Epic("Authentication")
@Feature("Logout Functionality")
@Story("User logout flow validation")
@Test(description = "Verify that a logged-in user can successfully log out and return to the login page")
@Severity(SeverityLevel.CRITICAL)

public class LogoutTest extends BaseTest {

    public void logoutFlowTest() {

        Allure.step("Step 1: Log in as a valid user", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("standard_user", "secret_sauce");

            InventoryPage inventory = new InventoryPage(driver);
            Assert.assertTrue(
                    inventory.isInventoryVisible(),
                    "Inventory page is not visible after login"
            );
        });

        Allure.step("Step 2: Open menu and click Logout", () -> {
            MenuPage menu = new MenuPage(driver);
            menu.openMenu();
            menu.logout();
        });

        Allure.step("Step 3: Verify user is logged out successfully", () -> {
            boolean isBackAtLogin = driver.findElement(By.id("login-button")).isDisplayed();
            Assert.assertTrue(
                    isBackAtLogin,
                    "User was not logged out successfully"
            );
        });

        Allure.step("Test completed: Logout flow executed and verified successfully.");
    };
};
