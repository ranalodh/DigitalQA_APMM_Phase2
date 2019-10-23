package com.ibm.testautomation.draywatch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ibm.testautomation.util.ActionLib;

public class DrayWatchViewSegmentsPage {

	ActionLib actionLib = new ActionLib();

	private By lblViewSegments = By.xpath("//div[@id='MainDiv']/descendant::span[contains(.,'View Segments')]");

	public String getTitle(WebDriver driver) throws Exception {
		Thread.sleep(4000);
		return driver.getTitle();
	}

}



