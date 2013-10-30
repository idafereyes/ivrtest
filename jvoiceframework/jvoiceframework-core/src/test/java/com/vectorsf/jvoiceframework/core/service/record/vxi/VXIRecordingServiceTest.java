package com.vectorsf.jvoiceframework.core.service.record.vxi;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockMultipartFile;

public class VXIRecordingServiceTest {
	
	static final String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core";
	static final String FILE_NAME = "recordingTest.wav";
	
    @Test
    @Ignore
	public void testSaveRecording(){
    	
		//Given
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
						
		VXIRecordingService recordingService = (VXIRecordingService) context.getBean(VXIRecordingService.class);

		MockMultipartFile recording = new MockMultipartFile("recording", new byte[1]);
		
		//When
    	recordingService.saveRecording(recording, FILE_NAME);
    	
    	//Then
    	File file = new File(recordingService.getFilePath() + FILE_NAME);
    	assertTrue(file.exists());
    	
    	//Finally
    	context.close();
    	if (file.exists()){
        	file.delete();   		
    	}
    }
    	
}
