package AppHooks;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import com.qa.util.SendEmailAttachment;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks {
	
	private DriverFactory driverfactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	
	@Before(order =1)
	public void launchBrowser() { 
		String browserName = prop.getProperty("browser");
		driverfactory = new DriverFactory();
		driver = driverfactory.init_driver(browserName);
	}

	@After(order =0)
	public void quitBrowser() {
		driver.quit();
	}
	
	@After(order = 1)
	public void sendAttachmentEmail() {
		SendEmailAttachment sendattachment = new SendEmailAttachment();
		sendattachment.driverfactory.init_driver(null);
	}
}
