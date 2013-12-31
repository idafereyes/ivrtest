package com.vectorsf.jvoiceframework.isban.logger.render.vxi;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.enums.InputVars;
import com.vectorsf.jvoiceframework.flow.render.vxi.VXIRenderer;
import com.vectorsf.jvoiceframework.isban.logger.enums.PromptType;

public class IsbanLoggerRenderer extends VXIRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String COMMA_QUOTED = "', '";
	protected static final String REC_PARAMS_DELIMITER = ";";

	
	@Override
	protected StringBuilder renderLoggerVarsDeclaration(Input input, String contextPath) {
    	
    	StringBuilder sb = new StringBuilder();
    	
		sb.append(VAR_NAME_TAG + LoggerVars.INPUT_INFO.getName() + "\" expr=\"''" + QUOTE_SPACE + END_TAG);
    	
		String recAvailable = getRecAvailable(input);
		
		sb.append(VAR_NAME_TAG + LoggerVars.DIALOGUEID.getName() + "\" expr=\"'" + input.getName() + "'" + QUOTE_SPACE + END_TAG);
		sb.append(VAR_NAME_TAG + LoggerVars.RECAVAILABLE.getName() + "\" expr=\"'" + recAvailable + "'" + QUOTE_SPACE + END_TAG);
		sb.append(VAR_NAME_TAG + LoggerVars.RECPARAMS.getName() + "\" expr=\"'timeout=" + input.getTimeout() + REC_PARAMS_DELIMITER);
		sb.append("interdigittimeout=" + input.getInterdigittimeout() + REC_PARAMS_DELIMITER);
		sb.append("confidencelevel=" + input.getConfidence() + REC_PARAMS_DELIMITER);
		sb.append("sensitivity=" + input.getSensitivity() + REC_PARAMS_DELIMITER);
		sb.append("speedvsaccuracy=" + input.getSpeedvsaccuracy() + REC_PARAMS_DELIMITER);
		sb.append("maxspeechtimeout=" + input.getMaxspeechtimeout() + REC_PARAMS_DELIMITER);
		sb.append("incompletetimeout=" + input.getIncompletetimeout() + REC_PARAMS_DELIMITER);
		sb.append("completetimeout=" + input.getCompletetimeout() + REC_PARAMS_DELIMITER);
		sb.append("'\" />");
		
		sb.append(VAR_NAME_TAG + LoggerVars.RECDETECTED.getName() + "\" expr=\"''\" />");
		sb.append(VAR_NAME_TAG + LoggerVars.USERINPUT.getName() + "\" expr=\"''\" />");

		sb.append("<script charset=\"UTF-8\" src=\"" + contextPath + "/resources/logger.js\"/>");

		return sb;
	}
	
	@Override
	protected StringBuilder renderLoggerVarsAssignment(String event){
    	
		StringBuilder sb = new StringBuilder();
		
		if (event.equalsIgnoreCase(Input.MATCH_EVENT)){
			sb.append(ASSIGN + LoggerVars.RECDETECTED.getName() + "\" expr=\"inputmode\" />");
			sb.append(ASSIGN + InputVars.ATTEMPTS.getName() + "\" expr=\"" + InputVars.ATTEMPTS.getName()  +" + 1 \" />");			
			sb.append(ASSIGN + InputVars.RETURNCODE.getName() + "\" expr=\"'MATCH'\" />");
			sb.append(ASSIGN + LoggerVars.USERINPUT.getName() + "\" expr=\"interpretation\" />");
			sb.append(ASSIGN + LoggerVars.RECPARAMS.getName() + "\" expr=\"" + LoggerVars.RECPARAMS.getName()  +" + 'confidence=' + confidence \" />");			
		} else if (event.equalsIgnoreCase(Input.HANGUP_EVENT)){
			sb.append(ASSIGN + LoggerVars.RECDETECTED.getName() + "\" expr=\"'NONE'\" />");
			sb.append(ASSIGN + InputVars.ATTEMPTS.getName() + "\" expr=\"" + InputVars.ATTEMPTS.getName()  +" + 1 \" />");
			sb.append(ASSIGN + InputVars.RETURNCODE.getName() + "\" expr=\"'HANGUP'\" />");
		} else {
			sb.append(ASSIGN + LoggerVars.RECDETECTED.getName() + "\" expr=\"'NONE'\" />");
			sb.append(ASSIGN + LoggerVars.USERINPUT.getName() + "\" expr=\"''\" />");			
		}
		
		sb.append("<script>");
		sb.append("<![CDATA[");
		sb.append("insertDialogueTrace("+ 
				LoggerVars.DIALOGUEID.getName() + ", " +
				LoggerVars.RECAVAILABLE.getName() + ", " +
				LoggerVars.RECDETECTED.getName() + ", " +
				InputVars.ATTEMPTS.getName() + ", " +
				InputVars.RETURNCODE.getName() + ", " +
				LoggerVars.USERINPUT.getName() + ", " +
				LoggerVars.RECPARAMS.getName() +					
				");");
		sb.append("]]>");
		sb.append("</script>");

		return sb;
	}
	
	@Override
    protected StringBuilder renderInputSubmitNamelist() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("namelist=\"interpretation utterance inputmode confidence ");
    	sb.append(LoggerVars.INPUT_INFO.getName() + QUOTE ); 	

		return sb;
	}

	@Override
	protected StringBuilder renderInputPromptsLogInfo(AudioItem ai){
		StringBuilder sb = new StringBuilder();
		
		if (ai.getSrc() == null || ai.getSrc().isEmpty()){			
			sb.append("<mark nameexpr=\"insertSpeechTrace('', '" +
					PromptType.TTS + COMMA_QUOTED +
					ai.getWording().getText() +									
					"')\"/>");
			
		} else if (ai.getWording() == null || ai.getWording().getText().isEmpty()){
			sb.append("<mark nameexpr=\"insertSpeechTrace('"+ ai.getSrc() + COMMA_QUOTED +
					PromptType.FILE + COMMA_QUOTED +
					"" +									
					"')\"/>");
		} else {
			sb.append("<mark nameexpr=\"insertSpeechTrace('"+ ai.getSrc() + COMMA_QUOTED +
					PromptType.FILE + COMMA_QUOTED +
					ai.getWording().getText() +									
					"')\"/>");

		}

		return sb;
	}


}
