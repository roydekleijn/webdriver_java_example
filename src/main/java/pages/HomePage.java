package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class HomePage extends LoadableComponent<HomePage> {
	private final WebDriver driver;

	@FindBy(css = ".login")
	private WebElement loginLink;

	@Override
	protected void load() {
		this.driver.get("http://demo.rdekleijn.nl");
	}

	@Override
	protected void isLoaded() throws Error {
		final String url = this.driver.getCurrentUrl();
		Assert.assertTrue(url.equals("http://demo.rdekleijn.nl/index.php"));
	}

	public HomePage(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnLoginLink() {
		this.loginLink.click();
	}

}
