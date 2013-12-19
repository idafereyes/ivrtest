package com.vectorsf.jvoiceframework.core.log;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ExtendedLogLoggerFactoryTest {
	
	@Test
	public void testExtendedLogLoggerFactory() {
		 assertNotNull("Logger must not be null", ExtendedLocLoggerFactory.getLogger(this.getClass()));
	}
	
	@Test
	public void testExtendedLogLoggerFactoryTwice() {
		 assertNotNull("Logger must not be null", ExtendedLocLoggerFactory.getLogger(this.getClass()));
	}

}
