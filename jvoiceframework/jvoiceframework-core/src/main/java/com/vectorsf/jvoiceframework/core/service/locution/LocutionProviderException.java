package com.vectorsf.jvoiceframework.core.service.locution;

public class LocutionProviderException extends RuntimeException {

	private static final long serialVersionUID = 3425019913246371383L;

	public LocutionProviderException () {}

	public LocutionProviderException (String message) {
      super (message);
	}

	public LocutionProviderException (Throwable cause) {
      super (cause);
	}

	public LocutionProviderException (String message, Throwable cause) {
      super (message, cause);
	}
}
