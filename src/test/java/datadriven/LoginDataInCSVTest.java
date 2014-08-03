package datadriven;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import lineair.TestBase;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.MyAccountPage;
import au.com.bytecode.opencsv.CSVReader;

@Test
public class LoginDataInCSVTest extends TestBase {
	
	@DataProvider
	public Iterator<String[]> loginAccounts() throws IOException {
        boolean header = false;
        CSVReader reader = new CSVReader(
				new FileReader("src/test/resources/accounts.csv"));
        Iterator<String[]> it = reader.readAll().iterator();
        reader.close();
        if (header == true) {
            it.next();
            it.remove();
        }
        return it;
}

	@Test(dataProvider = "loginAccounts")
	public void loginSuccess(String email, String password) {
		MyAccountPage accountPage = new HomePage(driver).get()
			.clickOnLogin()
			.loginWith(email, password);
		MatcherAssert.assertThat(accountPage.getWelcomeMessage(), Matchers.equalTo("Welcome to your account. Here you can manage all of your personal information and orders."));
	}
}
