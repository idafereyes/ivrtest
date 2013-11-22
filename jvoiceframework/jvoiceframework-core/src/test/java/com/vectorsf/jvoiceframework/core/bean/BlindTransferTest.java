package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BlindTransferTest {

	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String DEST = "666777888";
	static String TRANSFERAUDIO = "idleMusicDefault";
	
	static String EVENT1 = "answered";
	static String EVENT2 = "noanswered";
	static String EVENT3 = "disconnected";
	
	static String PROP_KEY1 = "timeout";
	static String PROP_VALUE1 = "10s";
	static String PROP_KEY2 = "bargein";
	static String PROP_VALUE2 = "true";
	
	static String CUSTOM_EVENT1 = "customEvent1";
	static String CUSTOM_EVENT2 = "customEvent2";
	
	static List<String> events;
	static Map<String, String> properties;
	static List<String> customEvents;
	
	static {
		events = new ArrayList<String>();
		events.add(EVENT1);
		events.add(EVENT2);
		events.add(EVENT3);
		
		properties = new HashMap<String, String>();
		properties.put(PROP_KEY1, PROP_VALUE1);
		properties.put(PROP_KEY2, PROP_VALUE2);
		
		customEvents = new ArrayList<String>();
		customEvents.add(CUSTOM_EVENT1);
		customEvents.add(CUSTOM_EVENT2);
	}
		
	@Test
	public void testSetAfterInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		BlindTransfer blindTransfer = (BlindTransfer)context.getBean(BlindTransfer.class);
		
		//When
		blindTransfer.setDest(DEST);
		blindTransfer.setTransferaudio(TRANSFERAUDIO);
		
		//Then
		//Verifies that transfer attributes transferaudio and dest have been set properly.
		assertEquals("dest value is not correct.",blindTransfer.getDest(), DEST);
		assertEquals("transferaudio value is not correct.",blindTransfer.getTransferaudio(), TRANSFERAUDIO);
		
		//Finally
		context.close();		
	}

	@Test
	public void testPropertiesInjection(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		BlindTransfer blindTransfer = (BlindTransfer)context.getBean(BlindTransfer.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", blindTransfer.getProperties());
		
		//Verify getter and setter for properties
		blindTransfer.setProperties(properties);
		assertEquals("Checking BlindTransfer properties getter and setter", 2, blindTransfer.getProperties().size());
		assertEquals("Checking BlindTransfer properties getter and setter", PROP_VALUE1, blindTransfer.getProperties().get(PROP_KEY1));
		assertEquals("Checking BlindTransfer properties getter and setter", PROP_VALUE2, blindTransfer.getProperties().get(PROP_KEY2));

		//Finally
		context.close();

	}

	@Test
	public void testCustomEvents(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		BlindTransfer blindTransfer = (BlindTransfer)context.getBean(BlindTransfer.class);
		
		//Then
		//Verifies that custom events list has been initialized so it is not null
		assertNotNull("Checking BlindTransfer custom events is not null.", blindTransfer.getCustomEvents());
		
		//Verify getter and setter for custom events
		blindTransfer.setCustomEvents(customEvents);
		assertEquals("Checking BlindTransfer custom event getter and setter", 2, blindTransfer.getCustomEvents().size());
		assertEquals("Checking BlindTransfer custom event getter and setter", CUSTOM_EVENT1, blindTransfer.getCustomEvents().get(0));
		assertEquals("Checking BlindTransfer custom event getter and setter", CUSTOM_EVENT2, blindTransfer.getCustomEvents().get(1));
		
		//Finally
		context.close();

	}
}
