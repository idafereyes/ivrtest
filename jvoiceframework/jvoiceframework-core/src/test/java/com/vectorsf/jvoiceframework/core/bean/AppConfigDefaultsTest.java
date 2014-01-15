package com.vectorsf.jvoiceframework.core.bean;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class AppConfigDefaultsTest {
	
	private static final String PARAM_AUDIOS_FORMAT_SUFFIX = ".abc";
	private static final String PARAM_AUDIOS_LOCATION_PREFIX = "/miruta";
	private static final boolean PARAM_BARGEIN = true;
	private static final boolean PARAM_CATCH_HANGUP = true;
	private static final String PARAM_CONFIDENCE = "0.4";
	private static final boolean PARAM_FLUSH = true;
	private static final String PARAM_GRAMMAR_PATH = "/migramatica";
	private static final String PARAM_GRAMMAR_FILE_EXTENSION = ".gramatica";
	private static final String PARAM_GRAMMAR_TYPE = "type/gramatica";
	private static final boolean PARAM_INPUT_BARGEIN = true;
	private static final int PARAM_INPUT_MAX_ATTEMPTS = 8;
	private static final int PARAM_INPUT_NO_INPUT_ATTEMPTS = 5;
	private static final int PARAM_INPUT_NO_MATCH_ATTEMPTS = 5;
	private static final String PARAM_INTERDIGITTIMEOUT = "9s";
	private static final boolean PARAM_RECORD_BEEP = true;
	private static final boolean PARAM_RECORD_DTMFTERM = true;
	private static final String PARAM_RECORD_FILENAME = "recordFileName.mp3";
	private static final String PARAM_RECORD_FILEPATH = "C:\\DATOS\\audios";
	private static final String PARAM_RECORD_FINALSILENCE = "11s";
	private static final boolean PARAM_RECORD_KEEP = true;
	private static final String PARAM_RECORD_MAXTIME = "50s";
	private static final String PARAM_TIMEOUT = "2s";
	private static final String PARAM_TRANSFER_CONNECTION_TIMEOUT = "15s";
	private static final Locale PARAM_USER_LOCALE = new Locale("en", "GB");
	private static final String PARAM_SENSITIVITY = "0.1";
	private static final String PARAM_SPEEDVSACCURACY= "0.2";
	private static final String PARAM_MAXSPEECHTIMEOUT= "11s";
	private static final String PARAM_COMPLETETIMEOUT= "3s";
	private static final String PARAM_INCOMPLETETIMEOUT= "0.1s";
	private static final boolean PARAM_RECORDUTTERANCE = true;

	
	
	private AppConfigDefaults configDefaults;
	
	@Before
	public void setUp() {
		configDefaults = new AppConfigDefaults();
	}
	
	@Test
	public void testConfigAudiosFormatSuffsix(){
		configDefaults.setAudiosFormatSuffix(PARAM_AUDIOS_FORMAT_SUFFIX);
		assertEquals("Checking AppConfigDefaults audios format suffix", PARAM_AUDIOS_FORMAT_SUFFIX, configDefaults.getAudiosFormatSuffix());
	}

	@Test
	public void testConfigAudiosLocationPrefix(){
		configDefaults.setAudiosFormatSuffix(PARAM_AUDIOS_LOCATION_PREFIX);
		assertEquals("Checking AppConfigDefaults audios location prefix", PARAM_AUDIOS_LOCATION_PREFIX, configDefaults.getAudiosFormatSuffix());
	}
	
	@Test
	public void testConfigBargein(){
		configDefaults.setBargein(PARAM_BARGEIN);
		assertEquals("Checking AppConfigDefaults bargein", PARAM_BARGEIN, configDefaults.isBargein());
	}
	
	@Test
	public void testConfigCatchHangup(){
		configDefaults.setCatchHangup(PARAM_CATCH_HANGUP);
		assertEquals("Checking AppConfigDefaults catch hangup", PARAM_CATCH_HANGUP, configDefaults.isCatchHangup());
	}
	
	@Test
	public void testConfigConfidence(){
		configDefaults.setConfidence(PARAM_CONFIDENCE);
		assertEquals("Checking AppConfigDefaults confidence", PARAM_CONFIDENCE, configDefaults.getConfidence());
	}
	
	@Test
	public void testConfigFlush(){
		configDefaults.setFlush(PARAM_FLUSH);
		assertEquals("Checking AppConfigDefaults flush", PARAM_FLUSH, configDefaults.isFlush());
	}
	
	@Test
	public void testConfigGrammarPath(){
		configDefaults.setGrammarPath(PARAM_GRAMMAR_PATH);
		assertEquals("Checking AppConfigDefaults grammar path", PARAM_GRAMMAR_PATH, configDefaults.getGrammarPath());
	}
	
	@Test
	public void testConfigGrammarFileExtension(){
		configDefaults.setGrammarsFileExtension(PARAM_GRAMMAR_FILE_EXTENSION);
		assertEquals("Checking AppConfigDefaults grammar file extension", PARAM_GRAMMAR_FILE_EXTENSION, configDefaults.getGrammarsFileExtension());
	}
	
	@Test
	public void testConfigGrammarType(){
		configDefaults.setGrammarType(PARAM_GRAMMAR_TYPE);
		assertEquals("Checking AppConfigDefaults grammar type", PARAM_GRAMMAR_TYPE, configDefaults.getGrammarType());
	}
	
	@Test
	public void testConfigInputBargein(){
		configDefaults.setInputBargein(PARAM_INPUT_BARGEIN);
		assertEquals("Checking AppConfigDefaults input bargein", PARAM_INPUT_BARGEIN, configDefaults.isInputBargein());
	}
	
	@Test
	public void testConfigInputMaxAttempts(){
		configDefaults.setInputMaxAttempts(PARAM_INPUT_MAX_ATTEMPTS);
		assertEquals("Checking AppConfigDefaults input max attempts", PARAM_INPUT_MAX_ATTEMPTS, configDefaults.getInputMaxAttempts());
	}
	
	@Test
	public void testConfigInputNoInputAttempts(){
		configDefaults.setInputNoInputAttempts(PARAM_INPUT_NO_INPUT_ATTEMPTS);
		assertEquals("Checking AppConfigDefaults input no input attempts", PARAM_INPUT_NO_INPUT_ATTEMPTS, configDefaults.getInputNoInputAttempts());
	}
	
	@Test
	public void testConfigInputNoMatchAttempts(){
		configDefaults.setInputNoMatchAttempts(PARAM_INPUT_NO_MATCH_ATTEMPTS);
		assertEquals("Checking AppConfigDefaults input no match attempts", PARAM_INPUT_NO_MATCH_ATTEMPTS, configDefaults.getInputNoMatchAttempts());
	}
	
	@Test
	public void testConfigInterdigitsTiemout(){
		configDefaults.setInterdigittimeout(PARAM_INTERDIGITTIMEOUT);
		assertEquals("Checking AppConfigDefaults interdigit timeout", PARAM_INTERDIGITTIMEOUT, configDefaults.getInterdigittimeout());
	}
	
	@Test
	public void testConfigRecordBeep(){
		configDefaults.setRecordBeep(PARAM_RECORD_BEEP);
		assertEquals("Checking AppConfigDefaults record beep", PARAM_RECORD_BEEP, configDefaults.isRecordBeep());
	}
	
	@Test
	public void testConfigRecordDtmfterm(){
		configDefaults.setRecordDtmfterm(PARAM_RECORD_DTMFTERM);
		assertEquals("Checking AppConfigDefaults record dtmfterm", PARAM_RECORD_DTMFTERM, configDefaults.isRecordDtmfterm());
	}
	
	@Test
	public void testConfigRecordFilename(){
		configDefaults.setRecordFileName(PARAM_RECORD_FILENAME);
		assertEquals("Checking AppConfigDefaults record filename", PARAM_RECORD_FILENAME, configDefaults.getRecordFileName());
	}
	
	@Test
	public void testConfigRecordFilepath(){
		configDefaults.setRecordFilePath(PARAM_RECORD_FILEPATH);
		assertEquals("Checking AppConfigDefaults record filepath", PARAM_RECORD_FILEPATH, configDefaults.getRecordFilePath());
	}
	
	@Test
	public void testConfigRecordFinalsilence(){
		configDefaults.setRecordFinalsilence(PARAM_RECORD_FINALSILENCE);
		assertEquals("Checking AppConfigDefaults record final silence", PARAM_RECORD_FINALSILENCE, configDefaults.getRecordFinalsilence());
	}
	
	@Test
	public void testConfigRecordKeep(){
		configDefaults.setRecordKeep(PARAM_RECORD_KEEP);
		assertEquals("Checking AppConfigDefaults record keep", PARAM_RECORD_KEEP, configDefaults.isRecordKeep());
	}
	
	@Test
	public void testConfigRecordMaxtime(){
		configDefaults.setRecordMaxtime(PARAM_RECORD_MAXTIME);
		assertEquals("Checking AppConfigDefaults record maxtime", PARAM_RECORD_MAXTIME, configDefaults.getRecordMaxtime());
	}
	
	@Test
	public void testConfigTimeout(){
		configDefaults.setTimeout(PARAM_TIMEOUT);
		assertEquals("Checking AppConfigDefaults timeout", PARAM_TIMEOUT, configDefaults.getTimeout());
	}
	
	@Test
	public void testConfigTransferConnectionTimeout(){
		configDefaults.setTransferConnectiontimeout(PARAM_TRANSFER_CONNECTION_TIMEOUT);
		assertEquals("Checking AppConfigDefaults transfer connection timeout", PARAM_TRANSFER_CONNECTION_TIMEOUT, configDefaults.getTransferConnectiontimeout());
	}
	
	@Test
	public void testConfigUserLocale(){
		configDefaults.setUserLocale(PARAM_USER_LOCALE);
		assertEquals("Checking AppConfigDefaults user locale", PARAM_USER_LOCALE, configDefaults.getUserLocale());
	}
	
	@Test
	public void testConfigSensitivity(){
		configDefaults.setSensitivity(PARAM_SENSITIVITY);
		assertEquals("Checking AppConfigDefaults sensitivity", PARAM_SENSITIVITY, configDefaults.getSensitivity());
	}
	
	@Test
	public void testConfigSpeedvsaccuracy(){
		configDefaults.setSpeedvsaccuracy(PARAM_SPEEDVSACCURACY);
		assertEquals("Checking AppConfigDefaults speedvsaccuracy", PARAM_SPEEDVSACCURACY, configDefaults.getSpeedvsaccuracy());
	}

	@Test
	public void testConfigMaxspeechtimeout(){
		configDefaults.setMaxspeechtimeout(PARAM_MAXSPEECHTIMEOUT);
		assertEquals("Checking AppConfigDefaults maxspeechtimeout", PARAM_MAXSPEECHTIMEOUT, configDefaults.getMaxspeechtimeout());
	}
	
	@Test
	public void testConfigCompleteTimeout(){
		configDefaults.setCompletetimeout(PARAM_COMPLETETIMEOUT);
		assertEquals("Checking AppConfigDefaults completetimeout", PARAM_COMPLETETIMEOUT, configDefaults.getCompletetimeout());
	}

	@Test
	public void testConfigIncompleteTimeout(){
		configDefaults.setIncompletetimeout(PARAM_INCOMPLETETIMEOUT);
		assertEquals("Checking AppConfigDefaults incompletetimeout", PARAM_INCOMPLETETIMEOUT, configDefaults.getIncompletetimeout());
	}

	@Test
	public void testConfigRecordutterancet(){
		configDefaults.setRecordutterance(PARAM_RECORDUTTERANCE);
		assertEquals("Checking AppConfigDefaults recordutterance", PARAM_RECORDUTTERANCE, configDefaults.isRecordutterance());
	}

}
