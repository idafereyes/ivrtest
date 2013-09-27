package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.enums.RecordEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferType;

public class VXIRenderer implements Renderer, Serializable {

    private static final long serialVersionUID = -5854263044507481102L;

    static final String BLOCK_START_TAG = "<block>";
    static final String BLOCK_END_TAG = "</block>";
    static final String SUBMIT_TAG = "<submit next=\"";
    static final String AMPERSAND = "&amp;";
    static final String CATCH_END_TAG = "</catch>";
    static final String CLOSE_TAG = ">"; 
    static final String END_TAG = "/>"; 
    static final String QUOTE_SPACE = "\" ";
    static final String QUOTE = "\"";
    static final String EVENT_ID = "_eventId_";
    static final String NAMELIST_EVENT_DURATION = "namelist=\"duration event\"";
    static final String NAMELIST_EVENT = "namelist=\"event\"";
    static final String SINGLE_QUOTE = "'";
    static final String EVENT_VAR_DECLARATION = "<var name=\"event\" expr=\"'";
    static final String AUDIO_START_TAG = "<audio ";
    static final String SRC_ATTRIBUTE_QUOTE = "src=\"";

    public String render(Output output, String flowURL) {
                
        StringBuilder code2render = new StringBuilder();
        
        //Renders properties if there are
        if (!output.getProperties().isEmpty()){
            code2render.append(renderProperties(output.getProperties()));            
        }
        
        code2render.append(BLOCK_START_TAG);
        
        //Renders audioItemsList
        code2render.append(renderOutputAudioItems(output));

        code2render.append(BLOCK_END_TAG);

        if (output.isCatchHangup() || output.isFlush()){
            //Renders an VXML field dummy to be able to catch the hangup event or send the output immediately to the VXML interpreter
            code2render.append(renderOutputFieldDummy(output, flowURL));            
        }
            
        return code2render.toString();
    }    

    private String renderOutputAudioItems(Output output) {
        
        StringBuilder audioItemsCode = new StringBuilder();

        List<AudioItem> audioItemsList = output.getAudioItems();
        
        for (int i=0;i<audioItemsList.size();i++){
            
            audioItemsCode.append("<prompt");
            
            //Adds bargeIn prompt attribute only if specified
            if (output.isBargein()){
                audioItemsCode.append(" bargein=\"true\"");
            }else{
                audioItemsCode.append(" bargein=\"false\"");
            }
            
            //Adds cond prompt attribute if specified
            if (audioItemsList.get(i).getCondition() != null){
                audioItemsCode.append(" cond=\"" + audioItemsList.get(i).getCondition() + "\"");
            }
            
            //Ends prompt start tag
            audioItemsCode.append(">");

            if (audioItemsList.get(i).getSrc() == null){
                //TTS
                audioItemsCode.append(audioItemsList.get(i).getWording());                
            }else if (audioItemsList.get(i).getWording() == null){
                audioItemsCode.append(AUDIO_START_TAG + SRC_ATTRIBUTE_QUOTE + audioItemsList.get(i).getSrc() + QUOTE + END_TAG);
            }else{
                //Audio with TTS backup prompt
                audioItemsCode.append(AUDIO_START_TAG + SRC_ATTRIBUTE_QUOTE + audioItemsList.get(i).getSrc() + QUOTE + CLOSE_TAG);
                audioItemsCode.append(audioItemsList.get(i).getWording());
                audioItemsCode.append("</audio>");
            }
            
            //Prompt end tag
            audioItemsCode.append("</prompt>");
        }
        
        return audioItemsCode.toString();
    }

    private String renderOutputCatchHangup(String flowURL) {

        StringBuilder catchHangupCode =  new StringBuilder();
        
        //Renders a catch tag for the hangup event and redirects back to the application server with hangup as _eventId param value. 
        catchHangupCode.append("<catch event=\"connection.disconnect.hangup\">");
        
        catchHangupCode.append(SUBMIT_TAG + flowURL + AMPERSAND + "_eventId_hangup\" />");
        
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
        fieldDummyCode.append("<filled>");
        
        //redirects back to the application server with success as _eventId param value. 
        fieldDummyCode.append(SUBMIT_TAG + flowURL + AMPERSAND + "_eventId_success\" />");
        
        fieldDummyCode.append("</filled>");
        
        //Render a catch for NOINPUT(normally will always happen with this timeout) and NOMATCH(just in case) events.
        fieldDummyCode.append("<catch event=\"noinput nomatch\" >");
        
        //redirects back to the application server with success as _eventId param value. 
        fieldDummyCode.append(SUBMIT_TAG + flowURL + AMPERSAND + "_eventId_success\" />");

        fieldDummyCode.append(CATCH_END_TAG);

        fieldDummyCode.append("</field>");
        
        return fieldDummyCode.toString();
    }

    public String render(Input prompt, String flowURL) {
        // TODO Auto-generated method stub
        return null;
    }

    public String render(Transfer transfer, String flowURL) {
        
        StringBuilder code2render = new StringBuilder();

        //Transfer start tag
        code2render.append("<transfer name=\"transferResult\" ");
        
        //Renders transfer attributes
        code2render.append(renderTransferAttributes(transfer));

        //Ends transfer start tag
        code2render.append(">");
                
        //Renders properties if there are
        if (!transfer.getProperties().isEmpty()){
            code2render.append(renderProperties(transfer.getProperties()));            
        }
        
        //Renders eventsList
        code2render.append(renderTransferEventsList(transfer, flowURL));
        
        //Transfer end tag
        code2render.append("</transfer>");

        return code2render.toString();
    }
    
    private StringBuilder renderTransferAttributes(Transfer transfer) {

    	StringBuilder attributesCode = new StringBuilder();

    	//Required attributes
    	attributesCode.append("dest=\"" + transfer.getDest() +    QUOTE_SPACE);
        String transferType = transfer.getType().toLowerCase();
        attributesCode.append("type=\"" + transferType + QUOTE_SPACE);
        
        //Optional attributes
        //Sets "timeout" optional attribute. N/A for blind transfer type.
        if (transfer.getTimeout() != null && !TransferType.BLIND.toString().equalsIgnoreCase(transferType)){
        	attributesCode.append("connecttimeout=\"" + transfer.getTimeout()  + QUOTE_SPACE);           
        }
        //Sets "maxtime" optional attribute. N/A for blind and consultation transfer types.
        if (transfer.getMaxtime() != null && TransferType.BRIDGE.toString().equalsIgnoreCase(transferType)){
        	attributesCode.append("maxtime=\"" + transfer.getMaxtime()  + QUOTE_SPACE);           
        }
        //Sets "transferaudio" optional attribute. 
        //TODO Completar la URI
        if (transfer.getTransferaudio() != null){
        	attributesCode.append("transferaudio=\"" + transfer.getTransferaudio()  + QUOTE_SPACE);           
        }
        return attributesCode;
    }

	private StringBuilder renderTransferEventsList(Transfer transfer,  String flowURL) {
                
        boolean isConnectionerror = false;
        boolean isError = false;
        boolean isHangup = false;
        String transferType = transfer.getType();

        List<String> customEvents = new ArrayList<String>();
        
        StringBuilder eventsListCode = new StringBuilder();
        
        Iterator<String> it = transfer.getEventsList().iterator();

        //Parses the events added to the eventsList to be in control at this specific transfer.
        while (it.hasNext()) {
            String event = it.next();
            
            if (TransferEvents.HANGUP.toString().equalsIgnoreCase(event)) {
                isHangup = true;
            } else if (TransferEvents.ERROR.toString().equalsIgnoreCase(event)){
                isError = true;
            } else if (TransferEvents.CONNECTIONERROR.toString().equalsIgnoreCase(event)) {
                isConnectionerror = true;
            //If it is not any of the defined events, it must be a custom event.
            } else if (isNotAnyFilledEvent(event)) {
                customEvents.add(event);
            }                

        }
        
        //Renders catch tags for the specified events
        eventsListCode.append(catchEvents(flowURL, transferType, isHangup, isError, isConnectionerror, customEvents ));
        
        //Renders a filled tag to catch the events that are known because of the content of the transfer variable.
        eventsListCode.append(filledEvents(flowURL));
                
        return eventsListCode;
    }

    private StringBuilder catchEvents(String flowURL, String transferType, boolean isHangup,
                                        boolean isError, boolean isConnectionerror, List<String> customEvents) {

        StringBuilder catchEventsCode = new StringBuilder();

        /********* CATCH for connection.disconnect.transfer *********/
        /*********       N/A for bridge transfer type    *********/
        if (!TransferType.BRIDGE.toString().equalsIgnoreCase(transferType)){
 
        	catchEventsCode.append("<catch event=\"connection.disconnect.transfer\">");
            //Redirects back to the application server with transferred as _eventId param value. event and duration also as request parameters.          
            catchEventsCode.append("<var name=\"duration\" expr=\"transferResult$.duration\" />");
            catchEventsCode.append(EVENT_VAR_DECLARATION+ TransferEvents.TRANSFERRED.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
            catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + TransferEvents.TRANSFERRED.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT_DURATION + END_TAG);
            catchEventsCode.append(CATCH_END_TAG);
            
        }
        
        /**************** CATCH for connection.disconnect.hangup *************************/
        /***************** Present only if specified at the list of events to be controlled **************************/
        if (isHangup){
            
            catchEventsCode.append("<catch event=\"connection.disconnect.hangup\">");
            //Redirects back to the application server with hangup as _eventId param value. event and duration also as request parameters.          
            catchEventsCode.append("<var name=\"duration\" expr=\"transferResult$.duration\" />");
            catchEventsCode.append(EVENT_VAR_DECLARATION + TransferEvents.HANGUP.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
            catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + TransferEvents.HANGUP.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT_DURATION + END_TAG);
            catchEventsCode.append(CATCH_END_TAG);

        }

        /*************************** CATCH for Custom Events *****************************/
        /***************** Custom events specified at the list of events to be controlled **************************/
        /*********It must be before error or error.connection catch. Otherwise, it would be overridden by them.**********/
        if (customEvents != null){
            
            for(String event : customEvents) {
                catchEventsCode.append("<catch event=\""+ event +"\">");
                //Redirects back to the application server with the name of the custome event as _eventId param value. event also as request parameter.          
                catchEventsCode.append(EVENT_VAR_DECLARATION + event + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
                catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + event + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
                catchEventsCode.append(CATCH_END_TAG);
            }
            
        }        
        
        /**************************** CATCH for error.connection ********************************/
        /********************* Present only if specified at the list of events to be controlled *****************************/
        /*****It must be above error catch. Otherwise, it would be overridden by error catch ******/
        if (isConnectionerror){
            
            catchEventsCode.append("<catch event=\"error.connection\">");
            //Redirects back to the application server with connectionerror as _eventId param value. event also as request parameter.          
            catchEventsCode.append(EVENT_VAR_DECLARATION + TransferEvents.CONNECTIONERROR.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
            catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + TransferEvents.CONNECTIONERROR.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
            catchEventsCode.append(CATCH_END_TAG);

        }
        
        /**************************** CATCH for error ********************************/
        /********************* Present only if specified at the list of events to be controlled *****************************/
        /*****It must be at the bottom. Otherwise, it would override other catches******/
        if (isError){            
            catchEventsCode.append("<catch event=\"error\">");
            //Redirects back to the application server with error as _eventId param value. event also as request parameter.          
            catchEventsCode.append(EVENT_VAR_DECLARATION + TransferEvents.ERROR.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
            catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + TransferEvents.ERROR.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
            catchEventsCode.append(CATCH_END_TAG);            
        }
        
        return catchEventsCode;
    }

    private StringBuilder filledEvents(String flowURL) {
        
        StringBuilder filledEventsCode = new StringBuilder();

        //Events filled at the transfer Javascript variable
        filledEventsCode.append("<filled>");    
        //Redirects back to the application server with the value of the transfer javascript variable as _eventId param value. event and duration also as request parameters.          
        filledEventsCode.append("<var name=\"url\" expr=\"'"+ flowURL +"'+'" + AMPERSAND +"' + '"+ EVENT_ID +"' + transferResult\" "+ END_TAG);
        filledEventsCode.append("<var name=\"duration\" expr=\"transferResult$.duration\" />");
        filledEventsCode.append("<var name=\"event\" expr=\"transferResult\" />");
        filledEventsCode.append("<submit expr=\"url\" namelist=\"duration event\" />");        
        filledEventsCode.append("</filled>");
        
        return filledEventsCode;
    }

    private boolean isNotAnyFilledEvent(String event) {
        for (TransferEvents transferEvents : TransferEvents.values()) {
            if (transferEvents.name().equalsIgnoreCase(event)) {
                return false;
            }
        }

        return true;        
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
        List<AudioItem> audioItemsList = record.getAudioItemsList();
        code2render.append(renderAudioItems(audioItemsList));
        
        //Renders record properties, if there are
        if (!record.getProperties().isEmpty()){
            code2render.append(renderProperties(record.getProperties()));        	
        }
        
        //Render catches for defined events and always recorded event (filled)
        code2render.append(renderRecordEventsList(record, flowURL));
        
        //Record end tag
        code2render.append("</record>");
        
        return code2render.toString();
    }

    private StringBuilder renderRecordEventsList(Record record, String flowURL) {
        
        boolean isError = false;
        boolean isHangup = false;
        boolean isNoresource = false;
        boolean isRecordunsupported = false;
        
        List<String> customEvents = new ArrayList<String>();
        
        StringBuilder eventsListCode = new StringBuilder();
        
        Iterator<String> it = record.getEventsList().iterator();

        //Parses the events added to the eventsList to be in control at this specific record to know which they are.
        while (it.hasNext()) {
            String event = it.next();
            
            if (RecordEvents.HANGUP.toString().equalsIgnoreCase(event)) {
                isHangup = true;
                continue;
            } else if (RecordEvents.ERROR.toString().equalsIgnoreCase(event)){
                isError = true;
                continue;
            } else if (RecordEvents.NORESOURCE.toString().equalsIgnoreCase(event)) {
            	isNoresource = true;
                continue;
            } else if (RecordEvents.RECORDUNSUPPORTED.toString().equalsIgnoreCase(event)) {
            	isRecordunsupported = true;
                continue;
            //If it is not any of the defined events, it must be a custom event.
            } else if (!RecordEvents.RECORDED.toString().equalsIgnoreCase(event)) {
                customEvents.add(event);
            }
        }
        
        //Renders VXML catches for the events in the list.
        eventsListCode.append(renderRecordCatchEvents(flowURL, isHangup, isError, isNoresource, isRecordunsupported, customEvents));
        
        //Renders VXML <filled> for the recorded event.
        eventsListCode.append(renderRecordFilledEvent(flowURL));
        
        return eventsListCode;
    }

	private StringBuilder renderRecordFilledEvent(String flowURL) {
		
		StringBuilder filledEventCode = new StringBuilder();
		
		filledEventCode.append("<filled>");
		//Takes values from record result.
		filledEventCode.append("<var name=\"duration\" expr=\"temprecording$.duration\" />");
		filledEventCode.append("<var name=\"size\" expr=\"temprecording$.size\" />");
		filledEventCode.append("<var name=\"termchar\" expr=\"temprecording$.termchar\" />");
		filledEventCode.append("<var name=\"maxtime\" expr=\"temprecording$.maxtime\" />");
		filledEventCode.append(EVENT_VAR_DECLARATION + RecordEvents.RECORDED.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
		
		//Redirects back to the application server with recorded as _eventId param value.
		//Other request parameters are: temprecording, event, duration, size, termchar and maxtime.
		//method must be "post" and enctype must be "multipart/form-data" because the recording is being sent at the request.
		filledEventCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + RecordEvents.RECORDED.toString().toLowerCase() + QUOTE_SPACE + "namelist=\"temprecording event duration size termchar maxtime\" method=\"post\" enctype=\"multipart/form-data\" " + END_TAG);

		filledEventCode.append("</filled>");
		
		
		return filledEventCode;
	}

	private StringBuilder renderRecordCatchEvents(String flowURL, boolean isHangup,
			boolean isError, boolean isNoresource, boolean isRecordunsupported,
			List<String> customEvents) {
		
		StringBuilder catchEventsCode = new StringBuilder();
		
		//Hangup event
		if (isHangup){
	    	catchEventsCode.append("<catch event=\"connection.disconnect.hangup\">");
	    	catchEventsCode.append(EVENT_VAR_DECLARATION + RecordEvents.HANGUP.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
	        //Redirects back to the application server with hangup as _eventId param value.          
	        catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + RecordEvents.HANGUP.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
	        catchEventsCode.append(CATCH_END_TAG);			
		}

		//Custom Events. Defined directly by the developer.
		if (customEvents != null){
            
            for(String event : customEvents) {
                catchEventsCode.append("<catch event=\""+ event +"\">");
    	    	catchEventsCode.append(EVENT_VAR_DECLARATION + event + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
                //Redirects back to the application server with the name of the custom event as _eventId param value. event also as request parameter.          
                catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + event + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
                catchEventsCode.append(CATCH_END_TAG);
            }
            
        }        	

		//noresource event
		if (isNoresource){
	    	catchEventsCode.append("<catch event=\"error.noresource\">");
	    	catchEventsCode.append(EVENT_VAR_DECLARATION + RecordEvents.NORESOURCE.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
	        //Redirects back to the application server with noresource as _eventId param value.
	    	catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + RecordEvents.NORESOURCE.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
	        catchEventsCode.append(CATCH_END_TAG);			
		}

		//recordunsupported event
		if (isRecordunsupported){
	    	catchEventsCode.append("<catch event=\"error.unsupported.record\">");
	    	catchEventsCode.append(EVENT_VAR_DECLARATION + RecordEvents.RECORDUNSUPPORTED.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
	        //Redirects back to the application server with recordunsupported as _eventId param value.
	    	catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + RecordEvents.RECORDUNSUPPORTED.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
	        catchEventsCode.append(CATCH_END_TAG);			
		}

		//error event
		if (isError){
	    	catchEventsCode.append("<catch event=\"error\">");
	    	catchEventsCode.append(EVENT_VAR_DECLARATION + RecordEvents.ERROR.toString().toLowerCase() + SINGLE_QUOTE + QUOTE_SPACE + END_TAG);
	        //Redirects back to the application server with error as _eventId param value.
	    	catchEventsCode.append(SUBMIT_TAG + flowURL + AMPERSAND + EVENT_ID + RecordEvents.ERROR.toString().toLowerCase() + QUOTE_SPACE + NAMELIST_EVENT + END_TAG);
	        catchEventsCode.append(CATCH_END_TAG);			
		}

        return catchEventsCode;
	}

	private StringBuilder renderProperties(Map<String, String> properties) {
        StringBuilder propsCode = new StringBuilder();
        
        Iterator<Entry<String, String>> it = properties.entrySet().iterator();
        
        //Renders each property
        while (it.hasNext()){
            Entry<String, String> pair = it.next();           
            propsCode.append("<property name=\""+ pair.getKey() + "\" value=\"" + pair.getValue() + "\" />");
        }
        
        return propsCode;
	}

	private StringBuilder renderAudioItems(List<AudioItem> audioItemsList) {
        
    	StringBuilder audioItemsCode = new StringBuilder();
        
        for (int i=0;i<audioItemsList.size();i++){
            
            audioItemsCode.append("<prompt");
                        
            //Adds cond prompt attribute if specified
            if (audioItemsList.get(i).getCond() != null){
                audioItemsCode.append(" cond=\"" + audioItemsList.get(i).getCond() + "\"");
            }
            
            //Ends prompt start tag
            audioItemsCode.append(">");

            if (audioItemsList.get(i).getSrc() == null){
                //TTS
                audioItemsCode.append(audioItemsList.get(i).getWording());                
            }else if (audioItemsList.get(i).getWording() == null){
                audioItemsCode.append(AUDIO_START_TAG + SRC_ATTRIBUTE_QUOTE + audioItemsList.get(i).getSrc() + QUOTE + END_TAG);
            }else{
                //Audio with TTS backup prompt
                audioItemsCode.append(AUDIO_START_TAG + SRC_ATTRIBUTE_QUOTE + audioItemsList.get(i).getSrc() + QUOTE + CLOSE_TAG);
                audioItemsCode.append(audioItemsList.get(i).getWording());
                audioItemsCode.append("</audio>");
            }
            
            //Prompt end tag
            audioItemsCode.append("</prompt>");
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

}
