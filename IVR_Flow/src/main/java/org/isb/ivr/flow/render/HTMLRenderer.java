package org.isb.ivr.flow.render;

import java.io.Serializable;

import org.isb.ivr.flow.bean.Input;
import org.isb.ivr.flow.bean.Prompt;


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
	
}
