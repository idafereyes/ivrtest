package com.vectorsf.jvoiceframework.core.service.ws;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;



/**
 * Handler para registrar en el log los mensajes SOAP de entrada y salida
 */
@Component("loggingHandler")
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {
	
	@Log
	private Logger logger;
	
	private String charset = "UTF-8";
	
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext smc) {
		logMessage(smc);
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext smc) {
		logMessage(smc);
		return true;
	}

	@Override
	public void close(MessageContext messageContext) {
		// No hay nada que limpiar
	}

	/**
	 * Comprueba la propiedad MESSAGE_OUTBOUND_PROPERTY para establecer si se trata de un mensaje de entrada o de salida.
	 * Escribe el mensaje usando la utilidad de registro de logs
	 * @param smc
	 */
	private void logMessage(SOAPMessageContext messageContext) {

		if (logger.isDebugEnabled()) {
			
			Boolean outboundProperty = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			
			StringOutputStream outputStream = new StringOutputStream();
			try {
				messageContext.getMessage().writeTo(outputStream);
				if (outboundProperty.booleanValue()) { 
					logger.debug(LoggingHandlerMessages.DEBUG_OUT_MESSAGE, outputStream.toString());
				} else {	
					logger.debug(LoggingHandlerMessages.DEBUG_IN_MESSAGE, outputStream.toString());
				}
			} catch (Exception e) {		
				logger.error(LoggingHandlerMessages.ERROR_LOGGING_MESSAGE, e);
			}
		} 		
	}

	/** Clase que representa un String como OutputString */ 
	public class StringOutputStream extends OutputStream {

		private StringBuffer buffer = new StringBuffer();
		
		public StringOutputStream() {}

		public void close() {}

		public void flush() {
			buffer.delete(0, buffer.length());
		}

		public StringBuffer getBuffer() {
			return buffer;
		}

		public void setBuffer(StringBuffer buffer) {
			this.buffer = buffer;
		}

		public void write(byte[] b) {
			String str = new String(b, Charset.forName(charset));
			this.buffer.append(str);
		}
		
		public void write(byte[] b, int off, int len) {
			String str = new String(b, off, len, Charset.forName(charset));
			this.buffer.append(str);
		}

		public void write(int b) {
			String str = Integer.toString(b);
			this.buffer.append(str);
		}

		public String toString() {
			return buffer.toString();
		}
	} 
}