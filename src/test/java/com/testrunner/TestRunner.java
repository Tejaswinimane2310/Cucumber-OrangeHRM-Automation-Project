package com.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions (
		
	    features = {"./Feature/Login.feature"},
		glue = "com.stepdefination",
	    plugin = {
						
						"pretty",
						"html:reports/myreport.html"
				},
		dryRun = false,
		tags = "@smoke"
		
		)

public class TestRunner {

}
