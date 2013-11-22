package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConsultationTransferTest {

	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String DEST = "666777888";
	static String TRANSFERAUDIO = "idleMusicDefault";
	static String TIMEOUT = "15s";

	@Test
	public void testValuesInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		//TODO Puede ser un mock?
		AppConfigDefaults appConfigDefaults = (AppConfigDefaults) context.getBean(AppConfigDefaults.class);

		//When
		ConsultationTransfer consultationTx = context.getBean(ConsultationTransfer.class);
		
		//Then
		//Verifies that timeout transfer attribute has taken appConfigDefault attribute value.
		assertEquals("timeout value is not correct.",consultationTx.getTimeout(), appConfigDefaults.getTransferConnectiontimeout());
		
		//Finally
		context.close();

	}
	
	@Test
	public void testSetValuesAfterInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		ConsultationTransfer consultationTx = (ConsultationTransfer)context.getBean(ConsultationTransfer.class);
		
		//When
		consultationTx.setTimeout(TIMEOUT);
		consultationTx.setDest(DEST);
		consultationTx.setTransferaudio(TRANSFERAUDIO);
		
		//Then
		//Verifies that timeout transfer attribute has been set properly and has no more the injected value.
		assertEquals("timeout value is not correct.",consultationTx.getTimeout(), TIMEOUT);
		//Verifies that transfer attributes transferaudio and dest have been set properly.
		assertEquals("dest value is not correct.",consultationTx.getDest(), DEST);
		assertEquals("transferaudio value is not correct.",consultationTx.getTransferaudio(), TRANSFERAUDIO);
		
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
		ConsultationTransfer consultationTx = (ConsultationTransfer)context.getBean(ConsultationTransfer.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", consultationTx.getProperties());
		
		//Finally
		context.close();

	}

}
