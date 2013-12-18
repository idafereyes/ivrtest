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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.BlindTransfer;
import com.vectorsf.jvoiceframework.core.bean.End;
import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.bean.Wording;

public class HTMLRendererTest {

	static final String FLOW_EXECUTION_URL = "http://flowExecutionUrl/";
	static final String RESOURCE_FILE_PATH = "src/test/resources/com/vectorsf/jvoiceframework/flow/render/html/";
	static final String PATTERN_UUID = "[0-9abcdef]{8}-[0-9abcdef]{4}-[0-9abcdef]{4}-[0-9abcdef]{4}-[0-9abcdef]{12}";
	static final String CONTEXT_PATH = "FAKE_CONTEXT_PATH";
	
	@Test
	public void testRenderEmptyStatesList(){
		
		//Given
		
		HTMLRenderer htmlRenderer = new HTMLRenderer();
		
		List<Object> states = new ArrayList<Object>();

		//When
		String htmlCode =  htmlRenderer.render(states, FLOW_EXECUTION_URL, CONTEXT_PATH);
		
		//Then
		assertEquals("HTML code printed different than expected.",htmlCode, ""); 
	}
	
	@Test
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
		String htmlCode = htmlRenderer.render(states, FLOW_EXECUTION_URL, CONTEXT_PATH);
		
		//Extract random uuid for comparing
		List<String> listIds = extractIds(htmlCode);
		String templateFile = readResourceFile(RESOURCE_FILE_PATH + "everyState.test");
		for(int i=0; i<listIds.size(); i++) {
			templateFile = templateFile.replaceAll("\\$\\{" + String.valueOf(i) + "\\}", listIds.get(i));
		}
		
		//Then
		assertEquals("HTML code printed different than expected.", htmlCode, templateFile);
	}

	
	@Test
	public void testOutput() throws FileNotFoundException{
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
		
		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(outputMock, FLOW_EXECUTION_URL, CONTEXT_PATH);
		
		//Extract random uuid for comparing
		List<String> listIds = extractIds(htmlCode);
		String templateFile = readResourceFile(RESOURCE_FILE_PATH + "output.test");
		for(int i=0; i<listIds.size(); i++) {
			templateFile = templateFile.replaceAll("\\$\\{" + String.valueOf(i) + "\\}", listIds.get(i));
		}

		//Then
		assertEquals("HTML code printed different than expected.", htmlCode, templateFile); 
		
	}
	
	@Test
	@Ignore
	public void testInput() throws FileNotFoundException{
		
		//TODO Hacer el test del Input.
	}
	
	@Test
	public void testBlindTransfer() throws FileNotFoundException{
		
		//Given
		BlindTransfer blindTxMock = mock(BlindTransfer.class);
		when(blindTxMock.getDest()).thenReturn("tel:666777888");
		when(blindTxMock.getTransferaudio()).thenReturn("idleMusic");

		List<String> customEvents = new ArrayList<String>();
		customEvents.add("error.unsupported.transfer");

		when(blindTxMock.getCustomEvents()).thenReturn(customEvents);

		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(blindTxMock.getProperties()).thenReturn(properties);

		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(blindTxMock, FLOW_EXECUTION_URL, CONTEXT_PATH);
		
		//Extract random uuid for comparing
		List<String> listIds = extractIds(htmlCode);
		String templateFile = readResourceFile(RESOURCE_FILE_PATH + "transfer.test");
		for(int i=0; i<listIds.size(); i++) {
			templateFile = templateFile.replaceAll("\\$\\{" + String.valueOf(i) + "\\}", listIds.get(i));
		}
				
		//Then
		assertEquals("HTML code printed different than expected.", htmlCode, templateFile); 
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
		
		when(recordMock.getAudioItems()).thenReturn(audioItemsList);
		
		List<String> customEvents = new ArrayList<String>();
		customEvents.add("com.nortel.ivr.record.notstored");

		when(recordMock.getCustomEvents()).thenReturn(customEvents);

		//Properties
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("other.property", "20s");
		properties.put("second.other.property", "35s");

		when(recordMock.getProperties()).thenReturn(properties);

		HTMLRenderer htmlRenderer = new HTMLRenderer();

		//When
		String htmlCode =  htmlRenderer.render(recordMock, FLOW_EXECUTION_URL, CONTEXT_PATH);
		
		//Extract random uuid for comparing
		List<String> listIds = extractIds(htmlCode);
		String templateFile = readResourceFile(RESOURCE_FILE_PATH + "record.test");
		for(int i=0; i<listIds.size(); i++) {
			templateFile = templateFile.replaceAll("\\$\\{" + String.valueOf(i) + "\\}", listIds.get(i));
		}
				
		//Then
		assertEquals("HTML code printed different than expected.", htmlCode, templateFile); 
	}

	@Test
	public void testEnd() throws FileNotFoundException{
		
		//Given
		End endMock = mock(End.class);
		
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
	
	private List<String> extractIds(String html) {
		List<String> listIds = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(PATTERN_UUID);
		Matcher matcher = pattern.matcher(html);
		
		boolean hasMatches = matcher.find();
		while(hasMatches) {
			String id = matcher.group();
			if( !existId(id, listIds) ) {
				listIds.add(id);
			}
			
			hasMatches = matcher.find();
		}
		
		return listIds;
	}

	private boolean existId(String id, List<String> list) {
		for(String listId : list) {
			if(listId.equals(id)) {
				return true;
			}
		}
		return false;
	}
}
