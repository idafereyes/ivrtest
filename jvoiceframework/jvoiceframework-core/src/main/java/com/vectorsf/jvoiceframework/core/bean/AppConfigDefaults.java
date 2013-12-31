package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
	
	//TODO En un primer momento le estamos asignando valores hardcodeados.
	//Está pendiente ver de qué manera toman valor estos atributos.
	
	/** Default locale for the user **/
	private Locale userLocale = new Locale("Es", "es");
	
	/** Audio items bargein default value. **/
	@Value(TRUE)
	private boolean bargein;
	
	public Locale getUserLocale() {
		return userLocale;
	}

	public void setUserLocale(Locale userLocale) {
		this.userLocale = userLocale;
	}

	/** Default value for flush prompt. When true, output must write all the 
	 * data in the flow processor.
	 */
	@Value(FALSE)
	private boolean flush;
	
	/** If true, an output can catch a hangup event. Used to know when user
	 *	hangups the call.
	 */
	@Value(FALSE)
	private boolean catchHangup;

	/**
	 * Default value for transfer connection timeout 
	 */
	@Value("10s")
	private String transferConnectiontimeout;

	/**
	 * Default value for bridge transfer timeout.
	 */
	@Value("20s")
	private String transferMaxtime;
	
	/**
	 * Default value for record beep. 
	 * If true, a beep must sound after the prompts
	 * asking for a record and before the record itself begins. 
	 */
	@Value(FALSE)
	private boolean recordBeep;

	/** Default value for record dtmf term.
	 * If true, it allows the user to finish the recording pressing a key.
	 */
	@Value(FALSE)
	private boolean recordDtmfterm;

	/**
	 * Default value for the record final silence.
	 * It specifies the timeout used to terminate the recording.
	 */
	@Value("5s")
	private String recordFinalsilence;

	/**
	 * Default record max time default.
	 * This time specifies the max time a recording can have.
	 */
	@Value("10s")
	private String recordMaxtime;

	/**
	 * Default recording file name.
	 */
	@Value("recordedAudio.wav")
	private String recordFileName;

	/**
	 * Default recording file path where the audios will be save.
	 */
	@Value("C:\\tmp\\recordedAudiosPath\\")
	private String recordFilePath;
	
	/**
	 * If true, the recording will be kept in order to be player again to the
	 * user.
	 */
	@Value(FALSE)
	private boolean recordKeep;

	/**
	 * Default value for input bargein
	 */
	@Value(TRUE)
	private boolean inputBargein;
	
	/**
	 * Default input total max attempts (No Input + No Match attempts)
	 */
	@Value("3")
	private int inputMaxAttempts;
	
	/**
	 * Default input no match attempts.
	 */
	@Value("2")
	private int inputNoMatchAttempts;
	
	/**
	 * Default input no input attempts.
	 */
	@Value("2")
	private int inputNoInputAttempts;
	
	/** Prefix or path location of audio items. Platform dependent. **/
	@Value("resources/audios/")
	private String audiosLocationPrefix;
	
	/** Suffix of audio items names. Platform dependent. **/ 
	@Value(".wav")
	private String audiosFormatSuffix;
	
	/**
	 * Default input timeout.
	 * If user does not say anything during this timeout, the platform will 
	 * throw a No Input event.
	 */
	@Value("4s")
	private String timeout;
	
	/**
	 * Default value for input interdigit timeout.
	 * It is the max time that the user has to press a key after he pressed 
	 * the last. If the user does not press the next key before this time
	 * the platform will finish the recognition. 
	 */
	@Value("3s")
	private String interdigittimeout;
	
	/** Minimum default confidence. If a recognition confidence is less than 
	 *  this value, the result is treat as a No Match by the recognizer. 
	 */
	@Value("0.5")
	private String confidence;
	
    /**
     * Default value for sensitivity property.
     * Indicates how sensitive the interpreter is to noise in a recognition. 
     * A value of 1.0 means that it is highly sensitive to quiet input. A value of 0.0 means it is least sensitive to noise.
     */
    @Value("0.5")
    private String sensitivity;

    /**
     * Default value for speedvsaccuracy property.
     * Specifies the desired balance between speed vs. accuracy. 
     * A value of 0.0 means fastest recognition. A value of 1.0 means best accuracy.
     */
    @Value("0.5")
    private String speedvsaccuracy;

    /**
     * Default value for maxspeechtimeout property.
	 * The maximum duration of user speech.
     */
    @Value("20s")
    private String maxspeechtimeout;

    /**
    * Default value for completetimeout property.
	* The length of silence required following user speech before the speech recognizer throws a match event.
	*/
    @Value("0.25s")
    private String completetimeout;

    /**
    * Default value for incompletetimeout property.
	* The length of silence required following user speech before the speech recognizer throws a nomatch event.
	*/
    @Value("2s")
    private String incompletetimeout;

	
	/** Standard content type for grammar **/
	@Value("application/srgs+xml")
	private String grammarType;
	
	/** Default value for the grammar path **/
	@Value("resources/grammars/")
	private String grammarPath;
	
	/** Default grammar file extension **/
	@Value(".grxml")
	private String grammarsFileExtension;
	
	public boolean isBargein() {
		return bargein;
	}

	public void setBargein(boolean bargein) {
		this.bargein = bargein;
	}

	public boolean isFlush() {
		return flush;
	}

	public void setFlush(boolean flush) {
		this.flush = flush;
	}

	public boolean isCatchHangup() {
		return catchHangup;
	}

	public void setCatchHangup(boolean catchHangup) {
		this.catchHangup = catchHangup;
	}

	public String getTransferConnectiontimeout() {
		return transferConnectiontimeout;
	}

	public void setTransferConnectiontimeout(String transferConnectiontimeout) {
		this.transferConnectiontimeout = transferConnectiontimeout;
	}

	public String getTransferMaxtime() {
		return transferMaxtime;
	}

	public void setTransferMaxtime(String transferMaxtime) {
		this.transferMaxtime = transferMaxtime;
	}

	public boolean isRecordBeep() {
		return recordBeep;
	}

	public void setRecordBeep(boolean recordBeep) {
		this.recordBeep = recordBeep;
	}

	public boolean isRecordDtmfterm() {
		return recordDtmfterm;
	}

	public void setRecordDtmfterm(boolean recordDtmfterm) {
		this.recordDtmfterm = recordDtmfterm;
	}

	public String getRecordFinalsilence() {
		return recordFinalsilence;
	}

	public void setRecordFinalsilence(String recordFinalsilence) {
		this.recordFinalsilence = recordFinalsilence;
	}

	public String getRecordFileName() {
		return recordFileName;
	}

	public String getRecordMaxtime() {
		return recordMaxtime;
	}

	public void setRecordMaxtime(String recordMaxtime) {
		this.recordMaxtime = recordMaxtime;
	}

	public void setRecordFileName(String recordFileName) {
		this.recordFileName = recordFileName;
	}

	public String getRecordFilePath() {
		return recordFilePath;
	}

	public void setRecordFilePath(String recordFilePath) {
		this.recordFilePath = recordFilePath;
	}

	public boolean isRecordKeep() {
		return recordKeep;
	}

	public void setRecordKeep(boolean recordKeep) {
		this.recordKeep = recordKeep;
	}

	public boolean isInputBargein() {
		return inputBargein;
	}

	public void setInputBargein(boolean inputBargein) {
		this.inputBargein = inputBargein;
	}

	public int getInputMaxAttempts() {
		return inputMaxAttempts;
	}

	public void setInputMaxAttempts(int inputMaxAttempts) {
		this.inputMaxAttempts = inputMaxAttempts;
	}

	public int getInputNoMatchAttempts() {
		return inputNoMatchAttempts;
	}

	public void setInputNoMatchAttempts(int inputNoMatchAttempts) {
		this.inputNoMatchAttempts = inputNoMatchAttempts;
	}

	public int getInputNoInputAttempts() {
		return inputNoInputAttempts;
	}

	public void setInputNoInputAttempts(int inputNoInputAttempts) {
		this.inputNoInputAttempts = inputNoInputAttempts;
	}

	public String getAudiosLocationPrefix() {
		return audiosLocationPrefix;
	}

	public void setAudiosLocationPrefix(String audiosLocationPrefix) {
		this.audiosLocationPrefix = audiosLocationPrefix;
	}

	public String getAudiosFormatSuffix() {
		return audiosFormatSuffix;
	}

	public void setAudiosFormatSuffix(String audiosFormatSuffix) {
		this.audiosFormatSuffix = audiosFormatSuffix;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getInterdigittimeout() {
		return interdigittimeout;
	}

	public void setInterdigittimeout(String interdigittimeout) {
		this.interdigittimeout = interdigittimeout;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getSensitivity() {
		return sensitivity;
	}

	public void setSensitivity(String sensitivity) {
		this.sensitivity = sensitivity;
	}

	public String getSpeedvsaccuracy() {
		return speedvsaccuracy;
	}

	public void setSpeedvsaccuracy(String speedvsaccuracy) {
		this.speedvsaccuracy = speedvsaccuracy;
	}

	public String getMaxspeechtimeout() {
		return maxspeechtimeout;
	}

	public void setMaxspeechtimeout(String maxspeechtimeout) {
		this.maxspeechtimeout = maxspeechtimeout;
	}

	public String getCompletetimeout() {
		return completetimeout;
	}

	public void setCompletetimeout(String completetimeout) {
		this.completetimeout = completetimeout;
	}

	public String getIncompletetimeout() {
		return incompletetimeout;
	}

	public void setIncompletetimeout(String incompletetimeout) {
		this.incompletetimeout = incompletetimeout;
	}

	public String getGrammarType() {
		return grammarType;
	}

	public void setGrammarType(String grammarType) {
		this.grammarType = grammarType;
	}

	public String getGrammarPath() {
		return grammarPath;
	}

	public void setGrammarPath(String grammarPath) {
		this.grammarPath = grammarPath;
	}

	public String getGrammarsFileExtension() {
		return grammarsFileExtension;
	}

	public void setGrammarsFileExtension(String grammarsFileExtension) {
		this.grammarsFileExtension = grammarsFileExtension;
	}

}
