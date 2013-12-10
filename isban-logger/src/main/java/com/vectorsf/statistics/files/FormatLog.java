// Paquete en el que se incluye esta clase.
package com.vectorsf.statistics.files;

// Importacion de clases necesarias.
import java.util.Iterator;
import java.util.List;

import com.vectorsf.statistics.enums.CallReturnCode;
import com.vectorsf.statistics.enums.DialogueReturnCode;
import com.vectorsf.statistics.enums.MessageDirection;
import com.vectorsf.statistics.enums.RecAvailable;
import com.vectorsf.statistics.enums.RecDetected;
import com.vectorsf.statistics.enums.ServiceReturnCode;
import com.vectorsf.statistics.enums.SignalDirection;
import com.vectorsf.statistics.events.EventParam;
import com.vectorsf.statistics.log.Constants;

/**
 * Traza para servicios de sistema IVR.
 */
public class FormatLog {

	// Definicion de atributos de clase.
	// private int nivelTrazas = 0;
	private String date = null;
	private String callID = null;
	private String serviceID = null;

	/**
	 * Constructor de traza.
	 * 
	 * @param nivelTrazas
	 *            Nivel de las trazas.
	 * @param idLlamada
	 *            Identificador unico de llamada.
	 * @param idServicio
	 *            Identificador del servicio al que pertenece la traza.
	 */
	public FormatLog(String date, String idLlamada, String idServicio) {

		// this.nivelTrazas = nivelTrazas;
		this.date = date;
		this.callID = idLlamada;
		this.serviceID = idServicio;
	}

	/**
	 * Metodo resuelve una cadena de texto que corresponde a la parte inicial
	 * comun a todas las trazas.
	 * 
	 * @param eventType
	 *            Tipo de traza a registrar.
	 * @return String Cadena de texto comun a las trazas.
	 */

	private String getInitLog(String eventType) {

		if (serviceID.length()>50){
			getDebugLog("La longitud del serviceID: "+serviceID+" supera los 50 caracteres.");
			return (date + Constants.SEPARATOR + callID + Constants.SEPARATOR
					+ serviceID.substring(Constants.ZERO,Constants.MAX_CARACTERES) + Constants.SEPARATOR + eventType);
		}else{
			return (date + Constants.SEPARATOR + callID + Constants.SEPARATOR
					+ serviceID + Constants.SEPARATOR + eventType);			
		}
	}

	// Rutinas Principales
	// -------------------

	/**
	 * Metodo para obtener una traza de "DEBUG" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|DEBUG|textoComentario
	 * 
	 * @param debugText
	 *            Texto a insertar como comentario.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getDebugLog(String debugText) {

		return (this.getInitLog(Constants.DEBUG_ID) + Constants.SEPARATOR + debugText);
		// codificarTextoTraza(textoComentario));

	}

	/**
	 * Metodo para obtener una traza de "INFO" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|INFO|textoComentario
	 * 
	 * @param infoText
	 *            Texto a insertar como comentario.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getInfoLog(String infoText) {

		return (this.getInitLog(Constants.INFO_ID) + Constants.SEPARATOR + infoText);
		// codificarTextoTraza(textoComentario));

	}

	/**
	 * Metodo para obtener una traza de "WARN" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|WARN|textoComentario
	 * 
	 * @param warnText
	 *            Texto a insertar como comentario.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getWarnLog(String description, String currentClassName) {

		return (getInitLog(Constants.WARN_ID) + Constants.SEPARATOR
				+ description + Constants.SEPARATOR + currentClassName);
		// codificarTextoTraza(textoComentario));

	}

	/**
	 * Metodo para obtener una traza de "FATAL" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|FATAL|textoComentario
	 * 
	 * @param fatalText
	 *            Texto a insertar como comentario.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getFatalLog(String description, String currentClassName) {

		return (getInitLog(Constants.FATAL_ID) + Constants.SEPARATOR
				+ description + Constants.SEPARATOR + currentClassName);
		// codificarTextoTraza(textoComentario));

	}

	/**
	 * Metodo para obtener una traza de "SENAL_TELF" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|SENAL_TELF|tipoTransaccion|
	 * tipoSenal|datosSenal
	 * 
	 * @param transactionType
	 *            Tipo de transaccion, ENVIO o RECEPCION.
	 * @param signalType
	 *            Tipo de senial.
	 * @param signalData
	 *            Datos de la senial.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getSignallingLog(SignalDirection signalDirection,
			String signalType, List<EventParam> signalData) {

		StringBuffer sb = new StringBuffer();
		sb.append(this.getInitLog(Constants.SIGNALLING_ID) + Constants.SEPARATOR
				+ signalDirection + Constants.SEPARATOR + signalType);
				
		return writeList(signalData, sb);
		
		// tipoTransaccion + Constants.SEPARATOR + tipoSenal +
		// Constants.SEPARATOR + codificarTextoTraza(datosSenal));

	}

	/**
	 * Metodo para obtener una traza de "INIT_CALL" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|INIT_CALL|ani|
	 * dnis|additionalParams
	 * 
	 * @param ani
	 *            Numeracion origen de la llamada.
	 * @param dnis
	 *            Numeracion destino de la llamada.
	 * @param additionalParams
	 *            Parametros adicionales de la llamada.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getInitCallLog(String ani, String dnis, List<EventParam> additionalParams) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(this.getInitLog(Constants.INIT_CALL_ID)).append(
				Constants.SEPARATOR).append(ani).append(Constants.SEPARATOR)
				.append(dnis);

		return writeList(additionalParams, sb);

	}

	/**
	 * Metodo para obtener una traza de "END_CALL" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|END_CALL|ani|
	 * dnis|returnCode|additionalParams
	 * 
	 * @param ani
	 *            Numeracion origen de la llamada.
	 * @param dnis
	 *            Numeracion destino de la llamada.
	 * @param returnCode
	 *            Numeracion destino de la llamada.
	 * @param additionalParams
	 *            Parámetros adicionales asociados a la finalización de la
	 *            llamada.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getEndCallLog(String ani, String dnis, CallReturnCode returnCode,
			List<EventParam> additionalParams) {

		StringBuffer sb = new StringBuffer();
		sb.append(this.getInitLog(Constants.END_CALL_ID)).append(
				Constants.SEPARATOR).append(ani).append(Constants.SEPARATOR)
				.append(dnis).append(Constants.SEPARATOR).append(returnCode);
		return writeList(additionalParams, sb);

	}

	/**
	 * Metodo para obtener una traza de "INIT_SERVICE" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|INIT_SERVICE|inputParams
	 * 
	 * @param inputParams
	 *            Parametros de entrada al servicio.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getInitServiceLog(List<EventParam> inputParams) {

		StringBuffer sb = new StringBuffer();
		sb.append(this.getInitLog(Constants.INIT_SERVICE_ID));

		return writeList(inputParams, sb);
		// codificarTextoTraza(paramsEntrada));

	}

	/**
	 * Metodo para obtener una traza de "END_SERVICE" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|END_SERVICE|returnCode|
	 * userResponse|additionalParams
	 * 
	 * @param returnCode
	 *            Codigo de retorno del servicio.
	 * @param userResponse
	 *            Respuesta del usuario.
	 * @param additionalParams
	 *            Parametros adicionales de salida.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getEndServiceLog(ServiceReturnCode returnCode, String userResponse,
			List<EventParam> additionalParams) {

		StringBuffer sb = new StringBuffer();
		sb.append(this.getInitLog(Constants.END_SERVICE_ID)).append(
				Constants.SEPARATOR).append(returnCode).append(
				Constants.SEPARATOR).append(userResponse);

		return writeList(additionalParams, sb);

		// codificarTextoTraza(codigoRetorno) + Constants.SEPARATOR +
		// codificarTextoTraza(paramsAdicionales)) + Constants.SEPARATOR +
		// respuestaUsuario + Constants.SEPARATOR + tipificacion;

	}

	/**
	 * Metodo para obtener una traza de "DIALOGUE" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|DIALOGUE|dialogueID|recAvailable|
	 * recDetected|tries|returnCode|inputUser|recParams
	 * 
	 * @param dialogueID
	 *            Identificador del dialogo.
	 * @param recAvailable
	 *            Tipo de reconocimiento activo.
	 * @param recDetected
	 *            Modo de respuesta.
	 * @param tries
	 *            Numero de intentos que lleva el usuario en ese menu.
	 * @param returnCode
	 *            Codigo de retorno del servicio.
	 * @param inputUser
	 *            Se indica la respuesta obtenida por parte del usuario cuando
	 *            la interacción se ha realizado de forma correcta.
	 * @param recParams
	 *            Parametros extras.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getDialogue(String dialogueID, RecAvailable recAvailable,
			RecDetected recDetected, int tries, DialogueReturnCode returnCode,
			String inputUser, List<EventParam> recParams) {
		
		StringBuffer sb = new StringBuffer();
		
		if (dialogueID.length()>50){
			getDebugLog("La longitud del dialogueID: "+dialogueID+" supera los 50 caracteres.");
		
			sb.append(getInitLog(Constants.DIALOGUE_ID) + Constants.SEPARATOR
					+ dialogueID.substring(Constants.ZERO,Constants.MAX_CARACTERES) + Constants.SEPARATOR + recAvailable
					+ Constants.SEPARATOR + recDetected + Constants.SEPARATOR
					+ tries + Constants.SEPARATOR + returnCode
					+ Constants.SEPARATOR + inputUser);
		}else{
			sb.append(getInitLog(Constants.DIALOGUE_ID) + Constants.SEPARATOR
					+ dialogueID + Constants.SEPARATOR + recAvailable
					+ Constants.SEPARATOR + recDetected + Constants.SEPARATOR
					+ tries + Constants.SEPARATOR + returnCode
					+ Constants.SEPARATOR + inputUser);
		}
		
		return writeList(recParams, sb);
		
		// codificarTextoTraza(idDialogo) + Constants.SEPARATOR + modoRespuesta
		// + Constants.SEPARATOR + codificarTextoTraza(entradaUsuario) +
		// Constants.SEPARATOR +
		// codificarTextoTraza(codigoRetorno));

	}

	/**
	 * Metodo para obtener una traza de "EXT_SYSTEM" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|EXT_SYSTEM|systemID|
	 * directionMessage|pathSystem
	 * |inputParams|outputParams|returnCode|descResult|timeOut
	 * 
	 * @param systemID
	 *            Identificador del sistema con el que se realiza el
	 *            intercambio.
	 * @param directionMessage
	 *            Indica si se trata de ENVIO, RECEPCION.
	 * @param pathSystem
	 *            Ruta del sistema externo.
	 * @param inputParams
	 *            Contenido del mensaje enviado. Vacio si se trata de una
	 *            recepción
	 * @param outputParams
	 *            Contenido del mensaje recibido. Vacio en caso de tratarse de
	 *            un envío.
	 * @param returnCode
	 *            Codigo de retorno de la transaccion.
	 * @param descResult
	 *            Mensaje descriptivo del resultado o datos adicionales al
	 *            mismo.
	 * @param timeOut
	 *            Tiempo de espera del sistema externo.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getExtSystemLog(String systemID, String systemType,
			String uri, String operation, MessageDirection directionMessage, String message,
			String returnCode, String returnDesc, List<EventParam> timeout) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(getInitLog(Constants.EXT_SYSTEM_ID) + Constants.SEPARATOR
				+ systemID + Constants.SEPARATOR + systemType
				+ Constants.SEPARATOR + uri + Constants.SEPARATOR
				+ operation + Constants.SEPARATOR
				+ directionMessage + Constants.SEPARATOR + message
				+ Constants.SEPARATOR + returnCode + Constants.SEPARATOR
				+ returnDesc);
		
		return writeList(timeout, sb);		
		
		// codificarTextoTraza(idSistema) + Constants.SEPARATOR +
		// codificarTextoTraza(rutaSistema) + Constants.SEPARATOR +
		// tipoTransaccion + Constants.SEPARATOR + codificarTextoTraza(mensaje)
		// + Constants.SEPARATOR +
		// codificarTextoTraza(codigoRetorno) + Constants.SEPARATOR +
		// codificarTextoTraza(descrResultado));

	}

	/**
	 * Metodo para obtener una traza de "ACTION" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|EJEC_ACCION|code|description
	 * 
	 * @param code
	 *            Codigo de la accion.
	 * @param description
	 *            Resultado de la operacion.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getActionLog(String actionID, String result) {

		if (actionID.length()>50){
			getDebugLog("La longitud del actionID: "+actionID+" supera los 50 caracteres.");
		
			return (getInitLog(Constants.ACTION_ID) + Constants.SEPARATOR + actionID.substring(Constants.ZERO,Constants.MAX_CARACTERES)
				+ Constants.SEPARATOR + result);
		}else{
			return (getInitLog(Constants.ACTION_ID) + Constants.SEPARATOR + actionID
					+ Constants.SEPARATOR + result);			
		}

	}

	/**
	 * Metodo para obtener una traza de "ERROR" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|ERROR|description|location
	 * 
	 * @param description
	 *            Identificador del evento.
	 * @param location
	 *            Localizacion en la que se ha producido el evento.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getErrorLog(String description, String currentClassNme) {

		return (getInitLog(Constants.ERROR_ID) + Constants.SEPARATOR
				+ description + Constants.SEPARATOR + currentClassNme);
		// codificarTextoTraza(idEvento) + Constants.SEPARATOR +
		// codificarTextoTraza(paramsEvento) +
		// Constants.SEPARATOR + codificarTextoTraza(localizacion));

	}

	/**
	 * Metodo para obtener una traza de "SPEECH" con el formato:
	 * 
	 * fechaHoraRegistro|idLlamada|idServicio|SPEECH|speechID
	 * 
	 * @param speechID
	 *            Identificador de la locución.
	 * @return String Cadena de texto que representa la traza.
	 */
	public String getSpeechLog(String speechID, String type, String text) {

		if (speechID.length()>50){
			getDebugLog("La longitud del speechID: "+speechID+" supera los 50 caracteres.");
		
			return (getInitLog(Constants.SPEECH_ID) + Constants.SEPARATOR + speechID.substring(Constants.ZERO,Constants.MAX_CARACTERES)
				+ Constants.SEPARATOR + type + Constants.SEPARATOR + text);
		}else{
			return (getInitLog(Constants.SPEECH_ID) + Constants.SEPARATOR + speechID		
				+ Constants.SEPARATOR + type + Constants.SEPARATOR + text);
		}
		// codificarTextoTraza(idEvento) + Constants.SEPARATOR +
		// codificarTextoTraza(paramsEvento) +
		// Constants.SEPARATOR + codificarTextoTraza(localizacion));

	}

	public String writeList(List<EventParam> additionalParams, StringBuffer sb) {
		if (additionalParams != null) {
			Iterator<EventParam> it = additionalParams.iterator();
			int cont = 1;
			while (it.hasNext()) {
				EventParam EventParam = (EventParam) it.next();
				if (cont == 1) {
					sb.append(Constants.SEPARATOR).append(EventParam);
				} else {
					sb.append(Constants.LIST_SEPARATOR).append(EventParam);
				}
				cont += 1;

			}
		}
		return sb.toString();
	}

	/**
	 * Metodo para probar la clase.
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		List<EventParam> additionalParams = new ArrayList<EventParam>();
//		EventParam param = new EventParam("paramName1", "paramValue1");
//		additionalParams.add(param);
//		EventParam param2 = new EventParam("paramName2", "paramValue2");
//		additionalParams.add(param2);
//
//		FormatLog traza = new FormatLog("20110411", "123456565",
//				"PruebaIdServicio");
//		System.out.println(traza.getDebugLog("Texto del comentario"));
//		System.out.println(traza.getSignallingLog(SignalDirection.SEND, "REL",
//				null));
//		System.out
//				.println(traza.getInitCallLog("123", "456", additionalParams));
//		System.out.println(traza.getEndCallLog("123", "456", CallReturnCode.END,
//				additionalParams));
//		List<EventParam> inputParams = new ArrayList<EventParam>();
//		EventParam param3 = new EventParam("paramName3", "paramValue3");
//		additionalParams.add(param3);
//		EventParam param4 = new EventParam("paramName4", "paramValue4");
//		additionalParams.add(param4);
//		inputParams.add(param3);
//		inputParams.add(param4);
//		System.out.println(traza.getInitServiceLog(inputParams));
//		System.out.println(traza.getEndServiceLog(ServiceReturnCode.TRANSFER, "1234",
//				additionalParams));
//
//		ArrayList<EventParam> recParams = new ArrayList<EventParam>();
//		System.out.println(traza
//				.getDialogue("CAC-PREG-CLI", RecAvailable.ASR, RecDetected.ASR,
//						2, DialogueReturnCode.OK, "campo InputUser", recParams));
//		// System.out.println(traza.getTrazaProc_Adminis("EnvioEstadisticas",
//		// "http://123.456.123.456:8082/...", "RECEPCION", "<xml...", "OK",
//		// "Salida correcta"));
////		System.out.println(traza.getExtSystemLog("BankSphere", "WS", "http://asdsadsad", "operation",
////				MessageDirection.IN, "Salida correcta", null));
//		System.out.println(traza.getActionLog("1", "OK"));
//		System.out.println(traza.getErrorLog("ERROR", "REGISTRAR_BBDD"));
//		System.out.println(traza.getSpeechLog("sgnedjghy", "type", "text"));
//	}

}
