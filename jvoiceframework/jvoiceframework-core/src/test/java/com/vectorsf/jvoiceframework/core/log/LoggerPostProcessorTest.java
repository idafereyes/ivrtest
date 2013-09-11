package com.vectorsf.jvoiceframework.core.log;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Class to test LoggerPostProcessor
 * @see com.vectorsf.jvoiceframework.core.log.LoggerPostProcessor
 * @author mvinuesa
 *
 */
public class LoggerPostProcessorTest {
	
	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.log";

	/**
	 * Test inject a ExtendedLocLogger in a spring bean, inject object must not be null
	 */
	@Test
	public void testExtendedLocLoggerNotNull() {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();

		LogBeanTest logBeanTest = (LogBeanTest) context.getBean(LogBeanTest.class);
		assertNotNull("ExtendedLocLogger must not be null", logBeanTest.getLogger());

		context.close();
	}
	

}
