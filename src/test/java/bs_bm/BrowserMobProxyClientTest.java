package bs_bm;

import org.testng.annotations.Test;

public class BrowserMobProxyClientTest {

	@Test
	public void testProxy() {
		final BrowserMobProxyClient bmc = new BrowserMobProxyClient("http://94.211.27.20", 9090);
		bmc.getPort();

	}
}
