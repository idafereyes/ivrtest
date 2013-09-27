package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;

/**
 * Implementación de renderizador que genera código HTML
 * 
 * @author dmartina
 */
@Component("renderer")
public class HTMLRenderer implements Renderer, Serializable {

    private static final long serialVersionUID = 4511972601190155577L;
    
    //String literals reuse
    private String tableHtml = "<table cellpadding=\"0\" cellspacing=\"0\">";
    private String tdHtml = "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">";
    private String startTdHtml = "<td>";
    private String endTdHtml = "</td>";
    private String endTableHtml = "</table><br/>";
    private String trStyledHtml = "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\" >";
    private String endTrHtml = "</td></tr>";
    private String endSpanHtml = "</span><br/>";
    
    
	public String render(List<Object> states, String flowURL) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(Input input, String flowURL) {
    	
    	// Identificador del elemento en la página
    	String identifier = UUID.randomUUID().toString();
    	StringBuilder html = new StringBuilder();
    	
    	// control para expandir información
    	html.append("<a onclick=\"javascript:toggle_visibility('" + identifier + "');\">Input </a>");
    	html.append(renderSummary(input.getMainAudios()));
    	
    	// Acceso rápido para hacer sumbit
    	html.append("<span style=\"display: inline-block;\">");
    	html.append("<form method=\"post\" action=\"" + flowURL + "\">"); 
        html.append("Event:");
        html.append("<select name=\"event\">");
        html.append("<option value=\"_eventId_match\">Match</option>");
        html.append("<option value=\"_eventId_maxnomatch\">No match (Max)</option>");
        html.append("<option value=\"_eventId_maxnoinput\">No input (Max)</option>");
        html.append("</select>");
        html.append("Interpretation: <input type=\"text\" value=\"\" name=\"interpretation\" />");
        html.append("<input type=\"submit\" id=\"inputSubmit\" value=\"Enter\" name=\"_eventId_match\">"); 
        html.append("</form>");
        html.append("</span>");
    	html.append("<div id='" + identifier + "' style='display:none'>");  
    	
    	//html.append("<p>Name = " + input.getName() + "</p>"); // Para qué vale el name???
    	//html.append(renderInputEvent(input)); // Esto para que todos los input no lanzan los mismos eventos?
    	
    	   	
        html.append(renderInputParameters(input));         
        html.append(renderAudioItems(input.getMainAudios(), "Main audio items"));
        html.append(renderAudioItems(input.getNoMatchAudios(), "No Match audio items"));
        html.append(renderAudioItems(input.getNoInputAudios(), "No input audio items"));
        html.append(renderInputGrammars(input));
        html.append(renderInputResults(flowURL));
    	
    	html.append("</div>");
    	html.append("<br>");
        return html.toString();
    }
    
    private String renderInputParameters(Input input) {
        //Parametros
        StringBuilder html = new StringBuilder();
        html.append(tableHtml);
        html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max Attempts</td>");
        html.append(tdHtml + input.getMaxAttempts() + endTrHtml);
        html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max No Input Attempts</td>");
        html.append(tdHtml + input.getMaxNoInput() + endTrHtml);
        html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Max No Match Attempts</td>");
        html.append(tdHtml + input.getMaxNoMatch() + endTrHtml);
        html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Bargein</td>");
        html.append(tdHtml + input.isBargein() + endTrHtml);
        html.append(endTableHtml);
        return html.toString();
    }
    
    private String renderInputGrammars(Input input) {
        StringBuilder html = new StringBuilder();
        //Grammars
        html.append(tableHtml);
        html.append("<tr><td colspan=\"2\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Grammars</b></td></tr>");
        if(!input.getGrammars().isEmpty()) {
            html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Type</td>");
            html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Src</td></tr>");
            for(Grammar g : input.getGrammars()) {
                html.append(trStyledHtml + g.getType() + endTdHtml);
                html.append(tdHtml + g.getSrc() + endTrHtml);
            }
        } else {
            html.append("<tr><td colspan=\"2\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No grammars</td></tr>");
        }
        html.append(endTableHtml);
        return html.toString();
    }
    
   
    private String renderInputResults(String flowURL) {
        StringBuilder html = new StringBuilder();
        
        //Resultados
        html.append("<script>");
        html.append("function eventChanged() {");
        html.append("    var e = document.getElementById('selectEvent');");
        html.append("    var opt = e.options[e.selectedIndex].value;");
        html.append("    document.getElementById('inputSubmit').name = '_eventId_' + opt;");
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
        
    
    private String renderSummary(List<AudioItem> audioItems) {
    	String summary = null; 
    	// Mostramos el primer audio item como resumen
        AudioItem audioItem = audioItems.get(0);
        if (audioItem != null){
        	if (audioItem.getSrc() != null) {
        		summary = audioItem.getSrc();
        	}
        	else if (audioItem.getWording() != null) {
        		summary = audioItem.getWording();
        	}
        	else {
        		summary = "No audio item";
        	}
        }
        return summary;
    }
    
    private String renderAudioItems(List<AudioItem> audioItems, String title) {
    	StringBuilder html = new StringBuilder();
    	html.append("<span class=\"property_title\">"+ title +"</span>");
    	if (audioItems.size() > 0) {
    		 Iterator<AudioItem> it = audioItems.iterator();
    		
    		 html.append("<table class=\"datagrid\" cellpadding=\"0\" cellspacing=\"0\">");    		 
    		 html.append("<thead class=\"datagrid\"><tr><th>Wording</th><th>Source</th><th>Condition</th></tr></thead>");
    		 
    		 while (it.hasNext()){
                 AudioItem prompt = it.next();
                 html.append("<tr class=\"datagrid\">");
                 html.append(startTdHtml + prompt.getWording() + endTdHtml);           
                 html.append(startTdHtml + prompt.getSrc() + endTdHtml);         
                 html.append(startTdHtml + prompt.getCondition() + endTdHtml);  
                 html.append("</tr>");
             }
    		 html.append("</table>");

    	}
    	return  html.toString();
    }
    
    public String render(Output output, String flowURL){
      // Identificador del elemento en la página
    	String identifier = UUID.randomUUID().toString();
    	StringBuilder html = new StringBuilder();
    	
    	
    	
    	html.append("<a title=\"Expandir / contrater\" onclick=\"javascript:toggle_visibility('" + identifier + "');\">Output </a>");
  

    	html.append("<span class=\"output_content\">" + renderSummary(output.getAudioItems()) + "</span>");
    	
    	
    	html.append("<div id='" + identifier + "' style='display:none'>");
    	
    	html.append("</br>");
    	html.append("<span class=\"property_title\">Bargein: </span><span class=\"property_value\">" + output.isBargein() + "</span><br>");
    	html.append("<span class=\"property_title\">Flush: </span><span class=\"property_value\">" + output.isFlush() + "</span><br>");
    	html.append("<span class=\"property_title\">CatchHangup: </span><span class=\"property_value\">" + output.isCatchHangup() + "</span><br>");

    	html.append(renderAudioItems(output.getAudioItems(), "Audio items:"));
    	html.append("</br>"); 
    	html.append("</div>"); 
    	html.append("</br>"); 
    	
    	
        return html.toString();
    }
    
    

    public String render(Transfer transfer, String flowURL) {
        String renderCode = "";
        
        renderCode += "<span>Transfer</span>";
        renderCode += "<span>dest: " + transfer.getDest() + endSpanHtml;
        renderCode += "<span>type: " + transfer.getType() + endSpanHtml;
        renderCode += "<span>transferaudio: " + transfer.getTransferaudio() + endSpanHtml;
        renderCode += "<span>timeout: " + transfer.getTimeout() + endSpanHtml;
        renderCode += "<span>maxtime: " + transfer.getMaxtime() + endSpanHtml;

        Iterator<String> it = transfer.getEventsList().iterator();
        
        renderCode += "<span>Events:</span><br>";            
        while (it.hasNext()){
            String event = it.next();
            renderCode += "<span>" + event + endSpanHtml;            
        }
        
        Iterator itMap = transfer.getProperties().keySet().iterator();

        while (itMap.hasNext()){
            String property = (String) itMap.next();
            String value = transfer.getProperties().get(property);
            
            renderCode += "<span>Properties" + endSpanHtml;            
            renderCode += "<span>Property: "+property+endSpanHtml;            
            renderCode += "<span>Value: "+value+endSpanHtml;            
        }

        return renderCode;
    }

	public String render(Record record, String flowURL) {
        String renderCode = "";
        
        renderCode += "<span>Record</span>";
        renderCode += "<span>beep: " + record.isBeep() + endSpanHtml;
        renderCode += "<span>dtmfterm: " + record.isDtmfterm() + endSpanHtml;
        renderCode += "<span>maxtime: " + record.getMaxtime() + endSpanHtml;
        renderCode += "<span>finalsilence: " + record.getFinalsilence() + endSpanHtml;
        renderCode += "<span>fileName: " + record.getFileName() + endSpanHtml;
        renderCode += "<span>filePath: " + record.getFilePath() + endSpanHtml;
        renderCode += "<span>keep: " + record.isKeep() + endSpanHtml;

        
        Iterator<AudioItem> itAudios = record.getAudioItemsList().iterator();
        renderCode += "<span>audioItemsList" + endSpanHtml;            
       
        while (itAudios.hasNext()){
            AudioItem prompt = itAudios.next();
            renderCode += "<span>Audio Item" + endSpanHtml;            
            renderCode += "<span>src: " + prompt.getSrc() + endSpanHtml;            
            renderCode += "<span>wording: " + prompt.getWording() + endSpanHtml;            
            renderCode += "<span>cond: " + prompt.getCondition() + endSpanHtml;            
        }

        Iterator<String> it = record.getEventsList().iterator();
        renderCode += "<span>Events:</span><br>";            
        while (it.hasNext()){
            String event = it.next();
            renderCode += "<span>" + event + endSpanHtml;            
        }
        
        Iterator itMap = record.getProperties().keySet().iterator();

        renderCode += "<span>Properties" + endSpanHtml;            

        while (itMap.hasNext()){
            String property = (String) itMap.next();
            String value = record.getProperties().get(property);
            
            renderCode += "<span>Property: "+ property + endSpanHtml;            
            renderCode += "<span>Value: "+ value + endSpanHtml;            
        }

        return renderCode;
	}

    public String render(End end, String flowURL) {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<h1>End</h1>");
    	sb.append("<p>");
    	sb.append(end.getName());
    	sb.append("</p>");
    	
    	return sb.toString();
    }

	public String renderStartPage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String renderEndPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
