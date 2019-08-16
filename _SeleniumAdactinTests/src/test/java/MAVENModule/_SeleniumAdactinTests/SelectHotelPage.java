package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.utils.Conditions;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * 
 */
public class SelectHotelPage {
	private long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));

	//set the constant expressions to be used in @FindBy args
	private final String XPATH_TXT_SELECT_HOTEL_NAME 		= "//*[starts-with(@id, 'hotel_name_')]";
	private final String XPATH_TXT_SELECT_HOTEL_LOCATION 	= "//*[starts-with(@id,'location_')]";
	private final String XPATH_TXT_SELECT_HOTEL_ROOM_TYPE 	= "//*[starts-with(@id,'room_type_')]";	
	private final String XPATH_TXT_SELECT_HOTEL_DAYS 		= "//*[starts-with(@id,'no_days_')]";
	private final String XPATH_TXT_SELECT_HOTEL_PRICE_NIGHT = "//*[starts-with(@id, 'price_night_')]";
	
	
	private final String XPATH_TXT_SELECT_HOTEL_ROOMS 		= "//*[starts-with(@id, 'rooms_')]";
	private final String XPATH_TXT_SELECT_HOTEL_TOTAL_PRICE = "//*[starts-with(@id, 'total_price_')]";
	private final String XPATH_SUBMIT_CONTINUE 				= "//*[@id='continue']";
	private final String XPATH_BTN_CANCEL 					= "//*[@id='cancel']";
	private final String XPATH_RADIO_BUTTON					= "//*[starts-with(@id,'radiobutton_')]";
		
	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_NAME)
	WebElement hotelName;
	
	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_LOCATION)
	WebElement hotelLocation;

	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_ROOM_TYPE)
	WebElement hotelRoomType;
	
	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_DAYS)
	WebElement hotelDays;
	
	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_PRICE_NIGHT)
	WebElement hotelPricePerNight;
	
	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_ROOMS)
	WebElement hotelRooms;

	@FindBy(xpath = XPATH_TXT_SELECT_HOTEL_TOTAL_PRICE)
	WebElement hotelTotalPrice;

	@FindBy(xpath = XPATH_SUBMIT_CONTINUE )
	WebElement btnContinue;

	@FindBy(xpath = XPATH_BTN_CANCEL )
	WebElement btnCancel;
	
	@FindBy(xpath = XPATH_RADIO_BUTTON)
	WebElement radioBtnSelect;
	

	public SelectHotelPage() {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		Conditions.waitForPageToLoad( driver,  WAIT_TIME_OUT);
		driver.manage().window().maximize();
	}
	
	public String getRoomType() {
		return hotelRoomType.getAttribute("value");
	}

	public String getNumRooms() {
		return hotelRooms.getAttribute("value");
	}

	
	public String gethotelName() {
		return hotelName.getAttribute("value");
	}
	
	public String gethotelLocation() {
		return hotelLocation.getAttribute("value");
	}
	
	public String gethotelDays() {
		return hotelDays.getAttribute("value");
	}
	
	public String gethotelPricePerNight() {
		return hotelPricePerNight.getAttribute("value");
	}
	
	public String gethotelTotalPrice() {
		return hotelTotalPrice.getAttribute("value");
	}

	public String calculateTotalPrice(String numRoomsSelected) {
		//no of rooms:
		//<input id="rooms_1" class="select_text" name="rooms_1" value="1 Rooms" 
		String rmNumberValue = hotelRooms.getAttribute("value");

		//room numbers mapping:  //[2 Rooms , 2 - Two] => [rmNumberValue, rmNumberSelected]
		String rmNumSelSub = numRoomsSelected.substring(0, 1);
		String rmNumValSub = rmNumberValue.substring(0, 1);
		Assert.assertEquals(rmNumSelSub, rmNumValSub); 
		System.out.println("Success! rmNumSelSub = " +  rmNumSelSub + " matched: " +  
				"rmNumValSub = " + rmNumValSub );

		//Now, verify whether the total price (excl.GST) is calculated as �price per night * no. of nights* no of rooms�.

		// no. of nights:
		//<input id="no_days_1" class="select_text" name="no_days_1" value="1 Days" 
		String rmHotelDaysValue = hotelDays.getAttribute("value");
		System.out.println("rmHotelDaysValue =" + rmHotelDaysValue +  "...");

		//price per night - //*[@id='price_night_']
		String rmHotelPriceNightValue = hotelPricePerNight.getAttribute("value");//AUD $ 325
		System.out.println("rmHotelPriceNightValue =" + rmHotelPriceNightValue +  "...");
		

		// total price (excl.GST) = //*[@id='total_price_']
		String rmHotelTotalPriceValue =hotelTotalPrice.getAttribute("value"); //AUD $ 335
		System.out.println("rmHotelTotalPriceValue =" + rmHotelTotalPriceValue +  "..."); //AUD $ 335

		//parse string so we can compare the dollar numbers as double types
		//price per night, e.g., 325.0
		String [] temp = rmHotelPriceNightValue.split(" ");
		String parsed = temp[2];
		Double pricePerNight = new Double(parsed);

		//nights, e.g. = 1
		temp = rmHotelDaysValue.split(" ");
		parsed = temp[0];
		Double numberOfNights = new Double(parsed); //1 Days

		//get actual total price and return it
		Double numberOfRooms = new Double(rmNumValSub);
		Double calculatedTotalPrice = pricePerNight * numberOfNights * numberOfRooms;
		return calculatedTotalPrice.toString();
	}
	

	public void submit() {
		btnContinue.submit();
	}
	
	public void selectContinue() {
		btnContinue.click();
	}

	public void cancel() {
		btnCancel.click();
	}
	
	public void selectRadioButton() {
		radioBtnSelect.click();
	}
}
