package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Epic("Authentication")
@Feature("Login Functionality")
@Story("Locked user login attempt")
@Test(description = "Locked user cannot log in and sees appropriate error message")
@Severity(SeverityLevel.CRITICAL)

public class LockedUserTest extends BaseTest {

    public void lockedUserCannotLogin() {

        Allure.step("Step 1: Open login page and attempt login with locked user credentials", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("locked_out_user", "secret_sauce");
        });

        Allure.step("Step 2: Retrieve and verify error message for locked user", () -> {
            LoginPage login = new LoginPage(driver);
            String errorMsg = login.getErrorMessage();
            Assert.assertTrue(
                    errorMsg.contains("Sorry, this user has been locked out."),
                    "Locked user error message not shown"
            );
        });
    };
};
