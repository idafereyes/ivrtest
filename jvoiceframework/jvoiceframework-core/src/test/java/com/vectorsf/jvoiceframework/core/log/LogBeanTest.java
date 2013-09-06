package com.vectorsf.jvoiceframework.core.log;

import org.springframework.stereotype.Component;

/**
 * Bean to Test LoggerPostProcessorTest
 * 
 * @author mvinuesa
 *
 */
@Component("logBeanText")
public class LogBeanTest {
	@Log
	private Logger logger;
	
	public Logger getLogger() {
		return logger;
	}
}
