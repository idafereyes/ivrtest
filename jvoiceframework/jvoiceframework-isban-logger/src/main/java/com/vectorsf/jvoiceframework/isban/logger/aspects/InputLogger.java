package com.vectorsf.jvoiceframework.isban.logger.aspects;

import java.util.List;
import java.util.StringTokenizer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.expression.spel.SpringELExpression;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.isban.logger.enums.DialogueReturnCode;
import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;
import com.vectorsf.jvoiceframework.isban.logger.enums.RecAvailable;
import com.vectorsf.jvoiceframework.isban.logger.enums.RecDetected;
import com.vectorsf.jvoiceframework.isban.logger.events.EventParam;
import com.vectorsf.jvoiceframework.isban.logger.log.StatisticsLogger;
import com.vectorsf.jvoiceframework.isban.logger.render.vxi.LoggerVars;

@Component
@Aspect
public class InputLogger {
	
	static final String TRACE_DELIMITER = "&";
	static final String PARAM_DELIMITER = "\\|";

	@Autowired
	private StatisticsLogger st;

	public StatisticsLogger getSt() {
		return st;
	}

	public void setSt(StatisticsLogger st) {
		this.st = st;
	}

	@Pointcut("execution(* org.springframework.webflow.execution.FlowExecutionListenerAdapter.eventSignaled(..))")
	public void eventSignaled(){}
	
	@Before("eventSignaled()")
	public void beforeMethod(JoinPoint joinPoint){		
 		RequestContext context = (RequestContext) joinPoint.getArgs()[0];
 		
 		if (context.inViewState() && (context.getCurrentState().getAttributes().get("model") != null)){

 	 	 		SpringELExpression spElExp = (SpringELExpression) context.getCurrentState().getAttributes().get("model");
 	 	 		
 	 	 		if (spElExp.getExpressionString().equals("lastInputResult")){
 	 	 			
 	 				StringTokenizer stTraces = new StringTokenizer(context.getRequestParameters().get(LoggerVars.INPUT_INFO.getName()), TRACE_DELIMITER);

	 			    while (stTraces.hasMoreTokens()) {   	
	 			    	String trace = stTraces.nextToken();
	 			    	if (trace.startsWith(EventType.DIALOGUE.toString())){
	 						writeDialogue(trace);
	 					}else if (trace.startsWith(EventType.SPEECH.toString())){
	 						writeSpeech(trace);
	 					}
	 			    }

	 	 		}
 	 		}
 	}

	private void writeDialogue(String dialogueTrace){

		String[] stdTrace = dialogueTrace.split(PARAM_DELIMITER);
				
		String dialogueID = stdTrace[1];

		RecAvailable recAvailable = null;
		try {
			recAvailable = RecAvailable.valueOf(stdTrace[2]);
		} catch (Exception e) {
			recAvailable = null;
		}
		
		RecDetected recDetected = null;
		try {
	    	if ("voice".equalsIgnoreCase(stdTrace[3])){
	    		recDetected = RecDetected.ASR;
	    	}else{
				recDetected = RecDetected.valueOf(stdTrace[3].toUpperCase());	    		
	    	}
		} catch (Exception e) {
			recDetected = null;
		}
		
		int tries = Integer.valueOf(stdTrace[4]);
		
		DialogueReturnCode returnCode = null;
		try {
			returnCode = DialogueReturnCode.valueOf(stdTrace[5]);
		} catch (Exception e) {
			returnCode = null;
		}
		
		String userInput = stdTrace[6];

		List<EventParam> recParams = null;
		
		st.DIALOGUE(dialogueID, recAvailable, recDetected, tries, returnCode, userInput, recParams);
	}
	
	private void writeSpeech(String speechTrace){

		String[] stdTrace = speechTrace.split(PARAM_DELIMITER);
		
		String speechID = stdTrace[1];
		String type = "";
		String text = "";
		if (stdTrace.length > 2){
			 type = stdTrace[2];
		}
		if (stdTrace.length > 3){
			text = stdTrace[3];
		}
		
		st.SPEECH(speechID, type, text);
	}


}
