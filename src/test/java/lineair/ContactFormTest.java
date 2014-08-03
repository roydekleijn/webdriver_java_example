package lineair;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ContactFormTest extends TestBase {

	@Test
	public void completeContactForm() {
		// Open the contact page
		driver.findElement(By.cssSelector("li#header_link_contact > a")).click();
		
		// Select heading subject
		Select subject = new Select(driver.findElement(By.cssSelector("select#id_contact")));
		subject.selectByVisibleText("Customer service");
		
		// Enter emailaddress
		driver.findElement(By.cssSelector("input#email")).sendKeys("test@test.com");
		
		// Enter reference
		driver.findElement(By.cssSelector("input#id_order")).sendKeys("0987654321");
		
		// Enter message
		driver.findElement(By.cssSelector("textarea#message")).sendKeys("Some message");
		
		// Click on Send
		driver.findElement(By.cssSelector("input#submitMessage")).click();
		
		// Verify message was send
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("div#center_column > p")).getText(), Matchers.equalTo("Your message has been successfully sent to our team."));
		
	}
}
