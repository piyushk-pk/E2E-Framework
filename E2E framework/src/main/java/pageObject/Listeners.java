package pageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import piyush.E2EFrameworkProject_Section29.Base1;
import resources.ExtentReporterNG;

/*
 * IF THERE IS AN ERROR "package org.testng not found" then do the following steps
 * Error came after including listeners or after including tentNg libraries
 * 1. Go to pom file
 * 2. click on Dependency tab
 * 3. Click on testng
 * 4. Click on properties
 * 5.Change source from "test" to "Compile"
 */

public class Listeners extends Base1 implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();

	// For parallel execution
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());

		// For parallel execution
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		// For Sequential execution
		// test.log(Status.PASS, "Test Passed");

		// For parallel execution
		extentTest.get().log(Status.PASS, "Test Passed");

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		// Failure logs will be retrieved and integrated to Extent reports
		extentTest.get().fail(result.getThrowable());

		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			// to get the instance of the driver from failed test cases
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			// Integrating screenshots in extend reports
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver),
					result.getMethod().getMethodName());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
