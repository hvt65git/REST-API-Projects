package MAVENModule._SeleniumAdactinTests.utils;

import org.openqa.selenium.WebElement;

public class ProxyConditions {

	public static WebElement isVisible(WebElement element) {
		return (element.isDisplayed()) ? element : null;
	}

}


