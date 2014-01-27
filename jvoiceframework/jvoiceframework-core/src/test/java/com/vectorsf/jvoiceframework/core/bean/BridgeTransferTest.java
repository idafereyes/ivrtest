package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BridgeTransferTest {

	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static String DEST = "666777888";
	static String TRANSFERAUDIO = "idleMusicDefault";
	static String TIMEOUT = "15s";
	static String MAXTIME = "35s";
	
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
		BridgeTransfer bridgeTx = applicationContext.getBean(BridgeTransfer.class);
		
		//Then
		//Verifies that transfer attributes maxtime and timeout have taken appConfigDefault attributes value.
		assertEquals("timeout value is not correct.",bridgeTx.getTimeout(), appConfigDefaults.getTransferConnectiontimeout());
		assertEquals("maxtime value is not correct.",bridgeTx.getMaxtime(), appConfigDefaults.getTransferMaxtime());
	}
	
	@Test
	public void testSetValuesAfterInjection(){

		BridgeTransfer bridgeTx = (BridgeTransfer)applicationContext.getBean(BridgeTransfer.class);
		
		//When
		bridgeTx.setTimeout(TIMEOUT);
		bridgeTx.setMaxtime(MAXTIME);
		bridgeTx.setDest(DEST);
		bridgeTx.setTransferaudio(TRANSFERAUDIO);
		
		//Then
		//Verifies that transfer attributes maxtime and timeout have been set properly and has no more the injected value.
		assertEquals("timeout value is not correct.",bridgeTx.getTimeout(), TIMEOUT);
		assertEquals("maxtime value is not correct.",bridgeTx.getMaxtime(), MAXTIME);
		//Verifies that transfer attributes transferaudio and dest have been set properly.
		assertEquals("dest value is not correct.",bridgeTx.getDest(), DEST);
		assertEquals("transferaudio value is not correct.",bridgeTx.getTransferaudio(), TRANSFERAUDIO);		
	}
	
	@Test
	public void testPropertiesInjection(){	
		//When
		BridgeTransfer bridgeTx = (BridgeTransfer)applicationContext.getBean(BridgeTransfer.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", bridgeTx.getProperties());

	}
	
	@AfterClass 
	public static void closeContext() {
		applicationContext.close();
	}

}
