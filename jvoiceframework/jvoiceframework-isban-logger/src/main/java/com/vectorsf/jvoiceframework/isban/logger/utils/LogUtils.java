package com.vectorsf.jvoiceframework.isban.logger.utils;

public class LogUtils {
	
	public static String parseEventLogMessage (String message){
		
		if (message != null){
			//Busca "\" y reemplaza por "\\"
			message = message.replace("\\", "\\\\");
			
			//Busca ‘|’ y reemplaza por ‘\|’
			message = message.replace("|", "\\|");
	
			//Busca ‘&’ y reemplaza ‘\&’
			message = message.replace("&","\\&");
	
			//Busca ‘=’ y reemplaza ‘\=’
			message = message.replace("=", "\\=");
			
			//Busca Salto de línea y reemplaza por ‘ ’
			message = message.replace("\n", " ");
			
		}
		return message;
	}

	public static String parseLogMessage (String message){
		
		if (message != null){
			//Busca "\" y reemplaza por "\\"
			message = message.replace("\\", "\\\\");
			
			//Busca ‘|’ y reemplaza por ‘\|’
			message = message.replace("|", "\\|");
			
			//Busca Salto de línea y reemplaza por ‘ ’
			message = message.replace("\n", " ");
	
		}
		
		return message;
	}


}
