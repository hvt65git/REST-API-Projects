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
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_106
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
public class TC_106 extends SeleniumTest {
	private TestCase tc = new TestCase();
	
	public TC_106() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_106_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_106_TESTDATA_FILE");
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
	public void tc106(){
		try {
			//launch login page and login
			new LoginPage().login();
			
			//init Search Hotels page with test data
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//get the selected entry in the Number of Rooms list
			String numRoomsXPATH = getSettingFromFile("XPATH_LST_NUM_ROOMS");
			
			WebElement element = new WebDriverWait(getWebDriver(), getWaitTimeout())
			.until(d->d.findElement(By.xpath(numRoomsXPATH)));

			String numRmSearchHotel = new Select(element).getFirstSelectedOption().getText();

			//perform search
			sp.search();
			System.out.println("The search just executed...");

			//get room text in Search Hotel form
			String numRmSelectHotelValue = WaitAndGetAttribute(
					Utils.getSettingFromFile("XPATH_TXT_SELECT_HOTEL_ROOMS"), 
					"value");

			//perform mapping:
			//Search Hotels->Select Hotel:
			//numRmSearchHotel->numRmSelectHotelValue
			//1 - One ->1 Rooms
			//5 - Five ->5 Rooms
			//10 - Ten -> 10 rooms
			String[] mappedNumRmSearchHotel = numRmSearchHotel.split(" ");
			String[] mappedNumRmSelectHotel = numRmSelectHotelValue.split(" ");

			Assert.assertEquals(mappedNumRmSelectHotel[0], mappedNumRmSearchHotel[0]);
			System.out.println("Success! " +  "mappedNumRmSelHotel[0] = " + mappedNumRmSelectHotel[0] + 
					" matched: " +  "mappedNumRmSearchHotel[0] = " + mappedNumRmSearchHotel[0] );

		}
		catch(Exception e){
			System.out.println("TC_106 failed, due to: " + e.getMessage());
			Assert.fail();
		}
	}
}
