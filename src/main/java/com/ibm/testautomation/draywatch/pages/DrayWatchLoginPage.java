package com.ibm.testautomation.draywatch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.util.ActionLib;

public class DrayWatchLoginPage {
	
	ActionLib actionLib = new ActionLib();
		
	private By txtLoginId = By.xpath("//input[@name='loginName']");
	private By txtPassword = By.xpath("//input[@name='password']");
	private By btnGo = By.xpath("//input[contains(@src,'button_goyellow.gif')]");
	
	public String getTitle(WebDriver driver) throws Exception {
		Thread.sleep(4000);
		return driver.getTitle();
	}
	
	public void loginToDrayWatch(WebDriver driver) throws Throwable {
		
		actionLib.sendValueToInput(txtLoginId, driver, System.getProperty("username"));
		actionLib.sendValueToInput(txtPassword, driver, System.getProperty("password"));
		actionLib.click(btnGo, driver);
	}
}
