package com.saucedemo.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private TestReporter reporter;

    @Override
    public void onStart(ITestContext context) {
        reporter = new TestReporter();
        reporter.initReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        reporter.startTest(result.getMethod().getMethodName(), "Test Started: " + result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        reporter.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        reporter.getTest().fail("Test Failed: " +result.getMethod()+" "+result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        reporter.getTest().skip("Test Skipped: " + result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        reporter.flushReports();
    }


}