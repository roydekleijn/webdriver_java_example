package pages2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class AuthenticationPage extends LoadableComponent<AuthenticationPage> {
	private final WebDriver driver;

	@FindBy(css = "input#email")
	private WebElement emailTextfield;

	@FindBy(css = "input#passwd")
	private WebElement passwordTextfield;

	@FindBy(css = "input#SubmitLogin")
	private WebElement loginButton;

	public AuthenticationPage(final WebDriver driver) {
		this.driver = driver;

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// Open the page
		this.driver.get("http://demo.rdekleijn.nl/index.php?controller=authentication&back=my-account");
	}

	@Override
	protected void isLoaded() throws Error {
		// verify if the correct page is open
		final String title = this.driver.getTitle();
		Assert.assertTrue(title.endsWith("Authentication - TestShop"));
	}

	public MyAccountPage loginWith(final String email, final String password) {
		this.emailTextfield.sendKeys(email);
		this.passwordTextfield.sendKeys(password);
		this.loginButton.click();
		return new MyAccountPage(this.driver);
	}

}
