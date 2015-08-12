package lineair;

import java.util.concurrent.TimeUnit;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

@Test(groups = { "all" })
public class WaitTest {

	public void orderProductWithoutWait() {
		// Create a new instance of the Firefox driver
		final WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Click on a product
		driver.findElements(By.cssSelector("a.product_image")).get(1).click();

		// Add product to cart
		driver.findElement(By.cssSelector("p#add_to_cart > input")).click();

		// Verify product is in cart
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("span.ajax_cart_quantity")).getText(), Matchers.equalTo("1"));

		// Close the browser
		driver.quit();
	}

	public void orderProductWithBadWait() throws InterruptedException {
		// Create a new instance of the Firefox driver
		final WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Click on a product
		driver.findElements(By.cssSelector("a.product_image")).get(1).click();

		// Add product to cart
		driver.findElement(By.cssSelector("p#add_to_cart > input")).click();

		// Just wait for 15 seconds
		Thread.sleep(15000);

		// Verify product is in cart
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("a > span.ajax_cart_quantity")).getText(), Matchers.equalTo("1"));

		// Close the browser
		driver.quit();
	}

	public void orderProductWithImplicitlyWait() {
		// Create a new instance of the Firefox driver
		final WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Set implicitly wait
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		// Click on a product
		driver.findElements(By.cssSelector("a.product_image")).get(1).click();

		// Add product to cart
		driver.findElement(By.cssSelector("p#add_to_cart > input")).click();

		// Verify product is in cart
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("a > span.ajax_cart_quantity")).getText(), Matchers.equalTo("1"));

		// Close the browser
		driver.quit();
	}

	public void orderProductWithExpectedCondition() {
		// Create a new instance of the Firefox driver
		final WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Set implicitly wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Click on a product
		driver.findElements(By.cssSelector("a.product_image")).get(1).click();

		// Add product to cart
		driver.findElement(By.cssSelector("p#add_to_cart > input")).click();

		// Wait for text to be present
		final WebDriverWait wait = new WebDriverWait(driver, 45, 100);
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("a > span.ajax_cart_quantity")), "1"));

		// Verify product is in cart
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("a > span.ajax_cart_quantity")).getText(), Matchers.equalTo("1"));

		// Close the browser
		driver.quit();
	}

	public void orderProductWithFluentWait() {
		// Create a new instance of the Firefox driver
		final WebDriver driver = new FirefoxDriver();

		// Open the website
		driver.get("http://selenium.polteq.com/testshop/index.php");

		// Set implicitly wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Click on a product
		driver.findElements(By.cssSelector("a.product_image")).get(1).click();

		// Add product to cart
		driver.findElement(By.cssSelector("p#add_to_cart > input")).click();

		// Wait for element
		final FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(45, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a > span.ajax_cart_quantity"))));

		// Verify product is in cart
		MatcherAssert.assertThat(driver.findElement(By.cssSelector("a > span.ajax_cart_quantity")).getText(), Matchers.equalTo("1"));

		// Close the browser
		driver.quit();
	}
}
