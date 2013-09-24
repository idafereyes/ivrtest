package com.vectorsf.jvoiceframework.flow.render;


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

import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.enums.TransferEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferType;

public class VXIRendererTest {
	
	static final String FLOW_EXECUTION_URL = "http://flowExecutionUrl/";


	@Test
	public void testCatchHangupFlushFalseAtOutput() throws FileNotFoundException{
		
		//Given	
		Output outputMock = mock(Output.class);
		when(outputMock.isBargein()).thenReturn(true);
		when(outputMock.isCatchHangup()).thenReturn(false);
		when(outputMock.isFlush()).thenReturn(false);

		AudioItem audioItem1 = mock(AudioItem.class);
		when(audioItem1.getSrc()).thenReturn("SAN-WELCOME");
		when(audioItem1.getWording()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItemsList()).thenReturn(audioItemsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/catchHangupFlushFalseAtOutput.test")); 
		
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
		when(audioItem1.getWording()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItemsList()).thenReturn(audioItemsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/flushTrueAtOutput.test")); 
		
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
		when(audioItem1.getWording()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItemsList()).thenReturn(audioItemsList);
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(outputMock.getProperties()).thenReturn(properties);

		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/propertiesAtOutput.test")); 
		
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
		when(audioItem1.getWording()).thenReturn("Bienvenido");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-WELCOME-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn("Gracias por llamar");
		
		List<AudioItem> audioItemsList = new ArrayList<AudioItem>();
		audioItemsList.add(audioItem1);
		audioItemsList.add(audioItem2);
		audioItemsList.add(audioItem3);
		
		when(outputMock.getAudioItemsList()).thenReturn(audioItemsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode =  vxiRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/catchHangupTrueAtOutput.test")); 
		
	}

	@Test
	public void testBlindTransfer() throws IOException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString());

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
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/blindTransfer.test")); 

	}
		
	@Test
	public void testConsultationTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.CONSULTATION.toString());
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
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/consultationTransfer.test")); 
		
	}

	@Test
	public void testBridgeTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BRIDGE.toString());
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
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/bridgeTransfer.test")); 
		
	}
	
	@Test
	public void testPropertiesAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString());

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
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/propertiesAtTransfer.test")); 
		
	}

	@Test
	public void testCustomEventsAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString());

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
		eventsList.add("error.unsupported.transfer");
		eventsList.add("error.connection.baddestination");				
				
		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/customEventsAtTransfer.test")); 
		
	}


	@Test
	public void testAttributesNotApplicableAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString());
		when(transferMock.getTimeout()).thenReturn("10s");
		when(transferMock.getMaxtime()).thenReturn("60s");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
				
		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/attributesNotApplicableAtTransfer.test")); 
		
	}

	@Test
	public void testTransferaudioAtTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BLIND.toString());
		when(transferMock.getTransferaudio()).thenReturn("idleMusic");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
				
		when(transferMock.getEventsList()).thenReturn(eventsList);
		
		VXIRenderer vxiRenderer = new VXIRenderer();

		//When
		String vxmlCode = vxiRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("VXML code printed different than expected.",vxmlCode, readResourceFile("src/test/resources/com/vectorsf/jvoiceframework/flow/render/transferaudioAtTransfer.test")); 
		
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
