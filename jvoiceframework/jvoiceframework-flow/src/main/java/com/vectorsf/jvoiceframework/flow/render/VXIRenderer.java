package com.vectorsf.jvoiceframework.flow.render;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Prompt;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;

public class VXIRenderer implements Renderer, Serializable {

	private static final long serialVersionUID = -5854263044507481102L;

    static final String BLOCK_START_TAG = "<block>";
    static final String BLOCK_END_TAG = "</block>";
    static final String SUBMIT_TAG = "<submit next=\"";
    static final String AMPERSAND = "&amp;";

    public String render(Prompt prompt, String flowURL) {
		// TODO Auto-generated method stub
		return null;
	}

    public String render(Output output, String flowURL) {
                
        StringBuilder code2render = new StringBuilder();
        
        //Renders properties if there are
        if (!output.getProperties().isEmpty()){
            code2render.append(renderProperties(output));            
        }
        
        code2render.append(BLOCK_START_TAG);
        
        //Renders audioItemsList
        code2render.append(renderAudioItems(output));

        code2render.append(BLOCK_END_TAG);

        if (output.isCatchHangup() || output.isFlush()){
            //Renders an VXML field dummy to be able to catch the hangup event or send the output immediately to the VXML interpreter
            code2render.append(renderFieldDummy(output, flowURL));            
        }
            
        return code2render.toString();
    }

    private String renderProperties(Output output) {
        
        StringBuilder propsCode = new StringBuilder();
        
         Iterator<Entry<String, String>> it = output.getProperties().entrySet().iterator();
        
        while (it.hasNext()){
            Entry<String, String> pair = it.next();           
            propsCode.append("<property name=\""+ pair.getKey() + "\" value=\"" + pair.getValue() + "\" />");
        }
        
        return propsCode.toString();
    }

    private String renderAudioItems(Output output) {
        
        StringBuilder audioItemsCode = new StringBuilder();

        List<AudioItem> audioItemsList = output.getAudioItemsList();
        
        for (int i=0;i<audioItemsList.size();i++){
            
            audioItemsCode.append("<prompt");
            
            //Adds bargeIn prompt attribute only if specified
            if (output.isBargein()){
                audioItemsCode.append(" bargein=\"true\"");
            }else{
                audioItemsCode.append(" bargein=\"false\"");
            }
            
            //Adds cond prompt attribute if specified
            if (audioItemsList.get(i).getCond() != null){
                audioItemsCode.append(" cond=\"" + audioItemsList.get(i).getCond() + "\"");
            }
            
            audioItemsCode.append(">");

            if (audioItemsList.get(i).getSrc() == null){
                //TTS
                audioItemsCode.append(audioItemsList.get(i).getWording());                
            }else if (audioItemsList.get(i).getWording() == null){
                audioItemsCode.append("<audio src=\""+ audioItemsList.get(i).getSrc() + "\"/>");
            }else{
                //Audio with TTS backup prompt
                audioItemsCode.append("<audio src=\""+ audioItemsList.get(i).getSrc() + "\">");
                audioItemsCode.append(audioItemsList.get(i).getWording());
                audioItemsCode.append("</audio>");
            }
            
            audioItemsCode.append("</prompt>");
        }
        
        return audioItemsCode.toString();
    }

    private String renderCatchHangup(String flowURL) {

        StringBuilder catchHangupCode =  new StringBuilder();
        
        catchHangupCode.append("<catch event=\"connection.disconnect.hangup\">");
        
        catchHangupCode.append(SUBMIT_TAG + flowURL + AMPERSAND + "_eventId_hangup\" />");
        
        catchHangupCode.append("</catch>");

        return catchHangupCode.toString();
    }

    private String renderFieldDummy(Output output, String flowURL) {

        StringBuilder fieldDummyCode = new StringBuilder();
        
        fieldDummyCode.append("<field name=\"dummy\">");
        
        if (output.isCatchHangup()){
            //Renders a VXML catch tag for the hangup event
            fieldDummyCode.append(renderCatchHangup(flowURL));
        }
        
        //TODO revisar que timeout poner y si debe ser diferente en función del valor de catchHangup
        fieldDummyCode.append("<property name=\"timeout\" value=\"0s\" />");
        
        //TODO revisar si se puede eliminar la gramática.
        fieldDummyCode.append("<grammar mode=\"dtmf\" src=\"builtin:dtmf/digits?minlength=1;maxlength=1\"/>");
        
        //Just in case it gets a match, but should never happen
        fieldDummyCode.append("<filled>");
        
        fieldDummyCode.append(SUBMIT_TAG + flowURL + AMPERSAND + "_eventId_success\" />");
        
        fieldDummyCode.append("</filled>");
        
        //NOINPUT and NOMATCH(just in case)
        fieldDummyCode.append("<catch event=\"noinput nomatch\" >");
        
        fieldDummyCode.append(SUBMIT_TAG + flowURL + AMPERSAND + "_eventId_success\" />");

        fieldDummyCode.append("</catch>");

        fieldDummyCode.append("</field>");
        
        return fieldDummyCode.toString();
    }

	public String render(Input prompt, String flowURL) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(Transfer transfer, String flowURL) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(Record record, String flowURL) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(End end, String flowURL) {
		// TODO Auto-generated method stub
		return null;
	}

}
