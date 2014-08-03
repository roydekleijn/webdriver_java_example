package lineair;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

@Test
public class NavigationTest {

	public void navigateToPage() {
		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Navigate to the contact page
		driver.findElement(By.cssSelector("li#header_link_contact > a"))
				.click();

		// Go back to the homepage
		driver.navigate().back();

		// Go forward to the contact page
		driver.navigate().forward();

		// Go back to the homepage
		driver.navigate().to("http://selenium.polteq.com/testshop/index.php");

		// Refresh the browser
		driver.navigate().refresh();

		// Close the browser
		driver.quit();
	}
}
