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
 * TEST CASE ID:		TC_109
 * 
 * OBJECTIVE:			To verify when pressed, logout button logs out from the application.
 * 
 * EXPECTED RESULTS:	User should logout from the application.
 * 
 * STEPS:			1. Launch hotel reservation application using URL as in test data.
					2. Login to the application using username and password as in test data.
					3. Select location as in test data.
					4. Select hotel as in test data.
					5. Select room type as in test data.
					6. Select no-of-rooms as in test data.
					7. Enter check-out-date as in test data.
					8. Select No-of-adults as in test data.
					9. Select No-of-children as in test data.
					10. Click on Search button.
					11. Select the hotel and click on continue button.
					12. Enter the details and click on book now.
					13. Click on logout and verify we have been logged out of the application.
					URL: http://adactin.com/HotelApp/index.php
					User:{test username}
					Password:{test password}
					Location: Sydney
					Hotel: Hotel Creek
					Room type: standard
					No-of-rooms:2
					Check-in-date: today�s date
					Check-out-date:today+1 date
					No-of-adults:1
					No-of-children:0
 * 
 * 	
 */

public class TC_109 extends SeleniumTest {
	private final String XPATHLogoutText = Utils.getSettingFromFile("XPATH_LOGOUT_TEXT");
	private TestCase tc = new TestCase();

	public TC_109() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_109_TESTDATA_FILE");
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
	public void test09() {
		try {
			//launch login page and login
			new LoginPage().login();
			
			//create SearchHotelsPage object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());
			
			//perform search
			sp.search();
			
			//create SelectHotePage object
			SelectHotelPage shp = new SelectHotelPage();
			
			//select the radio button
			shp.selectRadioButton();
			
			//select continue button
			shp.selectContinue();
			
			//create BookAHotelPage object
			BookAHotelPage bhp = new BookAHotelPage(tc.getXtd().getTestData());
			
			//click the logout link
			bhp.logout();
			
			//verify logout successful page appear - XPATHLogoutText
			new WebDriverWait(getWebDriver(), getWaitTimeout()).
				until(d->d.findElement(By.xpath(XPATHLogoutText)));
			
			System.out.println("Successfully found web element: " + XPATHLogoutText);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


