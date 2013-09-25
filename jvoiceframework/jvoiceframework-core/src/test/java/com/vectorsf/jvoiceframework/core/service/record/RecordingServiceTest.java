package com.vectorsf.jvoiceframework.core.service.record;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.vectorsf.jvoiceframework.core.bean.AppConfigDefaults;
import com.vectorsf.jvoiceframework.core.service.locution.LocutionProviderException;
import com.vectorsf.jvoiceframework.core.service.locution.TestLocution;

public class RecordingServiceTest {
	
	static final String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core";
	static final String FILE_NAME = "recordingTest.wav";
	static final String WRONG_FILE_PATH = "D:\\\notExistingPath";
	
    @Test
	public void testSaveRecording(){
    	
		//Given
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();
						
		RecordingService recordingService = (RecordingService) context.getBean(RecordingService.class);

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
