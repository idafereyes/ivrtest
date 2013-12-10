package com.vectorsf.statistics.files;

//import org.apache.commons.logging.Log;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLoggerFactory;
import com.vectorsf.jvoiceframework.core.log.Logger;
import com.vectorsf.statistics.events.Action;
import com.vectorsf.statistics.events.Debug;
import com.vectorsf.statistics.events.Dialogue;
import com.vectorsf.statistics.events.EndCall;
import com.vectorsf.statistics.events.EndService;
import com.vectorsf.statistics.events.Error;
import com.vectorsf.statistics.events.ExtSystem;
import com.vectorsf.statistics.events.Fatal;
import com.vectorsf.statistics.events.IVREvent;
import com.vectorsf.statistics.events.Info;
import com.vectorsf.statistics.events.InitCall;
import com.vectorsf.statistics.events.InitService;
import com.vectorsf.statistics.events.Signalling;
import com.vectorsf.statistics.events.Speech;
import com.vectorsf.statistics.events.Warn;
import com.vectorsf.statistics.log.WriteLogInterface;

public class WriteLogObject implements WriteLogInterface, Serializable {

	private static final long serialVersionUID = -1807260145991668820L;
	
	private FormatLog ivrLog;
	private Logger logger;

//	@Override	
	public void writeLog(IVREvent event) {

		logger = ExtendedLocLoggerFactory.getLogger(WriteLogObject.class);
		
		ivrLog = new FormatLog(event.getDate(), event.getCallId(), event
				.getServiceId());
		// EventType et = event.getEventType();
		if (event instanceof InitCall) {
			InitCall initCall = (InitCall) event;
			String initCallLog = ivrLog.getInitCallLog(initCall.getAni(),
					initCall.getDnis(), initCall.getAdditionalParams());
			logger.debug(initCallLog);
		} else if (event instanceof EndCall) {
			EndCall endCall = (EndCall) event;
			String endCallLog = ivrLog.getEndCallLog(endCall.getAni(), endCall
					.getDnis(), endCall.getReturnCode(), endCall
					.getAdditionalParams());
			logger.debug(endCallLog);
		} else if (event instanceof InitService) {
			InitService initService = (InitService) event;
			String initServiceLog = ivrLog.getInitServiceLog(initService
					.getInputParams());
			logger.debug(initServiceLog);
		} else if (event instanceof EndService) {
			EndService endService = (EndService) event;
			String endServiceLog = ivrLog.getEndServiceLog(endService
					.getReturnCode(), endService.getUserResponse(), endService
					.getAdditionalParams());
			logger.debug(endServiceLog);
		} else if (event instanceof Speech) {
			Speech speech = (Speech) event;
			String speechLog = ivrLog.getSpeechLog(speech.getSpeechID(), speech.getType(), speech.getText());
			logger.debug(speechLog);
		} else if (event instanceof Error) {
			Error error = (Error) event;
			String errorLog = ivrLog.getErrorLog(error.getDescription(), error
					.getCurrentClassName());
			logger.error(errorLog);
		} else if (event instanceof Dialogue) {
			Dialogue dialogue = (Dialogue) event;
			String dialogueLog = ivrLog.getDialogue(dialogue.getDialogueID(),
					dialogue.getRecAvailable(), dialogue.getRecDetected(),
					dialogue.getTries(), dialogue.getReturnCode(), dialogue
							.getUserInput(), dialogue.getRecParams());
			logger.debug(dialogueLog);
		} else if (event instanceof Debug) {
			Debug debug = (Debug) event;
			String debugLog = ivrLog.getDebugLog(debug.getInfo());
			logger.debug(debugLog);
		} else if (event instanceof ExtSystem) {
			ExtSystem extSystem = (ExtSystem) event;
			String extSystemLog = ivrLog.getExtSystemLog(extSystem
					.getSystemID(), extSystem.getSystemType(), extSystem
					.getUri(), extSystem.getOperation(), extSystem.getMessageDirection(), extSystem
					.getMessage(), extSystem.getReturnCode(), extSystem
					.getReturnDesc(), extSystem.getTimeout());
			logger.debug(extSystemLog);
		} else if (event instanceof Action) {
			Action action = (Action) event;
			String actionLog = ivrLog.getActionLog(action.getActionID(), action
					.getResult());
			logger.debug(actionLog);
		} else if (event instanceof Signalling) {
			Signalling signalling = (Signalling) event;
			String signallingLog = ivrLog.getSignallingLog(signalling
					.getSignalDirection(), signalling.getSignalType(),
					signalling.getSignalData());
			logger.debug(signallingLog);
		} else if (event instanceof Info) {
			Info info = (Info) event;
			String infoLog = ivrLog.getInfoLog(info.getInfo());					
			logger.info(infoLog);
		} else if (event instanceof Warn) {
			Warn warn = (Warn) event;
			String warnLog = ivrLog.getWarnLog(warn.getDescription(), warn.getCurrentClassName());
			logger.warn(warnLog);
		} else if (event instanceof Fatal) {
			//TODO Ver que hacemos
//			Fatal fatal = (Fatal) event;
//			String fatalLog = ivrLog.getFatalLog(fatal.getDescription(), fatal.getCurrentClassName());
//			logger.fatal(fatalLog);
			Fatal fatal = (Fatal) event;
			String fatalLog = ivrLog.getFatalLog(fatal.getDescription(), fatal.getCurrentClassName());
			logger.error(fatalLog);
		}

	}

}
