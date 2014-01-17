package com.vectorsf.jvoiceframework.core.service.record.vxi;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vectorsf.jvoiceframework.core.bean.User;
import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;
import com.vectorsf.jvoiceframework.core.service.record.RecordutteranceHandler;

/**
 * Implementation for VXI platform of RecordutteranceHandler interface.
 * 
 * @author idafereyes
 *
 */
@Controller
public class VXIRecordutteranceHandler implements RecordutteranceHandler {

    private static final String UTTERANCES_FOLDER = "utterances/";
    private static final String SLASH = "/";
    private static final String UTTERANCE_FORMAT_EXT = ".wav";


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
	 * Saves the utterance recorded by the IVR platform into the file system and
	 * writes an XML document to the http response.
	 * 
	 * @param utteranceTemprecording Utterance recorded by the IVR platform.
	 * @param inputName Input's name where the utterance was recorded.
	 * @param returnCode Event which took place at the input when the utterance was recorded. Possible values are: MATCH, NOMATCH and NOINPUT.
	 * @return an XML document because is invoked from a VXML data element. 
	 * This will be written to the http response automatically because the method is annotated as ResponseBody.
	 */
	@RequestMapping(value = "/saveUtteranceVXI", method = RequestMethod.POST)
	@ResponseBody
	public String saveUtterance(MultipartFile utteranceTemprecording, String inputName, String returnCode) {

		//Creates directory where the utterance will be saved 
		//(filePath/utterances/inputName/returnCode/jVoiceCallId)
		File dir = new File(filePath + UTTERANCES_FOLDER + inputName + SLASH + returnCode + SLASH + user.getJVoiceCallId()  + SLASH);		
		dir.mkdirs();
		
		//Timestamp creation
		Date date = new Date();
		date.getTime();		
		Timestamp tms = new Timestamp(date.getTime());		
		String formattedTms = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(tms);
		
		//Creates file "utterance-timestamp" inside the directory.
		File file = new File(dir, "utterance-" + formattedTms + UTTERANCE_FORMAT_EXT);
		
	   	try {
    		//Transfers the file to the given destination.
	   		utteranceTemprecording.transferTo(file);
	   		logger.debug(RecordutteranceHandlerMessages.UTTERANCE_SAVED, file);
		} catch (IllegalStateException e) {
			logger.error(RecordutteranceHandlerMessages.ERROR_SAVE_UTTERANCE, e);
		} catch (IOException e) {
			logger.error(RecordutteranceHandlerMessages.ERROR_SAVE_UTTERANCE, e);
		}    	
	   	
	   	//Must return an XML because is invoked from a VXML data element.
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><result/>";
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
