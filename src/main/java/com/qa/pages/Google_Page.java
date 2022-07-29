package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Google_Page {
	
	private WebDriver driver;
	
	//locators:
	
	private By searchBox = By.xpath("//*[@title='Search']");
	
	
	public Google_Page(WebDriver driver) {
		this.driver = driver;
	}

	
	public void enterText() {
		
		driver.findElement(searchBox).sendKeys("testing");
	}
}

