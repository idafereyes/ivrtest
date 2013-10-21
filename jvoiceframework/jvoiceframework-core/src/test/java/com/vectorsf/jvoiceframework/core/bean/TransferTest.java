package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransferTest {
	
	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String TRANSFERAUDIO = "idleMusicDefault";
	static String DEST = "666777888";
	static String TIMEOUT = "15s";
	static String MAXTIME = "35s";
	
	@Test
	public void testSetAfterInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		Transfer transfer = (Transfer)context.getBean("transfer");
		
		//When
		transfer.setDest(DEST);
		transfer.setTransferaudio(TRANSFERAUDIO);
		
		//Then
		//Verifies that transfer attributes transferaudio and dest have been set properly.
		assertEquals("dest value is not correct.",transfer.getDest(), DEST);
		assertEquals("transferaudio value is not correct.",transfer.getTransferaudio(), TRANSFERAUDIO);
		
		//Finally
		context.close();		
	}

	@Test
	public void testEventsListInitialization(){
		
		//Given
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		
		//When
		Transfer transfer = (Transfer)context.getBean("transfer");
		
		//Then
		//Verifies that eventsList has been initialized so it is not null
		assertNotNull("eventsList is null.", transfer.getEvents());
		
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
		Transfer transfer = (Transfer)context.getBean("transfer");
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", transfer.getProperties());
		
		//Finally
		context.close();

	}

}
