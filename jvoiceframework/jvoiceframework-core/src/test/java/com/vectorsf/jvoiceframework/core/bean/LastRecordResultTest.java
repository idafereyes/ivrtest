package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class LastRecordResultTest {
	
	private static final String PARAM_DURATION = "10s";
	private static final String PARAM_FILENAME = "C:\\DATOS\\tmp";
	private static final String PARAM_SIZE = "1024";
	private static final String PARAM_TERMCHAR = "#";
	
	@Test
	public void testLastRecordResult(){
		
		LastRecordResult lastRecordResult = new LastRecordResult();
		
		lastRecordResult.setDuration(PARAM_DURATION);
		assertEquals("Checking lastRecordResult duration", PARAM_DURATION, lastRecordResult.getDuration());
		
		lastRecordResult.setFileName(PARAM_FILENAME);
		assertEquals("Checking lastRecordResult file name", PARAM_FILENAME, lastRecordResult.getFileName());
		
		lastRecordResult.setMaxtime(true);
		assertTrue("Checking lastRecordResult maxtime", lastRecordResult.isMaxtime());

		lastRecordResult.setSize(PARAM_SIZE);
		assertEquals("Checking lastRecordResult size", PARAM_SIZE, lastRecordResult.getSize());
		
		MultipartFile temprecording = mock(MultipartFile.class);
		lastRecordResult.setTemprecording(temprecording);
		assertEquals("Checking lastRecordResult temprecording", temprecording, lastRecordResult.getTemprecording());

		lastRecordResult.setTermchar(PARAM_TERMCHAR);
		assertEquals("Checking lastRecordResult termchar", PARAM_TERMCHAR, lastRecordResult.getTermchar());
	}
}
