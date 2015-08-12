package structured;

import lineair.TestBase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import pages2.HomePage;
import pages2.MyAccountPage;

@Test(groups = { "all" })
public class LoginTest extends TestBase {

	public void loginSuccess() {
		final MyAccountPage accountPage = new HomePage(this.driver).get().clickOnLogin().loginWith("tester@test.com", "1qazxsw2");
		MatcherAssert.assertThat(accountPage.getWelcomeMessage(),
				Matchers.equalTo("Welcome to your account. Here you can manage all of your personal information and orders."));
		;
	}

	/*
	 * 
	 */

}
