package com.vectorsf.jvoiceframework.core.service.locution;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.qos.cal10n.MessageConveyor;

import com.vectorsf.jvoiceframework.core.bean.UserLocale;
import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;

/**
 * Pruebas para la implemetación del proveedor de locuciones basado en Cal10n
 * 
 * @author dmartina
 */
public class Cal10NLocutionProviderTest {
	
	  private Cal10NLocutionProvider locutionprovider;
	  private Locale defaultLocale;
	  
	  @Before
	  public void setUp() {
		  defaultLocale = new Locale("es", "ES");
		  
		  UserLocale userLocale = mock(UserLocale.class);
		  when(userLocale.getLocale()).thenReturn(defaultLocale);
		  
		  ExtendedLocLogger logger = mock(ExtendedLocLogger.class);
		  
		  locutionprovider = new Cal10NLocutionProvider();	 
		  locutionprovider.setUserLocale(userLocale);
		  locutionprovider.setLogger(logger);
	  }
		
	
	  @Test
	  public void testDefaultLocale() throws Exception {
		  String wording = locutionprovider.getLocution(TestLocution.TEST_KEY);	
		  assertEquals(wording, new MessageConveyor(defaultLocale).getMessage(TestLocution.TEST_KEY));
	  }
	  
	  @Test
	  public void testCustomLocale() throws Exception {	
		  Locale locale = new Locale("en", "US");	
		  String wording = locutionprovider.getLocution(TestLocution.TEST_KEY, locale);		
		  assertEquals(wording, new MessageConveyor(locale).getMessage(TestLocution.TEST_KEY));
	  }
	  
	  @Test
	  public void testArgs() throws Exception {
		  final String arg = "etc";
		  String wording = locutionprovider.getLocution(TestLocution.TEST_KEY_WITH_ARGS, arg);
		  assertEquals(wording, new MessageConveyor(defaultLocale).getMessage(TestLocution.TEST_KEY_WITH_ARGS, arg));
	  }
	  
	  @Test
	  public void testKeyNotFound() {
		  Exception ex = null;
		  try {
			locutionprovider.getLocution(TestLocution.TEST_KEY_NOT_PRESENT);
		  } catch (LocutionProviderException lpe) {
				ex = lpe;
		  }	
		  assertTrue(ex instanceof LocutionProviderException);
	  }
	  
	  @Test
	  public void testLocaleNotFound() throws Exception {
		  Exception ex = null;
		  try {
			  Locale locale = new Locale("en", "UK");
			  locutionprovider.getLocution(TestLocution.TEST_KEY, locale);	
		  } catch (LocutionProviderException lpe) {
				ex = lpe;
		  }	
		  assertTrue(ex instanceof LocutionProviderException);	  
	  }
}
