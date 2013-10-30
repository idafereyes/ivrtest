package com.vectorsf.jvoiceframework.core.service.record.vxi;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;
import com.vectorsf.jvoiceframework.core.service.record.RecordingService;

public class VXIRecordingService implements RecordingService {
	
	@Log
	private Logger logger;
	
    @Value("#{appConfigDefaults.recordFilePath}")
	private String filePath;
	
    public void saveRecording(MultipartFile recording, String fileName){
    	
		File file = new File(filePath + fileName);

    	try {
			recording.transferTo(file);
		} catch (IllegalStateException e) {
			logger.error(RecordingServiceMessages.ERROR_SAVE_RECORDING, e);
		} catch (IOException e) {
			logger.error(RecordingServiceMessages.ERROR_SAVE_RECORDING, e);
		}    	
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
