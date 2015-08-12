package bs_bm;

import org.testng.annotations.Test;

public class BrowserMobProxyClientTest {

	@Test
	public void testProxy() {
		BrowserMobProxyClient bmc = new BrowserMobProxyClient("http://94.211.27.20", 9090);
		int port = bmc.getPort();

	}
}
