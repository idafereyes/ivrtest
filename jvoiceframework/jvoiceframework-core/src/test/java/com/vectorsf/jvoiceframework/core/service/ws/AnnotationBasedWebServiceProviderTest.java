package com.vectorsf.jvoiceframework.core.service.ws;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Logger;
import com.vectorsf.jvoiceframework.ws.test.HelloService;


public class AnnotationBasedWebServiceProviderTest {
	
	  private AnnotationBasedWebServiceProvider webServiceProvider;
	  private Class<HelloService> wsPortInterface = HelloService.class;	
	  	  
	  private final static String TEST_NAMESPACE = "http://bluedash.net/ws";
	  private final static String TEST_URL = "http://test-host:test-port/test-path"; 
	  private final static String END_POINT_NAME = "HelloServiceSOAP";
	  
	  @Before
	  public void setUp() throws Exception {
		  Logger logger = mock(ExtendedLocLogger.class);
		  
		  EndpointProvider endpointProvider = mock(XMLBasedEndpointProvider.class);
		  Mockito.when(endpointProvider.getEndpoint(new QName(TEST_NAMESPACE, END_POINT_NAME))).thenReturn(TEST_URL);
	  
		  
		  AnnotationBasedWebServiceUtils annotationBasedWebServiceUtils = new AnnotationBasedWebServiceUtils();
		  annotationBasedWebServiceUtils.setLogger(logger);
		  
		  webServiceProvider = new AnnotationBasedWebServiceProvider();	 
		  
		  webServiceProvider.setLogger(logger);
		  webServiceProvider.setEndpointProvider(endpointProvider);
		  webServiceProvider.setHandlerChainProvider(new HandlerChainProvider());
		  webServiceProvider.setAnnotationBasedWebServiceUtils(annotationBasedWebServiceUtils);
	  }
	
	  @Test
	  public void testGetClient() throws Exception {
		  HelloService wsClient = webServiceProvider.getClient(wsPortInterface, END_POINT_NAME);
	  
		  BindingProvider bindingProvider = (BindingProvider) wsClient;
		  String endpoint = (String) bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
		  assertEquals(TEST_URL, endpoint);	  
	  }
	  
	  @Test(expected=WebServiceProviderException.class)
	  public void testGetClientNoAnnotation() throws Exception {
		@SuppressWarnings("unused")
		Object wsClient = webServiceProvider.getClient(Object.class, null);		  
	  }
  
}
