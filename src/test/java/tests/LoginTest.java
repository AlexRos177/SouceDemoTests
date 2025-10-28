package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {
        Allure.step("Open login page");
        Allure.step("Enter valid username and password");
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        Assert.assertTrue(inventory.isInventoryVisible(), "Inventory page is not visible");
    }

    @Test
    public void invalidLoginTest() {
        Allure.step("Open login page");
        Allure.step("Enter invalid username and password");
        LoginPage login = new LoginPage(driver);
        login.login("wrong_user", "wrong_password");

        Assert.assertTrue(login.getErrorMessage().contains("Username and password do not match"),
                "Error message not displayed");
    }
}
