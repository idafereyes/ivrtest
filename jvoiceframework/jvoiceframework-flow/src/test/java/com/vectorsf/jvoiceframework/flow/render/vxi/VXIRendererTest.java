package com.vectorsf.jvoiceframework.flow.render.vxi;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Grammar;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.bean.Wording;
import com.vectorsf.jvoiceframework.core.enums.RecordEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferType;
import com.vectorsf.jvoiceframework.flow.render.vxi.VXIRenderer;

public class VXIRendererTest {
	
	static final String FLOW_EXECUTION_URL = "http://flowExecutionUrl/";
	static final String RESOURCE_FILE_PATH = "src/test/resources/com/vectorsf/jvoiceframework/flow/render/vxi/";


	@Test
	public void testRenderEmptyStatesList(){
		
		//Given
		
		VXIRenderer vxiRenderer = new VXIRenderer();
		
		List<Object> states = new ArrayList<Object>();

		//When
		String vxmlCode =  vxiRenderer.render(states, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, ""); 
	}
	
	@Test
	public void testRenderEveryState() throws FileNotFoundException {
		
		//Given
				
		List<Object> states = new ArrayList<Object>();
		Output outputMock = mock(Output.class);
		states.add(outputMock);
		Input inputMock = mock(Input.class);
		states.add(inputMock);
		Transfer transferMock = mock(Transfer.class);
		states.add(transferMock);
		Record recordMock = mock(Record.class);
		states.add(recordMock);
		End endMock = mock(End.class);
		states.add(endMock);

		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(states, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "everyState.test")); 

	}

	@Test
	public void testCatchHangupFlushFalseAtOutput() throws FileNotFoundException{
		
		//Given	
		Output outputMock = mock(Output.class);
		when(outputMock.isBargein()).thenReturn(true);
		when(outputMock.isCatchHangup()).thenReturn(false);
		when(outputMock.isFlush()).thenReturn(false);

		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-WELCOME");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn(mock(Wording.class));
		when(audioItem3.getWording().getText()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItems()).thenReturn(audioItemsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "catchHangupFlushFalseAtOutput.test")); 
		
	}
	
	@Test
	public void testFlushTrueAtOutput() throws FileNotFoundException{
		
		//Given	
		Output outputMock = mock(Output.class);
		when(outputMock.isBargein()).thenReturn(false);
		when(outputMock.isCatchHangup()).thenReturn(false);
		when(outputMock.isFlush()).thenReturn(true);

		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-WELCOME");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn(mock(Wording.class));
		when(audioItem3.getWording().getText()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItems()).thenReturn(audioItemsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "flushTrueAtOutput.test")); 
		
	}
	
	@Test
	public void testPropertiesAtOutput() throws FileNotFoundException{
		
		//Given	
		Output outputMock = mock(Output.class);
		when(outputMock.isBargein()).thenReturn(false);
		when(outputMock.isCatchHangup()).thenReturn(false);
		when(outputMock.isFlush()).thenReturn(false);

		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-WELCOME");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn(mock(Wording.class));
		when(audioItem3.getWording().getText()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItems()).thenReturn(audioItemsList);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(outputMock.getProperties()).thenReturn(properties);

		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "propertiesAtOutput.test")); 
		
	}
	
	@Test
	public void testCatchHangupTrueAtOutput() throws FileNotFoundException{
		
		//Given	
		Output outputMock = mock(Output.class);
		when(outputMock.isBargein()).thenReturn(false);
		when(outputMock.isCatchHangup()).thenReturn(true);
		when(outputMock.isFlush()).thenReturn(false);

		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-WELCOME");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn(mock(Wording.class));
		when(audioItem3.getWording().getText()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItems()).thenReturn(audioItemsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "catchHangupTrueAtOutput.test")); 
		
	}

	@Test
	public void testBlindTransfer() throws IOException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString().toLowerCase());

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
		eventsList.add(TransferEvents.HANGUP.toString());
		eventsList.add(TransferEvents.CONNECTIONERROR.toString());
		eventsList.add(TransferEvents.ERROR.toString());
		eventsList.add(TransferEvents.NOANSWER.toString());
		eventsList.add("error.unsupported.transfer");
		
		when(transferMock.getEventsList()).thenReturn(eventsList);
				
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "blindTransfer.test")); 

	}
		
	@Test
	public void testConsultationTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.CONSULTATION.toString().toLowerCase());
		when(transferMock.getTimeout()).thenReturn("15s");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
		eventsList.add(TransferEvents.HANGUP.toString());
		eventsList.add(TransferEvents.CONNECTIONERROR.toString());
		eventsList.add(TransferEvents.ERROR.toString());
		eventsList.add(TransferEvents.NOANSWER.toString());
		eventsList.add("error.unsupported.transfer");
		
		when(transferMock.getEventsList()).thenReturn(eventsList);
				
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "consultationTransfer.test")); 
		
	}

	@Test
	public void testBridgeTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BRIDGE.toString().toLowerCase());
		when(transferMock.getTimeout()).thenReturn("15s");
		when(transferMock.getMaxtime()).thenReturn("30s");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
		eventsList.add(TransferEvents.HANGUP.toString());
		eventsList.add(TransferEvents.CONNECTIONERROR.toString());
		eventsList.add(TransferEvents.ERROR.toString());
		eventsList.add(TransferEvents.NOANSWER.toString());
		eventsList.add("error.unsupported.transfer");
		
		when(transferMock.getEventsList()).thenReturn(eventsList);
				
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "bridgeTransfer.test")); 
		
	}
	
	@Test
	public void testPropertiesAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString().toLowerCase());

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());

		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(transferMock.getProperties()).thenReturn(properties);

		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "propertiesAtTransfer.test")); 
		
	}

	@Test
	public void testCustomEventsAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString().toLowerCase());

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
		eventsList.add("error.unsupported.transfer");
		eventsList.add("error.connection.baddestination");				
				
		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "customEventsAtTransfer.test")); 
		
	}


	@Test
	public void testAttributesNotApplicableAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString().toLowerCase());
		when(transferMock.getTimeout()).thenReturn("10s");
		when(transferMock.getMaxtime()).thenReturn("60s");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
				
		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "attributesNotApplicableAtTransfer.test")); 
		
	}

	@Test
	public void testTransferaudioAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString().toLowerCase());
		when(transferMock.getTransferaudio()).thenReturn("idleMusic");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
				
		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "transferaudioAtTransfer.test")); 
		
	}
	
	@Test
	public void testCompleteRecord() throws FileNotFoundException{
		
		//Given
		Record recordMock = mock(Record.class);
		
		//Attributes
		when(recordMock.isBeep()).thenReturn(true);
		when(recordMock.isDtmfterm()).thenReturn(false);
		when(recordMock.getFinalsilence()).thenReturn("3s");
		when(recordMock.getMaxtime()).thenReturn("30s");
				
		//AudioItemsList
		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-RECORDING");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Por favor, diga su nombre");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-RECORDING-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn(mock(Wording.class));
		when(audioItem3.getWording().getText()).thenReturn("Despues del beep");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(recordMock.getAudioItemsList()).thenReturn(audioItemsList);

		//EventsList
		List<String> eventsList = new ArrayList<String>();
		eventsList.add(RecordEvents.RECORDED.toString());
		eventsList.add("com.nortel.ivr.record.notstored");
		eventsList.add(RecordEvents.HANGUP.toString());
		eventsList.add(RecordEvents.ERROR.toString());
		eventsList.add(RecordEvents.RECORDUNSUPPORTED.toString());
		eventsList.add(RecordEvents.NORESOURCE.toString());
				
		when(recordMock.getEventsList()).thenReturn(eventsList);
		
		//Properties
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(recordMock.getProperties()).thenReturn(properties);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(recordMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "record.test")); 
		
	}
	
	@Test
	public void testPropertiesAtRecord() throws FileNotFoundException{
		
		//Given
		Record recordMock = mock(Record.class);
			
		//Attributes
		when(recordMock.isBeep()).thenReturn(true);
		when(recordMock.isDtmfterm()).thenReturn(false);
		when(recordMock.getFinalsilence()).thenReturn("3s");
		when(recordMock.getMaxtime()).thenReturn("30s");

		//AudioItemsList
		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-RECORDING");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Por favor, diga su nombre");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		
		when(recordMock.getAudioItemsList()).thenReturn(audioItemsList);

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(RecordEvents.RECORDED.toString());
		
		when(recordMock.getEventsList()).thenReturn(eventsList);

		//Properties
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(recordMock.getProperties()).thenReturn(properties);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(recordMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "propertiesAtRecord.test")); 
		
	}
	
	@Test
	public void testCustomEventsAtRecord() throws FileNotFoundException{
		
		//Given
		Record recordMock = mock(Record.class);
			
		//Attributes
		when(recordMock.isBeep()).thenReturn(true);
		when(recordMock.isDtmfterm()).thenReturn(false);
		when(recordMock.getFinalsilence()).thenReturn("3s");
		when(recordMock.getMaxtime()).thenReturn("30s");

		//AudioItemsList
		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-RECORDING");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Por favor, diga su nombre");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		
		when(recordMock.getAudioItemsList()).thenReturn(audioItemsList);

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(RecordEvents.RECORDED.toString());
		eventsList.add("com.nortel.ivr.record.notstored");
		eventsList.add(RecordEvents.HANGUP.toString());
		eventsList.add(RecordEvents.ERROR.toString());
		eventsList.add(RecordEvents.RECORDUNSUPPORTED.toString());
		eventsList.add(RecordEvents.NORESOURCE.toString());
		
		when(recordMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(recordMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "customEventsAtRecord.test")); 
		
	}

	@Test
	public void testAudioItemsAtRecord() throws FileNotFoundException{
		
		//Given
		Record recordMock = mock(Record.class);
			
		//Attributes
		when(recordMock.isBeep()).thenReturn(true);
		when(recordMock.isDtmfterm()).thenReturn(false);
		when(recordMock.getFinalsilence()).thenReturn("3s");
		when(recordMock.getMaxtime()).thenReturn("30s");

		//AudioItemsList
		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-RECORDING");
		when(audioItem1.getWording()).thenReturn(mock(Wording.class));
		when(audioItem1.getWording().getText()).thenReturn("Por favor, diga su nombre");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-RECORDING-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn(mock(Wording.class));
		when(audioItem3.getWording().getText()).thenReturn("Despues del beep");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(recordMock.getAudioItemsList()).thenReturn(audioItemsList);

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(RecordEvents.RECORDED.toString());
		
		when(recordMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(recordMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "audioItemsAtRecord.test")); 
		
	}
	
	@Test
	public void testEnd() throws FileNotFoundException{
		
		//Given
		End endMock = mock(End.class);
			
		//Attributes
		when(endMock.getName()).thenReturn("testEnd");
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(endMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "end.test")); 
		
	}
	
	@Test
	public void testStartPage() throws FileNotFoundException{
		
		//Given
		VXIRenderer vxiRenderer = new VXIRenderer();
		
		//When
		String vxmlCode = vxiRenderer.renderStartPage();
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "startPage.test")); 
		
	}
	
	@Test
	public void testEndPage() throws FileNotFoundException{
		
		//Given
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.renderEndPage();
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile(RESOURCE_FILE_PATH + "endPage.test")); 
		
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
		when(grammarMock.getSrc()).thenReturn("dtmf/digits?length=1");
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
		
		List<String> events = new ArrayList<String>();
		events.add("maxint");
		events.add("maxnomatch");
		events.add("maxnoinput");
		when(inputMock.getEvents()).thenReturn(events);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(inputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/inputDtmf.test")); 
		
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
