package com.vectorsf.jvoiceframework.isban.logger.render.vxi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.BlindTransfer;
import com.vectorsf.jvoiceframework.core.bean.BridgeTransfer;
import com.vectorsf.jvoiceframework.core.bean.ConsultationTransfer;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Wording;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

public class IsbanLoggerRendererTest{

	static protected final String FLOW_EXECUTION_URL = "http://flowExecutionUrl/app-test/";
	static protected final String CONTEXT_PATH = "/app-test";

	static final protected String ISBAN_LOGGER_RESOURCE_FILE_PATH = "../jvoiceframework-isban-logger/src/test/resources/com/vectorsf/jvoiceframework/isban/logger/render/vxi/";
	
	protected Renderer renderer;
	
	@Before
	public void initializeRenderer(){
		renderer = new IsbanLoggerRenderer();
	}

	@Test
	public void testInputDtmf() throws FileNotFoundException{
		
		//Given
		Input inputMock = mock(Input.class);
		when(inputMock.getName()).thenReturn("INITIAL_MENU");
		when(inputMock.getMaxAttempts()).thenReturn(4);
		when(inputMock.getMaxNoInput()).thenReturn(3);
		when(inputMock.getMaxNoMatch()).thenReturn(3);
		when(inputMock.isBargein()).thenReturn(true);

		Grammar grammarMock = mock(Grammar.class);
		when(grammarMock.getMode()).thenReturn("builtin");
		when(grammarMock.getSrc()).thenReturn("builtin:dtmf/digits?length=1");
		List<Grammar> grammarList = new ArrayList<Grammar>();
		grammarList.add(grammarMock);
		when(inputMock.getGrammars()).thenReturn(grammarList);
		
		AudioItem ai1 = mock(AudioItem.class);
		when(ai1.getWording()).thenReturn(mock(Wording.class));
		when(ai1.getWording().getText()).thenReturn("No le he entendido.");
		when(ai1.getCondition()).thenReturn("noMatchAttempt == 1");
		
		AudioItem ai2 = mock(AudioItem.class);
		when(ai2.getWording()).thenReturn(mock(Wording.class));
		when(ai2.getWording().getText()).thenReturn("Intente hablar mas claro. No le he entendido.");
		when(ai2.getCondition()).thenReturn("noMatchAttempt == 2");
		List<AudioItem> noMatchAudios = new ArrayList<AudioItem>();
		noMatchAudios.add(ai1);
		noMatchAudios.add(ai2);
		when(inputMock.getNoMatchAudios()).thenReturn(noMatchAudios);
		
		AudioItem ai3 = mock(AudioItem.class);
		when(ai3.getWording()).thenReturn(mock(Wording.class));
		when(ai3.getWording().getText()).thenReturn("No le he oido.");
		List<AudioItem> noInputAudios = new ArrayList<AudioItem>();
		noInputAudios.add(ai3);
		when(inputMock.getNoInputAudios()).thenReturn(noInputAudios);
		
		AudioItem ai4 = mock(AudioItem.class);
		when(ai4.getWording()).thenReturn(mock(Wording.class));
		when(ai4.getWording().getText()).thenReturn("Por favor, digame su DNI.");
		List<AudioItem> mainAudios = new ArrayList<AudioItem>();
		mainAudios.add(ai4);
		when(inputMock.getMainAudios()).thenReturn(mainAudios);

		//When
		String vxmlCode = renderer.render(inputMock, FLOW_EXECUTION_URL, CONTEXT_PATH);

		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(ISBAN_LOGGER_RESOURCE_FILE_PATH + "inputDtmf.test")); 
	}
	
	@Test
	public void testRenderEveryState() throws FileNotFoundException {
		
		//Given
				
		List<Object> states = new ArrayList<Object>();
		Output outputMock = mock(Output.class);
		states.add(outputMock);
		Input inputMock = mock(Input.class);
		states.add(inputMock);
		BlindTransfer blindTxMock = mock(BlindTransfer.class);
		states.add(blindTxMock);
		ConsultationTransfer consultationTxMock = mock(ConsultationTransfer.class);
		states.add(consultationTxMock);
		BridgeTransfer bridgeTxMock = mock(BridgeTransfer.class);
		states.add(bridgeTxMock);
		Record recordMock = mock(Record.class);
		states.add(recordMock);
		End endMock = mock(End.class);
		states.add(endMock);

		//When
		String vxmlCode = renderer.render(states, FLOW_EXECUTION_URL, CONTEXT_PATH);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(ISBAN_LOGGER_RESOURCE_FILE_PATH + "everyState.test")); 

	}

	public String readResourceFile(String filename) throws FileNotFoundException{

		StringBuilder text = new StringBuilder();
	    Scanner scanner = new Scanner(new FileInputStream(filename));
	    try {
	      while (scanner.hasNextLine()){
	        text.append(scanner.nextLine());
	      }
	    }
	    finally{
	      scanner.close();
	    }

		return text.toString();	
	}

}
