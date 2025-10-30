package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

@Epic("Authentication")
@Feature("Login Functionality")
@Story("Data-driven login validation")
@Test(description = "Verify multiple login scenarios with different users and expected outcomes")
@Severity(SeverityLevel.CRITICAL)
public class LoginDataTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "valid"},
                {"locked_out_user", "secret_sauce", "locked"},
                {"problem_user", "secret_sauce", "valid"},
                {"wrong_user", "wrong_pass", "invalid"}
        };
    };

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        Allure.step("Step 1: Open login page and enter credentials for user: " + username, () -> {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
        });

        switch (expectedResult) {
            case "valid":
                Allure.step("Step 2: Validate successful login for user: " + username, () -> {
                    InventoryPage inventory = new InventoryPage(driver);
                    Assert.assertTrue(
                            inventory.isInventoryVisible(),
                            "Expected login to succeed for user: " + username
                    );
                });
                break;

            case "locked":
                Allure.step("Step 2: Validate locked user message appears for user: " + username, () -> {
                    LoginPage loginPage = new LoginPage(driver);
                    String error = loginPage.getErrorMessage();
                    Assert.assertTrue(
                            error.contains("locked out"),
                            "Expected locked user message for user: " + username
                    );
                });
                break;

            case "invalid":
                Allure.step("Step 2: Validate invalid credentials message appears for user: " + username, () -> {
                    LoginPage loginPage = new LoginPage(driver);
                    String error = loginPage.getErrorMessage();
                    Assert.assertTrue(
                            error.contains("do not match"),
                            "Expected invalid credentials message for user: " + username
                    );
                });
                break;

            default:
                Allure.step("Unexpected test data result type: " + expectedResult, () -> {
                    Assert.fail("Unexpected test data result type: " + expectedResult);
                });
        };

        Allure.step("Step 3: Test completed for user: " + username + " (expected result: " + expectedResult + ")");
    };
};
