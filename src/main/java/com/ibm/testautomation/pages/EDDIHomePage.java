package com.ibm.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ibm.testautomation.common.pages.BasePage;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

public class EDDIHomePage extends BasePage {

	private By loadCountry = By.id("HeaderForm_countryCode");
	private By jobs = By.id("el0");
	private By vessel = By.id("el4");
	private By jobAdmin = By.id("el15");
	CommonUtil commonUtil = new CommonUtil();
	PropertiesFileReader obj = new PropertiesFileReader();

	/**
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String getTitle(WebDriver driver) throws Exception {
		Thread.sleep(4000);
		return driver.getTitle();
	}

	/**
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void changeCountry(WebDriver driver) throws Exception

	{
		Select dropdown = new Select(driver.findElement(loadCountry));
		dropdown.selectByVisibleText(System.getProperty("loadcountry"));
		commonUtil.hightlightElement(loadCountry, driver);
		Thread.sleep(4000);
	}

	/**
	 * 
	 * @param driver
	 * @throws Exception
	 */
	public void clickJobAdmin(WebDriver driver) throws Exception

	{
		driver.findElement(jobs).click();
		commonUtil.hightlightElement(jobs, driver);
		Thread.sleep(3000);
		driver.findElement(vessel).click();
		commonUtil.hightlightElement(vessel, driver);
		Thread.sleep(3000);
		driver.findElement(jobAdmin).click();
		commonUtil.hightlightElement(jobAdmin, driver);
		Thread.sleep(4000);
	}

}
