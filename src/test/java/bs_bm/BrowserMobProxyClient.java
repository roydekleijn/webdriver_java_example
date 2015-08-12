package bs_bm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import net.lightbody.bmp.core.har.Har;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * The Class BrowserMobProxy.
 */
public class BrowserMobProxyClient {

	/** The service. */
	private final WebResource service;

	/** The mapper. */
	private final ObjectMapper mapper = new ObjectMapper();

	private int port;

	/** The Constant PORT_BEGININDEX. */
	public static final int PORT_BEGININDEX = 8;

	/** The Constant PORT_ENDINDEX. */
	public static final int PORT_ENDINDEX = 12;

	/** The Constant HTTP_STATUS_CODE_200. */
	public static final int HTTP_STATUS_CODE_200 = 200;

	/** The Constant HTTP_STATUS_CODE_204. */
	public static final int HTTP_STATUS_CODE_204 = 204;

	/**
	 * Instantiates a new browser mob proxy.
	 *
	 * @param host the host
	 * @param port the port
	 */
	public BrowserMobProxyClient(final String host, final int port) {
		final ClientConfig config = new DefaultClientConfig();
		final Client client = Client.create(config);
		this.service = client.resource(host + ":" + port + "/proxy");
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public int getPortUsingUpstreamProxy(final String upStreamProxyServer) {
		final ClientResponse response = this.service.queryParam("httpProxy", upStreamProxyServer).post(ClientResponse.class);
		return Integer.parseInt(response.getEntity(String.class).substring(BrowserMobProxyClient.PORT_BEGININDEX, BrowserMobProxyClient.PORT_ENDINDEX));
	}

	public boolean addHeader(final Map<String, String> headers) throws IOException {
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("headers").post(ClientResponse.class, this.mapper.writeValueAsString(headers));
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean autoBasicAuthorization(final String domain, final String username, final String password) throws IOException {
		final Map<String, String> auth = new HashMap<String, String>();
		auth.put("username", username);
		auth.put("password", password);

		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("auth").path("basic").path(domain)
				.post(ClientResponse.class, this.mapper.writeValueAsString(auth));
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean blacklistRequests(final String pattern, final int responseCode) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("regex", pattern);
		formData.add("status", Integer.toString(responseCode));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("blacklist").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean clearDNSCache() {
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("dns").path("cache").delete(ClientResponse.class);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public Har getHar() throws IOException {
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("har").get(ClientResponse.class);
		final String responseBody = response.getEntity(String.class);
		return this.mapper.readValue(responseBody, Har.class);
	}

	public int getPort() {
		final ClientResponse response = this.service.post(ClientResponse.class);
		final String responseBody = response.getEntity(String.class);
		final int actPort = Integer.parseInt(responseBody.substring(BrowserMobProxyClient.PORT_BEGININDEX, BrowserMobProxyClient.PORT_ENDINDEX));
		return actPort;
	}

	public int getPort(final int port) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("port", Integer.toString(port));
		final ClientResponse response = this.service.post(ClientResponse.class, formData);
		final int newPort = Integer.parseInt(response.getEntity(String.class).substring(BrowserMobProxyClient.PORT_BEGININDEX, BrowserMobProxyClient.PORT_ENDINDEX));
		return newPort;
	}

	public boolean enableLimiter(final boolean status) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("enable", Boolean.toString(status));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("limit").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setDownstreamKbps(final long downstreamKbps) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("downstreamKbps", Long.toString(downstreamKbps));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("limit").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setLatency(final long latency) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("latency", Long.toString(latency));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("limit").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setMaxBitsPerSecondThreshold(final long maxBitsPerSecond) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("maxBitsPerSecond", Long.toString(maxBitsPerSecond));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("limit").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setPayloadPercentage(final long payloadPercentage) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("payloadPercentage", Long.toString(payloadPercentage));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("limit").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setUpstreamKbps(final long upstreamKbps) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("upstreamKbps", Long.toString(upstreamKbps));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("limit").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean newHar(final String initialPageRef, final boolean captureContent, final boolean captureHeaders, final boolean captureBinaryContent) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		if (!initialPageRef.isEmpty()) {
			formData.add("initialPageRef", initialPageRef);
		}
		if (captureContent) {
			formData.add("captureContent", Boolean.toString(captureContent));
		}
		if (captureHeaders) {
			formData.add("captureHeaders", Boolean.toString(captureHeaders));
		}
		if (captureBinaryContent) {
			formData.add("captureBinaryContent", Boolean.toString(captureBinaryContent));
		}
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("har").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_204);
	}

	public boolean newPage(final String pageRef) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		if (!pageRef.isEmpty()) {
			formData.add("pageRef", pageRef);
		}
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("har").path("pageRef").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean remapHost(final Map<String, String> hosts) throws IOException {
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("hosts").post(ClientResponse.class, this.mapper.writeValueAsString(hosts));
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean rewriteUrl(final String matchRegex, final String replace) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("matchRegex", matchRegex);
		formData.add("replace", replace);
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("rewrite").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setConnectionTimeout(final long connectionTimeout) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("connectionTimeout", Long.toString(connectionTimeout));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("timeout").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setDNSCacheTimeout(final long dnsCacheTimeout) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("dnsCacheTimeout", Long.toString(dnsCacheTimeout));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("timeout").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setRequestTimeout(final long requestTimeout) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("requestTimeout", Long.toString(requestTimeout));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("timeout").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setRetryCount(final int retrycount) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("retrycount", Integer.toString(retrycount));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("retry").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean setSocketOperationTimeout(final long readTimeout) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("readTimeout", Long.toString(readTimeout));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("timeout").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean stop() {
		final ClientResponse response = this.service.path(Integer.toString(this.port)).delete(ClientResponse.class);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean waitForNetworkTrafficToStop(final long quietPeriodInMs, final long timeoutInMs) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("quietPeriodInMs", Long.toString(quietPeriodInMs));
		formData.add("timeoutInMs", Long.toString(timeoutInMs));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("wait").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

	public boolean whitelistRequests(final String patterns, final int responseCode) {
		final MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("regex", patterns);
		formData.add("status", Integer.toString(responseCode));
		final ClientResponse response = this.service.path(Integer.toString(this.port)).path("whitelist").put(ClientResponse.class, formData);
		return (response.getStatus() == BrowserMobProxyClient.HTTP_STATUS_CODE_200);
	}

}
