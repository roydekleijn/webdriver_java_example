package pages;

import org.openqa.selenium.WebDriver;

public class Factory {
	private WebDriver driver;

	public Factory(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage homePage() {
		return new HomePage(driver);
	}

	public SignInPage signInPage() {
		return new SignInPage(driver);
	}
}
