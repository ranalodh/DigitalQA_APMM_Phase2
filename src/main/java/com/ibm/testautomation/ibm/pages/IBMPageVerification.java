package com.ibm.testautomation.ibm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.common.pages.BasePage;

public class IBMPageVerification extends BasePage{
	
	private By searchtextbox = By.xpath("//input[@data-name = 'search']");
	
	public void searchIbmPage(WebDriver driver) throws Exception {
		
		driver.findElement(searchtextbox).sendKeys("IBM Testing");
	}
}
