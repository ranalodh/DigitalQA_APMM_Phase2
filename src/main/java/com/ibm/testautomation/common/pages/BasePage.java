package com.ibm.testautomation.common.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

public class BasePage {

	private final static Logger LOGGER = Logger.getLogger(BasePage.class.getName());

	/**
	 * 
	 * @param url
	 * @param driver
	 */
	public void launchBrowser(String url, WebDriver driver) {

		LOGGER.info("Opening Application URL : " + url);

		driver.get(url);

	}
	
	public String getTitle(WebDriver driver) throws Exception {
		
		Thread.sleep(4000);
		
		return driver.getTitle();
	}
}
