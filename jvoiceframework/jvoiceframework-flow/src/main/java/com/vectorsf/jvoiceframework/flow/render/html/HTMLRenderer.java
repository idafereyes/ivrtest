package com.vectorsf.jvoiceframework.flow.render.html;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.BlindTransfer;
import com.vectorsf.jvoiceframework.core.bean.BridgeTransfer;
import com.vectorsf.jvoiceframework.core.bean.ConsultationTransfer;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.flow.render.AbstractRenderer;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

/**
 * Implementación de renderizador que genera código HTML
 * 
 * @author dmartina
 */
@Component("renderer")
public class HTMLRenderer extends AbstractRenderer implements Renderer, Serializable {

	private static final long serialVersionUID = 4511972601190155577L;
    
    //String literals reuse
    private String tableHtml = "<table cellpadding=\"0\" cellspacing=\"0\">";
    private String tdHtml = "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">";
    private String startTdHtml = "<td>";
    private String endTdHtml = "</td>";
    private String endTableHtml = "</table><br/>";
    private String trStyledHtml = "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\" >";
    private String endTrHtml = "</td></tr>";
    private String endSpanBrHtml = "</span><br/>";
    private String endSpanHtml = "</span>";
    private String endFormHtml = "</form>";
    private String quoteEndTagHtml = "\">";
    private String formMethodPostActionHtml = "<form method=\"post\" action=\"";
   
	public String render(Input input, String flowURL, String contextPath) {
    	
    	// Identificador del elemento en la página
    	String identifier = UUID.randomUUID().toString();
    	StringBuilder html = new StringBuilder();
    	
    	// control para expandir información
    	html.append("<a onclick=\"javascript:toggle_visibility('" + identifier + "');\">Input </a>");
    	if (input.getMainAudios() != null && !input.getMainAudios().isEmpty()){
        	html.append(renderSummary(input.getMainAudios()));
    	}
    	
    	// Acceso rápido para hacer sumbit
    	html.append("<span style=\"display: inline-block;\">");
    	html.append(formMethodPostActionHtml + flowURL + quoteEndTagHtml); 
        html.append("Event:");
        html.append("<select name=\"_eventId\">");
        html.append("<option value=\"match\">Match</option>");
        html.append("<option value=\"maxnomatch\">No match (Max)</option>");
        html.append("<option value=\"maxnoinput\">No input (Max)</option>");
        html.append("</select>");
        html.append("Interpretation: <input type=\"text\" value=\"\" name=\"interpretation\" />");
        html.append("<input type=\"submit\" id=\"inputSubmit\" value=\"Enter\" name=\"_eventId_match\">"); 
        html.append(endFormHtml);
        html.append(endSpanHtml);
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
                html.append(trStyledHtml + g.getMode() + endTdHtml);
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
        html.append(formMethodPostActionHtml + flowURL + quoteEndTagHtml); 
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
        html.append(endFormHtml);
        return html.toString();
    }
        
    
    private String renderSummary(List<AudioItem> audioItems) {
    	String summary = null; 
    	// Mostramos el primer audio item como resumen
        AudioItem audioItem = audioItems.get(0);
        if (audioItem != null){
        	if (audioItem.getWording() != null && audioItem.getWording().getText() != null) {
        		summary = audioItem.getWording().getText();
        	}
        	else if (audioItem.getSrc() != null) {        		
        		summary = audioItem.getSrc();
        	}
        	else {
        		summary = "No audio item";
        	}
        }
        return summary;
    }
    
    private String renderAudioItems(List<AudioItem> audioItems, String title) {
    	StringBuilder html = new StringBuilder();
    	html.append("<span class=\"property_title\">"+ title +endSpanHtml);
    	if (audioItems.size() > 0) {
    		 Iterator<AudioItem> it = audioItems.iterator();
    		
    		 html.append("<table class=\"datagrid\" cellpadding=\"0\" cellspacing=\"5\">");    		 
    		 html.append("<thead class=\"datagrid\"><tr><th>Wording</th><th>Source</th><th>Condition</th></tr></thead>");
    		 
    		 while (it.hasNext()){
                 AudioItem prompt = it.next();
                 html.append("<tr class=\"datagrid\">");
                 html.append(startTdHtml + (prompt.getWording()==null || prompt.getWording().getText()==null ? "null" : prompt.getWording().getText()) + endTdHtml);           
                 html.append(startTdHtml + prompt.getSrc() + endTdHtml);         
                 html.append(startTdHtml + prompt.getCondition() + endTdHtml);  
                 html.append("</tr>");
             }
    		 html.append("</table>");

    	}
    	return  html.toString();
    }
    
    public String render(Output output, String flowURL, String contextPath){
      // Identificador del elemento en la página
    	String identifier = UUID.randomUUID().toString();
    	StringBuilder html = new StringBuilder();
    	
    	
    	
    	html.append("<a title=\"Expandir / contrater\" onclick=\"javascript:toggle_visibility('" + identifier + "');\">Output </a>");
  
    	if (output.getAudioItems() != null && !output.getAudioItems().isEmpty()){
    		html.append("<span class=\"output_content\">" + renderSummary(output.getAudioItems()) + endSpanHtml);
    	}
    	if(output.isFlush() || output.isCatchHangup()) {

    		html.append(formMethodPostActionHtml + flowURL + quoteEndTagHtml); 
            html.append("<input type=\"hidden\" name=\"_eventId\" value=\"success\">");
            html.append("<input type=\"submit\"  value=\"continuar\">");
             
            html.append(endFormHtml);

    	}
    	
    	html.append("<div id='" + identifier + "' style='display:none'>");
    	
    	html.append("</br>");
    	html.append("<span class=\"property_title\">Bargein: </span><span class=\"property_value\">" + output.isBargein() + "</span><br>");
    	html.append("<span class=\"property_title\">Flush: </span><span class=\"property_value\">" + output.isFlush() + "</span><br>");
    	html.append("<span class=\"property_title\">CatchHangup: </span><span class=\"property_value\">" + output.isCatchHangup() + "</span><br>");

    	if (output.getAudioItems() != null && !output.getAudioItems().isEmpty()){
    		html.append(renderAudioItems(output.getAudioItems(), "Audio items:"));
    	}
    	
    	
    	html.append("</br>"); 
    	html.append("</div>"); 
    	html.append("</br>"); 
    	
    	
        return html.toString();
    }
    
	public String render(Record record, String flowURL, String contextPath) {
		
	      // Identificador del elemento en la página
	    	String identifier = UUID.randomUUID().toString();
	    	StringBuilder html = new StringBuilder();
	    		    	
	    	html.append("<a title=\"Expandir / contrater\" onclick=\"javascript:toggle_visibility('" + identifier + "');\">Record </a>");
	  
	    	if (record.getAudioItems() != null && !record.getAudioItems().isEmpty()){
	    		html.append("<span class=\"output_content\">" + renderSummary(record.getAudioItems()) + endSpanHtml);
	    	}
	    	
    		html.append(formMethodPostActionHtml + flowURL + "\" enctype=\"multipart/form-data\" >"); 
            html.append("<input type=\"file\" name=\"temprecording\"><br/>");
            html.append("<input type=\"submit\" name=\"_eventId_recorded\" value=\"Grabar\">");
             
            html.append(endFormHtml);

            return html.toString();
	}

    public String render(End end, String flowURL) {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("<h1>End</h1>");
    	
    	return sb.toString();
    }

	public String renderStartPage() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append("Aplicacion prototipo jVoice Framework");
		sb.append("</title>");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/HTMLRenderer.css\" />");
		sb.append("</head>");
		
		return sb.toString();
	}

	public String renderEndPage() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("</html>");
		
		return sb.toString();
	}

	public String render(BlindTransfer blindTx, String flowURL, String contextPath) {
        
    	// Identificador del elemento en la página
    	String identifier = UUID.randomUUID().toString();
    	StringBuilder html = new StringBuilder();
    	
    	// control para expandir información
    	html.append("<a onclick=\"javascript:toggle_visibility('" + identifier + "');\">Blind Transfer </a>");
        html.append( " Destination: " + blindTx.getDest());
    	
    	// Acceso rápido para hacer sumbit
    	html.append("<span style=\"display: inline-block;\">");
    	html.append(formMethodPostActionHtml + flowURL + quoteEndTagHtml); 
        html.append(" Event:");
        html.append("<select name=\"_eventId\">");
        html.append("<option value=\"transferred\"> Transferred </option>");
        html.append("<option value=\"error\"> Error </option>");
        html.append("<option value=\"unknown\"> Unknown </option>");
        html.append("</select>");
        html.append("<input type=\"submit\" id=\"inputSubmit\" value=\"Enter\" name=\"_eventId_transferred\">"); 
        html.append(endFormHtml);
        html.append(endSpanHtml);
    	html.append("<div id='" + identifier + "' style='display:none'>");  

    	html.append("</div>");
    	html.append("<br>");

        return html.toString();
	}

	public String render(ConsultationTransfer consultationTx, String flowURL, String contextPath) {
        String renderCode = "";
        
        renderCode += "<span>Transfer</span>";
        renderCode += "<span>dest: " + consultationTx.getDest() + endSpanBrHtml;
        renderCode += "<span>transferaudio: " + consultationTx.getTransferaudio() + endSpanBrHtml;
        renderCode += "<span>timeout: " + consultationTx.getTimeout() + endSpanBrHtml;
        
        Iterator itMap = consultationTx.getProperties().keySet().iterator();

        while (itMap.hasNext()){
            String property = (String) itMap.next();
            String value = consultationTx.getProperties().get(property);
            
            renderCode += "<span>Properties" + endSpanBrHtml;            
            renderCode += "<span>Property: "+property+endSpanBrHtml;            
            renderCode += "<span>Value: "+value+endSpanBrHtml;            
        }

        return renderCode;
	}

	public String render(BridgeTransfer bridgeTx, String flowURL, String contextPath) {
        String renderCode = "";
        
        renderCode += "<span>Transfer</span>";
        renderCode += "<span>dest: " + bridgeTx.getDest() + endSpanBrHtml;
        renderCode += "<span>transferaudio: " + bridgeTx.getTransferaudio() + endSpanBrHtml;
        renderCode += "<span>timeout: " + bridgeTx.getTimeout() + endSpanBrHtml;
        renderCode += "<span>maxtime: " + bridgeTx.getMaxtime() + endSpanBrHtml;
        
        Iterator itMap = bridgeTx.getProperties().keySet().iterator();

        while (itMap.hasNext()){
            String property = (String) itMap.next();
            String value = bridgeTx.getProperties().get(property);
            
            renderCode += "<span>Properties" + endSpanBrHtml;            
            renderCode += "<span>Property: "+property+endSpanBrHtml;            
            renderCode += "<span>Value: "+value+endSpanBrHtml;            
        }

        return renderCode;
	}

	public String renderEmptyPage(String flowURL) {
		String code = "";
		
		return code;
	}
}
