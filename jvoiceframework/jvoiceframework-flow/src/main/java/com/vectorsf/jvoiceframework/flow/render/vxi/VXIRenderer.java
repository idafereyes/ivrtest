package com.vectorsf.jvoiceframework.flow.render.vxi;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.BlindTransfer;
import com.vectorsf.jvoiceframework.core.bean.BridgeTransfer;
import com.vectorsf.jvoiceframework.core.bean.ConsultationTransfer;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.enums.InputVars;
import com.vectorsf.jvoiceframework.flow.render.AbstractRenderer;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

public class VXIRenderer extends AbstractRenderer implements Renderer, Serializable {

    private static final long serialVersionUID = -5854263044507481102L;
    
    static final String BLOCK_START_TAG = "<block>";
    static final String BLOCK_END_TAG = "</block>";
    static final String SAY_AS_START_TAG = "<say-as ";
    //TODO pendiente de revisión del funcionamiento del say-as en distintos motores TTS
    //Ver si añadir prefijo "vxml:"
    static final String INTERPRET_AS_ATTR = "interpret-as=\"";
    static final String SAY_AS_END_TAG = "</say-as>";
    static final String FORMAT_ATTR = "format=\"";
    static final String SUBMIT_TAG = "<submit next=\"";
    static final String AMPERSAND = "&amp;";
    static final String CATCH_END_TAG = "</catch>";
    static final String FILLED_START_TAG = "<filled>";
    static final String FILLED_END_TAG = "</filled>";
    static final String IF_END_TAG = "</if>";
    static final String PROMPT_START_TAG = "<prompt";
    static final String PROMPT_END_TAG = "</prompt>";
    static final String ASSIGN = "<assign name=\"";
    static final String PROMPT_COND = "<prompt cond=\"";
    static final String COND_ATTR = " cond=\"";
    static final String CLOSE_TAG = ">"; 
    static final String END_TAG = "/>"; 
    static final String QUOTE_SPACE = "\" ";
    static final String QUOTE = "\"";
    static final String QUOTE_END_TAG = "\" >";
    static final String QUOTE_EXPR = "\" expr=\"";
    static final String EVENT_ID = "_eventId_";
    static final String NAMELIST_DURATION = "namelist=\"duration\"";
    static final String SINGLE_QUOTE = "'";
    static final String AUDIO_START_TAG = "<audio ";
    static final String SRC_ATTRIBUTE_QUOTE = "src=\"";
    static final String LANG_ATTR = " xml:lang=\"";
    static final String VAR_NAME_TAG = "<var name=\"";
    static final String IF_COND_TAG = "<if cond=\"";
    static final String METHOD_POST_SPACE = "method=\"post\" ";

    // Grammar path values from configuration
    @Value("#{appConfigDefaults.grammarType}")
    private String grammarType;
    
    @Value("#{appConfigDefaults.grammarPath}")
    private String grammarPath;
    
    @Value("#{appConfigDefaults.grammarsFileExtension}")
    private String grammarsFileExtension;
        
	public String render(Output output, String flowURL) {
                
        StringBuilder code2render = new StringBuilder();
        
        //Renders properties if there are
        if (!output.getProperties().isEmpty()){
            code2render.append(renderProperties(output.getProperties()));            
        }
        
        code2render.append(BLOCK_START_TAG);
        
        //Renders audioItemsList
        code2render.append(renderOutputAudioItems(output, flowURL));

        code2render.append(BLOCK_END_TAG);

        if (output.isCatchHangup() || output.isFlush()){
            //Renders an VXML field dummy to be able to catch the hangup event or send the output immediately to the VXML interpreter
            code2render.append(renderOutputFieldDummy(output, flowURL));            
        }
            
        return code2render.toString();
    }    

    private String renderOutputAudioItems(Output output, String flowURL) {
        
        StringBuilder audioItemsCode = new StringBuilder();

        List<AudioItem> audioItemsList = output.getAudioItems();
        
        for (int i=0;i<audioItemsList.size();i++){
            
            audioItemsCode.append(PROMPT_START_TAG);
            
            //Adds bargeIn prompt attribute only if specified
            if (output.isBargein()){
                audioItemsCode.append(" bargein=\"true\"");
            }else{
                audioItemsCode.append(" bargein=\"false\"");
            }
            
            //Adds cond prompt attribute if specified
            if (audioItemsList.get(i).getCondition() != null){
                audioItemsCode.append(COND_ATTR + audioItemsList.get(i).getCondition() + QUOTE);
            }
            
            if(audioItemsList.get(i).getWording() != null
            		&& audioItemsList.get(i).getWording().getLocale() != null) {
            	audioItemsCode.append(LANG_ATTR);
            	audioItemsCode.append(audioItemsList.get(i).getWording().getLocale().toString().replaceAll("\\_", "-"));
            	audioItemsCode.append(QUOTE);
            }
            
            //Ends prompt start tag
            audioItemsCode.append(">");
            
            //Renders prompt tag body
            audioItemsCode.append(renderAudioItem(audioItemsList.get(i), flowURL));
            
            //Prompt end tag
            audioItemsCode.append(PROMPT_END_TAG);
        }
        
        return audioItemsCode.toString();
    }

    private String renderAudioItem(AudioItem audioItem, String flowURL) {
		
		String contextPath = getContextPath(flowURL);
    	StringBuilder audioItemCode = new StringBuilder();

        //TTS
    	if (audioItem.getSrc() == null){
    		//Adds say-as tag if specified.
        	if (audioItem.getWording().getSayAs() != null){
        		audioItemCode.append(SAY_AS_START_TAG + INTERPRET_AS_ATTR + audioItem.getWording().getSayAs().getInterpretAs().getName() + QUOTE_SPACE);
        		//With format specified
        		if (audioItem.getWording().getSayAs().getFormat() != null){
        			audioItemCode.append(FORMAT_ATTR + audioItem.getWording().getSayAs().getFormat() + QUOTE_SPACE);
        		}
        		audioItemCode.append(CLOSE_TAG);
                audioItemCode.append(audioItem.getWording().getText());
                audioItemCode.append(SAY_AS_END_TAG);
        	//Without say-as
        	} else {
                audioItemCode.append(audioItem.getWording().getText());            		
        	}
        
        //Audio without TTS backup prompt
        }else if (audioItem.getWording() == null || audioItem.getWording().getText() == null){
            audioItemCode.append(AUDIO_START_TAG + SRC_ATTRIBUTE_QUOTE + contextPath + "/" + audioItem.getSrc() + QUOTE + END_TAG);
        
        //Audio with TTS backup prompt
        }else{
            audioItemCode.append(AUDIO_START_TAG + SRC_ATTRIBUTE_QUOTE + contextPath + "/" + audioItem.getSrc() + QUOTE + CLOSE_TAG);
    		//Adds say-as tag if specified.
        	if (audioItem.getWording().getSayAs() != null){
        		audioItemCode.append(SAY_AS_START_TAG + INTERPRET_AS_ATTR + audioItem.getWording().getSayAs().getInterpretAs().getName() + QUOTE_SPACE);
        		//With format specified
        		if (audioItem.getWording().getSayAs().getFormat() != null){
        			audioItemCode.append(FORMAT_ATTR + audioItem.getWording().getSayAs().getFormat() + QUOTE_SPACE);
        		}
        		audioItemCode.append(CLOSE_TAG);
                audioItemCode.append(audioItem.getWording().getText());
                audioItemCode.append(SAY_AS_END_TAG);
            //Without say-as
        	} else {
                audioItemCode.append(audioItem.getWording().getText());            		
        	}
            audioItemCode.append("</audio>");
        }
        
        return audioItemCode.toString();
	}

	private String renderOutputCatchHangup(String flowURL) {

        StringBuilder catchHangupCode =  new StringBuilder();
        
        //Renders a catch tag for the hangup event and redirects back to the application server with hangup as _eventId param value. 
        catchHangupCode.append("<catch event=\"connection.disconnect.hangup\">");
        
        catchHangupCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Output.HANGUP_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        
        catchHangupCode.append(CATCH_END_TAG);

        return catchHangupCode.toString();
    }

    private String renderOutputFieldDummy(Output output, String flowURL) {

        StringBuilder fieldDummyCode = new StringBuilder();
        
        //Renders a dummy field. Needed to be able to catch a hangup event at an output and to play the prompts immediately
        fieldDummyCode.append("<field name=\"dummy\">");
        
        if (output.isCatchHangup()){
            //Renders a VXML catch tag for the hangup event
            fieldDummyCode.append(renderOutputCatchHangup(flowURL));
        }
        
        //Minimum timeout possible
        fieldDummyCode.append("<property name=\"timeout\" value=\"0.1s\" />");
        
        //Renders a grammar that no needs to interact with the recognizer
        fieldDummyCode.append("<grammar mode=\"dtmf\" src=\"builtin:dtmf/digits?minlength=1;maxlength=1\"/>");
        
        //Just in case it gets a match, but should never happen with this timeout.
        fieldDummyCode.append(FILLED_START_TAG);
        
        //redirects back to the application server with success as _eventId param value. 
        fieldDummyCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Output.SUCCESS_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        
        fieldDummyCode.append(FILLED_END_TAG);
        
        //Render a catch for NOINPUT(normally will always happen with this timeout) and NOMATCH(just in case) events.
        fieldDummyCode.append("<catch event=\"noinput nomatch\" >");
        
        //redirects back to the application server with success as _eventId param value. 
        fieldDummyCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Output.SUCCESS_EVENT + QUOTE_SPACE +  METHOD_POST_SPACE + END_TAG);

        fieldDummyCode.append(CATCH_END_TAG);

        fieldDummyCode.append("</field>");
        
        return fieldDummyCode.toString();
    }

    public String render(Input input, String flowURL) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(renderInputInitVar(input));
        sb.append(renderInputStartField(input));
        sb.append(renderInputProperties(input));
        sb.append(renderInputGrammars(input));
        sb.append(renderInputPrompts(input, flowURL));
        sb.append(renderInputCatches(input, flowURL));
        sb.append(renderInputFilled(input, flowURL));
        
        sb.append(renderInputEnd());
        
        return sb.toString();
    }

    private String renderInputInitVar(Input input) {
    	StringBuilder sb = new StringBuilder();
    	
    	//Renders properties if there are
        if (!input.getProperties().isEmpty()){
            sb.append(renderProperties(input.getProperties()));            
        }
        
    	sb.append(VAR_NAME_TAG + InputVars.ATTEMPTS.getName() + "\" expr=\"0\" />");
    	sb.append(VAR_NAME_TAG + InputVars.NOINPUTATTEMPTS.getName() + "\" expr=\"0\" />");
    	sb.append(VAR_NAME_TAG + InputVars.NOMATCHATTEMPTS.getName() + "\" expr=\"0\" />");
		
    	sb.append(VAR_NAME_TAG + InputVars.MAXNOMATCHATTEMPTS.getName() + QUOTE_EXPR + input.getMaxNoMatch() + QUOTE_SPACE + END_TAG);
    	sb.append(VAR_NAME_TAG + InputVars.MAXNOINPUTATTEMPTS.getName() + QUOTE_EXPR + input.getMaxNoInput() + QUOTE_SPACE + END_TAG);
    	sb.append(VAR_NAME_TAG + InputVars.MAXATTEMPTS.getName() + QUOTE_EXPR + input.getMaxAttempts() + QUOTE_SPACE + END_TAG);
		
		String recAvailable = getRecAvailable(input);
		sb.append(VAR_NAME_TAG + InputVars.RECAVAILABLE.getName() + "\" expr=\"'" + recAvailable + "'" + QUOTE_SPACE + END_TAG);
		sb.append(VAR_NAME_TAG + InputVars.RETURNCODE.getName() + "\" expr=\"''\" />");
		
    	return sb.toString();
    }
    
    private String renderInputStartField(Input input) {
    	StringBuilder sb = new StringBuilder();
    	
    	if(input.getName() != null) {
    		sb.append("<field name=\"" + input.getName() + QUOTE_END_TAG);
    	} else {
    		sb.append("<field>");
    	}
		
    	return sb.toString();
    }
    
    private String renderInputProperties(Input input) {
    	StringBuilder sb = new StringBuilder();
    	
		// TIMEOUT
    	if(input.getTimeout() != null ) {
    		sb.append("<property name=\"timeout\" value=\"" + input.getTimeout() + "\" />");
    	}
		
    	// INTERDIGITTIMEOUT
    	if(input.getInterdigittimeout() != null ) {
    		sb.append("<property name=\"interdigittimeout\" value=\"" + input.getInterdigittimeout() + "\" />");
    	}
    	
    	// CONFIDENCE
    	if(input.getConfidence() != null ) {
    		sb.append("<property name=\"confidence\" value=\"" + input.getConfidence() + "\" />");
    	}
    	
    	// BARGEIN
    	if(input.isBargein()) {
    		sb.append("<property name=\"bargein\" value=\"true\" />");
    	} else {
    		sb.append("<property name=\"bargein\" value=\"false\" />");
    	}
    	
		// INPUTMODES
		String recAvailable = getRecAvailable(input);
		if("ASR".equalsIgnoreCase(recAvailable) || "ASRDTMF".equalsIgnoreCase(recAvailable)) {
			sb.append("<property name=\"inputmodes\" value=\"voice\" />");
		}
		
    	return sb.toString();
    }
    
    private String renderInputEnd() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("</field>");
    	
    	return sb.toString();
    }
    
    private String renderInputGrammars(Input input) {
    	StringBuilder sb = new StringBuilder();
    	
    	for(Grammar grammar : input.getGrammars()) {
    		if("voice".equalsIgnoreCase(grammar.getMode())) {
    			sb.append("<grammar mode=\"voice\" ");
    			if(grammar.getSrc().trim().startsWith("builtin:")) {
    				sb.append("src=\"" + grammar.getSrc().trim() + "\"/>");
    			} else {
    				sb.append("type=\"" + grammarType + "\" ");
    				sb.append("src=\"" + grammarPath + grammar.getSrc() + grammarsFileExtension + "\"/>");
    			}
    		} else {
    			sb.append("<grammar mode=\"dtmf\" ");
    			if(grammar.getSrc().trim().startsWith("builtin:")) {
    				sb.append("src=\"" + grammar.getSrc().trim() + "\"/>");
    			} else {
    				sb.append("type=\"" + grammarType + "\" src=\"" + grammarPath + grammar.getSrc().trim() + grammarsFileExtension +"\"/>");
    			}
    		}
    	}
    	
    	return sb.toString();
    }
    
    private String renderInputPrompts(Input input, String flowURL) {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append(renderInputPromptsList(input.getNoMatchAudios(), "NOMATCH", flowURL));
    	sb.append(renderInputPromptsList(input.getNoInputAudios(), "NOINPUT", flowURL));
    	sb.append(renderInputPromptsList(input.getMainAudios(), null, flowURL));
		
		return sb.toString();
    }
    
    /**
     * Writes VXML code for a list of prompts associated with an event.
     * 
     * @param audioItems List of AudioItems to parse to VXML
     * @param event Event name (NOMATCH or NOINPUT) or null if no event 
     * 				is associated
     * @return VXML code.
     */
    private String renderInputPromptsList(List<AudioItem> audioItems, String event, String flowURL) {
    	StringBuilder sb = new StringBuilder();
    	
    	for(AudioItem ai : audioItems) {
    		// Start tag
			sb.append(PROMPT_START_TAG);
			
			// Conditions
			if(event != null) {
				if(ai.getCondition() != null && !ai.getCondition().isEmpty()) {
					sb.append(COND_ATTR + ai.getCondition() + " &amp;&amp; " + InputVars.RETURNCODE.getName() + " == '" + event + "'\"");
				} else {
					sb.append(COND_ATTR + InputVars.RETURNCODE.getName() + " == '" + event + "'\"");
				}
			} else {
				if(ai.getCondition() != null && !ai.getCondition().isEmpty()) {
					sb.append(COND_ATTR + ai.getCondition() + QUOTE_SPACE);
				}
			}
			
			// Wording language
			if(ai.getWording() != null && ai.getWording().getLocale() != null) {
				sb.append(LANG_ATTR);
				sb.append(ai.getWording().getLocale().toString().replaceAll("\\_", "-"));
				sb.append(QUOTE);
			}
			
			// Bargein
			sb.append(renderInputBargein(ai.isBargein()));
			
			// End tag
			sb.append(">");
			
			// Body tag
			sb.append(renderInputAudios(ai, flowURL));
			
			// Close tag
			sb.append(PROMPT_END_TAG);
		}
    	
    	return sb.toString();
    }
    
    private String renderInputBargein(Boolean bargein) {
    	if(bargein != null) {
	    	if(bargein) {
				return " bargein=\"true\"";
			} else {
				return " bargein=\"false\"";
			}
    	}
    	return "";
    }
    
	private String renderInputAudios(AudioItem ai, String flowURL) {
		String contextPath = getContextPath(flowURL);
		
		StringBuilder sb = new StringBuilder();
		
		//TTS
		if (ai.getSrc() == null || ai.getSrc().isEmpty()){
			//TODO Añadir logs
    		//Adds say-as tag if specified.
        	if (ai.getWording().getSayAs() != null){
        		sb.append(SAY_AS_START_TAG + INTERPRET_AS_ATTR + ai.getWording().getSayAs().getInterpretAs().getName() + QUOTE_SPACE);
        		//With format
        		if (ai.getWording().getSayAs().getFormat() != null){
        			sb.append(FORMAT_ATTR + ai.getWording().getSayAs().getFormat() + QUOTE_SPACE);
        		}
        		sb.append(CLOSE_TAG);
                sb.append(ai.getWording().getText());
                sb.append(SAY_AS_END_TAG);
        	//Without say-as
        	} else {
                sb.append(ai.getWording().getText());        		
        	}
			
        //Audio without TTS backup prompt
		} else if (ai.getWording() == null || ai.getWording().getText().isEmpty()){
			//TODO Añadir logs
			sb.append("<audio src=\"" + contextPath + ai.getSrc() + QUOTE_SPACE + END_TAG);

		//Audio with TTS backup prompt
		} else{
			//TODO Meter logs
			sb.append("<audio src=\"" + contextPath + ai.getSrc() + QUOTE_END_TAG);
    		//Adds say-as tag if specified.
        	if (ai.getWording().getSayAs() != null){
        		sb.append(SAY_AS_START_TAG + INTERPRET_AS_ATTR + ai.getWording().getSayAs().getInterpretAs().getName() + QUOTE_SPACE);
        		//With format
        		if (ai.getWording().getSayAs().getFormat() != null){
        			sb.append(FORMAT_ATTR + ai.getWording().getSayAs().getFormat() + QUOTE_SPACE);
        		}
        		sb.append(CLOSE_TAG);
                sb.append(ai.getWording().getText());
                sb.append(SAY_AS_END_TAG);
            //Without say-as
        	} else {
                sb.append(ai.getWording().getText());        		
        	}
			sb.append("</audio>");
		}
		return sb.toString();
	}
	
	private String renderInputCatches(Input input, String flowURL) {
		StringBuilder sb = new StringBuilder();
		
		List<String> customEvents = input.getCustomEvents();
		
		// escribimos los customEvents
		for(String customEvent : customEvents) {
			if(customEvent == null) {
				continue;
			}
			
			sb.append("<catch event=\"" + customEvent + QUOTE_END_TAG);
			//TODO Añadir trazas
			sb.append(renderInputSubmit(flowURL, customEvent));
			sb.append(CATCH_END_TAG);
		}

		sb.append(renderInputCatchNoMatch(flowURL));
		sb.append(renderInputCatchNoInput(flowURL));
		sb.append(renderInputOtherCatches(flowURL));
				
		return sb.toString();
	}

	private String renderInputOtherCatches(String flowURL) {
        
		StringBuilder sb = new StringBuilder();
        
        /**************** CATCH for connection.disconnect.hangup *************************/            
        sb.append("<catch event=\"connection.disconnect.hangup\">");
		sb.append(renderInputSubmit(flowURL, Input.HANGUP_EVENT));
        sb.append(CATCH_END_TAG);
 
        /**************** CATCH for error.noresource *************************/            
        /*****It must be above error catch. Otherwise, it would be overridden by error catch ******/          
        sb.append("<catch event=\"error.noresource\">");
		sb.append(renderInputSubmit(flowURL, Input.NORESOURCE_EVENT));
        sb.append(CATCH_END_TAG);
        
        /**************************** CATCH for error ********************************/
        /*****It must be at the bottom. Otherwise, it would override other catches******/
        sb.append("<catch event=\"error\">");
		sb.append(renderInputSubmit(flowURL, Input.ERROR_EVENT));
        sb.append(CATCH_END_TAG);


        return sb.toString();
	}

	private String renderInputCatchNoInput(String flowURL) {
		StringBuilder sb = new StringBuilder();
		//escribimos el catch no input
		sb.append("<catch event=\"noinput\" >");
		sb.append(ASSIGN + InputVars.NOINPUTATTEMPTS.getName() + QUOTE_EXPR + InputVars.NOINPUTATTEMPTS.getName() + " + 1\" />");
		sb.append(ASSIGN + InputVars.ATTEMPTS.getName() + QUOTE_EXPR + InputVars.NOMATCHATTEMPTS.getName() + " + " + InputVars.NOINPUTATTEMPTS.getName() + QUOTE_SPACE + END_TAG);
		sb.append(ASSIGN + InputVars.RETURNCODE.getName() + "\" expr=\"'NOINPUT'\" />");
		
		/*************** MAXATTEMPTS ***************/
		sb.append(IF_COND_TAG + InputVars.ATTEMPTS.getName() + " &gt;= " + InputVars.MAXATTEMPTS.getName() + QUOTE_END_TAG);
		//escribimos la traza del MAXINT
		//TODO Escribir trazas
		sb.append(renderInputSubmit(flowURL, Input.MAXATTEMPTS_EVENT));
		sb.append(IF_END_TAG);

		/*************** MAXNOINPUT ***************/
		sb.append(IF_COND_TAG + InputVars.NOINPUTATTEMPTS.getName() + " == " + InputVars.MAXNOINPUTATTEMPTS.getName() + QUOTE_END_TAG);
		//escribimos la traza del MAXNOINPUT
		//TODO Escribir trazas
		sb.append(renderInputSubmit(flowURL, Input.MAXNOINPUT_EVENT));
		sb.append(IF_END_TAG);
		
		//escribimos la traza del NOINPUT
		//TODO Escribir trazas
		sb.append("<reprompt />");
		sb.append(CATCH_END_TAG);
		
		return sb.toString();
	}

	private String renderInputCatchNoMatch(String flowURL) {
		StringBuilder sb = new StringBuilder();
		
		//escribimos el catch del no match
		sb.append("<catch event=\"nomatch\" >");
		sb.append(ASSIGN + "" + InputVars.NOMATCHATTEMPTS.getName() + QUOTE_EXPR + InputVars.NOMATCHATTEMPTS.getName() + " + 1\" />");
		sb.append(ASSIGN + InputVars.ATTEMPTS.getName() + QUOTE_EXPR + InputVars.NOMATCHATTEMPTS.getName() + " + " + InputVars.NOINPUTATTEMPTS.getName() + QUOTE_SPACE + END_TAG);
		sb.append(ASSIGN + InputVars.RETURNCODE.getName() + "\" expr=\"'NOMATCH'\" />");
		
		/*************** MAXATTEMPTS ***************/
		sb.append(IF_COND_TAG + InputVars.ATTEMPTS.getName() + " &gt;= " + InputVars.MAXATTEMPTS.getName() + QUOTE_END_TAG);
		//escribimos la traza del MAXATTEMPTS
		//TODO Hacer trazas
		sb.append(renderInputSubmit(flowURL, Input.MAXATTEMPTS_EVENT));
		sb.append(IF_END_TAG);

		/*************** MAXNOMATCH ***************/
		sb.append(IF_COND_TAG + InputVars.NOMATCHATTEMPTS.getName() + " == " + InputVars.MAXNOMATCHATTEMPTS.getName() + "\" >");
		//escribimos la traza del MAXNOMATCH
		//TODO Hacer trazas
		sb.append(renderInputSubmit(flowURL, Input.MAXNOMATCH_EVENT));
		sb.append(IF_END_TAG);
		
		//escribimos la traza del NOMATCH
		//TODO Hacer trazas
		sb.append("<reprompt />");
		sb.append(CATCH_END_TAG);
		
		return sb.toString();
	}
	
	private String renderInputFilled(Input input, String flowURL) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(FILLED_START_TAG);
		//TODO Añadir log
		
		//escribimos los datos del resultado
		sb.append("<var name=\"interpretation\" />");
		sb.append("<var name=\"utterance\" expr=\"application.lastresult$.utterance\" />");
		sb.append("<var name=\"inputmode\" expr=\"application.lastresult$.inputmode\" />");
		sb.append("<var name=\"confidence\" expr=\"application.lastresult$.confidence\" />");
		sb.append("<script>");
		sb.append("<![CDATA[");
		sb.append("if(inputmode == 'voice') {");
		sb.append("interpretation = lastresult$.interpretation.out;");
		sb.append("} else {");
		sb.append("interpretation = utterance;");
		sb.append("}");
		sb.append("]]>");
		sb.append("</script>");
		//TODO Mirar si devuelve los valores correctos
		
		//Match Audios
    	sb.append(renderInputPromptsList(input.getMatchAudios(), null, flowURL));

		sb.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Input.MATCH_EVENT + QUOTE_SPACE +  METHOD_POST_SPACE + "namelist=\"interpretation utterance inputmode confidence\" />");

		sb.append(FILLED_END_TAG);
		
		return sb.toString();
	}
	
	private String renderInputSubmit(String url, String event) {
		StringBuilder sb = new StringBuilder();
			
		//Different information to be stored at componentId variable depending on the parent
		sb.append("<var name=\"interpretation\" expr=\"null\" />");
		sb.append("<var name=\"utterance\" expr=\"null\" />");
		sb.append("<var name=\"inputmode\" expr=\"null\" />");
		sb.append("<var name=\"confidence\" expr=\"null\" />");
		
		sb.append(SUBMIT_TAG + url + AMPERSAND + EVENT_ID + event + QUOTE_SPACE +  METHOD_POST_SPACE + "namelist=\"interpretation utterance inputmode confidence\" />");
		
		return sb.toString();
	}
	
    private String getRecAvailable(Input input) {
		boolean isAsr = false;
		boolean isDtmf = false;
		
		for(Grammar grammar : input.getGrammars()) {
			if(grammar.getMode().trim().equalsIgnoreCase("voice")) {
				isAsr = true;
			} else if (grammar.getMode().trim().equalsIgnoreCase("dtmf")) {
				isDtmf = true;
			}
		}
		
		return (isAsr && isDtmf) ? "ASRDTMF" : ( isAsr ? "ASR" : "DTMF");
	}
    
	public String render(BlindTransfer blindTx, String flowURL) {
        StringBuilder code2render = new StringBuilder();

        //Transfer start tag
        code2render.append("<transfer name=\"transferResult\" ");
        
        //Renders transfer attributes        
        code2render.append(renderBlindTxAttributes(blindTx));

        //Ends transfer start tag
        code2render.append(">");
                
        //Renders properties if there are
        if (!blindTx.getProperties().isEmpty()){
            code2render.append(renderProperties(blindTx.getProperties()));            
        }
        
        //Renders events catch
        code2render.append(renderTransferredCatch(flowURL));
        code2render.append(renderCommonTxEvents(blindTx, flowURL));
        //Renders a filled tag to catch the events that are known because of the content of the transfer variable.
        code2render.append(renderFilledTxEvents(flowURL));
        
        //Transfer end tag
        code2render.append("</transfer>");

        return code2render.toString();
	}


	private StringBuilder renderCommonTxAttributes(Transfer transfer) {
        
		StringBuilder sb = new StringBuilder();

    	//Required attribute
    	sb.append("dest=\"" + transfer.getDest() + QUOTE_SPACE);
        
        //Sets "transferaudio" optional attribute. 
        if (transfer.getTransferaudio() != null){
        	sb.append("transferaudio=\"" + transfer.getTransferaudio()  + QUOTE_SPACE);           
        }

        return sb;
	}
	
	private StringBuilder renderBlindTxAttributes(BlindTransfer blindTx) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("type=\"blind" + QUOTE_SPACE);
		sb.append(renderCommonTxAttributes(blindTx));
		
		return sb;
	}


	public String render(ConsultationTransfer consultationTx, String flowURL) {
        StringBuilder code2render = new StringBuilder();

        //Transfer start tag
        code2render.append("<transfer name=\"transferResult\" ");
        
        //Renders transfer attributes        
        code2render.append(renderConTxAttributes(consultationTx));

        //Ends transfer start tag
        code2render.append(">");
                
        //Renders properties if there are
        if (!consultationTx.getProperties().isEmpty()){
            code2render.append(renderProperties(consultationTx.getProperties()));            
        }
        
        //Renders events catch
        code2render.append(renderTransferredCatch(flowURL));
        code2render.append(renderCommonTxEvents(consultationTx, flowURL));
        //Renders a filled tag to catch the events that are known because of the content of the transfer variable.
        code2render.append(renderFilledTxEvents(flowURL));
        
        //Transfer end tag
        code2render.append("</transfer>");

        return code2render.toString();
	}

	private StringBuilder renderConTxAttributes(ConsultationTransfer consultationTx) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("type=\"consultation" + QUOTE_SPACE);
		sb.append(renderCommonTxAttributes(consultationTx));
        //Optional attributes
        //Sets "timeout" optional attribute.
        if (consultationTx.getTimeout() != null){
        	sb.append("connecttimeout=\"" + consultationTx.getTimeout()  + QUOTE_SPACE);           
        }
		
		return sb;
	}

	public String render(BridgeTransfer bridgeTx, String flowURL) {
        StringBuilder code2render = new StringBuilder();

        //Transfer start tag
        code2render.append("<transfer name=\"transferResult\" ");
        
        //Renders transfer attributes        
        code2render.append(renderBridgeTxAttributes(bridgeTx));

        //Ends transfer start tag
        code2render.append(">");
                
        //Renders properties if there are
        if (!bridgeTx.getProperties().isEmpty()){
            code2render.append(renderProperties(bridgeTx.getProperties()));            
        }
        
        //Renders events catch
        code2render.append(renderCommonTxEvents(bridgeTx, flowURL));
        //Renders a filled tag to catch the events that are known because of the content of the transfer variable.
        code2render.append(renderFilledTxEvents(flowURL));
        
        //Transfer end tag
        code2render.append("</transfer>");

        return code2render.toString();
	}
	
    private StringBuilder renderBridgeTxAttributes(BridgeTransfer bridgeTx) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("type=\"bridge" + QUOTE_SPACE);
		sb.append(renderCommonTxAttributes(bridgeTx));
       
		//Optional attributes
        //Sets "timeout" optional attribute.
        if (bridgeTx.getTimeout() != null){
        	sb.append("connecttimeout=\"" + bridgeTx.getTimeout()  + QUOTE_SPACE);           
        }

        //Sets "maxtime" optional attribute.
        if (bridgeTx.getMaxtime() != null){
        	sb.append("maxtime=\"" + bridgeTx.getMaxtime()  + QUOTE_SPACE);           
        }

		return sb;
	}

	private StringBuilder renderCommonTxEvents(Transfer transfer,  String flowURL) {
		
        StringBuilder eventsListCode = new StringBuilder();
        
        List<String> customEvents = transfer.getCustomEvents();

        //Renders catch tags for the specified events
        eventsListCode.append(renderCommonTxCatches(flowURL, customEvents));
                        
        return eventsListCode;
    }

	private StringBuilder renderTransferredCatch(String flowURL){

        StringBuilder catchEventsCode = new StringBuilder();
        
        /********* CATCH for connection.disconnect.transfer *********/
        /*********       N/A for bridge transfer type    *********/
 
    	catchEventsCode.append("<catch event=\"connection.disconnect.transfer\">");
        //Redirects back to the application server with transferred as _eventId param value. event and duration also as request parameters.          
        catchEventsCode.append("<var name=\"duration\" expr=\"transferResult$.duration\" />");
        catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + BlindTransfer.TRANSFERRED_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + NAMELIST_DURATION + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);
        
        return catchEventsCode;
	}
	
    private StringBuilder renderCommonTxCatches(String flowURL, List<String> customEvents) {

        StringBuilder catchEventsCode = new StringBuilder();
        
        /**************** CATCH for connection.disconnect.hangup *************************/            
        catchEventsCode.append("<catch event=\"connection.disconnect.hangup\">");
        //Redirects back to the application server with hangup as _eventId param value. event and duration also as request parameters.          
        catchEventsCode.append("<var name=\"duration\" expr=\"transferResult$.duration\" />");
        catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Transfer.HANGUP_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + NAMELIST_DURATION + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);

        /*************************** CATCH for Custom Events *****************************/
        /***************** Custom events specified at the list of events to be controlled **************************/
        /*********It must be before error or error.connection catch. Otherwise, it would be overridden by them.**********/
        if (customEvents != null && !customEvents.isEmpty()){
            
            for(String event : customEvents) {
                catchEventsCode.append("<catch event=\""+ event +"\">");
                //Redirects back to the application server with the name of the custome event as _eventId param value. event also as request parameter.          
                catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + event + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
                catchEventsCode.append(CATCH_END_TAG);
            }
            
        }        
        
        /**************************** CATCH for error.connection ********************************/
        /*****It must be above error catch. Otherwise, it would be overridden by error catch ******/          
        catchEventsCode.append("<catch event=\"error.connection\">");
        //Redirects back to the application server with connectionerror as _eventId param value. event also as request parameter.          
        catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Transfer.CONNECTIONERROR_EVENT+ QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);
        
        /**************************** CATCH for error ********************************/
        /*****It must be at the bottom. Otherwise, it would override other catches******/
        catchEventsCode.append("<catch event=\"error\">");
        //Redirects back to the application server with error as _eventId param value. event also as request parameter.          
        catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Transfer.ERROR_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);            
        
        return catchEventsCode;
    }

    private StringBuilder renderFilledTxEvents(String flowURL) {
        
        StringBuilder filledEventsCode = new StringBuilder();

        //Events filled at the transfer Javascript variable
        filledEventsCode.append(FILLED_START_TAG);    
        //Redirects back to the application server with the value of the transfer javascript variable as _eventId param value. duration also as request parameter.          
        filledEventsCode.append("<var name=\"url\" expr=\"'"+ flowURL +"'+'" + AMPERSAND +"' + '"+ EVENT_ID +"' + transferResult\" "+ END_TAG);
        filledEventsCode.append("<var name=\"duration\" expr=\"transferResult$.duration\" />");
        filledEventsCode.append("<submit expr=\"url\" " + METHOD_POST_SPACE + "namelist=\"duration\" />");        
        filledEventsCode.append(FILLED_END_TAG);
        
        return filledEventsCode;
    }

    public String render(Record record, String flowURL) {
        StringBuilder code2render = new StringBuilder();
        
        //Record start tag and type attribute which is fixed by the framework, not the developer
        code2render.append("<record type=\"audio/x-wav\" ");
        
        //Renders record attributes
        code2render.append(renderRecordAttributes(record));
        
        //Ends record start tag
        code2render.append(">");
        
        //Renders record audio items
        List<AudioItem> audioItems = record.getAudioItems();
        code2render.append(renderRecordAudioItems(audioItems, flowURL));
        
        //Renders record properties, if there are
        if (!record.getProperties().isEmpty()){
            code2render.append(renderProperties(record.getProperties()));        	
        }
        
        //Render catches for defined events
        code2render.append(renderRecordEventsList(record, flowURL));
        
        //Record end tag
        code2render.append("</record>");
        
        return code2render.toString();
    }

    private StringBuilder renderRecordEventsList(Record record, String flowURL) {
                
        StringBuilder eventsListCode = new StringBuilder();
               
        List<String> customEvents = record.getCustomEvents();

        //Renders VXML catches for the defined events.
        eventsListCode.append(renderRecordCatchEvents(flowURL, customEvents));
        
        //Renders VXML <filled> for the recorded event.
        eventsListCode.append(renderRecordFilledEvent(flowURL));
        
        return eventsListCode;
    }

	private StringBuilder renderRecordFilledEvent(String flowURL) {
		
		StringBuilder filledEventCode = new StringBuilder();
		
		filledEventCode.append(FILLED_START_TAG);
		//Takes values from record result.
		filledEventCode.append("<var name=\"duration\" expr=\"temprecording$.duration\" />");
		filledEventCode.append("<var name=\"size\" expr=\"temprecording$.size\" />");
		filledEventCode.append("<var name=\"termchar\" expr=\"temprecording$.termchar\" />");
		filledEventCode.append("<var name=\"maxtime\" expr=\"temprecording$.maxtime\" />");
		
		//Redirects back to the application server with recorded as _eventId param value.
		//Other request parameters are: temprecording, duration, size, termchar and maxtime.
		//method must be "post" and enctype must be "multipart/form-data" because the recording is being sent at the request.
		filledEventCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Record.RECORDED_EVENT + QUOTE_SPACE + "namelist=\"temprecording duration size termchar maxtime\" method=\"post\" enctype=\"multipart/form-data\" " + END_TAG);

		filledEventCode.append(FILLED_END_TAG);
		
		
		return filledEventCode;
	}

	private StringBuilder renderRecordCatchEvents(String flowURL, List<String> customEvents) {
		
		StringBuilder catchEventsCode = new StringBuilder();
		
        /**************** CATCH for connection.disconnect.hangup *************************/            
    	catchEventsCode.append("<catch event=\"connection.disconnect.hangup\">");
        //Redirects back to the application server with hangup as _eventId param value.          
        catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Record.HANGUP_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);			

        /*************************** CATCH for Custom Events *****************************/
        /***************** Custom events specified at the list of events to be controlled **************************/
        /*********It must be before error or error.connection catch. Otherwise, it would be overridden by them.**********/
		if (customEvents != null && !customEvents.isEmpty()){
            
            for(String event : customEvents) {
                catchEventsCode.append("<catch event=\""+ event +"\">");
                //Redirects back to the application server with the name of the custom event as _eventId param value.         
                catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + event + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
                catchEventsCode.append(CATCH_END_TAG);
            }
           
        }        	

        /**************************** CATCH for error.noresource ********************************/
        /*****It must be above error catch. Otherwise, it would be overridden by error catch ******/          
    	catchEventsCode.append("<catch event=\"error.noresource\">");
        //Redirects back to the application server with noresource as _eventId param value.
    	catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Record.NORESOURCE_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);			

        /**************************** CATCH for error.unsupported.record ********************************/
        /*****It must be above error catch. Otherwise, it would be overridden by error catch ******/          
    	catchEventsCode.append("<catch event=\"error.unsupported.record\">");
        //Redirects back to the application server with recordunsupported as _eventId param value.
    	catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Record.RECORDUNSUPPORTED_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);			

        /**************************** CATCH for error ********************************/
        /*****It must be at the bottom. Otherwise, it would override other catches******/
    	catchEventsCode.append("<catch event=\"error\">");
        //Redirects back to the application server with error as _eventId param value.
    	catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + Record.ERROR_EVENT + QUOTE_SPACE + METHOD_POST_SPACE + END_TAG);
        catchEventsCode.append(CATCH_END_TAG);			

        return catchEventsCode;
	}

	private StringBuilder renderProperties(Map<String, String> properties) {
        StringBuilder propsCode = new StringBuilder();
        
        Iterator<Entry<String, String>> it = properties.entrySet().iterator();
        
        //Renders each property
        while (it.hasNext()){
            Entry<String, String> pair = it.next();           
            propsCode.append("<property name=\""+ pair.getKey() + "\" value=\"" + pair.getValue() + QUOTE_SPACE + END_TAG);
        }
        
        return propsCode;
	}

	private StringBuilder renderRecordAudioItems(List<AudioItem> audioItems, String flowURL) {
        
    	StringBuilder audioItemsCode = new StringBuilder();
        
        for (int i=0;i<audioItems.size();i++){
            
            audioItemsCode.append(PROMPT_START_TAG);
                        
            //Adds cond prompt attribute if specified
            if (audioItems.get(i).getCondition() != null){
                audioItemsCode.append(COND_ATTR + audioItems.get(i).getCondition() + QUOTE);
            }
            
            if (audioItems.get(i).getWording() != null && audioItems.get(i).getWording().getLocale() != null){
                audioItemsCode.append(LANG_ATTR + audioItems.get(i).getWording().getLocale().toString().replaceAll("\\_", "-") + QUOTE);
            }
            
            //Ends prompt start tag
            audioItemsCode.append(">");
            
            //Renders prompt tag body
            audioItemsCode.append(renderAudioItem(audioItems.get(i), flowURL));
                        
            //Prompt end tag
            audioItemsCode.append(PROMPT_END_TAG);
        }
        
        return audioItemsCode;
	}

	private StringBuilder renderRecordAttributes(Record record) {
    	
    	StringBuilder attributesCode = new StringBuilder();
    	
		boolean beep = record.isBeep();
		boolean dtmfterm = record.isDtmfterm();
		String finalsilence = record.getFinalsilence();
		String maxtime = record.getMaxtime();
		
		//TODO Si cambiamos los atributos a Boolean, comprobar siempre que no sean null los atributos por si acaso.
		attributesCode.append("name=\"" + "temprecording" + QUOTE_SPACE);
		attributesCode.append("beep=\"" + beep + QUOTE_SPACE);
		attributesCode.append("dtmfterm=\"" + dtmfterm + QUOTE_SPACE);
		attributesCode.append("finalsilence=\"" + finalsilence + QUOTE_SPACE);
		attributesCode.append("maxtime=\"" + maxtime + QUOTE_SPACE);

		return attributesCode;
	}

	public String render(End end, String flowURL) {

		StringBuilder endCode = new StringBuilder();
		
		endCode.append("<block>");
		endCode.append("<exit/>");
		endCode.append("</block>");
		
    	return endCode.toString();
    }

	public String renderStartPage() {

		StringBuilder startPageCode = new StringBuilder();
		
		startPageCode.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		startPageCode.append("<vxml ");

		//TODO Versión en una constante o por configuración?
		startPageCode.append("version=\""+ "2.1" + QUOTE_SPACE);

		//TODO Namespace en una constante o por configuración?
		startPageCode.append("xmlns=\""+ "http://www.w3.org/2001/vxml" + QUOTE_SPACE);

		//TODO Con esto del locutionProvider, qué lenguaje ponemos? Podemos hacer un getLocale()?
		startPageCode.append("xml:lang=\""+ "es-ES" + QUOTE_SPACE);
		
		//TODO Añadir el atributo application? (en el que se hace referencia a la página root.

		startPageCode.append(CLOSE_TAG);
		
		//Forces VXML interpreter to not cache VXML pages
		//TODO dejar esto?
		startPageCode.append("<meta http-equiv=\"Expires\" content=\"0\"/>");
		
		startPageCode.append("<form>");
		
		return startPageCode.toString();
	}

	public String renderEndPage() {
		
		StringBuilder endPageCode = new StringBuilder();
		
		endPageCode.append("</form>");

		endPageCode.append("</vxml>");

		return endPageCode.toString();
	}

	public String renderEmptyPage(String flowURL) {
		StringBuilder emptyPageCode = new StringBuilder();
		emptyPageCode.append(renderStartPage());
		emptyPageCode.append("<block>");
		emptyPageCode.append("<submit next=\"" + flowURL + AMPERSAND + "_eventId_success\" " + METHOD_POST_SPACE + "/>");
		emptyPageCode.append("</block>");
		emptyPageCode.append(renderEndPage());
		return emptyPageCode.toString();
	}
	
	public String getContextPath(String flowURL) {
		String newflowURL = flowURL.trim();
		if(newflowURL.isEmpty() || newflowURL.equals("/") || newflowURL.equals("//")) {
			return "";
		}
		if(newflowURL.startsWith("http://")) {
			return getContextPathFromAbsoluteUrl(newflowURL);
		} else {
			return getContextPathFromRelativeUrl(newflowURL);
		}
	}

	private String getContextPathFromRelativeUrl(String newflowURL) {
		//position of first slash
		int s = newflowURL.indexOf('/');
		//position of second slash
		int e = 0;
		if(s != -1) {
			e = newflowURL.indexOf('/', s + 1);
		} else {
			e = -1;
		}
		
		String ctx = "";
		if(s == 0 && e == -1) {
			ctx = newflowURL.substring(0, newflowURL.length());
		} else if(s == 0 && e != -1) {
			ctx = newflowURL.substring(0, e);
		} else if( s == -1 && e == -1) {
			ctx = "/" + newflowURL;
		} else {
			ctx = "/" + newflowURL.substring(0, s);
		}
		return ctx;
	}

	private String getContextPathFromAbsoluteUrl(String newflowURL) {
		int s = newflowURL.indexOf("//");
		if(s == -1) {
			return "";
		}
		s = newflowURL.indexOf('/', s + 2);
		if(s == -1) {
			return "";
		}
		int e = newflowURL.indexOf('/', s + 1);
		if(e == -1) {
			return newflowURL.substring(s);
		}
		return newflowURL.substring(s, e);
	}
	
}

