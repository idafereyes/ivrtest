package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.admin.AppConfig;

/**
 * This bean class contains the default values for the main parameters in the 
 * application.
 *
 */
@Component("appConfigDefaults")
public class AppConfigDefaults implements Serializable {

	private static final long serialVersionUID = -9142571578698682638L;
	
	static final String FALSE = "false";
	static final String TRUE = "true";
	
	@Autowired
	private AppConfig appConfig;
	
	/** Default locale for the user **/
	private Locale userLocale;
	
	/** Audio items bargein default value. **/
	private boolean bargein;

	/** Default value for flush prompt. When true, output must write all the data in the flow processor.*/
	private boolean flush;
	
	/** If true, an output can catch a hangup event. Used to know when user hangups the call.*/
	private boolean catchHangup;

	/** Default value for transfer connection timeout */
	private String transferConnectiontimeout;

	/** Default value for bridge transfer timeout. */
	private String transferMaxtime;
	
	/**Default value for record beep. 
	 * If true, a beep must sound after the prompts
	 * asking for a record and before the record itself begins. */
	private boolean recordBeep;

	/** Default value for record dtmf term. If true, it allows the user to finish the recording pressing a key. */
	private boolean recordDtmfterm;

	/** Default value for the record final silence. */
	private String recordFinalsilence;

	/** Default record max time default. This time specifies the max time a recording can have.*/
	private String recordMaxtime;

	/** Default recording file name. */
	private String recordFileName;

	/** Default recording file path where the audios will be save. */
	private String recordFilePath;
	
	/** If true, the recording will be kept in order to be player again to the user.*/
	private boolean recordKeep;

	/** Default value for input bargein */
	private boolean inputBargein;
	
	/** Default input total max attempts (No Input + No Match attempts) */
	private int inputMaxAttempts;
	
	/** Default input no match attempts. */
	private int inputNoMatchAttempts;
	
	/** Default input no input attempts.*/
	private int inputNoInputAttempts;
	
	/** Prefix or path location of audio items. Platform dependent. **/
	@Value("resources/audios/")
	private String audiosLocationPrefix;
	
	/** Suffix of audio items names. Platform dependent. **/ 
	private String audiosFormatSuffix;
	
	/**
	 * Default input timeout.
	 * If user does not say anything during this timeout, the platform will 
	 * throw a No Input event.
	 */
	private String timeout;
	
	/**
	 * Default value for input interdigit timeout.
	 * It is the max time that the user has to press a key after he pressed 
	 * the last. If the user does not press the next key before this time
	 * the platform will finish the recognition. 
	 */
	private String interdigittimeout;
	
	/** Minimum default confidence. If a recognition confidence is less than 
	 *  this value, the result is treat as a No Match by the recognizer. 
	 */
	private String confidence;
	
    /**
     * Default value for sensitivity property.
     * Indicates how sensitive the interpreter is to noise in a recognition. 
     * A value of 1.0 means that it is highly sensitive to quiet input. A value of 0.0 means it is least sensitive to noise.
     */
    private String sensitivity;

    /**
     * Default value for speedvsaccuracy property.
     * Specifies the desired balance between speed vs. accuracy. 
     * A value of 0.0 means fastest recognition. A value of 1.0 means best accuracy.
     */
    private String speedvsaccuracy;

    /**
     * Default value for maxspeechtimeout property.
	 * The maximum duration of user speech.
     */
    private String maxspeechtimeout;

    /**
    * Default value for completetimeout property.
	* The length of silence required following user speech before the speech recognizer throws a match event.
	*/
    private String completetimeout;

    /**
    * Default value for incompletetimeout property.
	* The length of silence required following user speech before the speech recognizer throws a nomatch event.
	*/
    private String incompletetimeout;

    /**
    * Default value for recordutterance Input property.
	* Enables recording during recognition when set to true.
	*/
    private boolean recordutterance;

	/** Standard content type for grammar **/
	private String grammarType;
	
	/** Default value for the grammar path **/
	private String grammarPath;
	
	/** Default grammar file extension **/
	private String grammarsFileExtension;
	
	
	@PostConstruct
	private void initParams() {
		initLocale();
		if (appConfig.getValue("bargein") != null) {
			setBargein(Boolean.valueOf(appConfig.getValue("bargein")));
		}
		if (appConfig.getValue("flush") != null) {
			setFlush(Boolean.valueOf(appConfig.getValue("flush")));
		}
		if (appConfig.getValue("catchHangup") != null) {
			setCatchHangup(Boolean.valueOf(appConfig.getValue("catchHangup")));
		}
		if (appConfig.getValue("transferConnectiontimeout") != null) {
			setTransferConnectiontimeout(appConfig.getValue("transferConnectiontimeout"));
		}
		if (appConfig.getValue("transferMaxtime") != null) {
			setTransferMaxtime(appConfig.getValue("transferMaxtime"));
		}
		if (appConfig.getValue("recordBeep") != null) {
			setRecordBeep(Boolean.valueOf(appConfig.getValue("recordBeep")));
		}
		if (appConfig.getValue("recordDtmfterm") != null) {
			setRecordDtmfterm(Boolean.valueOf(appConfig.getValue("recordDtmfterm")));
		}
		if (appConfig.getValue("recordFinalsilence") != null) {
			setRecordFinalsilence(appConfig.getValue("recordFinalsilence"));
		}
		if (appConfig.getValue("recordMaxtime") != null) {
			setRecordMaxtime(appConfig.getValue("recordMaxtime"));
		}
		if (appConfig.getValue("recordFileName") != null) {
			setRecordFileName(appConfig.getValue("recordFileName"));
		}
		if (appConfig.getValue("recordFilePath") != null) {
			setRecordFilePath(appConfig.getValue("recordFilePath"));
		}
		if (appConfig.getValue("recordKeep") != null) {
			setRecordKeep(Boolean.valueOf(appConfig.getValue("recordKeep")));
		}
		if (appConfig.getValue("inputBargein") != null) {
			setInputBargein(Boolean.valueOf(appConfig.getValue("inputBargein")));
		}
		if (appConfig.getValue("inputMaxAttempts") != null) {
			setInputMaxAttempts(Integer.parseInt(appConfig.getValue("inputMaxAttempts")));
		}
		if (appConfig.getValue("inputNoMatchAttempts") != null) {
			setInputNoMatchAttempts(Integer.parseInt(appConfig.getValue("inputNoMatchAttempts")));
		}
		if (appConfig.getValue("inputNoInputAttempts") != null) {
			setInputNoInputAttempts(Integer.parseInt(appConfig.getValue("inputNoInputAttempts")));
		}
		if (appConfig.getValue("audiosLocationPrefix") != null) {
			setAudiosLocationPrefix(appConfig.getValue("audiosLocationPrefix"));
		}
		if (appConfig.getValue("audiosFormatSuffix") != null) {
			setAudiosFormatSuffix(appConfig.getValue("audiosFormatSuffix"));
		}
		if (appConfig.getValue("timeout") != null) {
			setTimeout(appConfig.getValue("timeout"));
		}
		if (appConfig.getValue("interdigittimeout") != null) {
			setInterdigittimeout(appConfig.getValue("interdigittimeout"));
		}
		if (appConfig.getValue("confidence") != null) {
			setConfidence(appConfig.getValue("confidence"));
		}
		if (appConfig.getValue("sensitivity") != null) {
			setSensitivity(appConfig.getValue("sensitivity"));
		}
		if (appConfig.getValue("speedvsaccuracy") != null) {
			setSpeedvsaccuracy(appConfig.getValue("speedvsaccuracy"));
		}
		if (appConfig.getValue("maxspeechtimeout") != null) {
			setMaxspeechtimeout(appConfig.getValue("maxspeechtimeout"));
		}
		if (appConfig.getValue("completetimeout") != null) {
			setCompletetimeout(appConfig.getValue("completetimeout"));
		}
		if (appConfig.getValue("incompletetimeout") != null) {
			setIncompletetimeout(appConfig.getValue("incompletetimeout"));
		}
		if (appConfig.getValue("recordutterance") != null) {
			setRecordutterance(Boolean.valueOf(appConfig.getValue("recordutterance")));
		}
		if (appConfig.getValue("grammarType") != null) {
			setGrammarType(appConfig.getValue("grammarType"));
		}
		if (appConfig.getValue("grammarPath") != null) {
			setGrammarPath(appConfig.getValue("grammarPath"));
		}
		if (appConfig.getValue("grammarsFileExtension") != null) {
			setGrammarsFileExtension(appConfig.getValue("grammarsFileExtension"));
		}
	}
	
	/**
	 * 
	 */
	private void initLocale() {
		String language = appConfig.getValue("language");
		String region = appConfig.getValue("region");
		String variant = appConfig.getValue("variant");
		if (language != null && region != null && variant != null) {
			userLocale = new Locale(language, region, variant);
		} else if (language != null && region != null) {
			userLocale = new Locale(language, region);
		} else if (language != null) {
			userLocale = new Locale(language);
		} else {
			userLocale = Locale.getDefault();
		}
	}
	
	public Locale getUserLocale() {
		return userLocale;
	}
	
	public boolean isBargein() {
		return bargein;
	}

	private void setBargein(boolean bargein) {
		this.bargein = bargein;
	}

	public boolean isFlush() {
		return flush;
	}

	private void setFlush(boolean flush) {
		this.flush = flush;
	}

	public boolean isCatchHangup() {
		return catchHangup;
	}

	private void setCatchHangup(boolean catchHangup) {
		this.catchHangup = catchHangup;
	}

	public String getTransferConnectiontimeout() {
		return transferConnectiontimeout;
	}

	private void setTransferConnectiontimeout(String transferConnectiontimeout) {
		this.transferConnectiontimeout = transferConnectiontimeout;
	}

	public String getTransferMaxtime() {
		return transferMaxtime;
	}

	private void setTransferMaxtime(String transferMaxtime) {
		this.transferMaxtime = transferMaxtime;
	}

	public boolean isRecordBeep() {
		return recordBeep;
	}

	private void setRecordBeep(boolean recordBeep) {
		this.recordBeep = recordBeep;
	}

	public boolean isRecordDtmfterm() {
		return recordDtmfterm;
	}

	private void setRecordDtmfterm(boolean recordDtmfterm) {
		this.recordDtmfterm = recordDtmfterm;
	}

	public String getRecordFinalsilence() {
		return recordFinalsilence;
	}

	private void setRecordFinalsilence(String recordFinalsilence) {
		this.recordFinalsilence = recordFinalsilence;
	}

	public String getRecordFileName() {
		return recordFileName;
	}

	public String getRecordMaxtime() {
		return recordMaxtime;
	}

	private void setRecordMaxtime(String recordMaxtime) {
		this.recordMaxtime = recordMaxtime;
	}

	private void setRecordFileName(String recordFileName) {
		this.recordFileName = recordFileName;
	}

	public String getRecordFilePath() {
		return recordFilePath;
	}

	private void setRecordFilePath(String recordFilePath) {
		this.recordFilePath = recordFilePath;
	}

	public boolean isRecordKeep() {
		return recordKeep;
	}

	private void setRecordKeep(boolean recordKeep) {
		this.recordKeep = recordKeep;
	}

	public boolean isInputBargein() {
		return inputBargein;
	}

	private void setInputBargein(boolean inputBargein) {
		this.inputBargein = inputBargein;
	}

	public int getInputMaxAttempts() {
		return inputMaxAttempts;
	}

	private void setInputMaxAttempts(int inputMaxAttempts) {
		this.inputMaxAttempts = inputMaxAttempts;
	}

	public int getInputNoMatchAttempts() {
		return inputNoMatchAttempts;
	}

	private void setInputNoMatchAttempts(int inputNoMatchAttempts) {
		this.inputNoMatchAttempts = inputNoMatchAttempts;
	}

	public int getInputNoInputAttempts() {
		return inputNoInputAttempts;
	}

	private void setInputNoInputAttempts(int inputNoInputAttempts) {
		this.inputNoInputAttempts = inputNoInputAttempts;
	}

	public String getAudiosLocationPrefix() {
		return audiosLocationPrefix;
	}

	private void setAudiosLocationPrefix(String audiosLocationPrefix) {
		this.audiosLocationPrefix = audiosLocationPrefix;
	}

	public String getAudiosFormatSuffix() {
		return audiosFormatSuffix;
	}

	private void setAudiosFormatSuffix(String audiosFormatSuffix) {
		this.audiosFormatSuffix = audiosFormatSuffix;
	}

	public String getTimeout() {
		return timeout;
	}

	private void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getInterdigittimeout() {
		return interdigittimeout;
	}

	private void setInterdigittimeout(String interdigittimeout) {
		this.interdigittimeout = interdigittimeout;
	}

	public String getConfidence() {
		return confidence;
	}

	private void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getSensitivity() {
		return sensitivity;
	}

	private void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}

	public String getSpeedvsaccuracy() {
		return speedvsaccuracy;
	}

	private void setSpeedvsaccuracy(String speedvsaccuracy) {
		this.speedvsaccuracy = speedvsaccuracy;
	}

	public String getMaxspeechtimeout() {
		return maxspeechtimeout;
	}

	private void setMaxspeechtimeout(String maxspeechtimeout) {
		this.maxspeechtimeout = maxspeechtimeout;
	}

	public String getCompletetimeout() {
		return completetimeout;
	}

	private void setCompletetimeout(String completetimeout) {
		this.completetimeout = completetimeout;
	}

	public String getIncompletetimeout() {
		return incompletetimeout;
	}

	private void setIncompletetimeout(String incompletetimeout) {
		this.incompletetimeout = incompletetimeout;
	}

	public boolean isRecordutterance() {
		return recordutterance;
	}

	private void setRecordutterance(boolean recordutterance) {
		this.recordutterance = recordutterance;
	}

	public String getGrammarType() {
		return grammarType;
	}

	private void setGrammarType(String grammarType) {
		this.grammarType = grammarType;
	}

	public String getGrammarPath() {
		return grammarPath;
	}

	private void setGrammarPath(String grammarPath) {
		this.grammarPath = grammarPath;
	}

	public String getGrammarsFileExtension() {
		return grammarsFileExtension;
	}

	private void setGrammarsFileExtension(String grammarsFileExtension) {
		this.grammarsFileExtension = grammarsFileExtension;
	}

}
