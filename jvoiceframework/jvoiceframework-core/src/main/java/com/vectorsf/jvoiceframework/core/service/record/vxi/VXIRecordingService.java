package com.vectorsf.jvoiceframework.core.service.record.vxi;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.vectorsf.jvoiceframework.core.bean.User;
import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;
import com.vectorsf.jvoiceframework.core.service.record.RecordingService;

/**
 * Implementation for VXI platform of RecordingService interface.
 * 
 * @author idafereyes
 *
 */
public class VXIRecordingService implements RecordingService {
	
	@Log
	private Logger logger;
	
   /**
    * The path where the recording must be saved.
    * Take its value from the application configuration defaults.
    */
	@Value("#{appConfigDefaults.recordFilePath}")
	private String filePath;
    
   /**
    * User architectural bean.
    * Contains jVoiceCalId needed to complete the path where the recording must be saved.
    */
    @Autowired
    private User user;
	
    /**
     * Saves the recording on the path specified by filePath and jVoiceCallId with fileName as name.
     */
    public void saveRecording(MultipartFile recording, String fileName){
    	
    	//Creates a File with the directory where the recording must be saved.
    	File dir = new File(filePath + user.getJVoiceCallId() + "/");
    	
    	//Creates the directory structure if didn't exist
    	if (!dir.exists()){
    		dir.mkdirs();
    	}
    	
    	//Creates a File with the complete path and name for the recording saving.
    	File file = new File (filePath + user.getJVoiceCallId() + "/" + fileName);

    	try {
    		//Transfers the file to the given destination.
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
