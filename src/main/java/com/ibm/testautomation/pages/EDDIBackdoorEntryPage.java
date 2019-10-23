package com.ibm.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.common.pages.BasePage;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

public class EDDIBackdoorEntryPage extends BasePage {

	private By username = By.id("Backgate_username");
	private By submit = By.id("Backgate__loginStart");
	PropertiesFileReader obj = new PropertiesFileReader();
	CommonUtil commonUtil = new CommonUtil();

	/**
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String getTitle(WebDriver driver) throws Exception {
		Thread.sleep(2000);
		return driver.getTitle();
	}

	/**
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void loginEDDI(WebDriver driver) throws Exception

	{
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(System.getProperty("username"));
		// commonUtil.hightlightElement(username, driver);
		// commonUtil.hightlightElement(submit, driver);
		new CommonUtil().captureScreen(driver, commonUtil.getAppPort(System.getProperty("env")));
		driver.findElement(submit).click();
		Thread.sleep(6000);
	}
}
