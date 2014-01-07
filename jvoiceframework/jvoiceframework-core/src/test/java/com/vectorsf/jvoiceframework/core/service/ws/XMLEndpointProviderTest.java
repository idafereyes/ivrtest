package com.vectorsf.jvoiceframework.core.service.ws;

import javax.xml.namespace.QName;

import org.junit.BeforeClass;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class XMLEndpointProviderTest {
	private static XMLBasedEndpointProvider endpointProvider;
	private final static String TEST_NAMESPACE = "http://test.namespace.com/";
	private final static String TEST_NAME = "test-name";
	private final static String TEST_NOT_PRESENT_NAMESPACE = "BAD";
	private final static String TEST_NOT_PRESENT_NAME = "BAD";
	private final static String TEST_URL = "http://test-host:test-port/test-path";

	private final static String TEST_RESOURCE = "com/vectorsf/jvoiceframework/core/service/ws/ws-endpoints.xml";

	@BeforeClass
	public static void setUp() throws Exception {

		Logger logger = mock(ExtendedLocLogger.class);

		endpointProvider = new XMLBasedEndpointProvider();
		endpointProvider.setLogger(logger);
		endpointProvider.setResource(TEST_RESOURCE);
		endpointProvider.initialize();
	}

	@Test
	public void testGetEndpointByQName() throws Exception {
		String url = endpointProvider.getEndpoint(new QName(TEST_NAMESPACE, TEST_NAME));
		assertEquals(TEST_URL, url);
	}

	@Test
	public void testGetEndpoint() throws Exception {
		String url = endpointProvider.getEndpoint(TEST_NAMESPACE, TEST_NAME);
		assertEquals(TEST_URL, url);
	}

	@Test
	public void testGetNotPresentEndpointByQName() throws Exception {
		String url = endpointProvider.getEndpoint(new QName(TEST_NOT_PRESENT_NAMESPACE, TEST_NOT_PRESENT_NAME));
		assertEquals(null, url);
	}

	@Test
	public void testGetNotPresentEndpoint() throws Exception {
		String url = endpointProvider.getEndpoint(TEST_NOT_PRESENT_NAMESPACE, TEST_NOT_PRESENT_NAME);
		assertEquals(null, url);
	}

}
