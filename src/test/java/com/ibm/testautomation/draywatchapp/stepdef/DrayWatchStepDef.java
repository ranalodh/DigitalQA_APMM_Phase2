package com.ibm.testautomation.draywatchapp.stepdef;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.ibm.testautomation.common.pages.BasePage;
import com.ibm.testautomation.draywatch.pages.DrayWatchHomePage;
import com.ibm.testautomation.draywatch.pages.DrayWatchLoginPage;
import com.ibm.testautomation.draywatch.pages.DrayWatchViewSegmentsPage;
import com.ibm.testautomation.listener.ExtentReportListener;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DrayWatchStepDef extends ExtentReportListener {

	BasePage basePage = new BasePage();
	DrayWatchHomePage drayWatchHomePage = new DrayWatchHomePage();
	DrayWatchLoginPage drayWatchloginPage = new DrayWatchLoginPage();
	DrayWatchViewSegmentsPage drayWatchViewSegmentPage = new DrayWatchViewSegmentsPage();	
	PropertiesFileReader obj = new PropertiesFileReader();
	
	public static WebDriver driver = CommonUtil.getDriver();
	ExtentTest logInfo = null;
	SoftAssert softAssert = new SoftAssert();
	CommonUtil commonUtil = new CommonUtil();

	/**
	 * 
	 * @param scenario
	 */
	@Before
	public void beforeScenario(Scenario scenario) {

		test = extent.createTest(scenario.getName());
		test = test.createNode(scenario.getName());
	}

	/**
	 * 
	 * @param scenario
	 */
	@After
	public void afterScenario(Scenario scenario) {

		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	@Given("^The User Opens The DrayWatch Application$")
	public void the_user_opens_the_draywatch_application() throws Throwable {
		
		Properties properties = obj.getProperty();

		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "The User Opens The DrayWatch Application");
			basePage.launchBrowser(System.getProperty("env"), driver);
			Assert.assertEquals(basePage.getTitle(driver), properties.getProperty("homepage.page.title"));
			logInfo.pass("DrayWatch Home Page Opened Successfully - URL -" + System.getProperty("env"));	

		}catch(AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			Assert.fail();
		}
	}

	@When("^The User Enters the Log On Information$")
	public void the_user_enters_the_log_on_information() throws Throwable {
		
		Properties properties = obj.getProperty();

		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "The User Enters the Log On Information");
			drayWatchHomePage.enterShortNameandClickGo(driver);
			logInfo.pass("Successfully entered the Short Name");	
			
			Assert.assertEquals(drayWatchHomePage.validateWelcomeLabel(driver), true);
			logInfo.pass("Login page open Successful");	

		}catch(AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			Assert.fail();
		}
	}

	@And("^Enters the Login credential And click On Go$")
	public void enters_the_login_credential_and_click_on_go() throws Throwable {

		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "Enters the Login credential And click On Go"); 
			drayWatchloginPage.loginToDrayWatch(driver);
			logInfo.pass("Successfully entered the login creential");		

		}catch(AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			Assert.fail();
		}
	}

	@Then("^Verify The User Is Able To Open DrayWatch Application$")
	public void verify_the_user_is_able_to_open_draywatch_application() throws Throwable {
		
		Properties properties = obj.getProperty();

		try {
			logInfo = test.createNode(new GherkinKeyword("Given"), "Verify The User Is Able To Open DrayWatch Application");
			
			Assert.assertEquals(drayWatchViewSegmentPage.getTitle(driver).substring(0, 9), properties.getProperty("viewsegmentspage.page.title"));
			logInfo.pass("DrayWatch Application Login successful");

		}catch(AssertionError | Exception e) {
			testStepHandle("FAIL", driver, logInfo, e.getLocalizedMessage());
			logInfo.addScreenCaptureFromPath(captureScreenShot(driver));
			Assert.fail();
		}
	}
}
