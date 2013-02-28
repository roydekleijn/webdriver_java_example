package etsyTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.Factory;
import pages.HomePage;
import pages.SignInPage;

public class EtsyTests {
	protected WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new FirefoxDriver();
		driver.get("http://www.etsy.com");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void navigationToRegisterPage() {
		HomePage homePage = new HomePage(driver);
		homePage.clickSigninLink();
		homePage.clickRegisterLink();

	}

	@Test
	public void basicExample() {
		HomePage homePage = new HomePage(driver);
		homePage.clickSigninLink();
		SignInPage joinPage = new SignInPage(driver);
		joinPage.setUsername("Test");
		joinPage.setPassword("Case");
	}

	@Test
	public void exampleUsingMethodChaining() {
		HomePage homePage = new HomePage(driver);
		homePage.clickSigninLink().setUsername("Test")
				.setPassword("Case").submitForm();
	}

	@Test
	public void exampleUsingFactory() {
		Factory pages = new Factory(driver);
		pages.homePage().clickSigninLink().setUsername("Test")
				.setPassword("Case").submitForm();
	}

}
