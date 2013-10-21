package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BlindTransferTest {

	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String DEST = "666777888";
	static String TRANSFERAUDIO = "idleMusicDefault";
		
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
	public void testEventsInjection(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		BlindTransfer blindTransfer = (BlindTransfer)context.getBean(BlindTransfer.class);
		
		//Then
		//Verifies that events list has been initialized so it is not null
		assertNotNull("events is null.", blindTransfer.getEvents());
		
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
		
		//Finally
		context.close();

	}


}
