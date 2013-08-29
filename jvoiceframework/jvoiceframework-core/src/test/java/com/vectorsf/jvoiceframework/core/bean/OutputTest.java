package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OutputTest {
	
	static boolean  BARGEIN = true;
	static boolean  FLUSH = true;
	static boolean  CATCH_HANGUP = true;
	static String SRC_TEXT = "testAudio";
	static String WORDING_TEXT = "it is a wording test";
	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	
	@Test
	public void testSettersGetters(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		Output output = (Output)context.getBean("output");
		
		//When	
		output.setBargein(BARGEIN);
		output.setFlush(FLUSH);
		output.setCatchHangup(CATCH_HANGUP);
		
		//Then
		//Verifies that attributes has the same value set before
		assertEquals("bargein value is not correct.",output.isBargein(),BARGEIN);
		assertEquals("flush value is not correct.",output.isFlush(),FLUSH);
		assertEquals("catchHangup value is not correct.",output.isCatchHangup(),CATCH_HANGUP);
		
		//Finally
		context.close();
		
	}
	
	@Test
	public void testAudioItemListInjection(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		Output output = (Output)context.getBean("output");
		
		//Then
		//Verifies that audioItemList has been injected so it is not null
		assertNotNull("audioItemsList is null.", output.getAudioItemsList());
		
		//Finally
		context.close();

	}

}
