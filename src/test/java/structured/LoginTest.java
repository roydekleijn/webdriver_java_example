package structured;

import lineair.TestBase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.MyAccountPage;

@Test
public class LoginTest extends TestBase {

	public void loginSuccess() {
		MyAccountPage accountPage = new HomePage(driver).get()
			.clickOnLogin()
			.loginWith("tester@test.com", "1qazxsw2");
		MatcherAssert.assertThat(accountPage.getWelcomeMessage(), Matchers.equalTo("Welcome to your account. Here you can manage all of your personal information and orders."));
	}
}
