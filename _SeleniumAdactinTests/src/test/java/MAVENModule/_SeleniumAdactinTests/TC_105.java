package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;



import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;



/*
 * TEST CASE ID:		TC_105
 * 
 * OBJECTIVE:			To verify whether Check-in date and Check-out date are 
 * 						being displayed in Select Hotel page according to the dates selected in search Hotel.
 * 
 * EXPECTED RESULTS:	Check-in-date and check-out-date should be displayed according to the data entered in search hotel form.
 * 
 * STEPS:				1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Select location as in test data.
						4. Select hotel as in test data.
						5. Select room type as in test data.
						6. Select no-of-rooms as in test data.
						7. Enter check-out-date as in test data.
						8. Select No-of-adults as in test data.
						9. Select No-of-children as in test data.
						10. Click on Search button.
						11. Verify that check-in-date and check-out-dates are the same as selected in search hotel form
 *					
 * TEST DATA:		
 * 
 * 						URL: http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						Location: Sydney
						Hotel: Hotel Creek
						Room type: standard
						No-of-rooms:1
						Check-in-date: today�s date
						Checkoutdate:today+1 date
						No-of-adults:1
						No-of-children:0		

 */

public class TC_105 extends SeleniumTest {
	private SimpleDateFormat dateFormatter; 
	private String datePickInXpath;
	private String datePickOutXpath;
	private TestCase tc = new TestCase();

	public TC_105() {
		
		try {	
			dateFormatter = new SimpleDateFormat(Utils.getSettingFromFile("TC_DATE_FORMAT"));	
			datePickInXpath  = Utils.getSettingFromFile("XPATH_TXT_CHECKIN_DATE");
			datePickOutXpath = Utils.getSettingFromFile("XPATH_TXT_CHECKOUT_DATE");
			
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_105_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_105_TESTDATA_FILE");
			ExcelTestData xtd = tc. new ExcelTestData(excelFilePath, excelWorksheetName);
			xtd.loadTestDataFromExcelFile();
			tc.setXtd(xtd);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(); 
		}
	}

	@Test
	public void tc105(){
		try {
			//launch login page and login
			new LoginPage().login();
			
			//arrange - create test object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//get dates from the Search Hotels forms and compare them:
			String datePickInValue  =  WaitAndGetAttribute(datePickInXpath, "value"); 
			String datePickOutValue =  WaitAndGetAttribute(datePickOutXpath, "value"); 

			System.out.println("Check in date (dd/MM/yyyy) = " + datePickInValue);
			System.out.println("Check out date (dd/MM/yyyy) = " + datePickOutValue);

			//print out today's date
			Date todaysDate = new Date();
			System.out.println("Today's date (dd/MM/yyyy) = " + dateFormatter.format(todaysDate));

			//perform search
			sp.search();
			System.out.println("The search just executed...");

			//get the dates from the Select Hotel page
			//see if we can find the page first
			String welcomeBannerXpath = "//*[text()='Welcome to AdactIn Group of Hotels']";
			new WebDriverWait(getWebDriver(), getWaitTimeout())
			.until(d->d.findElement(By.xpath(welcomeBannerXpath)));
			
			//Just successfully found the Welcome Banner: //*[text()='Welcome to AdactIn Group of Hotels']
			String temp = null;
			temp = (String) welcomeBannerXpath.subSequence(welcomeBannerXpath.indexOf("\'")+1,
					welcomeBannerXpath.lastIndexOf("\'"));
			System.out.println("Just successfully found the Welcome Banner: " + temp);

			//Verify arrival date
			String SearchHotelArrDateXPATH = getSettingFromFile("XPATH_TXT_ARR_DATE"); 
			WebElement arrDate = new WebDriverWait(getWebDriver(), getWaitTimeout())
			.until(d->d.findElement(By.xpath(SearchHotelArrDateXPATH)));
			
			Assert.assertEquals(arrDate.getAttribute("value"), datePickInValue);
			System.out.println("Success! " +   arrDate.getAttribute("value") + " matched: " +  "datePickInValue = " + datePickInValue );
			
			//Verify departure date
			//XPATH_TXT_DEP_DATE = "//*[@id='dep_date_0']
			String SearchHotelDeptDateXPATH = getSettingFromFile("XPATH_TXT_DEP_DATE"); 
			WebElement depDate = new WebDriverWait(getWebDriver(), getWaitTimeout())
			.until(d->d.findElement(By.xpath(SearchHotelDeptDateXPATH)));
			
			Assert.assertEquals(depDate.getAttribute("value"), datePickOutValue);
			System.out.println("Success! " +   depDate.getAttribute("value") + " matched: " +  "datePickOutValue = " + datePickOutValue );
		}
		catch(Exception e){
			System.out.println("TC_105 failed, due to: " + e.getMessage());
			Assert.fail();
		}
	}
}
