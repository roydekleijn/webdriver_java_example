package datadriven;

import lineair.TestBase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.MyAccountPage;

@Test
public class LoginDataInCodeTest extends TestBase {
	
	@DataProvider
	public Object[][] loginAccounts() {
	 return new Object[][] {
	   { "tester@test.com", "1qazxsw2" },
	   { "tester1@test.com", "1qazxsw2" },
	 };
	}

	@Test(dataProvider = "loginAccounts")
	public void loginSuccess(String email, String password) {
		MyAccountPage accountPage = new HomePage(driver).get()
			.clickOnLogin()
			.loginWith(email, password);
		MatcherAssert.assertThat(accountPage.getWelcomeMessage(), Matchers.equalTo("Welcome to your account. Here you can manage all of your personal information and orders."));
	}
}
