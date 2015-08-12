package datadriven;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import lineair.TestBase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages2.HomePage;
import pages2.MyAccountPage;
import au.com.bytecode.opencsv.CSVReader;

@Test(groups = { "all" })
public class LoginDataInCSVTest extends TestBase {

	@DataProvider
	public Iterator<String[]> loginAccounts() throws IOException {
		final boolean header = false;
		final CSVReader reader = new CSVReader(new FileReader("src/test/resources/accounts.csv"));
		final Iterator<String[]> it = reader.readAll().iterator();
		reader.close();
		if (header == true) {
			it.next();
			it.remove();
		}
		return it;
	}

	@Test(dataProvider = "loginAccounts")
	public void loginSuccess(final String email, final String password) {
		final MyAccountPage accountPage = new HomePage(this.driver).get().clickOnLogin().loginWith(email, password);
		MatcherAssert.assertThat(accountPage.getWelcomeMessage(),
				Matchers.equalTo("Welcome to your account. Here you can manage all of your personal information and orders."));
	}
}
