package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsultationTransferTest {

	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String DEST = "666777888";
	static String TRANSFERAUDIO = "idleMusicDefault";
	static String TIMEOUT = "15s";

	private static ClassPathXmlApplicationContext applicationContext = null;
	
	@BeforeClass
	public static void startContext() {
		applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/bean/test-config-context.xml");
		applicationContext.refresh();
	}
	
	@Test
	public void testValuesInjection(){
		
		//TODO Puede ser un mock?
		AppConfigDefaults appConfigDefaults = (AppConfigDefaults) applicationContext.getBean(AppConfigDefaults.class);

		//When
		ConsultationTransfer consultationTx = applicationContext.getBean(ConsultationTransfer.class);
		
		//Then
		//Verifies that timeout transfer attribute has taken appConfigDefault attribute value.
		assertEquals("timeout value is not correct.",consultationTx.getTimeout(), appConfigDefaults.getTransferConnectiontimeout());
	}
	
	@Test
	public void testSetValuesAfterInjection(){
		
		ConsultationTransfer consultationTx = (ConsultationTransfer)applicationContext.getBean(ConsultationTransfer.class);
		
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
	}
	
	@Test
	public void testPropertiesInjection(){

		//When
		ConsultationTransfer consultationTx = (ConsultationTransfer)applicationContext.getBean(ConsultationTransfer.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", consultationTx.getProperties());
	}
	
	@AfterClass 
	public static void closeContext() {
		applicationContext.close();
	}

}
