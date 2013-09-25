package com.vectorsf.jvoiceframework.flow.processor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

public class FlowProcessorTest {
	public static final String INPUT_CODE = "INPUT_FAKE_CODE";
	public static final String OUTPUT_CODE = "OUTPUT_FAKE_CODE";
	public static final String FLOW_URL = "FLOW_FAKE_URL";
	
	@Test
	public void testRender() {
 
		// Given
		Renderer rendererMock = mock(Renderer.class);
		Input inputMock = mock(Input.class);
		Output outputMock = mock(Output.class);
		
		// Esto no nos sirve para nada. S�lo queremos probar que se renderiza una sola vez cada componente
		when(rendererMock.render(outputMock, FLOW_URL)).thenReturn(OUTPUT_CODE);
		when(rendererMock.render(inputMock, FLOW_URL)).thenReturn(OUTPUT_CODE);
		//given(rendererMock.render(isA(Input.class), anyString())).willReturn(anyString());
		
		FlowProcessor flowProcessor = new FlowProcessor();
		flowProcessor.setRenderer(rendererMock);
		flowProcessor.setStates(new ArrayList());
		flowProcessor.process(inputMock);
		flowProcessor.process(outputMock);
		
		// When
		flowProcessor.render(FLOW_URL);
		
		// Then
		verify(rendererMock, times(1)).render(inputMock, FLOW_URL);
		verify(rendererMock, times(1)).render(outputMock, FLOW_URL);
	}
}
