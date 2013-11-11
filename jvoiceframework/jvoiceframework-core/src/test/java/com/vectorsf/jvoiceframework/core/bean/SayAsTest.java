package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.vectorsf.jvoiceframework.core.enums.InterpretAs;

public class SayAsTest {
	
	private static final String PARAM_FORMAT = "ordinal";
	private static final InterpretAs PARAM_INTERPRET_AS = InterpretAs.NUMBER;
	
	@Test
	public void testSayAs(){

		SayAs sayAs = new SayAs();
		
		sayAs.setFormat(PARAM_FORMAT);
		assertEquals("Checking SayAs format", PARAM_FORMAT, sayAs.getFormat());
		
		sayAs.setInterpretAs(PARAM_INTERPRET_AS);
		assertEquals("Checking SayAs interpret as", PARAM_INTERPRET_AS, sayAs.getInterpretAs());
	}
	
	@Test
	public void testSayAsConstructor1(){
		
		SayAs sayAs = new SayAs(PARAM_INTERPRET_AS);
		assertEquals("Checking SayAs constructor1 interpret as", PARAM_INTERPRET_AS, sayAs.getInterpretAs());
		assertNull("Checking SayAs constructor1 format", sayAs.getFormat());
		
	}
	
	@Test
	public void testSayAsConstructor2(){
		
		SayAs sayAs = new SayAs(PARAM_INTERPRET_AS, PARAM_FORMAT);
		assertEquals("Checking SayAs constructor2 interpret as", PARAM_INTERPRET_AS, sayAs.getInterpretAs());
		assertEquals("Checking SayAs constructor2 format", PARAM_FORMAT, sayAs.getFormat());
		
	}
}
