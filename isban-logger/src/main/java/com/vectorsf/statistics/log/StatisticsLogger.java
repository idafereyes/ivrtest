package com.vectorsf.statistics.log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import com.vectorsf.statistics.config.ConfigurationManager;
import com.vectorsf.statistics.enums.CallReturnCode;
import com.vectorsf.statistics.enums.DialogueReturnCode;
import com.vectorsf.statistics.enums.EventType;
import com.vectorsf.statistics.enums.MessageDirection;
import com.vectorsf.statistics.enums.RecAvailable;
import com.vectorsf.statistics.enums.RecDetected;
import com.vectorsf.statistics.enums.ServiceReturnCode;
import com.vectorsf.statistics.enums.SignalDirection;
import com.vectorsf.statistics.events.Action;
import com.vectorsf.statistics.events.Debug;
import com.vectorsf.statistics.events.Dialogue;
import com.vectorsf.statistics.events.EndCall;
import com.vectorsf.statistics.events.EndService;
import com.vectorsf.statistics.events.Error;
import com.vectorsf.statistics.events.EventParam;
import com.vectorsf.statistics.events.ExtSystem;
import com.vectorsf.statistics.events.Fatal;
import com.vectorsf.statistics.events.IVREvent;
import com.vectorsf.statistics.events.Info;
import com.vectorsf.statistics.events.InitCall;
import com.vectorsf.statistics.events.InitService;
import com.vectorsf.statistics.events.Signalling;
import com.vectorsf.statistics.events.Speech;
import com.vectorsf.statistics.events.Warn;
import com.vectorsf.statistics.utils.DateUtils;
import com.vectorsf.statistics.utils.LogUtils;

@Component("statisticsLogger")
public class StatisticsLogger implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3213014980191560349L;
	
	private String callID;
	private String serviceID;
	ConfigurationManager conf = ConfigurationManager.buildInstance();
	public static final String COMMON_LOGGER = "COMMONLOGGER";
	private DateUtils dateUtils = new DateUtils();
	
	private HashMap<EventType,Boolean> eventsActivationState = new HashMap<EventType,Boolean>();

	public StatisticsLogger() {
		setEventsActivationState();
	}
	
	public StatisticsLogger(String service, String callID) {
		this.serviceID = service;
		this.callID = callID;
		setEventsActivationState();
	}

	/**
	 * @param signalDirection
	 * @param signalType
	 * @param signalData
	 */
	public void SIGNALLING(SignalDirection signalDirection, String signalType,
			List<EventParam> signalData) {
		
		if (isEventActive(EventType.SIGNALLING)) {
			// Parte general
			Signalling signalling = new Signalling(callID, serviceID, EventType.SIGNALLING, dateUtils.obtenerFechaHoraActual());

			// Parte Específica
			signalling.setSignalDirection(signalDirection);
			signalling.setSignalType(LogUtils.parseLogMessage(signalType));
			signalling.setSignalData(signalData);

			prepareToSend(signalling);
		}
	}

	/**
	 * @param systemID
	 * @param systemType
	 * @param uri
	 * @param directionMessage
	 * @param message
	 * @param returnCode
	 * @param returnDesc
	 * @param timeout
	 */
	public void EXT_SYSTEM(String systemID, String systemType, String uri, String operation,
			MessageDirection messageDirection, String message, String returnCode,
			String returnDesc, List<EventParam> timeout) {
		if (isEventActive(EventType.EXT_SYSTEM)) {

			// Parte general
			ExtSystem extSystem = new ExtSystem(callID, serviceID,
					EventType.EXT_SYSTEM, dateUtils
							.obtenerFechaHoraActual());

			// Parte Específica
			extSystem.setSystemID(LogUtils.parseLogMessage(systemID));
			extSystem.setSystemType(LogUtils.parseLogMessage(systemType));
			extSystem.setUri(LogUtils.parseLogMessage(uri));
			extSystem.setOperation(LogUtils.parseLogMessage(operation));
			extSystem.setMessageDirection(messageDirection);
			extSystem.setMessage(LogUtils.parseLogMessage(message));
			extSystem.setReturnCode(LogUtils.parseLogMessage(returnCode));
			extSystem.setReturnDesc(LogUtils.parseLogMessage(returnDesc));
			extSystem.setTimeout(timeout);

			prepareToSend(extSystem);
		}

	}

	/**
	 * @param speechID
	 */
	public void SPEECH(String speechID, String type, String text) {

		if (isEventActive(EventType.SPEECH)) {
			// Parte general
			Speech speech = new Speech(callID, serviceID, EventType.SPEECH,
					dateUtils.obtenerFechaHoraActual());

			// Parte Específica
			speech.setSpeechID(LogUtils.parseLogMessage(speechID));
			speech.setType(LogUtils.parseLogMessage(type));
			speech.setText(LogUtils.parseLogMessage(text));

			prepareToSend(speech);
		}
	}

	/**
	 * @param info
	 */
	public void DEBUG(String info) {
		if (isEventActive(EventType.DEBUG)) {

			// Parte general
			Debug debug = new Debug(callID, serviceID, EventType.DEBUG, dateUtils
							.obtenerFechaHoraActual());

			// Parte Específica
			debug.setInfo(LogUtils.parseLogMessage(info));

			prepareToSend(debug);
		}
	}

	// Estadísticas
	/**
	 * @param code
	 * @param description
	 */
	public void ACTION(String actionID, String result) {
		if (isEventActive(EventType.ACTION)) {

			// Parte general
			Action action = new Action(callID, serviceID, EventType.ACTION, dateUtils
					.obtenerFechaHoraActual());

			// Parte Específica
			action.setActionID(LogUtils.parseLogMessage(actionID));
			action.setResult(LogUtils.parseLogMessage(result));

			prepareToSend(action);
		}
	}

	/**
	 * @param ani
	 * @param dnis
	 * @param additionalParams
	 */
	public void INIT_CALL(String ani, String dnis, List<EventParam> additionalParams) {
		if (isEventActive(EventType.INIT_CALL)) {

			// Parte general
			// InitCall initCall = new InitCall(session.getCallID(), session
			// .getServiceID(), EventType.INIT_CALL, dateUtils
			// .obtenerFechaHoraActual(),session.getApplication());

			InitCall initCall = new InitCall(callID, serviceID,
					EventType.INIT_CALL, dateUtils
							.obtenerFechaHoraActual());

			// Parte Específica
			initCall.setAni(LogUtils.parseLogMessage(ani));
			initCall.setDnis(LogUtils.parseLogMessage(dnis));
			initCall.setAdditionalParams(additionalParams);

			prepareToSend(initCall);
		}
	}

	/**
	 * @param ani
	 * @param dnis
	 * @param returnCode
	 * @param additionalParams
	 */
	public void END_CALL(String ani, String dnis, CallReturnCode returnCode,
			List<EventParam> additionalParams) {
		if (isEventActive(EventType.END_CALL)) {

			// Parte general
			EndCall endCall = new EndCall(callID, serviceID, EventType.END_CALL, dateUtils
					.obtenerFechaHoraActual());

			// Parte Específica
			endCall.setAni(LogUtils.parseLogMessage(ani));
			endCall.setDnis(LogUtils.parseLogMessage(dnis));
			endCall.setReturnCode(returnCode);
			endCall.setAdditionalParams(additionalParams);

			prepareToSend(endCall);
		}
	}

	/**
	 * @param inputParams
	 */
	public void INIT_SERVICE(List<EventParam> inputParams) {
		if (isEventActive(EventType.INIT_SERVICE)) {
			InitService initService = new InitService(callID, serviceID, EventType.INIT_SERVICE, dateUtils
							.obtenerFechaHoraActual());

			initService.setInputParams(inputParams);
			prepareToSend(initService);
		}
	}

	/**
	 * @param returnCode
	 * @param userResponse
	 * @param additionalParams
	 */
	public void END_SERVICE(ServiceReturnCode returnCode, String userResponse,
			List<EventParam> additionalParams) {
		if (isEventActive(EventType.END_SERVICE)) {
			EndService endService = new EndService(callID, serviceID, EventType.END_SERVICE, dateUtils
					.obtenerFechaHoraActual());
			endService.setEventType(EventType.END_SERVICE);
			endService.setAdditionalParams(additionalParams);
			endService.setReturnCode(returnCode);
			endService.setUserResponse(LogUtils.parseLogMessage(userResponse));
			endService.setDate(dateUtils.obtenerFechaHoraActual());

			prepareToSend(endService);
		}
	}

	/**
	 * @param description
	 * @param currentClassName
	 */
	public void ERROR(String description, String currentClassName) {
		if (isEventActive(EventType.ERROR)) {
			Error error = new Error(callID, serviceID, EventType.ERROR, dateUtils
							.obtenerFechaHoraActual());
			error.setDescription(LogUtils.parseLogMessage(description));
			error.setCurrentClassName(LogUtils.parseLogMessage(currentClassName));

			prepareToSend(error);
		}
	}

	/**
	 * @param dialogueID
	 * @param recAvailable
	 * @param recDetected
	 * @param tries
	 * @param returnCode
	 * @param userInput
	 * @param recParams
	 */
	public void DIALOGUE(String dialogueID, RecAvailable recAvailable,
			RecDetected recDetected, int tries, DialogueReturnCode returnCode,
			String userInput, List<EventParam> recParams) {
		if (isEventActive(EventType.DIALOGUE)) {
			Dialogue dialogue = new Dialogue(callID, serviceID, EventType.DIALOGUE, dateUtils
					.obtenerFechaHoraActual());
			dialogue.setDialogueID(LogUtils.parseLogMessage(dialogueID));
			dialogue.setRecAvailable(recAvailable);
			dialogue.setRecDetected(recDetected);
			dialogue.setTries(tries);
			dialogue.setReturnCode(returnCode);
			dialogue.setUserInput(LogUtils.parseLogMessage(userInput));
			dialogue.setRecParams(recParams);

			prepareToSend(dialogue);
		}
	}

	/**
	 * @param description
	 * @param currentClassName
	 */
	public void WARN(String description, String currentClassName) {
		if (isEventActive(EventType.WARN)) {
			Warn warn = new Warn(callID, serviceID, EventType.WARN, dateUtils
							.obtenerFechaHoraActual());
			warn.setDescription(LogUtils.parseLogMessage(description));
			warn.setCurrentClassName(LogUtils.parseLogMessage(currentClassName));

			prepareToSend(warn);
		}
	}
	
	/**
	 * @param description
	 * @param currentClassName
	 */
	public void FATAL(String description, String currentClassName) {
		if (isEventActive(EventType.FATAL)) {
			Fatal fatal = new Fatal(callID, serviceID, EventType.FATAL, dateUtils
							.obtenerFechaHoraActual());
			fatal.setDescription(LogUtils.parseLogMessage(description));
			fatal.setCurrentClassName(LogUtils.parseLogMessage(currentClassName));

			prepareToSend(fatal);
		}
	}

	/**
	 * @param infoText
	 */
	public void INFO(String infoText) {
		if (isEventActive(EventType.INFO)) {

			// Parte general
			Info info = new Info(callID, serviceID, EventType.INFO, dateUtils
							.obtenerFechaHoraActual());

			// Parte Específica
			info.setInfo(LogUtils.parseLogMessage(infoText));

			prepareToSend(info);
		}
	}


	private void prepareToSend(IVREvent event) {
		WriteLogInterface writeLogInterface = null;
		//TODO recorrer la lista de posibles writeLogClasses
		
		List<Object> writeLogClasses = conf.getWriteLogClasses();
		
		Iterator<Object> it = writeLogClasses.iterator();
		

		while(it.hasNext()){
			Object writeLogClass = it.next();
			try {
				Class instance = Class.forName((String)writeLogClass);
				writeLogInterface = (WriteLogInterface) instance.newInstance();
			} catch (ClassNotFoundException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (InstantiationException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		} catch (IllegalAccessException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
			writeLogInterface.writeLog(event);
		}
	
	}
	
	public String getCallID() {
		return callID;
	}

	public void setCallID(String callID) {
		this.callID = callID;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public boolean isEventActive(EventType event) {
		
		if (eventsActivationState.get(event) != null){
			return eventsActivationState.get(event);		
		}
		return false;
	}

	public void setEventActive(EventType event, boolean active) {
		eventsActivationState.put(event, active);
	}
	
	private void setEventsActivationState() {
		setEventActive(EventType.ACTION, conf.isEventActive(EventType.ACTION));
		setEventActive(EventType.DEBUG, conf.isEventActive(EventType.DEBUG));
		setEventActive(EventType.DIALOGUE, conf.isEventActive(EventType.DIALOGUE));
		setEventActive(EventType.END_CALL, conf.isEventActive(EventType.END_CALL));
		setEventActive(EventType.END_SERVICE, conf.isEventActive(EventType.END_SERVICE));
		setEventActive(EventType.ERROR, conf.isEventActive(EventType.ERROR));
		setEventActive(EventType.EXT_SYSTEM, conf.isEventActive(EventType.EXT_SYSTEM));
		setEventActive(EventType.FATAL, conf.isEventActive(EventType.FATAL));
		setEventActive(EventType.INFO, conf.isEventActive(EventType.INFO));
		setEventActive(EventType.INIT_CALL, conf.isEventActive(EventType.INIT_CALL));
		setEventActive(EventType.INIT_SERVICE, conf.isEventActive(EventType.INIT_SERVICE));
		setEventActive(EventType.SIGNALLING, conf.isEventActive(EventType.SIGNALLING));
		setEventActive(EventType.SPEECH, conf.isEventActive(EventType.SPEECH));
		setEventActive(EventType.WARN, conf.isEventActive(EventType.WARN));
	}

}
