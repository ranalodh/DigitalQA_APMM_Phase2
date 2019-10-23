package com.ibm.testautomation.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.common.pages.BasePage;

public class GoogleVerification extends BasePage {

	private By searchtext = By.xpath("//input[@name = 'q']");
	private By searchbutton =  By.xpath("//input[@value = 'Google Search']");

	public void searchGooglePage(WebDriver driver)  throws Exception {

		driver.findElement(searchtext).sendKeys("Testing");
		driver.findElement(searchbutton).click();
	}
}
