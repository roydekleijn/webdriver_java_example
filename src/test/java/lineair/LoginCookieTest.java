package lineair;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

@Test(groups = { "all" })
public class LoginCookieTest {

	public void login() {
		// Create a new instance of the Firefox driver
		WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Click on the login link
		driver.findElement(By.cssSelector("a.login")).click();

		// Enter username
		driver.findElement(By.cssSelector("input#email")).sendKeys("tester@test.com");

		// Or use one of the following
		// driver.findElement(By.cssSelector("input[id=email]")).sendKeys("tester@test.com");
		// driver.findElement(By.id("email")).sendKeys("tester@test.com");
		// driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("tester@test.com");

		// Enter password
		driver.findElement(By.cssSelector("input#passwd")).sendKeys("1qazxsw2");

		// Click on Log in
		driver.findElement(By.cssSelector("input#SubmitLogin")).click();

		// Verify if logout link is displayed
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("a.logout")).isDisplayed(), Matchers.equalTo(true));

		// Delete cookies
		driver.manage().deleteAllCookies();

		// Refresh page
		driver.navigate().refresh();

		// Verify if logout link is NOT displayed
		MatcherAssert.assertThat(driver.findElements(By.cssSelector("a.logout")).isEmpty(), Matchers.equalTo(true));

		// Close the browser
		driver.quit();
	}
}
