package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_120
 * 
 * OBJECTIVE:			To verify that the order gets cancelled after click on Cancel order number link

 * EXPECTED RESULTS:	Order number should not longer be present in booked itinerary page after cancellation
 * 
 * STEPS:				
						To verify that the order gets cancelled after click on Cancel order number link
						1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Book the Hotel as in previous test cases. Keep a note of order number generated
						4. Click on Booked Itinerary link
						5. Search for order number booked
						6. Click on Cancel <Order Number>
						7. Click Yes on pop-up which asks to cancel order or not
						8. Verify that order number is cancelled and no longer exists in Booked Itinerary page
						http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
 * 
 * 	
 */

public class TC_120 extends SeleniumTest {
	private final TestCase tc = new TestCase();
	private final long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));

	public TC_120() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_119_TESTDATA_FILE");
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
	public void test120() {
		try {
			//launch login page and login
			new LoginPage().login();

			//create SearchHotelsPage object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//perform search
			sp.search();

			//create SelectHotePage object
			SelectHotelPage shp = new SelectHotelPage();

			//advance to the Hotel Booking page

			//select the radio button
			shp.selectRadioButton();

			//select continue button
			shp.selectContinue();

			//create BookAHotelPage object
			BookAHotelPage bhp = new BookAHotelPage(tc.getXtd().getTestData());

			//book it!
			bhp.bookNow();

			//create BookingConfirmationPage object
			BookingConfirmationPage bcp = new BookingConfirmationPage(null);

			//before clicking the My Itinerary button and advancing to next window,
			//save the main current fields settings for comparison to the field settings in the next page
			String bcpOrderNo 		=  bcp.getOrderNo();
			String bcpHotelName 	=  bcp.getHotelName(); 
			String bcpLocation 		=  bcp.getLocation();
			String bcpTotalRooms 	=  bcp.getTotalRooms();
			String bcpArrivalDate 	=  bcp.getArrivalDate();
			String bcpDepartureDate =  bcp.getDepartureDate();
			String bcpFinalDrive	=  bcp.getFinalPrice();

			//click My Itinerary button
			bcp.clickMyItineraryButton();

			//create BookedItineraryPage object 
			BookedItineraryPage bip = new BookedItineraryPage(bcpOrderNo);

			//search for order id using search text box and go button
			bip.searchOrderID(bcpOrderNo);

			/*
			 *TODO:
			 *
			 *
			//dynamic cancel button with order id
			//needs a dynamic xpath to find it
			//dynamic xpath = //*[@value="Cancel 8D93026X59"]
			<input id="btn_id_210782" name="btn_id_210782" style="width:150px;" value="Cancel 8D93026X59" 
			onclick="cancel_booking('210782','8D93026X59');" type="button"/>


			(1) launch the Booked Itinerary Page passing in the OrderID- done

			(2) call method to search for record with Order Id and find it - done

			(3) compose dynamic xpath to find cancel button element using OrderID

			(4) find the dynamic cancel button

			(5) click the dynamic cancel button

			(6) verify we can no longer find the dynamic cancel button after the page has updated 
			 * 
			 */

			//compose dynamic xpath to find cancel button element using OrderID
			String xpathCancelBtn = "//*[@value='Cancel " + bcpOrderNo + "']";
			
			////*[@value= 'Cancel 'GS9M24A51F
			////*[@value="Cancel 13KS2L7AF2"]

			//find the dynamic cancel button then click it
			new WebDriverWait(DriverFactory.getWebDriver(),WAIT_TIME_OUT)
			.until(d->d.findElement(By.xpath(xpathCancelBtn))).click();
			System.out.println("found element with xpath: "
					+ xpathCancelBtn);

			//wait for alert popup and accept it
			new WebDriverWait(DriverFactory.getWebDriver(),WAIT_TIME_OUT)
			.until(ExpectedConditions.alertIsPresent()).accept();
			System.out.println("found alert and accepted it.");
			
			//search for record again
			bip.searchOrderID(bcpOrderNo);

			try {
				//try to find the dynamic cancel button again
				new WebDriverWait(DriverFactory.getWebDriver(),WAIT_TIME_OUT)
				.until(d->d.findElement(By.xpath(xpathCancelBtn)));
				System.out.println("fail. found element with xpath: "
						+ xpathCancelBtn);
			}
			catch(Exception e) {
				//success
				System.out.println("success! could not find element with xpath: "
						+ xpathCancelBtn);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


