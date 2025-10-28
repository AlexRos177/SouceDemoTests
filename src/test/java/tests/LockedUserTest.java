package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LockedUserTest extends BaseTest {

    @Test
    public void lockedUserCannotLogin() {
        LoginPage login = new LoginPage(driver);
        login.login("locked_out_user", "secret_sauce");

        String errorMsg = login.getErrorMessage();
        Assert.assertTrue(
                errorMsg.contains("Sorry, this user has been locked out."),
                "Locked user error message not shown"
        );
    }
}
