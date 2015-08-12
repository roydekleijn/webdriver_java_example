package browserstack;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoTest {
	private WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws MalformedURLException {
		final String bsname = "xxx";
		final String bskey = "xxx";
		final DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "firefox");
		caps.setCapability("browser_version", "31.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "7");
		caps.setCapability("browserstack.debug", "true");

		this.driver = new RemoteWebDriver(new URL("http://" + bsname + ":" + bskey + "@hub.browserstack.com/wd/hub"), caps);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		this.driver.quit();
	}

	@Test(invocationCount = 15, groups = { "bsdemo" })
	public void exampleTest() {
		this.driver.get("http://www.google.com/ncr");
		final WebElement element = this.driver.findElement(By.name("q"));

		element.sendKeys("BrowserStack");
		element.submit();

		System.out.println(this.driver.getTitle());
	}
}
