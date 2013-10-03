package com.vectorsf.jvoiceframework.testapp.service.portfolio;


public abstract class Account {
	private final int ENDING_LENGTH = 4;
	
	private String status;
	private String id;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdEnding() {
		return this.getId().substring(this.getId().length() - ENDING_LENGTH, this.getId().length() - 1);
	}

}
