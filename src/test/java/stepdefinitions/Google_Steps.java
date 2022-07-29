package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.pages.Google_Page;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Google_Steps {
	
	private Google_Page googlepage = new Google_Page(DriverFactory.getDriver());

	@Given("I go to google page")
	public void i_go_to_google_page() throws InterruptedException {
		
		DriverFactory.getDriver().get("https://www.google.com/");
		Thread.sleep(5000);
	  
	}

	@Then("I type testing inside of search box")
	public void i_type_testing_inside_of_search_box() {
		
		googlepage.enterText();
		
	    
	}

}
