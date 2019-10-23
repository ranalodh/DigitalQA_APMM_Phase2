package com.ibm.testautomation.util;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ActionLib {

	private final static Logger LOGGER = Logger.getLogger(ActionLib.class.getName());
	private static final WebDriver WebDriver = null;
	public  int intSyncTimeOut=30000;
	Select dropdown = null;

	public void click(By objectref, WebDriver driver) throws Throwable {
		
		try {

			LOGGER.info("Into Click() :: Object Reference Is:" + objectref);
			LOGGER.info("Clicking on Webelement: " + objectref);
			LOGGER.info(".........Click Event Started........");

			//driver.findElement(objectref).click();

			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				element.click();
				LOGGER.info("...............Click Event Completed........");

			} else {

				LOGGER.info("........Unable to Click - Object Not Found........");
				Assert.assertTrue(false, "Unable to Click on the Object");
			}		

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("Not able to Click --- " + e.getMessage());

		}
	}

	public void wait(WebDriver driver) throws Throwable {

		try {
			LOGGER.info("Wait for 10 seconds");
			LOGGER.info("Into wait() :: Driver Is:" + driver);
			LOGGER.info("...................wait Started........");

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			LOGGER.info("...................wait Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to Wait --- " + e.getMessage());

		}
	}

	public boolean isElementExist(By objectref, WebDriver driver) throws Throwable {

		boolean isElementExist = false;
		try {

			LOGGER.info("Locating object " + objectref);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("...................findObject Event Started........");

			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				isElementExist = true;
				LOGGER.info("Object found");

			}else {
				isElementExist = false;
				LOGGER.info("Object Not found");
				Assert.assertTrue(false, "The Object does not exist");
			}	

			/*int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				isElementExist = true;

			} else {
				isElementExist = false;
			}*/

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return isElementExist;
		
	}

	public int getElementSize(By objectref, WebDriver driver) throws Throwable {

		int elementSize = 0;
		try {

			LOGGER.info("Locating object " + objectref);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("...................findObject Event Started........");

			int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				elementSize = E;

			} else {
				elementSize = E;
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementSize;

	}

	public boolean validateListItems(By objectref, WebDriver driver, List<String> expectedOptions) throws Throwable {
		Boolean isMatched = false;

		List<String> options = new ArrayList<String>();
		try {

			LOGGER.info("Locating object " + objectref);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("Into findObject() :: expectedOptions Reference Is:" + expectedOptions);

			options = getAllOptions(objectref, driver);

			if (options.equals(expectedOptions)) {
				isMatched = true;
				LOGGER.info("isMatched " + isMatched);
			} else {
				isMatched = false;
				LOGGER.info("isMatched " + isMatched);
			}

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}

		return isMatched;

	}

	public List<String> getAllOptions(By dropdown, WebDriver driver) {

		List<String> options = new ArrayList<String>();
		try {
			for (WebElement option : new Select(driver.findElement(dropdown)).getOptions()) {
				String optionTxt = option.getText().trim();
				if (!optionTxt.equals(null) && !optionTxt.equals("Select Role") && !optionTxt.equals("Select Report")) {
					LOGGER.info("Options are : " + optionTxt);
					options.add(optionTxt);
				}
			}
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());
		}
		LOGGER.info("options " + options);
		return options;
	}

	public String getElementText(By objectref, WebDriver driver) throws Throwable {

		String elementText = "";
		try {
			LOGGER.info("Locating object " + objectref);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("...................findObject Event Started........");

			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				LOGGER.info("Object found");
				elementText = element.getText().trim();
				System.out.println(elementText);
				LOGGER.info("Object Value --- " + elementText);
				
			} else{
				elementText = "No Element Found";
				LOGGER.info("No Object Found");
				Assert.assertTrue(false, "Unable to find the Object");
			}

			/*int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				elementText = driver.findElement(objectref).getAttribute(attributeName);
				LOGGER.info("Object Value --- " + elementText);

			} else {
				elementText = "No Element Found";
			}*/

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementText;

	}
	
	public String getElementAttribute(By objectref, WebDriver driver, String attributeName) throws Throwable {

		String elementText = "";
		try {
			LOGGER.info("Locating object " + objectref);
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);
			LOGGER.info("...................findObject Event Started........");

			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				elementText = element.getAttribute(attributeName).trim();
				LOGGER.info("Object Value --- " + elementText);
			} else {
				LOGGER.info("No Object Found");
				elementText = "No Element Found";
				Assert.assertTrue(false, "Unable to find the Object");
			}

			/*int E = driver.findElements(objectref).size();

			if (E > 0) {
				LOGGER.info("Object found");
				elementText = driver.findElement(objectref).getAttribute(attributeName);
				LOGGER.info("Object Value --- " + elementText);

			} else {
				LOGGER.info("No Object Found");
				elementText = "No Element Found";
			}*/

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return elementText;

	}

	public String getAlertText(WebDriver driver) throws Throwable {
		
		String alertText = null;
		try {			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			alertText = driver.switchTo().alert().getText();
			LOGGER.info("alertText --- " + alertText);
		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
		return alertText;

	}

	public void alertAccess(WebDriver driver) throws Throwable {

		try {			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			driver.switchTo().alert().accept();

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
	}

	public void alertDismiss(WebDriver driver) throws Throwable {

		try {			
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			driver.switchTo().alert().dismiss();

		} catch (Exception e) {
			LOGGER.warning("Not able to Locate object --- " + e.getMessage());

		}
	}

	public void selectByIndex(By objectref, WebDriver driver, int index) throws Throwable {

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			//int E = driver.findElements(objectref).size();
			
			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);
			
			if (element!=null){
				LOGGER.info("Object found");
				dropdown = new Select(element);
				dropdown.selectByIndex(index);
			} else {
				LOGGER.info("Object Not found");
				Assert.assertTrue(false, "Unable to find the Object");
			}
			/*if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByIndex(index);
			} else {
				LOGGER.info("Object Not found");
			}*/

			LOGGER.info("...............Dropdown Event Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}

	public void selectByVisibleText(By objectref, WebDriver driver, String visibleText) throws Throwable {

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			
			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);
			
			if (element!=null){
				LOGGER.info("Object found");
				dropdown = new Select(element);
				dropdown.selectByVisibleText(visibleText);
			} else {
				LOGGER.info("Object Not found");
				Assert.assertTrue(false, "Unable to find the Object");
			}
			
			/*int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByVisibleText(visibleText);
			} else {
				LOGGER.info("Object Not found");
			}*/

			LOGGER.info("...............Dropdown Event Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}

	public void selectByValue(By objectref, WebDriver driver, String value) throws Throwable {

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);
			LOGGER.info("...................Dropdown Event Started........");
			
			WebElement element= WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);
			
			if (element!=null){
				LOGGER.info("Object found");
				dropdown = new Select(element);
				dropdown.selectByValue(value);
			} else {
				LOGGER.info("Object Not found");
				Assert.assertTrue(false, "Unable to find the Object");
			}
			
			/*int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				dropdown = new Select(driver.findElement(objectref));
				dropdown.selectByVisibleText(value);
			} else {
				LOGGER.info("Object Not found");
			}*/

			LOGGER.info("...............Dropdown Event Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to Select value from the dropdown --- " + e.getMessage());

		}
	}

	public void sendValueToInput(By objectref, WebDriver driver, String value) throws Throwable {

		try {
			LOGGER.info("Selecting the dropdown text " + objectref);
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("Into dropDown() :: Object Reference Is:" + objectref);

			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				element.click();	
				element.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),value);
				LOGGER.info("Object found - Data enetered successfully");
			} else {
				LOGGER.info("Object Not found - Unable to enter data");
				Assert.assertTrue(false, "Unable to find the Object");
			}
			
			/*int E = driver.findElements(objectref).size();
			if (E > 0) {
				LOGGER.info("Object found");
				driver.findElement(objectref).sendKeys(value);
			} else {
				LOGGER.info("Object Not found");
			}*/
			
			LOGGER.info("...............sendValueToInput Event Completed........");
			
		} catch (Exception e) {
			LOGGER.warning("Not able to find Input element --- " + e.getMessage());

		}
	}

	public void switchWindow(WebDriver driver) throws Throwable {

		try {
			LOGGER.info("Into dropDown() :: Driver Is:" + driver);
			LOGGER.info("...................switchWindow Event Started........");
			String parentWinHandle = driver.getWindowHandle();
			LOGGER.info("Parent window handle: " + parentWinHandle);
			Set<String> winHandles = driver.getWindowHandles();
			// Loop through all handles
			for (String handle : winHandles) {
				if (!handle.equals(parentWinHandle)) {
					driver.switchTo().window(handle);
					Thread.sleep(1000);
					LOGGER.info("Title of the new window: " + driver.getTitle());
				}
			}

			LOGGER.info("...............switchWindow Event Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able to switchWindow --- " + e.getMessage());

		}
	}

	public void javascriptClick(By objectref, WebDriver driver) {

		try {
			LOGGER.info("Into javascriptClick() :: Driver Is:" + driver);
			LOGGER.info("Into javascriptClick() :: Object Reference Is:" + objectref);
			LOGGER.info("...................javascriptClick Event Started........");

			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);

			if (element!=null){
				LOGGER.info("Object found");			
				//WebElement element = driver.findElement(objectref);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				//executor.executeScript("arguments[0].click();", element);
				
				executor.executeScript("var el=arguments[0]; " + "setTimeout(function() { el.click(); },100);", element); 
				
			} else {
				LOGGER.info("Object Not found");
				Assert.assertTrue(false, "Unable to find the Object");
			}
			LOGGER.info("...............javascriptClick Event Completed........");

		} catch (Exception e) {
			LOGGER.warning("Not able click javascriptClick --- " + e.getMessage());

		}
	}
	
	public boolean isSelected(By objectref, WebDriver driver) {

		Boolean isSelected = false;	
		try {
			LOGGER.info("Into findObject() :: Driver Is:" + driver);
			LOGGER.info("Into findObject() :: Object Reference Is:" + objectref);

			WebElement element = WaitForObjectToLoadAndReturnIfExist(objectref, intSyncTimeOut, driver);
			
			if (element!=null){
				LOGGER.info("Object found");
				isSelected = element.isSelected();
				//isSelected = driver.findElement(objectref).isSelected();
			} else {
				LOGGER.info("Object Not found");
				Assert.assertTrue(false, "Unable to find the Object");
			}

		}catch (Exception e) {
			LOGGER.warning("Unable to get the selected status ---- " + e.getMessage());
		}

		return isSelected;
	}
	
	public WebElement WaitForObjectToLoadAndReturnIfExist(By objectref, int intTimeInMillis, WebDriver driver) throws Exception {

		long t0, t1;
		boolean blnObjectExistFlag=false;
		WebElement elementReturned=null;
		try{

			WebDriverWait wait = new WebDriverWait(driver, intTimeInMillis/1000);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript(
							"return document.readyState"
							).equals("complete");
				}
			});

			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver wdriver) {
					while(true){
						if ((Boolean) ((JavascriptExecutor) wdriver).executeScript("return jQuery.active == 0")){
							break;
						}
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {}
					}
					return true;

				}
			});		

		}catch(Exception e){

		}

		try{
			t0 = System.currentTimeMillis();
			do {

				List<WebElement> ElementList=(driver.findElements(objectref));
				for (int i=0;i<ElementList.size();i++) {

					if (ElementList.get(i).isDisplayed()){
						elementReturned=ElementList.get(i);

						blnObjectExistFlag=true;
						break;
					}
				}

				ElementList.clear();
				t1 = System.currentTimeMillis();
			} while (t1 - t0 < intTimeInMillis && !blnObjectExistFlag);
		}catch(Exception e){

		}

		return elementReturned;
	}
}

/*public WebElement WaitForObjectToLoadAndReturnIfExist(By objectref, int timeoutInSeconds, WebDriver driver) throws Exception {
	WebElement element = null;
	try {
	WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	element = wait.until(ExpectedConditions.presenceOfElementLocated(objectref));
	}catch(NoSuchElementException elementException) {
		elementException.printStackTrace();
	}
    return element;
} 
actionLib.isElementExist(loginViewContainer, driver, CommonUtil.ELEMENT_WAIT_TIMEOUT) 
*/