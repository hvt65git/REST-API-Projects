package MAVENModule._SeleniumAdactinTests.system;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverSetup {
	public DesiredCapabilities getDesiredCapabilities();
	public WebDriver getWebDriverObject(DesiredCapabilities capabilities);
}