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
	public void testDefaultValuesInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		//TODO Puede ser un mock?
		AppConfigDefaults appConfigDefaults = (AppConfigDefaults) context.getBean(AppConfigDefaults.class);
		
		//When
		Output output = (Output)context.getBean(Output.class);
		
		//Then
		//Verifies that output attributes have taken appConfigDefault attributes value.
		assertEquals("bargein value is not correct.",output.isBargein(), appConfigDefaults.isBargein());
		assertEquals("flush value is not correct.",output.isFlush(), appConfigDefaults.isBargein());
		assertEquals("catchHangup value is not correct.",output.isCatchHangup(), appConfigDefaults.isBargein());
		
		//Finally
		context.close();
		
	}
	
	@Test
	public void testSetAfterInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		Output output = (Output)context.getBean(Output.class);
		
		//When
		output.setBargein(BARGEIN);
		output.setFlush(FLUSH);
		output.setCatchHangup(CATCH_HANGUP);
		
		//Then
		//Verifies that output attributes have been set properly and has no more the injected value.
		assertEquals("bargein value is not correct.",output.isBargein(), BARGEIN);
		assertEquals("flush value is not correct.",output.isFlush(), FLUSH);
		assertEquals("catchHangup value is not correct.",output.isCatchHangup(), CATCH_HANGUP);
		
		//Finally
		context.close();
		
	}
	
	@Test
	public void testAudioItemListInitialization(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		Output output = (Output)context.getBean(Output.class);
		
		//Then
		//Verifies that audioItemList has been injected so it is not null
		assertNotNull("audioItemsList is null.", output.getAudioItems());
		
		//Finally
		context.close();

	}
	
	@Test
	public void testPropertiesMapInitialization(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		Output output = (Output)context.getBean(Output.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", output.getProperties());
		
		//Finally
		context.close();

	}


}
