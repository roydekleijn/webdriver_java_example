package datadriven;

import lineair.TestBase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages2.HomePage;
import pages2.MyAccountPage;

@Test(groups = { "all" })
public class LoginDataInCodeTest extends TestBase {

	@DataProvider
	public Object[][] loginAccounts() {
		return new Object[][]{ { "tester@test.com", "1qazxsw2" }, { "tester1@test.com", "1qazxsw2" }, };
	}

	@Test(dataProvider = "loginAccounts")
	public void loginSuccess(final String email, final String password) {
		final MyAccountPage accountPage = new HomePage(this.driver).get().clickOnLogin().loginWith(email, password);
		MatcherAssert.assertThat(accountPage.getWelcomeMessage(),
				Matchers.equalTo("Welcome to your account. Here you can manage all of your personal information and orders."));
	}
}
