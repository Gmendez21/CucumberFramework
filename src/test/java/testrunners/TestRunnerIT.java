package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = {"src/test/resources/AppFeatures/Search.feature"},
	        glue = {"stepdefinitions", "AppHooks"},
	        //tags = "",
	        plugin = {"pretty",
	        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	        		"timeline:test-output-thread/"
	        
	        }, 
	        monochrome = true, 
	        publish = false 
	        )

public class TestRunnerIT {

	}


