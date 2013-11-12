package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Locale;

import org.junit.Test;

public class WordingTest {
	
	private static final String PARAM_TEXT = "Texto de prueba";
	private static final Locale locale = new Locale("es_ES");
	
	@Test
	public void testWording(){

		Wording wording = new Wording();
		
		wording.setLocale(locale);
		assertEquals("Checking Wording locale", locale, wording.getLocale());
		
		SayAs sayAs = mock(SayAs.class);
		wording.setSayAs(sayAs);
		assertEquals("Checking Wording say as", sayAs, wording.getSayAs());
		
		wording.setText(PARAM_TEXT);
		assertEquals("Checking Wording text", PARAM_TEXT, wording.getText());
	}
	
	@Test
	public void testWordingConstructor(){
		
		SayAs sayAs = mock(SayAs.class);
		Wording wording = new Wording(PARAM_TEXT, locale, sayAs);
		
		assertEquals("Checking Wording constructor locale", locale, wording.getLocale());
		assertEquals("Checking Wording constructor say as", sayAs, wording.getSayAs());
		assertEquals("Checking Wording constructor text", PARAM_TEXT, wording.getText());
	}
}
