package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;


import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_104
 * 
 * OBJECTIVE:			To verify whether locations in Select Hotel page are displayed according to the location selected in Search Hotel
 * 
 * EXPECTED RESULTS:	 Location displayed in Select Hotel should be the same as location selected in search hotel form.
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
						11. Verify that hotel displayed is the same as selected in search Hotel form.
 * 
 * 	
 */

public class TC_104 extends SeleniumTest {
	TestCase tc = new TestCase();

	public TC_104() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_104_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_104_TESTDATA_FILE");
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
public void test04() {

	try {
		//launch login page and login
		new LoginPage().login();
		
		//arrange - create test object
		SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

		//get the selected entry in the hotels list
		final String hotelXPATH = getSettingFromFile("XPATH_LST_HOTELS");//.//*[@id='hotels']
		WebElement element = new WebDriverWait(getWebDriver(), getWaitTimeout())
		.until(d->d.findElement(By.xpath(hotelXPATH)));

		String hotelSelected = new Select(element).getFirstSelectedOption().getText();

		//act - perform search
		sp.search();
		System.out.println("The search just executed, with hotelSelected = " + hotelSelected + "...");

		//see if we can find the page first
		String welcomeBannerXpath = "//*[text()='Welcome to AdactIn Group of Hotels']";
		new WebDriverWait(getWebDriver(), getWaitTimeout())
		.until(d->d.findElement(By.xpath(welcomeBannerXpath)));
		System.out.println("Just successfully found the Welcome B: " + welcomeBannerXpath);

		//verify that hotel displayed is the same as selected in search Hotel form.
		//<input id="hotel_name_1" class="select_text" name="hotel_name_1" value="Hotel Sunshine" 
		//onfocus="disable_ctrlV()" onkeypress="return Nothingonly(event)" type="text"/>

		String SearchHotelXPATH = getSettingFromFile("XPATH_TXT_SELECT_HOTEL"); 
		System.out.println("SearchHotelXPATH = " + SearchHotelXPATH +  "...");
		System.out.println("Calling WebDriverWait...ExpectedConditions.presenceOfElementLocated(By.xpath(SearchHotelXPATH)...");

		element = new WebDriverWait(getWebDriver(), getWaitTimeout())
		.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SearchHotelXPATH)));

		Assert.assertEquals(element.getAttribute("value"), hotelSelected);
		System.out.println("Success! " +   element.getAttribute("value") + " matched: " +  "hotelSelected = " + hotelSelected );
	}
	catch(Exception e) {
		System.out.println(e.getMessage());	
		Assert.fail();
	}
}
}


