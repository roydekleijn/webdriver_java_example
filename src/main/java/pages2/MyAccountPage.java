package pages2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class MyAccountPage extends LoadableComponent<MyAccountPage> {

	private WebDriver driver;

	@FindBy(css = "a.login")
	private WebElement loginLink;
	
	@FindBy(css = "div#center_column > p")
	private WebElement welcomeMessage;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// Open the page
		driver.get("http://selenium.polteq.com/testshop/index.php?controller=my-account");
	}

	@Override
	protected void isLoaded() throws Error {
		// verify if the correct page is open
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.endsWith("?controller=my-account"));
	}
	
	public String getWelcomeMessage() {
		return welcomeMessage.getText();
	}

}
