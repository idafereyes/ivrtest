package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConfigDefaultsTest {

	private static final boolean PARAM_BARGEIN = true;
	private static final boolean PARAM_FLUSH = false;
	private static final boolean PARAM_CATCH_HANGUP = false;
	private static final String PARAM_TRANSFER_CONNECTION_TIMEOUT = "10s";
	private static final String PARAM_TRANSFER_MAX_TIMEOUT = "20s";
	private static final boolean PARAM_RECORD_BEEP = false;
	private static final boolean PARAM_RECORD_DTMFTERM = false;
	private static final String PARAM_RECORD_FINALSILENCE = "5s";
	private static final String PARAM_RECORD_MAXTIME = "10s";
	private static final String PARAM_RECORD_FILENAME = "recordedAudio.wav";
	private static final String PARAM_RECORD_FILEPATH = "C:\\tmp\\recordedAudiosPath\\";
	private static final boolean PARAM_RECORD_KEEP = false;
	private static final boolean PARAM_INPUT_BARGEIN = true;
	private static final int PARAM_INPUT_MAX_ATTEMPTS = 3;
	private static final int PARAM_INPUT_NO_MATCH_ATTEMPTS = 2;
	private static final int PARAM_INPUT_NO_INPUT_ATTEMPTS = 2;
	private static final String PARAM_AUDIOS_LOCATION_PREFIX = "resources/audios/";
	private static final String PARAM_AUDIOS_FORMAT_SUFFIX = ".wav";
	private static final String PARAM_TIMEOUT = "4s";
	private static final String PARAM_INTERDIGITTIMEOUT = "3s";
	private static final String PARAM_CONFIDENCE = "0.5";
	private static final String PARAM_SENSITIVITY = "0.5";
	private static final String PARAM_SPEEDVSACCURACY= "0.5";
	private static final String PARAM_MAXSPEECHTIMEOUT= "20s";
	private static final String PARAM_COMPLETETIMEOUT= "0.25s";
	private static final String PARAM_INCOMPLETETIMEOUT= "2s";
	private static final boolean PARAM_RECORDUTTERANCE = false;
	private static final String PARAM_GRAMMAR_TYPE = "application/srgs+xml";
	private static final String PARAM_GRAMMAR_PATH = "resources/grammars/";
	private static final String PARAM_GRAMMAR_FILE_EXTENSION = ".grxml";

	private static final Locale PARAM_USER_LOCALE = new Locale("es", "ES");

	private static ClassPathXmlApplicationContext applicationContext = null;
	
	private static AppConfigDefaults configDefaults = null;
	
	@BeforeClass
	public static void startContext() {
		applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/bean/test-config-context.xml");
		applicationContext.refresh();
		configDefaults = (AppConfigDefaults)applicationContext.getBean("appConfigDefaults");
	}
	
	
	@Test
	public void testConfigAudiosFormatSuffsix(){
		assertEquals("Checking AppConfigDefaults audios format suffix", PARAM_AUDIOS_FORMAT_SUFFIX, configDefaults.getAudiosFormatSuffix());
	}

	@Test
	public void testConfigAudiosLocationPrefix(){
		assertEquals("Checking AppConfigDefaults audios location prefix", PARAM_AUDIOS_LOCATION_PREFIX, configDefaults.getAudiosLocationPrefix());
	}
	
	@Test
	public void testConfigBargein(){
		assertEquals("Checking AppConfigDefaults bargein", PARAM_BARGEIN, configDefaults.isBargein());
	}
	
	@Test
	public void testConfigCatchHangup(){
		assertEquals("Checking AppConfigDefaults catch hangup", PARAM_CATCH_HANGUP, configDefaults.isCatchHangup());
	}
	
	@Test
	public void testConfigConfidence(){
		assertEquals("Checking AppConfigDefaults confidence", PARAM_CONFIDENCE, configDefaults.getConfidence());
	}
	
	@Test
	public void testConfigFlush(){
		assertEquals("Checking AppConfigDefaults flush", PARAM_FLUSH, configDefaults.isFlush());
	}
	
	@Test
	public void testConfigGrammarPath(){
		assertEquals("Checking AppConfigDefaults grammar path", PARAM_GRAMMAR_PATH, configDefaults.getGrammarPath());
	}
	
	@Test
	public void testConfigGrammarFileExtension(){
		assertEquals("Checking AppConfigDefaults grammar file extension", PARAM_GRAMMAR_FILE_EXTENSION, configDefaults.getGrammarsFileExtension());
	}
	
	@Test
	public void testConfigGrammarType(){
		assertEquals("Checking AppConfigDefaults grammar type", PARAM_GRAMMAR_TYPE, configDefaults.getGrammarType());
	}
	
	@Test
	public void testConfigInputBargein(){
		assertEquals("Checking AppConfigDefaults input bargein", PARAM_INPUT_BARGEIN, configDefaults.isInputBargein());
	}
	
	@Test
	public void testConfigInputMaxAttempts(){
		assertEquals("Checking AppConfigDefaults input max attempts", PARAM_INPUT_MAX_ATTEMPTS, configDefaults.getInputMaxAttempts());
	}
	
	@Test
	public void testConfigInputNoInputAttempts(){
		assertEquals("Checking AppConfigDefaults input no input attempts", PARAM_INPUT_NO_INPUT_ATTEMPTS, configDefaults.getInputNoInputAttempts());
	}
	
	@Test
	public void testConfigInputNoMatchAttempts(){
		assertEquals("Checking AppConfigDefaults input no match attempts", PARAM_INPUT_NO_MATCH_ATTEMPTS, configDefaults.getInputNoMatchAttempts());
	}
	
	@Test
	public void testConfigInterdigitsTiemout(){
		assertEquals("Checking AppConfigDefaults interdigit timeout", PARAM_INTERDIGITTIMEOUT, configDefaults.getInterdigittimeout());
	}
	
	@Test
	public void testConfigRecordBeep(){
		assertEquals("Checking AppConfigDefaults record beep", PARAM_RECORD_BEEP, configDefaults.isRecordBeep());
	}
	
	@Test
	public void testConfigRecordDtmfterm(){
		assertEquals("Checking AppConfigDefaults record dtmfterm", PARAM_RECORD_DTMFTERM, configDefaults.isRecordDtmfterm());
	}
	
	@Test
	public void testConfigRecordFilename(){
		assertEquals("Checking AppConfigDefaults record filename", PARAM_RECORD_FILENAME, configDefaults.getRecordFileName());
	}
	
	@Test
	public void testConfigRecordFilepath(){
		assertEquals("Checking AppConfigDefaults record filepath", PARAM_RECORD_FILEPATH, configDefaults.getRecordFilePath());
	}
	
	@Test
	public void testConfigRecordFinalsilence(){
		assertEquals("Checking AppConfigDefaults record final silence", PARAM_RECORD_FINALSILENCE, configDefaults.getRecordFinalsilence());
	}
	
	@Test
	public void testConfigRecordKeep(){
		assertEquals("Checking AppConfigDefaults record keep", PARAM_RECORD_KEEP, configDefaults.isRecordKeep());
	}
	
	@Test
	public void testConfigRecordMaxtime(){
		assertEquals("Checking AppConfigDefaults record maxtime", PARAM_RECORD_MAXTIME, configDefaults.getRecordMaxtime());
	}
	
	@Test
	public void testConfigTimeout(){
		assertEquals("Checking AppConfigDefaults timeout", PARAM_TIMEOUT, configDefaults.getTimeout());
	}
	
	@Test
	public void testConfigTransferConnectionTimeout(){
		assertEquals("Checking AppConfigDefaults transfer connection timeout", PARAM_TRANSFER_CONNECTION_TIMEOUT, configDefaults.getTransferConnectiontimeout());
	}
	
	@Test
	public void testConfigTransferMaxTimeout(){
		assertEquals("Checking AppConfigDefaults transfer max timeout", PARAM_TRANSFER_MAX_TIMEOUT, configDefaults.getTransferMaxtime());
	}
	
	@Test
	public void testConfigUserLocale(){
		assertEquals("Checking AppConfigDefaults user locale", PARAM_USER_LOCALE, configDefaults.getUserLocale());
	}
	
	@Test
	public void testConfigSensitivity(){
		assertEquals("Checking AppConfigDefaults sensitivity", PARAM_SENSITIVITY, configDefaults.getSensitivity());
	}
	
	@Test
	public void testConfigSpeedvsaccuracy(){
		assertEquals("Checking AppConfigDefaults speedvsaccuracy", PARAM_SPEEDVSACCURACY, configDefaults.getSpeedvsaccuracy());
	}

	@Test
	public void testConfigMaxspeechtimeout(){
		assertEquals("Checking AppConfigDefaults maxspeechtimeout", PARAM_MAXSPEECHTIMEOUT, configDefaults.getMaxspeechtimeout());
	}
	
	@Test
	public void testConfigCompleteTimeout(){
		assertEquals("Checking AppConfigDefaults completetimeout", PARAM_COMPLETETIMEOUT, configDefaults.getCompletetimeout());
	}

	@Test
	public void testConfigIncompleteTimeout(){
		assertEquals("Checking AppConfigDefaults incompletetimeout", PARAM_INCOMPLETETIMEOUT, configDefaults.getIncompletetimeout());
	}

	@Test
	public void testConfigRecordutterancet(){
		assertEquals("Checking AppConfigDefaults recordutterance", PARAM_RECORDUTTERANCE, configDefaults.isRecordutterance());
	}

}
