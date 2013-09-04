package com.vectorsf.jvoiceframework.core.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfigDefaults")
public class AppConfigDefaults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9142571578698682638L;
	
	//TODO En un primer momento le estamos asignando un valor hardcodeado.
	//Está pendiente ver de qué manera toman valor estos atributos.
	@Value("false")
	private boolean bargein;
	
	//TODO En un primer momento le estamos asignando un valor hardcodeado.
	//Está pendiente ver de qué manera toman valor estos atributos.
	@Value("false")
	private boolean flush;
	
	//TODO En un primer momento le estamos asignando un valor hardcodeado.
	//Está pendiente ver de qué manera toman valor estos atributos.
	@Value("false")
	private boolean catchHangup;
	
	//TODO En un primer momento le estamos asignando un valor hardcodeado.
	//Está pendiente ver de qué manera toman valor estos atributos.
	@Value("transferIdleMusic")
	private String transferaudio;
	
	//TODO En un primer momento le estamos asignando un valor hardcodeado.
	//Está pendiente ver de qué manera toman valor estos atributos.
	@Value("10s")
	private String transferConnectiontimeout;

	//TODO En un primer momento le estamos asignando un valor hardcodeado.
	//Está pendiente ver de qué manera toman valor estos atributos.
	@Value("20s")
	private String maxtime;

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

	public String getTransferaudio() {
		return transferaudio;
	}

	public void setTransferaudio(String transferaudio) {
		this.transferaudio = transferaudio;
	}

	public String getTransferConnectiontimeout() {
		return transferConnectiontimeout;
	}

	public void setTransferConnectiontimeout(String transferConnectiontimeout) {
		this.transferConnectiontimeout = transferConnectiontimeout;
	}

	public String getMaxtime() {
		return maxtime;
	}

	public void setMaxtime(String maxtime) {
		this.maxtime = maxtime;
	}

}
