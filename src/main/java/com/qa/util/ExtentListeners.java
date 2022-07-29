package com.qa.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentListeners implements ITestListener{

	static Date d = new Date();
	
	static String filename = "Google_" + d.toString().replace(":", "_") + ".html";
	
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports"+filename);
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase  : "+result.getMethod().getMethodName());
		testReport.set(test);
		
		test.log(Status.INFO, "Result is: " + d);
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ "PASSED"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
	}
	
	public void onTestFailure(ITestResult result) {
		
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured: Click to see"
		+ "</font>" + "</b>" + "</summary" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		try {
			ExtentManager.captureScreenShot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		}catch (IOException e) {
		
		
	}
		
		String failureLog= "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		testReport.get().skip(m);
}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	public void onStart(ITestContext context) {
		
	}
	
	public void onFinish(ITestContext context) {
		
		if (extent !=null) {
			extent.flush();
		}
	}

	
	}
	
