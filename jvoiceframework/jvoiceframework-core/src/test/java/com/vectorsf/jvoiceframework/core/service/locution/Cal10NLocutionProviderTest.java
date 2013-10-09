package com.vectorsf.jvoiceframework.core.service.locution;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import ch.qos.cal10n.MessageConveyor;

import com.vectorsf.jvoiceframework.core.bean.User;
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
		  
		  User user = mock(User.class);
		  when(user.getLocale()).thenReturn(defaultLocale);
		  
		  ExtendedLocLogger logger = mock(ExtendedLocLogger.class);
		  
		  locutionprovider = new Cal10NLocutionProvider();	 
		  locutionprovider.setUser(user);
		  locutionprovider.setLogger(logger);
		  locutionprovider.setLocationPrefix("builtin:jvoice-test-app/");
		  locutionprovider.setFormatSuffix("");
	  }
		
	
	  @Test
	  public void testGetWordingWithDefaultLocale() throws Exception {
		  String wording = locutionprovider.getWording(TestLocution.TEST_WORDING_KEY);	
		  assertEquals(wording, new MessageConveyor(defaultLocale).getMessage(TestLocution.TEST_WORDING_KEY));
	  }

	  @Test
	  public void testGetAudioSrcWithDefaultLocale() throws Exception {
		  String src = locutionprovider.getAudioSrc(TestLocution.TEST_AUDIO_SRC_KEY);	
		  assertEquals(src, locutionprovider.getLocationPrefix() + new MessageConveyor(defaultLocale).getMessage(TestLocution.TEST_AUDIO_SRC_KEY) + locutionprovider.getFormatSuffix());
	  }

	  @Test
	  public void testGetWordingWithCustomLocale() throws Exception {	
		  Locale locale = new Locale("en", "US");	
		  String wording = locutionprovider.getWording(TestLocution.TEST_WORDING_KEY, locale);		
		  assertEquals(wording, new MessageConveyor(locale).getMessage(TestLocution.TEST_WORDING_KEY));
	  }

	  @Test
	  public void testGetAudioSrcWithCustomLocale() throws Exception {	
		  Locale locale = new Locale("en", "US");	
		  String src = locutionprovider.getAudioSrc(TestLocution.TEST_AUDIO_SRC_KEY, locale);
		  assertEquals(src, locutionprovider.getLocationPrefix() + new MessageConveyor(locale).getMessage(TestLocution.TEST_AUDIO_SRC_KEY) + locutionprovider.getFormatSuffix());
	  }

	  @Test
	  public void testArgs() throws Exception {
		  final String arg = "etc";
		  String wording = locutionprovider.getWording(TestLocution.TEST_WORDING_KEY_WITH_ARGS, arg);
		  assertEquals(wording, new MessageConveyor(defaultLocale).getMessage(TestLocution.TEST_WORDING_KEY_WITH_ARGS, arg));
	  }
	  
	  @Test
	  public void testLocaleNotFoundAtGetWording() throws Exception {
		  Exception ex = null;
		  try {
			  Locale locale = new Locale("en", "UK");
			  locutionprovider.getWording(TestLocution.TEST_WORDING_KEY, locale);	
		  } catch (LocutionProviderException lpe) {
				ex = lpe;
		  }	
		  assertTrue(ex instanceof LocutionProviderException);	  
	  }
	  
	  @Test
	  public void testLocaleNotFoundAtGetAudioSrc() throws Exception {
		  Exception ex = null;
		  try {
			  Locale locale = new Locale("en", "UK");
			  locutionprovider.getAudioSrc(TestLocution.TEST_AUDIO_SRC_KEY, locale);	
		  } catch (LocutionProviderException lpe) {
				ex = lpe;
		  }	
		  assertTrue(ex instanceof LocutionProviderException);	  
	  }

}
