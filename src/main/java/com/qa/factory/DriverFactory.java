package com.qa.factory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	public WebDriver driver;
	private Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	@SuppressWarnings("deprecation")
	public WebDriver init_driver(String browser) {
		
		System.out.println("browser value is: " + browser);
		
		if (browser.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "/Users/15713/Documents/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			
			tlDriver.set(new ChromeDriver(options));
		}else {
			System.out.println("Please pass the correct browser value: " + browser);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		return getDriver();
		
	}
	
	public Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./scr/test/resources/config/config.properties");
			prop.load(ip);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
