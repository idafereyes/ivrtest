package com.vectorsf.jvoiceframework.isban.logger.utils;

import java.util.ArrayList;
import java.util.List;

//import com.santander.commons.prompt.Prompt;
//import com.santander.commons.prompt.PromptType;


import com.vectorsf.jvoiceframework.isban.logger.exceptions.ParseException;

public class PromptUtils {
	
	
	/*
	 * Sample Prompts
	 * 
	
	<audio src="builtin:SuperlineaDES/BT-BIENVENIDA-T"></audio>
	<prompt bargein="true">
       <audio src="builtin:SuperlineaDES/BT-IDENTIFICACION-PT1">Por favor, introduzca los n�meros de su documento de identidad.</audio>
	</prompt>
	<prompt bargein="true">
       <audio src="builtin:SuperlineaDES/BT-MENU-VALORES-PT1">Si desea consultar cotizaciones de valores, pulse 1. ... Para volver a escuchar el men� inicial, pulse asterisco.</audio>
       <audio src="builtin:SuperlineaDES/BT-DESPEDIDA">Muchas gracias por su llamada.</audio>
	</prompt>
	<prompt bargein="true">
       OBRASCON HUARTE c�digo 656 est� cotizando a 17 con 89 euros. La oferta es de 2251 t�tulos a 17 con 90 euros, y hay una demanda de 303 t�tulos a 17 con 87 euros. 
	</prompt>
	<value expr="'3955'" class="number:digits" mode="recorded"/>
	<value expr="'1'" class="number:cardinal" mode="recorded"/>
	
	 *
	 */
	
//	public static List<Prompt> parsePromptQueue(String promptQueue) throws ParseException {
//		
//		try {
//			String promptID =  null;
//			ArrayList <Prompt> promptList = new ArrayList<Prompt>();		
//			String[] splittedPromptQueue = promptQueue.split("\n");
//			String textline = null;
//			int start;
//			int end;
//	
//			for (int i=0; i<splittedPromptQueue.length; i++)
//			{
//				textline = splittedPromptQueue[i].trim();
//				
//				if (textline != null && !textline.startsWith("<prompt") && !textline.startsWith("</prompt"))
//				{
//					if (textline.indexOf("<audio") != -1) 
//					{// Vocabulary
//						start = textline.indexOf("\""); // caracter inicial de src
//						end = textline.indexOf("\"", start + 1); // caracter final de src
//						promptList.add(new Prompt(textline.substring(start + 1, end), "", PromptType.MMF));
//					}
//					else if (textline.indexOf("<value") != -1)
//					{// Numset
//						start = textline.indexOf('"'); // caracter inicial de expr
//						end = textline.indexOf('"', start + 1); // caracter final de expr
//						promptID = textline.substring(start + 2, end - 1);
//						start = textline.indexOf('"', end + 1);
//						end = textline.indexOf('"', start + 1);
//						promptID = textline.substring(start + 1, end) + "/" + promptID;
//						promptList.add(new Prompt(promptID, "", PromptType.MMF));
//					}
//					else
//					{// TTS
//						if (!textline.equals(""))
//							promptList.add(new Prompt("", textline, PromptType.TTS));
//					}				
//				}
//			}
//			return promptList;
//		}
//		catch(Exception e) {
//			throw new ParseException("Exception parsing queuePrompt: " + e.getMessage(), e);
//		}
//	}
}
