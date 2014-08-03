package lineair;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test
@Listeners({ ScreenshotListener.class })
public class LoginUsingTestBaseTest extends TestBase {

	public void login() {
		// Click on the login link
		driver.findElement(By.cssSelector("a.login")).click();

		// Enter username
		driver.findElement(By.cssSelector("input#email")).sendKeys(
				"tester@test.com");

		// Enter password
		driver.findElement(By.cssSelector("input#passwd")).sendKeys("1qaxx");

		// Click on Log in
		driver.findElement(By.cssSelector("input#SubmitLogin")).click();

		// Verify if the logout link is displayed
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("a.logout"))
				.isDisplayed(), Matchers.equalTo(true));
	}
}
