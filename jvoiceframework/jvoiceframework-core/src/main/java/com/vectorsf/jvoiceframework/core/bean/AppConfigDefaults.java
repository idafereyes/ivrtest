package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfigDefaults")
public class AppConfigDefaults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9142571578698682638L;
	
	static final String FALSE = "false";
	
	//TODO En un primer momento le estamos asignando valores hardcodeados.
	//Está pendiente ver de qué manera toman valor estos atributos.
	
	private Locale userLocale = new Locale("Es", "es");
	
	@Value(FALSE)
	private boolean bargein;
	
	public Locale getUserLocale() {
		return userLocale;
	}

	public void setUserLocale(Locale userLocale) {
		this.userLocale = userLocale;
	}

	@Value(FALSE)
	private boolean flush;
	
	@Value(FALSE)
	private boolean catchHangup;

	@Value("10s")
	private String transferConnectiontimeout;

	@Value("20s")
	private String transferMaxtime;
	
	@Value(FALSE)
	private boolean recordBeep;

	@Value(FALSE)
	private boolean recordDtmfterm;

	@Value("5s")
	private String recordFinalsilence;

	@Value("10s")
	private String recordMaxtime;

	@Value("recordedAudio.wav")
	private String recordFileName;

	@Value("D:\\tmp\\recordedAudiosPath\\")
	private String recordFilePath;
	
	@Value(FALSE)
	private boolean recordKeep;

	@Value(FALSE)
	private boolean inputBargein;
	
	@Value("3")
	private int inputMaxAttempts;
	
	@Value("2")
	private int inputNoMatchAttempts;
	
	@Value("2")
	private int inputNoInputAttempts;
	
	@Value("builtin:jvoice-test-app/")
	private String audiosLocationPrefix;
	
	@Value("")
	private String audiosFormatSuffix;
	
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

}
