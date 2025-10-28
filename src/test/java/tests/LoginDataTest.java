package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginDataTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", "valid"},
                {"locked_out_user", "secret_sauce", "locked"},
                {"problem_user", "secret_sauce", "valid"},
                {"wrong_user", "wrong_pass", "invalid"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        switch (expectedResult) {
            case "valid":
                InventoryPage inventory = new InventoryPage(driver);
                Assert.assertTrue(inventory.isInventoryVisible(),
                        "Expected login to succeed for user: " + username);
                break;

            case "locked":
                Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                        "Expected locked user message for user: " + username);
                break;

            case "invalid":
                Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"),
                        "Expected invalid credentials message for user: " + username);
                break;

            default:
                Assert.fail("Unexpected test data result type: " + expectedResult);
        }
    }
}
