package pages2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class HomePage extends LoadableComponent<HomePage> {
	private final WebDriver driver;

	@FindBy(css = "a.login")
	private WebElement loginLink;

	public HomePage(final WebDriver driver) {
		this.driver = driver;

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// Open the page
		this.driver.get("http://selenium.polteq.com/index.php");
	}

	@Override
	protected void isLoaded() throws Error {
		// verify if the correct page is open
		final String url = this.driver.getCurrentUrl();
		Assert.assertTrue(url.endsWith("/index.php"));
	}

	public AuthenticationPage clickOnLogin() {
		this.loginLink.click();
		return new AuthenticationPage(this.driver);
	}

}
