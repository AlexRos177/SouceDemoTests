package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class TestUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public TestUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Wait for element to be visible
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // 🔹 Wait for element to be clickable
    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // 🔹 Take screenshot (useful for debugging failed tests)
    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
        String fullPath = screenshotDir + fileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Path.of(screenshotDir));
            Files.copy(srcFile.toPath(), Path.of(fullPath));
            System.out.println("✅ Screenshot saved: " + fullPath);
        } catch (IOException e) {
            System.err.println("❌ Failed to save screenshot: " + e.getMessage());
        }

        return fullPath;
    }

    // 🔹 Generate random string (useful for fake data)
    public String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 🔹 Generate random ZIP code (5 digits)
    public String randomZipCode() {
        Random random = new Random();
        int zip = 10000 + random.nextInt(90000);
        return String.valueOf(zip);
    }
}
