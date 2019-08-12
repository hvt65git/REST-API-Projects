package sffb9;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Page Object Model implementation of Google main page
 */
public class GoogleMainPage {
	private final String URL = "https://google.com";
	private final long WAIT_TIMEOUT = 10; //sec
	private final String XPATH_TXT_BOX = "//*[@name = 'q']";
	
	
	@FindBy(xpath = XPATH_TXT_BOX)
	WebElement txtBoxElement;
	
	public GoogleMainPage() {
		//init then display page 
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}
	
	public void search(String term) {
		//wait for txtBoxElement to be visible
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIMEOUT)
		.until(ExpectedConditions.visibilityOf(txtBoxElement));
		
		//search for the term
		txtBoxElement.sendKeys(term);
		txtBoxElement.submit();
	}
}
