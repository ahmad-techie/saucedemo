package com.saucedemo.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucedemo.testBase.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("test listener started");
        initReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        startTest(result.getMethod().getMethodName(), "Test Started: " + result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getTest().fail("Test Failed: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getTest().skip("Test Skipped: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        flushReports();
    }

    //    ############################################################ Extent Reports ##################################################################

    private ExtentReports extent;
    private ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    /**
     * Initialize the ExtentReports instance with a specific report path.
     */
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

    /**
     * Create a new test entry in the report for each test case.
     * @param testName the name of the test case
     * @param description a brief description of the test
     */
    public void startTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testThread.set(test);
    }

    /**
     * Retrieve the current test instance for logging in the report.
     * @return ExtentTest instance associated with the current thread
     */
    public ExtentTest getTest() {
        return testThread.get();
    }

    /**
     * Remove the test instance associated with the current thread after each test.
     */
    public void endTest() {
        testThread.remove();
    }

    /**
     * Write everything to the report and close the report instance.
     */
    public void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

//    @Override
//    public void onStart(ITestContext context) {
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//    }

}