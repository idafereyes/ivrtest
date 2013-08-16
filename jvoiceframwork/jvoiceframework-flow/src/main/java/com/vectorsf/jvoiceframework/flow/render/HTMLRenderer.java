package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Iterator;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;

/**
 * Implementaci�n de renderizador que genera c�digo HTML
 * 
 * @author dmartina
 */
public class HTMLRenderer implements Renderer, Serializable {

	private static final long serialVersionUID = 4511972601190155577L;
	
	public String render(Input input, String flowURL) {
		String html = new String();

		// Pendiente comprobar si es necesario pasarlo como par�metro. Seria mejor en un input hidden. 
		html += "<form method=\"post\" action=\"" + flowURL + "\">"; 
		html += "<input type=\"text\" name=\"" + input.getName() + "\">";	
		
		// Dispara un evento que tiene como nombre el value del input. Podemos definir eventos d�ndole el valor que queramos
		html += "<input type=\"submit\" value=\"Enter\" name=\"_eventId\">"; 
		
		html += "</form>";
		return html;
	}
	
	public String render(Prompt prompt, String flowURL){
		return "<span>" + prompt.getMessage() + "</span><br>";
	}
	
	public String render(Output output, String flowURL){
		String renderCode = "";
		
		renderCode += "<span>bargein: " + output.isBargein() + "</span><br>";
		renderCode += "<span>flush: " + output.isFlush() + "</span><br>";
		renderCode += "<span>catchHangup: " + output.isCatchHangup() + "</span><br>";
		
		Iterator<AudioItem> it = output.getAudioItemsList().iterator();
		while (it.hasNext()){
			AudioItem prompt = it.next();
			renderCode += "<span>prompt</span><br>";			
			renderCode += "<span>src: " + prompt.getSrc() + "</span><br>";			
			renderCode += "<span>wording: " + prompt.getWording() + "</span><br>";			
		}
		
		return renderCode;
	}

	
}
