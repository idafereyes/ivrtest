package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RecordTest {
	
	static String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static boolean BEEP = true;
	static boolean DTMFTERM = true;
	static String MAXTIME = "20s";
	static String FINALSILENCE = "2s";
	static String FILE_NAME = "recordingTestName";
	static String FILE_PATH = "http://recordingTestPath/";
	static boolean KEEP = true;
	
	@Test
	public void testDefaultValuesInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		//TODO Puede ser un mock?
		AppConfigDefaults appConfigDefaults = (AppConfigDefaults) context.getBean(AppConfigDefaults.class);

		//When
		Record record = context.getBean(Record.class);
		
		//Then
		//Verifies that record attributes beep, dtmfterm, maxtime, finalsilence, fileName, filePath and keep
		//have taken appConfigDefault attributes value.
		assertEquals("beep value is not correct.", record.isBeep(), appConfigDefaults.isRecordBeep());
		assertEquals("dtmfterm value is not correct.", record.isDtmfterm(), appConfigDefaults.isRecordDtmfterm());
		assertEquals("maxtime value is not correct.",record.getMaxtime(), appConfigDefaults.getRecordMaxtime());
		assertEquals("finalsilence value is not correct.", record.getFinalsilence(), appConfigDefaults.getRecordFinalsilence());
		assertEquals("fileName value is not correct.", record.getFileName(), appConfigDefaults.getRecordFileName());
		assertEquals("filePath value is not correct.", record.getFilePath(), appConfigDefaults.getRecordFilePath());
		assertEquals("keep value is not correct.", record.isKeep(), appConfigDefaults.isRecordKeep());
		
		//Finally
		context.close();

	}
	
	@Test
	public void testSetAfterInjection(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
		Record record = context.getBean(Record.class);

		//When
		record.setBeep(BEEP);
		record.setDtmfterm(DTMFTERM);
		record.setMaxtime(MAXTIME);
		record.setFinalsilence(FINALSILENCE);
		record.setFileName(FILE_NAME);
		record.setFilePath(FILE_PATH);
		record.setKeep(KEEP);
		
		//Then
		//Verifies that  record attributes beep, dtmfterm, maxtime, finalsilence, fileName, filePath and keep
		//have been set properly and has no more the injected value.
		assertEquals("beep value is not correct.", record.isBeep(), BEEP);
		assertEquals("dtmfterm value is not correct.", record.isDtmfterm(), DTMFTERM);
		assertEquals("maxtime value is not correct.",record.getMaxtime(), MAXTIME);
		assertEquals("finalsilence value is not correct.", record.getFinalsilence(), FINALSILENCE);
		assertEquals("fileName value is not correct.", record.getFileName(), FILE_NAME);
		assertEquals("filePath value is not correct.", record.getFilePath(), FILE_PATH);
		assertEquals("keep value is not correct.", record.isKeep(), KEEP);
	
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
		Record record = (Record)context.getBean(Record.class);
		
		//Then
		//Verifies that eventsList has been initialized so it is not null
		assertNotNull("eventsList is null.", record.getEventsList());
		
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
		Record record = (Record)context.getBean(Record.class);
		
		//Then
		//Verifies that properties map has been initialized so it is not null
		assertNotNull("properties map is null.", record.getProperties());
		
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
		Record record = (Record)context.getBean(Record.class);
		
		//Then
		//Verifies that audioItemList has been initialized so it is not null
		assertNotNull("audioItemsList is null.", record.getAudioItemsList());
		
		//Finally
		context.close();

	}

}
