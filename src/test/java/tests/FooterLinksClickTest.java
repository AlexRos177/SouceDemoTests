package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Epic("Website Navigation")
@Feature("Footer Links")
@Test(description = "Verify that all footer links open the correct pages. " +
        "Logs in, clicks each footer link (X, Facebook, LinkedIn)")
@Story("Footer link navigation")
@Severity(SeverityLevel.CRITICAL)

public class FooterLinksClickTest extends BaseTest {

    public void verifyFooterLinksNavigation() {
        Allure.step("Step 1: Log in with valid credentials", () -> {
            LoginPage login = new LoginPage(driver);
            login.login("standard_user", "secret_sauce");
        });

        Allure.step("Step 2: Verify Inventory page loaded", () -> {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                    "Inventory page not loaded after login");
        });

        List<WebElement> footerLinks = driver.findElements(By.cssSelector(".social a"));
        Allure.step("Found " + footerLinks.size() + " footer links");

        List<String> expectedDestinations = new ArrayList<>();
        expectedDestinations.add("x.com/saucelabs");
        expectedDestinations.add("facebook.com/saucelabs");
        expectedDestinations.add("linkedin.com/company/sauce-labs");

        String mainWindow = driver.getWindowHandle();

        for (int i = 0; i < footerLinks.size(); i++) {
            WebElement link = footerLinks.get(i);
            String href = link.getAttribute("href");

            Allure.step("Clicking footer link: " + href);

            link.click();
            Set<String> windowHandles = driver.getWindowHandles();
            Assert.assertTrue(windowHandles.size() > 1, "No new tab opened after clicking " + href);

            for (String handle : windowHandles) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    break;
                };
            };

            Allure.step("Switched to new tab: " + driver.getCurrentUrl());

            String currentUrl = driver.getCurrentUrl();
            boolean isExpected = expectedDestinations.stream().anyMatch(currentUrl::contains);

            Assert.assertTrue(isExpected, "Unexpected destination: " + currentUrl);

            driver.close();
            driver.switchTo().window(mainWindow);
        };
    };
};
