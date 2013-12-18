package com.vectorsf.jvoiceframework.isban.logger.aspects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.isban.logger.enums.PromptType;
import com.vectorsf.jvoiceframework.isban.logger.log.StatisticsLogger;

public abstract class AudioItemsLogger {
	
	@Autowired
	private StatisticsLogger st;
	
	public StatisticsLogger getSt() {
		return st;
	}

	public void setSt(StatisticsLogger st) {
		this.st = st;
	}

	public void logAudioItems(List<AudioItem> audioItems){
		
        for(AudioItem ai : audioItems) {
        	
        	//Si no hay condición o es true, se va a cantar el prompt y se pinta la traza
        	if (ai.getCondition() == null || ai.getCondition().equals("true")){
        		if (ai.getSrc() == null){
        			//TTS
            		st.SPEECH("", PromptType.TTS.toString(), ai.getWording().getText());        		
	        	
        		}else if (ai.getWording() == null || ai.getWording().getText() == null){
	            //Audio without TTS backup prompt
        			st.SPEECH(ai.getSrc(), PromptType.FILE.toString(), ai.getWording().getText());
		        }else{
		    		//Audio with TTS backup prompt
        			st.SPEECH(ai.getSrc(), PromptType.FILE.toString(), "");		
		        }
        	}
        }

	}

}
