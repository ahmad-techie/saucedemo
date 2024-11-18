package com.saucedemo.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReporter {
    private ExtentReports extent;
    private final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public void initReports() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/test-output/reports/TestReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Regression Suite");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "QA Team");
        }
    }

    public void startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testThread.set(test);
    }

    public ExtentTest getTest() {
        return testThread.get();
    }

    public void endTest() {
        testThread.remove();
    }

    public void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
