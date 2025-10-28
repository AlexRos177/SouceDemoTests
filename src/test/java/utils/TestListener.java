package utils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ReportManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        ExtentTest test = testThread.get();
        test.log(Status.FAIL, "❌ Test Failed: " + result.getThrowable());

        if (driver != null) {
            try {
                String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.log(Status.WARNING, "⚠️ Screenshot capture failed: " + e.getMessage());
            }
        } else {
            test.log(Status.WARNING, "⚠️ WebDriver was null — screenshot not taken");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("\n📊 Extent Report generated at:");
        System.out.println(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "SauceDemoReport.html\n");
    }

    private String takeScreenshot(String testName, WebDriver driver) throws IOException {
        String folder = System.getProperty("user.dir") + "/reports/screenshots/";
        Files.createDirectories(Paths.get(folder));

        String path = folder + testName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), Paths.get(path));
        return path;
    }
}
