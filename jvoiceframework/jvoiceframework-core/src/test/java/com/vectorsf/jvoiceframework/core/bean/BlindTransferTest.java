package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlindTransferTest {

	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String DEST = "666777888";
	static String TRANSFERAUDIO = "idleMusicDefault";
	
	static String PROP_KEY1 = "timeout";
	static String PROP_VALUE1 = "10s";
	static String PROP_KEY2 = "bargein";
	static String PROP_VALUE2 = "true";
	
	static String CUSTOM_EVENT1 = "customEvent1";
	static String CUSTOM_EVENT2 = "customEvent2";
	
	static Map<String, String> properties;
	static List<String> customEvents;
	
	static {		
		properties = new HashMap<String, String>();
		properties.put(PROP_KEY1, PROP_VALUE1);
		properties.put(PROP_KEY2, PROP_VALUE2);
		
		customEvents = new ArrayList<String>();
		customEvents.add(CUSTOM_EVENT1);
		customEvents.add(CUSTOM_EVENT2);
	}
	
	private static ClassPathXmlApplicationContext applicationContext = null;
	
	@BeforeClass
	public static void startContext() {
		applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/bean/test-config-context.xml");
		applicationContext.refresh();
	}
		
	@Test
	public void testSetAfterInjection(){
		
		BlindTransfer blindTransfer = (BlindTransfer)applicationContext.getBean(BlindTransfer.class);
		
		//When
		blindTransfer.setDest(DEST);
		blindTransfer.setTransferaudio(TRANSFERAUDIO);
		
		//Then
		//Verifies that transfer attributes transferaudio and dest have been set properly.
		assertEquals("dest value is not correct.",blindTransfer.getDest(), DEST);
		assertEquals("transferaudio value is not correct.",blindTransfer.getTransferaudio(), TRANSFERAUDIO);	
	}

	@Test
	public void testPropertiesInjection(){
		
		BlindTransfer blindTransfer = (BlindTransfer)applicationContext.getBean(BlindTransfer.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", blindTransfer.getProperties());
		
		//Verify getter and setter for properties
		blindTransfer.setProperties(properties);
		assertEquals("Checking BlindTransfer properties getter and setter", 2, blindTransfer.getProperties().size());
		assertEquals("Checking BlindTransfer properties getter and setter", PROP_VALUE1, blindTransfer.getProperties().get(PROP_KEY1));
		assertEquals("Checking BlindTransfer properties getter and setter", PROP_VALUE2, blindTransfer.getProperties().get(PROP_KEY2));;

	}

	@Test
	public void testCustomEvents(){
		
		//When
		BlindTransfer blindTransfer = (BlindTransfer)applicationContext.getBean(BlindTransfer.class);
		
		//Then
		//Verifies that custom events list has been initialized so it is not null
		assertNotNull("Checking BlindTransfer custom events is not null.", blindTransfer.getCustomEvents());
		
		//Verify getter and setter for custom events
		blindTransfer.setCustomEvents(customEvents);
		assertEquals("Checking BlindTransfer custom event getter and setter", 2, blindTransfer.getCustomEvents().size());
		assertEquals("Checking BlindTransfer custom event getter and setter", CUSTOM_EVENT1, blindTransfer.getCustomEvents().get(0));
		assertEquals("Checking BlindTransfer custom event getter and setter", CUSTOM_EVENT2, blindTransfer.getCustomEvents().get(1));	

	}
	
	@AfterClass 
	public static void closeContext() {
		applicationContext.close();
	}
}
