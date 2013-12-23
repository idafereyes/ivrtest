package com.vectorsf.jvoiceframework.core.service.ws;

import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.junit.Before;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.log.Logger;
import com.vectorsf.jvoiceframework.core.service.ws.LoggingHandler;

import static org.mockito.Mockito.mock;

public class LoggingHandlerTest {
	  private LoggingHandler loggingHandler;
	  private SOAPMessageContext smc;
	  
	  @Before
	  public void setUp() throws Exception {
		  Logger logger = mock(Logger.class);
		  loggingHandler = new LoggingHandler();
		  loggingHandler.setLogger(logger);
		  smc = mock(SOAPMessageContext.class);
		  
	  }
	
	  @Test
	  public void testHandleMessage() throws Exception {
		  loggingHandler.handleFault(smc);
	  }
	  

	  @Test
	  public void testHandleFault() throws Exception {
		  loggingHandler.handleFault(smc);
	  }

}
