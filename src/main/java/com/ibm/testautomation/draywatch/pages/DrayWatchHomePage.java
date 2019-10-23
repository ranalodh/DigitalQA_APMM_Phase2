package com.ibm.testautomation.draywatch.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.ibm.testautomation.common.pages.BasePage;
import com.ibm.testautomation.util.ActionLib;
import com.ibm.testautomation.util.PropertiesFileReader;

public class DrayWatchHomePage extends BasePage {
	
	ActionLib actionLib = new ActionLib();
	PropertiesFileReader obj = new PropertiesFileReader();
	
	private By logon = By.xpath("//input[@name='ShortName']");
	private By btnlogon = By.xpath("//img[contains(@src,'arrowOff.gif')]/ancestor::a");
	private By lblWelcome = By.xpath("//img[contains(@src,'spacer.gif')]/ancestor::tr[1]");
	
	public String getTitle(WebDriver driver) throws Exception {
		Thread.sleep(4000);
		return driver.getTitle();
	}

	public void enterShortNameandClickGo(WebDriver driver) throws Throwable {
		
		Properties properties = obj.getProperty();
		
		actionLib.sendValueToInput(logon, driver, properties.getProperty("shortname"));
		actionLib.javascriptClick(btnlogon, driver);
		//actionLib.click(btnlogon, driver);	
	}
	
	public boolean validateWelcomeLabel(WebDriver driver) throws Throwable {
		
		return actionLib.isElementExist(lblWelcome, driver);

	}
	
}
