package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.utils.Conditions;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

public class BookingConfirmationPage {
	private long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));

	private final String XPATH_TXT_BOOK_CONFIRM_ORDER_NO 	= "//*[@id='order_no']";
	private final String XPATH_TXT_HOTEL_NAME 				= "//*[@id='hotel_name']";
	private final String XPATH_TXT_LOCATION					= "//*[@id='location']";
	private final String XPATH_TXT_TOTAL_ROOMS 				= "//*[@id='total_rooms']";
	private final String XPATH_TXT_ARRIVAL_DATE 			= "//*[@id='arrival_date']";
	private final String XPATH_TXT_DEPARTURE_DATE 			= "//*[@id='departure_text']";
	private final String XPATH_TXT_FINAL_PRICE 				= "//*[@id='final_price']";
	
	private final String XPATH_BTN_BOOK_CONFIRM_SEARCH 		= "//*[@id='search_hotel']";
	private final String XPATH_BTN_BOOK_CONFIRM_ITINERARY 	= "//*[@id='my_itinerary']";
	private final String XPATH_BTN_BOOK_CONFIRM_LOGOUT		= "//*[@id='logout']";
	
	@FindBy(xpath = XPATH_TXT_BOOK_CONFIRM_ORDER_NO)
	WebElement orderNo;

	@FindBy(xpath = XPATH_TXT_HOTEL_NAME)
	WebElement hotelName;
	
	@FindBy(xpath =  XPATH_TXT_LOCATION)	
	WebElement location;
	
	@FindBy(xpath = XPATH_TXT_TOTAL_ROOMS)				
	WebElement totalRooms;
	
	@FindBy(xpath = XPATH_TXT_ARRIVAL_DATE)
	WebElement arrivalDate;
	
	@FindBy(xpath = XPATH_TXT_DEPARTURE_DATE)
	WebElement departureDate;
	
	@FindBy(xpath = XPATH_TXT_FINAL_PRICE)
	WebElement finalPrice;
	
	@FindBy(xpath = XPATH_BTN_BOOK_CONFIRM_ITINERARY)
	WebElement myItineraryBtn;

	public BookingConfirmationPage(String[][] testData) {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		Conditions.waitForPageToLoad(driver, WAIT_TIME_OUT);
		driver.manage().window().maximize();
	}

	public String getOrderNo() throws Exception {
		//need to place an explicit dynamic wait here or null will be returned
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
		.until(ExpectedConditions.visibilityOf(orderNo));
		return orderNo.getAttribute("value");
	}

	public void clickMyItineraryButton() throws Exception {
		//needed to put in an explicit dynamic wait
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
		.until(ExpectedConditions.visibilityOf(myItineraryBtn));
		myItineraryBtn.click();
	}
	
	public String getHotelName() throws Exception {
		return hotelName.getAttribute("value");
	}
	public String getLocation() throws Exception {
		return location.getAttribute("value");
	}
	public String getTotalRooms() throws Exception {
		return totalRooms.getAttribute("value");
	}
	public String getArrivalDate() throws Exception {
		return arrivalDate.getAttribute("value");
	}
	public String getDepartureDate() throws Exception {
		return departureDate.getAttribute("value");
	}
	
	public String getFinalPrice() throws Exception {
		return finalPrice.getAttribute("value");
	}
	
}
