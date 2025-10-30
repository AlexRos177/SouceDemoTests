package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("Authentication")
@Feature("Login Functionality")
@Severity(SeverityLevel.CRITICAL)

public class LoginTest extends BaseTest {

    @Test(description = "Verify login works with valid credentials")
    @Story("Successful login with valid user")
    public void validLoginTest() {

        Allure.step("Step 1: Open login page and enter valid credentials", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Verify that inventory page is visible", () -> {
            InventoryPage inventory = new InventoryPage(driver);
            Assert.assertTrue(inventory.isInventoryVisible(), "Inventory page is not visible");
        });

        Allure.step("Test completed: Valid login verified successfully.");
    }

    @Test(description = "Verify login fails with invalid credentials")
    @Story("Login attempt with invalid username and password")
    public void invalidLoginTest() {

        Allure.step("Step 1: Open login page and enter invalid credentials", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("wrong_user", "wrong_password");
        });

        Allure.step("Step 2: Verify error message for invalid credentials", () -> {
            LoginPage login = new LoginPage(driver);
            Assert.assertTrue(
                    login.getErrorMessage().contains("Username and password do not match"),
                    "Error message not displayed"
            );
        });

        Allure.step("Test completed: Invalid login error verified successfully.");
    };

    @Test(description = "Verify login fails when password is incorrect")
    @Story("Login attempt with valid username and invalid password")
    public void invalidPasswordTest() {

        Allure.step("Step 1: Open login page and enter valid username with invalid password", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("standard_user", "wrong_password");
        });

        Allure.step("Step 2: Verify error message for invalid password", () -> {
            LoginPage login = new LoginPage(driver);
            Assert.assertTrue(
                    login.getErrorMessage().contains("Username and password do not match"),
                    "Error message not displayed"
            );
        });

        Allure.step("Test completed: Invalid password error verified successfully.");
    };

    @Test(description = "Verify login fails when username is incorrect")
    @Story("Login attempt with invalid username and valid password")
    public void invalidUsernameTest() {

        Allure.step("Step 1: Open login page and enter invalid username with valid password", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("new_user", "secret_sauce");
        });

        Allure.step("Step 2: Verify error message for invalid username", () -> {
            LoginPage login = new LoginPage(driver);
            Assert.assertTrue(
                    login.getErrorMessage().contains("Username and password do not match"),
                    "Error message not displayed"
            );
        });

        Allure.step("Test completed: Invalid username error verified successfully.");
    };
};
