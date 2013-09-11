package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransferTest {
	
	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String TRANSFERAUDIO = "idleMusicDefault";
	static String TIMEOUT = "15s";
	static String MAXTIME = "35s";

	@Test
	public void testDefaultValuesInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		//TODO Puede ser un mock?
		AppConfigDefaults appConfigDefaults = (AppConfigDefaults) context.getBean(AppConfigDefaults.class);

		//When
		Transfer transfer = context.getBean(Transfer.class);
		
		//Then
		//Verifies that transfer attributes transferaudio, maxtime and timeout have taken appConfigDefault attributes value.
		assertEquals("transferaudio value is not correct.",transfer.getTransferaudio(), appConfigDefaults.getTransferaudio());
		assertEquals("timeout value is not correct.",transfer.getTimeout(), appConfigDefaults.getTransferConnectiontimeout());
		assertEquals("maxtime value is not correct.",transfer.getMaxtime(), appConfigDefaults.getMaxtime());
		
		//Finally
		context.close();

	}
	
	@Test
	public void testSetAfterInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		Transfer transfer = (Transfer)context.getBean(Transfer.class);
		
		//When
		transfer.setTransferaudio(TRANSFERAUDIO);
		transfer.setTimeout(TIMEOUT);
		transfer.setMaxtime(MAXTIME);
		
		//Then
		//Verifies that transfer attributes transferaudio, maxtime and timeout have been set properly and has no more the injected value.
		assertEquals("transferaudio value is not correct.",transfer.getTransferaudio(), TRANSFERAUDIO);
		assertEquals("timeout value is not correct.",transfer.getTimeout(), TIMEOUT);
		assertEquals("maxtime value is not correct.",transfer.getMaxtime(),MAXTIME);
		
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
		Transfer transfer = (Transfer)context.getBean(Transfer.class);
		
		//Then
		//Verifies that eventsList has been initialized so it is not null
		assertNotNull("eventsList is null.", transfer.getEventsList());
		
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
		Transfer transfer = (Transfer)context.getBean(Transfer.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", transfer.getProperties());
		
		//Finally
		context.close();

	}
	

}
