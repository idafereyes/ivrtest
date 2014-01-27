package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OutputTest {
	
	static boolean  BARGEIN = true;
	static boolean  FLUSH = true;
	static boolean  CATCH_HANGUP = true;
	static String SRC_TEXT = "testAudio";
	static String WORDING_TEXT = "it is a wording test";
	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	
	private static ClassPathXmlApplicationContext applicationContext = null;
	
	@BeforeClass
	public static void startContext() {
		applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/bean/test-config-context.xml");
		applicationContext.refresh();
	}
	
	@Test
	public void testDefaultValuesInjection(){
	
		//TODO Puede ser un mock?
		AppConfigDefaults appConfigDefaults = (AppConfigDefaults) applicationContext.getBean(AppConfigDefaults.class);
		
		//When
		Output output = (Output)applicationContext.getBean(Output.class);
		
		//Then
		//Verifies that output attributes have taken appConfigDefault attributes value.
		assertEquals("bargein value is not correct.",output.isBargein(), appConfigDefaults.isBargein());
		assertEquals("flush value is not correct.",output.isFlush(), appConfigDefaults.isFlush());
		assertEquals("catchHangup value is not correct.",output.isCatchHangup(), appConfigDefaults.isCatchHangup());	
	}
	
	@Test
	public void testSetAfterInjection(){

		Output output = (Output)applicationContext.getBean(Output.class);
		
		//When
		output.setBargein(BARGEIN);
		output.setFlush(FLUSH);
		output.setCatchHangup(CATCH_HANGUP);
		
		//Then
		//Verifies that output attributes have been set properly and has no more the injected value.
		assertEquals("bargein value is not correct.",output.isBargein(), BARGEIN);
		assertEquals("flush value is not correct.",output.isFlush(), FLUSH);
		assertEquals("catchHangup value is not correct.",output.isCatchHangup(), CATCH_HANGUP);
	}
	
	@Test
	public void testAudioItemListInitialization(){
		//When
		Output output = (Output)applicationContext.getBean(Output.class);
		
		//Then
		//Verifies that audioItemList has been injected so it is not null
		assertNotNull("audioItemsList is null.", output.getAudioItems());
	}
	
	@Test
	public void testPropertiesMapInitialization(){
		//When
		Output output = (Output)applicationContext.getBean(Output.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", output.getProperties());
	}
	
	@AfterClass 
	public static void closeContext() {
		applicationContext.close();
	}

}
