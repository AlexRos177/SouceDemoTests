package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Epic("Website Navigation")
@Feature("Footer Links")
public class FooterLinksTest extends BaseTest {

    @Test(description = "Verify that all footer links are valid and reachable after login")
    @Story("Footer link validation for logged-in user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Logs into SauceDemo, verifies that all footer links (X, Facebook, LinkedIn) return valid HTTP responses.")
    @Step("Get HTTP response code for {url}")
    private int getResponseCode(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
        return responseCode;
    }

    @Test(description = "Verify footer links are valid or accessible")
    public void validateFooterLinks() throws IOException {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Inventory page not loaded");

        List<WebElement> footerLinks = driver.findElements(By.cssSelector(".social a"));
        for (WebElement link : footerLinks) {
            String url = link.getAttribute("href");
            int code = getResponseCode(url);

            Allure.step("Response code for " + url + " = " + code);

            Assert.assertTrue(
                    (code < 400) || (code == 403) || (code >= 300 && code < 400),
                    "Broken or restricted link: " + url + " [code: " + code + "]"
            );
        }
    }
}
