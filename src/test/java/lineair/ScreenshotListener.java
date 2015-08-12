package lineair;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		Object currentClass = result.getInstance();
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			WebDriver webDriver = ((TestBase) currentClass).driver;
			if (webDriver != null) {
				File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
				String fileName = result.getName() + UUID.randomUUID();
				File targetFile = new File("target/screenshots/" + fileName + ".png");
				FileUtils.copyFile(screenshotFile, targetFile);
				result.setAttribute("screenshot", "<a target='blank' href='../screenshots/" + fileName + ".png'>Screenshot</a>");
				Reporter.setCurrentTestResult(result);
				Reporter.log("<a target='blank' href='" + targetFile.getAbsolutePath() + "'> Screenshot</a>\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
