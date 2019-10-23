package com.ibm.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.ibm.testautomation.common.pages.BasePage;
import com.ibm.testautomation.util.CommonUtil;
import com.ibm.testautomation.util.PropertiesFileReader;

import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;


public class VesselJobAdministrationPage extends BasePage {

	private CommonUtil commonUtil = new CommonUtil();
	private By startDateFilter = By.id("startDateFilter");
	private By gcssStatusFilter = By.id("gcssStatusFilter");
	private By dndStatusFilter = By.id("dndStatusFilter");
	private By submissionDateList = By.xpath("//tr//tr//tr//tr//td[7]");	
	private By returnButton = By.xpath("//td[@class='btnArea']//input[2]");
	private By logDesNext = By.xpath("//a[3]//img[1]");
	private By noRecord= By.xpath("//tr[@class='gridRowEven']//td");
	private PropertiesFileReader obj = new PropertiesFileReader();
	private final static Logger LOGGER = Logger.getLogger(VesselJobAdministrationPage.class.getName());
	private SoftAssert softAssert = new SoftAssert();
	private boolean isVerify= false;
	private boolean isValidateLogDescriptions = false;
	private boolean logDescription = false; 
	private String pageRange = null;

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
	public boolean verifyCompletionDate(WebDriver driver) throws Exception {

		LOGGER.info("In verifyCompletionDate");

		Select dropdownstartDateFilter = new Select(driver.findElement(startDateFilter));
		dropdownstartDateFilter.selectByVisibleText(/* "Last 30 Days" */"All");
		Thread.sleep(2000);

		Select dropdowngcssStatusFilter = new Select(driver.findElement(gcssStatusFilter));
		dropdowngcssStatusFilter.selectByVisibleText("COMPLETED");
		Thread.sleep(2000);

		Select dropdowndndStatusFilter = new Select(driver.findElement(dndStatusFilter));
		dropdowndndStatusFilter.selectByVisibleText("COMPLETED");
		Thread.sleep(4000);

		LOGGER.info("submissionDateList size - " + driver.findElements(submissionDateList).size());

		if (driver.findElements(submissionDateList).size() > 1) {

			LOGGER.info("Submission Date range from " + commonUtil.fromDate() + " To " + commonUtil.toDate());
			LOGGER.info("No of Days from start and end date "
					+ commonUtil.getdateList(commonUtil.fromDate(), commonUtil.toDate()).size());

			for (int i = 2; i <= driver.findElements(submissionDateList).size(); i++) {

				LOGGER.info("In Loop - index - " + i);

				LOGGER.info("Submission from Page for index " + i + " date "
						+ driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText());
				LOGGER.info("Completion from Page for index " + i + " date "
						+ driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[8]")).getText());

				if (driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText() != ""
						&& driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[8]")).getText() != ""
						// && driver.findElement(By.xpath("//tr//tr//tr//tr[" + i
						// +"]//td[7]")).getText().equals(commonUtil.fromDate()/*"2017-01-03"*/)
						// || driver.findElement(By.xpath("//tr//tr//tr//tr[" + i
						// +"]//td[7]")).getText().equals(commonUtil.toDate()/*"2017-01-03"*/)
						&& commonUtil.getdateList(commonUtil.fromDate(), commonUtil.toDate()).contains(
								driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText())) {

					LOGGER.info("Matching Submission date found for Incident date range from " + commonUtil.fromDate()
							+ " To " + commonUtil.toDate());
					LOGGER.info("Comparing Submission and Completion Dates - index - " + i);

					isVerify = commonUtil.compareDate(
							driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText(),
							driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[8]")).getText());
				} else {

					softAssert.assertTrue(false, "No Matching Found");
					// isVerify = false;
					LOGGER.info("Highlight Submission and Completion Date for Failure scenario");
					commonUtil.hightlightElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]"), driver);
					commonUtil.hightlightElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[8]"), driver);
					commonUtil.captureScreen(driver, commonUtil.getAppPort(System.getProperty("env")));
				}
			}
		} else {
			LOGGER.info("No Record found for specific Date Range");
			commonUtil.hightlightElement(noRecord, driver);
			isVerify = false;
			// commonUtil.captureScreen(driver);
		}
		return isVerify;
	}
	/**
	 * 
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public boolean validateLogDescriptions(WebDriver driver) throws Exception {

		LOGGER.info("In validateLogDescriptions");
		
		Properties properties = obj.getProperty();		

		LOGGER.info("submissionDateList size - " + driver.findElements(submissionDateList).size());
		if(driver.findElements(submissionDateList).size() > 1) {
		for (int i = 2; i <= driver.findElements(submissionDateList).size(); i++) {

			if (driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText() != ""
					&& driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[8]")).getText() != ""
					//&& driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText().equals(commonUtil.fromDate()/*"2017-01-03"*/)
					//|| driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText().equals(commonUtil.toDate()/*"2017-01-03"*/)
					 && commonUtil.getdateList(commonUtil.fromDate(), commonUtil.toDate()).contains(driver.findElement(By.xpath("//tr//tr//tr//tr[" + i +"]//td[7]")).getText())
					) {

				if (commonUtil.compareDate(driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[7]")).getText(),
					driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[8]")).getText())) {
					
					LOGGER.info("Get Job Description by clicking on Job Id " + driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[1]")).getText());
					LOGGER.info("Job Id Element path - //tr//tr//tr//tr[" + i + "]//td[1]");
					Thread.sleep(4000);
					commonUtil.hightlightElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[1]"), driver);
					commonUtil.captureScreen(driver,  commonUtil.getAppPort(System.getProperty("env")));
					
					// Click matching Job Id
					driver.findElement(By.xpath("//tr//tr//tr//tr[" + i + "]//td[1]/a")).click();
					Thread.sleep(4000);
					
					// check is at View Booking Log page or not
					String expectedViewBookingLogPageTitle = properties.getProperty("viewbookinglogpage.page.title");
					String actualViewBookingLogTitle = new ViewBookingLogPage().getTitle(driver);
					Assert.assertEquals(actualViewBookingLogTitle, expectedViewBookingLogPageTitle,	"View Booking Log page is not opening");
					
					LOGGER.info("expectedViewBookingLogPageTitle -- "+ expectedViewBookingLogPageTitle);
					LOGGER.info("actualViewBookingLogTitle -- " + actualViewBookingLogTitle);
					LOGGER.info("View Booking Log page is opened");
					commonUtil.captureScreen(driver,  commonUtil.getAppPort(System.getProperty("env")));
					// click Return Button
					Thread.sleep(1000);
					//get value of //td[@class='gridPageOfPage'] = Page 1 of 11
					LOGGER.info("value of //td[@class='gridPageOfPage'] -- " + driver.findElement(By.xpath("//td[@class='gridPageOfPage']")).getText());
					
					pageRange = driver.findElement(By.xpath("//td[@class='gridPageOfPage']")).getText();
					
					for(int p=commonUtil.getStartPageNo(pageRange); p <= commonUtil.getEndPageNo(pageRange); p++) {
						
						logDescription = new ViewBookingLogPage().verifyLogDescription(driver);
						
						LOGGER.info("page "+ p + " logDescription " + logDescription);
						
						if(logDescription == false) {
							
							if(p != commonUtil.getEndPageNo(pageRange)) {
								
								   LOGGER.info("Going to click on logDesNext");
								   driver.findElement(logDesNext).click();
								   Thread.sleep(2000);
								   LOGGER.info("After click on logDesNext");
								  commonUtil.captureScreen(driver,  commonUtil.getAppPort(System.getProperty("env")));
								   
							}else {
								commonUtil.captureScreen(driver,  commonUtil.getAppPort(System.getProperty("env")));
								LOGGER.info("Before calling returnButton");
								driver.findElement(returnButton).click();
								LOGGER.info("After calling returnButton");
							}
							
						}else {
							LOGGER.info("In case of isValidateLogDescriptions = true");							
							isValidateLogDescriptions = true;
						}						
					}
					Thread.sleep(2000);

				} else {
					isValidateLogDescriptions = true;
				}
			}else {
				 softAssert.assertTrue(false,
						 "No Log Descriptons present"); 
			}
		}
		}else {
			isValidateLogDescriptions = true;
			commonUtil.hightlightElement(noRecord, driver);
		}
		return isValidateLogDescriptions;
	}
}
