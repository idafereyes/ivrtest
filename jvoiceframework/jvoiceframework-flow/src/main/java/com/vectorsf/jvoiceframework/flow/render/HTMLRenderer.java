package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Iterator;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;

/**
 * Implementación de renderizador que genera código HTML
 * 
 * @author dmartina
 */
public class HTMLRenderer implements Renderer, Serializable {

	private static final long serialVersionUID = 4511972601190155577L;
	
	public String render(Input input, String flowURL) {
		StringBuilder html = new StringBuilder();

		html.append("<h1>Input</h1>");
		// Input name
		html.append("<p>Name = " + input.getName() + "</p>");
		
		//Parametros
		html.append("<table cellpadding=\"0\" cellspacing=\"0\">");
		html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max Attempts</td>");
		html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.getMaxAttempts() + "</td></tr>");
		html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max No Input Attempts</td>");
		html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.getMaxNoInput() + "</td></tr>");
		html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max No Match Attempts</td>");
		html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.getMaxNoMatch() + "</td></tr>");
		html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Bargein</td>");
		html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + input.isBargein() + "</td></tr>");
		html.append("</table><br/>");
		
		//Grammars
		html.append("<table cellpadding=\"0\" cellspacing=\"0\">");
		html.append("<tr><td colspan=\"2\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Grammars</b></td></tr>");
		if(!input.getGrammars().isEmpty()) {
			html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Type</td>");
			html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Src</td></tr>");
			for(Grammar g : input.getGrammars()) {
				html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + g.getType() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + g.getSrc() + "</td></tr>");
			}
		} else {
			html.append("<tr><td colspan=\"2\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No grammars</td></tr>");
		}
		html.append("</table><br/>");
		
		//Audios
		html.append("<table cellpadding=\"0\" cellspacing=\"0\">");
		html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Audios</b></td></tr>");
		html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Cond</td>");
		html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Src</td>");
		html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Wording</td></tr>");
		
		html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black; background-color: #7070FF; text-align: center;\"><b>Main</b></td></tr>");
		if(!input.getMainAudios().isEmpty()) {
			for(AudioItem ai : input.getMainAudios()) {
				html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getCond() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getSrc() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getWording() + "</td></tr>");
			}
		} else {
			html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>");
		}
		
		html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black; background-color: #7070FF; text-align: center;\"><b>No Match</b></td></tr>");
		if(!input.getNoMatchAudios().isEmpty()) {
			for(AudioItem ai : input.getNoMatchAudios()) {
				html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getCond() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getSrc() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getWording() + "</td></tr>");
			}
		} else {
			html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>");
		}
		
		html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black; background-color: #7070FF; text-align: center;\"><b>No Input</b></td></tr>");
		if(!input.getNoInputAudios().isEmpty()) {
			for(AudioItem ai : input.getNoInputAudios()) {
				html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getCond() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getSrc() + "</td>");
				html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + ai.getWording() + "</td></tr>");
			}
		} else {
			html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>");
		}
		
		html.append("</table><br/>");
		
		//Resultados
		//Evento
		html.append("<script>");
		html.append("function eventChanged() {");
		html.append("	var e = document.getElementById('selectEvent');");
		html.append("	var opt = e.options[e.selectedIndex].value;");
		html.append("	document.getElementById('inputSubmit').name = '_eventId_' + opt;");
		html.append("}");
		html.append("</script>");
		html.append("<form method=\"post\" action=\"" + flowURL + "\">"); 
		html.append("Event:");
		html.append("<select name=\"event\" id=\"selectEvent\" onchange=\"eventChanged();\" >");
		html.append("<option value=\"match\">match</option>");
		html.append("<option value=\"maxnomatch\">maxnomatch</option>");
		html.append("<option value=\"maxnoinput\">maxnoinput</option>");
		html.append("</select><br/>");
		
		
		html.append("<div style=\"border: solid 1px black;\" >");
		html.append("Result<br/>");
		html.append("Interpretation: <input type=\"text\" value=\"\" name=\"interpretation\" /><br/>");
		html.append("Utterance: <input type=\"text\" value=\"\" name=\"utterance\" /><br/>");
		html.append("Confidence: <input type=\"text\" value=\"\" name=\"confidence\" /><br/>");
		html.append("Input mode: <input type=\"text\" value=\"\" name=\"inputmode\" /><br/>");
		html.append("</div><br/>");
		
		// Dispara un evento que tiene como nombre el value del input. Podemos definir eventos dandole el valor que queramos
		html.append("<input type=\"submit\" id=\"inputSubmit\" value=\"Enter\" name=\"_eventId_match\">"); 
		html.append("</form>");
		return html.toString();
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
