package com.vectorsf.jvoiceframework.core.service.ws;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Logger;

public class XMLEndpointProviderTest {
	  private XMLBasedEndpointProvider endpointProvider;
	  private final static String TEST_NAMESPACE = "http://test.namespace.com/";
	  private final static String TEST_NAME = "test-name";
	  private final static String TEST_NOT_PRESENT_NAMESPACE = "BAD";
	  private final static String TEST_NOT_PRESENT_NAME = "BAD";
	  private final static String TEST_URL = "http://test-host:test-port/test-path";
	  
	  private final static String TEST_RESOURCE = "com/vectorsf/jvoiceframework/core/service/ws/ws-endpoints.xml";
	  
	  @Before
	  public void setUp() throws Exception {

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
