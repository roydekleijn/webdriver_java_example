package org.jumbo.intershop.layout;

import java.io.IOException;
import java.util.UUID;

import org.jumbo.TakeElementScreenshot;
import org.jumbo.TestBase;
import org.jumbo.imageCompare.ImageCompare;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Verificatie extends TestBase {

	public void hideDynamicElements(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "opacity: 0;");
	}

	@Test
	public void homepage() throws IOException {
		String orig = "";
		String act = "";
		getData().getDriver().get("http://test.jomni.nl");
		// Exclude dynamic parts
		hideDynamicElements(getData().getDriver(), getData().getDriver()
				.findElement(By.cssSelector("div.jum-carousel-group")));
		// takescreenshot of element
		TakeElementScreenshot screenshot = new TakeElementScreenshot(getData()
				.getDriver());
		orig = screenshot.shoot("homepage",
				getData().getDriver().findElement(By.cssSelector("body")));

		getData().getDriver().get("http://acc.jomni.nl");
		// Exclude dynamic parts
		hideDynamicElements(getData().getDriver(), getData().getDriver()
				.findElement(By.cssSelector("div.jum-carousel-group")));
		// takescreenshot of element
		TakeElementScreenshot screenshot2 = new TakeElementScreenshot(getData()
				.getDriver());
		act = screenshot2.shoot("homepage",
				getData().getDriver().findElement(By.cssSelector("body")));

		// Create a compare object specifying the 2 images for comparison.
		System.out.println(orig);
		System.out.println(act);
		ImageCompare ic = new ImageCompare("target/screenshots/homepage1.jpg",
				"target/screenshots/homepage3.jpg");
		// Set the comparison parameters.
		// (num vertical regions, num horizontal regions, sensitivity,
		// stabilizer)
		ic.setParameters(25, 25, 1, 10);
		// ic.autoSetParameters();
		// Display some indication of the differences in the image.
		ic.setDebugMode(2);
		// Compare.
		ic.compare();
		// Display if these images are considered a match according to our
		// parameters.
		System.out.println("Match: " + ic.match());
		// If its not a match then write a file to show changed regions.
		// if (!ic.match())
		ImageCompare.saveJPG(ic.getChangeIndicator(),
				"src/test/resources/layout/result/" + UUID.randomUUID()
						+ ".jpg");
	}

}
