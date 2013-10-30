package com.vectorsf.jvoiceframework.core.service.record;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for the service that implements all actions related to the process of recording an input from the user.
 * 
 * @author idafereyes
 */
public interface RecordingService {
		
	/**
	 * Stores the recorded audio in a file in the filesystem given its name.
	 * 
	 * @param recording multipartFile recorded to the user and passed by the VXML interpreter.
	 * @param fileName The name that the file will have in the filesystem.  
	 */
    void saveRecording(MultipartFile recording, String fileName);

}