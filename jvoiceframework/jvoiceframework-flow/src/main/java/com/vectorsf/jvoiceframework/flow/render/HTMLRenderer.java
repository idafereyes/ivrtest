package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Iterator;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;

/**
 * Implementación de renderizador que genera código HTML
 * 
 * @author dmartina
 */
public class HTMLRenderer implements Serializable {

    private static final long serialVersionUID = 4511972601190155577L;
    
    //String literals reuse
    private String tableHtml = "<table cellpadding=\"0\" cellspacing=\"0\">";
    private String tdHtml = "<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">";
    private String endTdHtml = "</td>";
    private String endTableHtml = "</table><br/>";
    private String trStyledHtml = "<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\" >";
    private String endTrHtml = "</td></tr>";
    private String endSpanHtml = "</span><br/>";
    
    public String render(Input input, String flowURL) {
        StringBuilder html = new StringBuilder();

        html.append("<h1>Input</h1>");
        // Input name
        html.append("<p>Name = " + input.getName() + "</p>");
        
        html.append(renderInputParameters(input));
        
        html.append(renderInputGrammars(input));
        
        html.append(renderInputAudios(input));
        
        html.append(renderInputEvent(input));
        
        html.append(renderInputResults(flowURL));
        
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
    
    private String renderInputAudios(Input input) {
        StringBuilder html = new StringBuilder();
        //Audios
        html.append(tableHtml);
        html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Audios</b></td></tr>");
        html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Cond</td>");
        html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Src</td>");
        html.append("<td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">Wording</td></tr>");
        
        html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black; background-color: #7070FF; text-align: center;\"><b>Main</b></td></tr>");
        if(!input.getMainAudios().isEmpty()) {
            for(AudioItem ai : input.getMainAudios()) {
                html.append(trStyledHtml + ai.getCond() + endTdHtml);
                html.append(tdHtml + ai.getSrc() + endTdHtml);
                html.append(tdHtml + ai.getWording() + endTrHtml);
            }
        } else {
            html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>");
        }
        
        html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black; background-color: #7070FF; text-align: center;\"><b>No Match</b></td></tr>");
        if(!input.getNoMatchAudios().isEmpty()) {
            for(AudioItem ai : input.getNoMatchAudios()) {
                html.append(trStyledHtml + ai.getCond() + endTdHtml);
                html.append(tdHtml + ai.getSrc() + endTdHtml);
                html.append(tdHtml + ai.getWording() + endTrHtml);
            }
        } else {
            html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>");
        }
        
        html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black; background-color: #7070FF; text-align: center;\"><b>No Input</b></td></tr>");
        if(!input.getNoInputAudios().isEmpty()) {
            for(AudioItem ai : input.getNoInputAudios()) {
                html.append(trStyledHtml + ai.getCond() + endTdHtml);
                html.append(tdHtml + ai.getSrc() + endTdHtml);
                html.append(tdHtml + ai.getWording() + endTrHtml);
            }
        } else {
            html.append("<tr><td colspan=\"3\" style=\"padding: 0 10px 0 10px; border: solid 1px black;\">No audios</td></tr>");
        }
        
        html.append(endTableHtml);
        return html.toString();
    }
    
    private String renderInputEvent(Input input) {
        StringBuilder html = new StringBuilder();
        //Eventos
        html.append(tableHtml);
        html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\"><b>Events</b></td></tr>");
        for(String event: input.getEvents()) {
            html.append("<tr><td style=\"padding: 0 10px 0 10px; border: solid 1px black;\">" + event + endTrHtml);
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
    
    public String render(Prompt prompt, String flowURL){
        return "<span>" + prompt.getMessage() + endSpanHtml;
    }
    
    public String render(Output output, String flowURL){
        String renderCode = "";
        
        renderCode += "<span>bargein: " + output.isBargein() + endSpanHtml;
        renderCode += "<span>flush: " + output.isFlush() + endSpanHtml;
        renderCode += "<span>catchHangup: " + output.isCatchHangup() + endSpanHtml;
        
        Iterator<AudioItem> it = output.getAudioItemsList().iterator();
        while (it.hasNext()){
            AudioItem prompt = it.next();
            renderCode += "<span>prompt" + endSpanHtml;            
            renderCode += "<span>src: " + prompt.getSrc() + endSpanHtml;            
            renderCode += "<span>wording: " + prompt.getWording() + endSpanHtml;            
            renderCode += "<span>cond: " + prompt.getCond() + endSpanHtml;            
        }
        
        return renderCode;
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
            renderCode += "<span>cond: " + prompt.getCond() + endSpanHtml;            
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

    
}
