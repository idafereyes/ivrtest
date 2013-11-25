package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LastInputResultTest {
	
	private static final String PARAM_CONFIDENCE = "0.8";
	private static final String PARAM_INPUTMODE = "dtmf";
	private static final String PARAM_INTERPRETATION = "cuentas";
	private static final String PARAM_UTTERANCE = "quiero mis cuentas";
	
	@Test
	public void testLastInputResult(){

		LastInputResult lastInputResult = new LastInputResult();
		
		lastInputResult.setConfidence(PARAM_CONFIDENCE);
		assertEquals("Checking lastInputResult confidence", PARAM_CONFIDENCE, lastInputResult.getConfidence());
		
		lastInputResult.setInputmode(PARAM_INPUTMODE);
		assertEquals("Checking lastInputResult input mode", PARAM_INPUTMODE, lastInputResult.getInputmode());

		lastInputResult.setInterpretation(PARAM_INTERPRETATION);
		assertEquals("Checking lastInputResult interpretation", PARAM_INTERPRETATION, lastInputResult.getInterpretation());

		lastInputResult.setUtterance(PARAM_UTTERANCE);
		assertEquals("Checking lastInputResult utterance", PARAM_UTTERANCE, lastInputResult.getUtterance());

	}
}
