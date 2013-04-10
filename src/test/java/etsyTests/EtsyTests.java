package etsyTests;

import org.hamcrest.Matchers;

import org.hamcrest.MatcherAssert;

import org.testng.Assert;

import org.openqa.selenium.By;
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
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	/**
	 * This example doesn't use the page object model pattern.
	 * All the Selenium API calls are visible in the test and makes it hard to read.
	 * Also the names of the WebElements are visible in the test, which is not so nice either.
	 */
	@Test
	public void exampleWithoutPageObjectModel() {
		driver.get("http://www.etsy.com");
		driver.findElement(By.cssSelector("a#sign-in")).click();
		driver.findElement(By.cssSelector("input#username-existing")).sendKeys("Test");
		driver.findElement(By.cssSelector("input#password-existing")).sendKeys("Case");
		driver.findElement(By.cssSelector("span#signin-button input")).click();
	}
	
	/**
	 * This example doesn't make use of the advantages of the Page Object Model.
	 * The Only advantage is that all the page specific elements are defined only once
	 */
	@Test
	public void navigationToRegisterPage() {
		HomePage homePage = new HomePage(driver);
		homePage.init();
		homePage.clickSigninLink();
		homePage.clickRegisterLink();
	}

	/**
	 * This example doesn't make use of the advantages of the Page Object Model.
	 * The Only advantage is that all the page specific elements are defined only once
	 */
	@Test
	public void basicExample() {
		HomePage homePage = new HomePage(driver);
		homePage.init();
		homePage.clickSigninLink();
		SignInPage joinPage = new SignInPage(driver);
		joinPage.setUsername("Test");
		joinPage.setPassword("Case");
	}

	/**
	 * This example uses the features of using the page object model. 
	 * The returntype of the used methods is either this (same object) or another object.
	 * Setting the return type allows you to chain the methods
	 */
	@Test
	public void exampleUsingMethodChaining() {
		HomePage homePage = new HomePage(driver);
		homePage
			.init()
			.clickSigninLink()
				.setUsername("Test")
				.setPassword("Case")
				.submitForm();
		
		MatcherAssert.assertThat(true, Matchers.equalTo(false));
	}

	/**
	 * This example uses a factory. Which could be handy if you have a lot of pages.
	 * The factory class is aware of all the pages. In the end it hides all the object initialization from the test
	 */
	@Test
	public void exampleUsingFactory() {
		Factory pages = new Factory(driver);
		pages.homePage()
			.init()
			.clickSigninLink()
				.setUsername("Test")
				.setPassword("Case")
				.submitForm();
	}

}
