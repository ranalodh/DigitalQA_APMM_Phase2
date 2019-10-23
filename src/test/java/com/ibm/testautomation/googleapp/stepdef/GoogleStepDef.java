package com.ibm.testautomation.googleapp.stepdef;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.ibm.testautomation.google.pages.GoogleVerification;
import com.ibm.testautomation.listener.ExtentReportListener;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class GoogleStepDef extends ExtentReportListener{

	GoogleVerification googleVerification = new GoogleVerification();
	PropertiesFileReader obj = new PropertiesFileReader();
	ExtentTest logInfo = null;

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

	@Given("^The user opens the Google home page$")
	public void the_user_opens_the_google_home_page() throws Throwable {
		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "The user opens the Google home page");
			googleVerification.launchBrowser(System.getProperty("env"), driver);
			logInfo.pass("Google Home Page Opened Successfully - URL -" + System.getProperty("env"));		
			
			googleVerification.searchGooglePage(driver);
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			//Assert.fail();
		}
	}

}
