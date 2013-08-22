package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Iterator;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
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

		html += "<h1>Input</h1>";
		html += "<p>Name = " + input.getName() + "</p>";
		
		//Parametros
		html += "<table cellpadding=\"0\" cellspacing=\"0\">";
		html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max Attempts</td>";
		html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.getMaxAttempts() + "</td></tr>";
		html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max No Input Attempts</td>";
		html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.getMaxNoInput() + "</td></tr>";
		html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max No Match Attempts</td>";
		html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.getMaxNoMatch() + "</td></tr>";
		html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Bargein</td>";
		html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.isBargein() + "</td></tr>";
		html += "</table><br/>";
		
		//Grammars
		html += "<table cellpadding=\"0\" cellspacing=\"0\">";
		html += "<tr><td colspan=\"2\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Grammars</b></td></tr>";
		if(!input.getGrammars().isEmpty()) {
			html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Type</td>";
			html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Src</td></tr>";
			for(Grammar g : input.getGrammars()) {
				html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + g.getType() + "</td>";
				html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + g.getSrc() + "</td></tr>";
			}
		} else {
			html += "<tr><td colspan=\"2\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No grammars</td></tr>";
		}
		html += "</table><br/>";
		
		//Audios
		html += "<table cellpadding=\"0\" cellspacing=\"0\">";
		html += "<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Audios</b></td></tr>";
		if(!input.getMainAudios().isEmpty()) {
			html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Cond</td>";
			html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Src</td>";
			html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Wording</td></tr>";
			for(AudioItem ai : input.getMainAudios()) {
				html += "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getCond() + "</td>";
				html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getSrc() + "</td>";
				html += "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getWording() + "</td></tr>";
			}
		} else {
			html += "<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>";
		}
		html += "</table><br/>";
				
		// Pendiente comprobar si es necesario pasarlo como parÃ¡metro. Seria mejor en un input hidden. 
		html += "<form method=\"post\" action=\"" + flowURL + "\">"; 
		html += "Evento: <input type=\"text\" name=\"" + input.getName() + "\"><br/>";	
		
		// Dispara un evento que tiene como nombre el value del input. Podemos definir eventos dÃ¡ndole el valor que queramos
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
