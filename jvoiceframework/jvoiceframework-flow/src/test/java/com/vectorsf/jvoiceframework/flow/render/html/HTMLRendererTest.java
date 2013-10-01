package com.vectorsf.jvoiceframework.flow.render.html;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.enums.RecordEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferEvents;
import com.vectorsf.jvoiceframework.core.enums.TransferType;
import com.vectorsf.jvoiceframework.flow.render.html.HTMLRenderer;

public class HTMLRendererTest {

	static final String FLOW_EXECUTION_URL = "http://flowExecutionUrl/";
	static final String RESOURCE_FILE_PATH = "src/test/resources/com/vectorsf/jvoiceframework/flow/render/html/";

	@Test
	public void testRenderEmptyStatesList(){
		
		//Given
		
		HTMLRenderer htmlRenderer = new HTMLRenderer();
		
		List<Object> states = new ArrayList<Object>();

		//When
		String htmlCode =  htmlRenderer.render(states, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, ""); 
	}
	
	@Test
	@Ignore
	public void testRenderEveryState() throws FileNotFoundException{
		
		//Given
		
		HTMLRenderer htmlRenderer = new HTMLRenderer();
		
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

		//When
		String htmlCode = htmlRenderer.render(states, FLOW_EXECUTION_URL);
			
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "everyState.test")); 
	}

	
	@Test
	@Ignore
	public void testOutput() throws FileNotFoundException{
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
		
		when(outputMock.getAudioItems()).thenReturn(audioItemsList);
		
		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(outputMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "output.test")); 
		
	}
	
	@Test
	@Ignore
	public void testInput() throws FileNotFoundException{
		
		//TODO Hacer el test del Input.
	}
	
	@Test
	public void testTransfer() throws FileNotFoundException{
		
		//Given
		Transfer transferMock = mock(Transfer.class);
		when(transferMock.getDest()).thenReturn("tel:666777888");
		when(transferMock.getType()).thenReturn(TransferType.BRIDGE.toString());
		when(transferMock.getMaxtime()).thenReturn("30s");
		when(transferMock.getTimeout()).thenReturn("10s");
		when(transferMock.getTransferaudio()).thenReturn("idleMusic");

		List<String> eventsList = new ArrayList<String>();
		eventsList.add(TransferEvents.TRANSFERRED.toString());
		eventsList.add(TransferEvents.HANGUP.toString());
		eventsList.add(TransferEvents.CONNECTIONERROR.toString());
		eventsList.add(TransferEvents.ERROR.toString());
		eventsList.add(TransferEvents.NOANSWER.toString());
		eventsList.add("error.unsupported.transfer");
		
		when(transferMock.getEventsList()).thenReturn(eventsList);

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(transferMock.getProperties()).thenReturn(properties);

		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(transferMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "transfer.test")); 
	}

	@Test
	public void testRecord() throws FileNotFoundException{
		
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
		when(audioItem1.getWording()).thenReturn("Por favor, diga su nombre");

		AudioItem audioItem2 = mock(AudioItem.class);
		when(audioItem2.getSrc()).thenReturn("SAN-RECORDING-B");

		AudioItem audioItem3 = mock(AudioItem.class);
		when(audioItem3.getWording()).thenReturn("Despues del beep");
		
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

		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(recordMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "record.test")); 
	}

	@Test
	public void testEnd() throws FileNotFoundException{
		
		//Given
		End endMock = mock(End.class);
		when(endMock.getName()).thenReturn("testEnd");
		
		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(endMock, FLOW_EXECUTION_URL);
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "end.test")); 
	}
	
	@Test
	public void testStartPage() throws FileNotFoundException{
		
		//Given
		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode = htmlRenderer.renderStartPage();
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "startPage.test")); 
		
	}

	@Test
	public void testEndPage() throws FileNotFoundException{
		
		//Given
		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode = htmlRenderer.renderEndPage();
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, readResourceFile(RESOURCE_FILE_PATH + "endPage.test")); 
		
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
