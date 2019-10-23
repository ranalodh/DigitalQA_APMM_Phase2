package com.ibm.testautomation.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.ibm.testautomation.common.pages.BasePage;
import com.ibm.testautomation.util.PropertiesFileReader;

public class ViewBookingLogPage extends BasePage {

	private final static Logger LOGGER = Logger.getLogger(ViewBookingLogPage.class.getName());
	private By logDescriptions = By.xpath("//tr//tr//tr//td[4]");
	PropertiesFileReader obj = new PropertiesFileReader();
	SoftAssert softAssert = new SoftAssert();

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
	 * @return
	 * @throws Exception
	 */
	public boolean verifyLogDescription(WebDriver driver) throws Exception {
		List<String> logDescriptionValues = new ArrayList<String>();

		LOGGER.info("In verifyLogDescription");
		LOGGER.info("logDescriptions size : " + driver.findElements(logDescriptions).size());

		if (driver.findElements(logDescriptions).size() > 0) {
			for (int i = 2; i <= driver.findElements(logDescriptions).size(); i++) {

				if (driver.findElement(By.xpath("//tr//tr//tr[" + i + "]//td[4]")).getText() != "") {

					logDescriptionValues.add(driver.findElement(By.xpath("//tr//tr//tr[" + i + "]//td[4]")).getText());
				}
			}

			LOGGER.info("Job Description - " + logDescriptionValues);
			LOGGER.info("logDescriptionValues ERROR status - " + logDescriptionValues.contains("ERROR"));

			Thread.sleep(1000);

		} else {
			softAssert.assertTrue(false, "No Log Descriptons present");
		}
		return logDescriptionValues.contains("ERROR");
	}
}
