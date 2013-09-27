package com.vectorsf.jvoiceframework.testapp.service.promotion;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Component("promotion")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4255731899810096095L;
	private String wording;
	private Calendar expiration;
	
	public String getWording() {
		return wording;
	}
	public void setWording(String wording) {
		this.wording = wording;
	}
	public Calendar getExpiration() {
		return expiration;
	}
	public void setExpiration(Calendar expiration) {
		this.expiration = expiration;
	}
}
