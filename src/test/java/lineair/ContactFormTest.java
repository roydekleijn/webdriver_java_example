package lineair;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactFormTest extends TestBase {

	@Test(groups = { "all" })
	public void completeContactForm() {
		// Open the contact page
		this.driver.findElement(By.cssSelector("li#header_link_contact > a")).click();

		// Select heading subject
		// Select subject = new
		// Select(driver.findElement(By.cssSelector("select#id_contact")));
		// subject.selectByVisibleText("Customer service");

		// Alternatief
		this.driver.findElement(By.xpath("//select[@id = 'id_contact']/option[@value='1']")).click();

		// Enter emailaddress
		this.driver.findElement(By.cssSelector("input#email")).sendKeys("test@test.com");

		// Enter reference
		this.driver.findElement(By.cssSelector("input#id_order")).sendKeys("0987654321");

		// Enter message
		this.driver.findElement(By.cssSelector("textarea#message")).sendKeys("Some message");

		// Click on Send
		this.driver.findElement(By.cssSelector("input#submitMessage")).click();

		// Verify message was send
		MatcherAssert.assertThat(this.driver.findElement(By.cssSelector("div#center_column > p")).getText(),
				Matchers.equalTo("Your message has been successfully sent to our team."));

	}

}
