package org.vector.jvoice.flow.bean;

import java.io.Serializable;

/**
 * Representa un estado Prompt IVR+
 * 
 * @author dmarina
 */
public class Prompt implements Serializable{

	private static final long serialVersionUID = -5942501816585768384L;
	
	private String message;

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
