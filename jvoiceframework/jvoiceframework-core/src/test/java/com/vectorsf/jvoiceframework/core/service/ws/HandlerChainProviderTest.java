package com.vectorsf.jvoiceframework.core.service.ws;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.PortInfo;

import org.junit.Before;
import org.junit.Test;

public class HandlerChainProviderTest {
		
	  HandlerChainProvider handlerChainProvider;
	  
	  @Before
	  public void setUp() {
		  handlerChainProvider = new HandlerChainProvider();  
	  }
	
	  @Test
	  public void testGetHandlerChain() {
		  PortInfo portInfo = mock(PortInfo.class);
		  @SuppressWarnings("rawtypes")
		  List<Handler> handlerChain = handlerChainProvider.getHandlerChain(portInfo);
		  @SuppressWarnings("rawtypes")
		  List<Handler> directHandlerChain = handlerChainProvider.getHandlers();
		  assertTrue( handlerChain.size() == directHandlerChain.size() && handlerChain.containsAll(directHandlerChain));
	  }
}
