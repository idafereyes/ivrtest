package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class LastRecordResultTest {
		
	@Test
	public void testLastRecordResult(){
		
		LastRecordResult lastRecordResult = mock(LastRecordResult.class);
		when(lastRecordResult.getDuration()).thenReturn("10s");
		when(lastRecordResult.getEvent()).thenReturn("recorded");
		when(lastRecordResult.getFileName()).thenReturn("C:\\DATOS\\tmp");
		when(lastRecordResult.getSize()).thenReturn("1024");
		when(lastRecordResult.getTermchar()).thenReturn("#");
		when(lastRecordResult.isMaxtime()).thenReturn(true);
		
		MultipartFile multipartFile = mock(MultipartFile.class);
		when(lastRecordResult.getTemprecording()).thenReturn(multipartFile);
		
		assertEquals("Checking lastRecordResult duration", "10s", lastRecordResult.getDuration());
		assertEquals("Checking lastRecordResult event", "recorded", lastRecordResult.getEvent());
		assertEquals("Checking lastRecordResult file name", "C:\\DATOS\\tmp", lastRecordResult.getFileName());
		assertEquals("Checking lastRecordResult size", "1024", lastRecordResult.getSize());
		assertEquals("Checking lastRecordResult termchar", "#", lastRecordResult.getTermchar());
		assertEquals("Checking lastRecordResult maxtime", true, lastRecordResult.isMaxtime());
		
		assertEquals("Checking lastRecordResult temprecording", multipartFile, lastRecordResult.getTemprecording());
	}
}
