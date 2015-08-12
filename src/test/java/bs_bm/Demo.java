package bs_bm;

import java.net.URL;

import net.lightbody.bmp.core.har.Har;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Demo {

	@Test(invocationCount = 5)
	public void bmtest() throws Exception {
		// start the proxy
		// ProxyServer server = new ProxyServer(4444);
		// server.start();

		// get the Selenium proxy object
		// Proxy proxy = server.seleniumProxy();

		// configure it as a desired capability
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		final BrowserMobProxyClient bmc = new BrowserMobProxyClient("http://94.211.27.20", 9090);
		final int port = bmc.getPort();
		bmc.setPort(port);
		final Proxy proxy = new Proxy();
		proxy.setHttpProxy("94.211.27.20:" + port);
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		// capabilities.setCapability(CapabilityType.PROXY, proxy);

		final String bsname = "roydekleijn1";
		final String bskey = "zQS2TTf8esQxq1j96oKD";
		capabilities.setCapability("browser", "firefox");
		capabilities.setCapability("browser_version", "31.0");
		capabilities.setCapability("os", "Windows");
		capabilities.setCapability("os_version", "7");
		capabilities.setCapability("browserstack.debug", "true");

		final WebDriver driver = new RemoteWebDriver(new URL("http://" + bsname + ":" + bskey + "@hub.browserstack.com/wd/hub"), capabilities);

		// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);

		// start the browser up
		// WebDriver driver = new FirefoxDriver(capabilities);

		// create a new HAR with the label "yahoo.com"

		bmc.newHar("yahoo.com", false, true, false);

		// open yahoo.com
		driver.get("http://selenium.polteq.com");
		bmc.waitForNetworkTrafficToStop(5000, 45000);

		// get the HAR data
		final Har har = bmc.getHar();
		System.out.println(har.getLog().getEntries().size());
	}
}
