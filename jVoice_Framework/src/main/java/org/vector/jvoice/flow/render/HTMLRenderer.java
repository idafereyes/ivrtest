package org.vector.jvoice.flow.render;

import java.io.Serializable;
import java.util.Iterator;

import org.vector.jvoice.flow.bean.AudioItem;
import org.vector.jvoice.flow.bean.Input;
import org.vector.jvoice.flow.bean.Output;
import org.vector.jvoice.flow.bean.Prompt;

/**
 * Implementación de renderizador que genera código HTML
 * 
 * @author dmartina
 */
public class HTMLRenderer implements Renderer, Serializable {

	private static final long serialVersionUID = 4511972601190155577L;
	
	public String render(Input input, String flowURL) {
		String html = new String();

		// Pendiente comprobar si es necesario pasarlo como parámetro. Seria mejor en un input hidden. 
		html += "<form method=\"post\" action=\"" + flowURL + "\">"; 
		html += "<input type=\"text\" name=\"" + input.getName() + "\">";	
		
		// Dispara un evento que tiene como nombre el value del input. Podemos definir eventos dándole el valor que queramos
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
		
		Iterator it = output.getAudioItemsList().iterator();
		while (it.hasNext()){
			AudioItem prompt = (AudioItem) it.next();
			renderCode += "<span>prompt</span><br>";			
			renderCode += "<span>src: " + prompt.getSrc() + "</span><br>";			
			renderCode += "<span>wording: " + prompt.getWording() + "</span><br>";			
		}
		
		return renderCode;
	}

	
}
