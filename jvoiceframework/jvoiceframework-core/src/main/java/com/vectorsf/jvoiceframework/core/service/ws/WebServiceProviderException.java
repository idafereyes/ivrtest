package com.vectorsf.jvoiceframework.core.service.ws;

public class WebServiceProviderException extends RuntimeException {
	private static final long serialVersionUID = -561877526629764975L;
	
	public WebServiceProviderException () {}

	public WebServiceProviderException (String message) {
      super (message);
	}

	public WebServiceProviderException (Throwable cause) {
      super (cause);
	}

	public WebServiceProviderException (String message, Throwable cause) {
      super (message, cause);
	}
}
