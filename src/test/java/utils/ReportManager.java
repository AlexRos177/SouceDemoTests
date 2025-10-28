package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {

            String reportDir = System.getProperty("user.dir") + File.separator + "reports";
            new File(reportDir).mkdirs();

            String reportPath = reportDir + File.separator + "SauceDemoReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("SauceDemo Test Report");
            spark.config().setReportName("Automation Results - NewSourceTests");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Optional metadata
            extent.setSystemInfo("Project", "NewSourceTests");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", System.getProperty("user.name"));
        }
        return extent;
    }
}
