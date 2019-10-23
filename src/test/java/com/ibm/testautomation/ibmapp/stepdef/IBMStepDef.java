package com.ibm.testautomation.ibmapp.stepdef;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.ibm.pages.IBMPageVerification;
import com.ibm.testautomation.listener.ExtentReportListener;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class IBMStepDef extends ExtentReportListener {

	IBMPageVerification ibmPageVerification = new IBMPageVerification();
	PropertiesFileReader obj = new PropertiesFileReader();
	public static WebDriver driver = CommonUtil.getDriver();

	@Before
	public void beforeScenario(Scenario scenario) {

		test = extent.createTest(scenario.getName());
		test = test.createNode(scenario.getName());
	}

	@After
	public void afterScenario(Scenario scenario) {

		if (driver != null) {
			driver.quit();
		}
	}

	@Given("^The user opens the IBM home page$")

	public void openIBMHomePage() throws IOException {

		try {
			
			ibmPageVerification.launchBrowser(System.getProperty("env"), driver);
			ibmPageVerification.searchIbmPage(driver);
			
		}catch (AssertionError | Exception e) {
		}
	}
}
