package com.qa.util;

import java.io.File;
import java.io.IOException;

import java.sql.DriverManager;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(fileName);
		
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setDocumentTitle(fileName);
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setReportName(fileName);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Automation Tester", "German Mendez");
		extent.setSystemInfo("Organization", "Google");
		extent.setSystemInfo("Build no", "1");
		
		return extent;	
	}
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenShot() {
		
		File scrFile = ((TakesScreenshot) DriverManager.getDrivers()).getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
