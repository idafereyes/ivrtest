package com.vectorsf.jvoiceframework.core.service.record;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for the controller that handles all actions related with recordutterance.
 * 
 * @author idafereyes
 */
public interface RecordutteranceHandler {
	

	/**
	 * Saves the utterance recorded by the IVR platform into the file system.
	 * 
	 * @param utteranceTemprecording Utterance recorded by the IVR platform.
	 * @param inputName Input's name where the utterance was recorded.
	 * @param returnCode Event which took place at the input when the utterance was recorded. Possible values are: MATCH, NOMATCH and NOINPUT.
	 * @return 
	 */
	String saveUtterance(MultipartFile utterance, String inputName, String returnCode);

}
