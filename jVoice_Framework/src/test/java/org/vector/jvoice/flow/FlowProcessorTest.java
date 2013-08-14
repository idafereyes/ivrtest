package org.vector.jvoice.flow;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.vector.jvoice.flow.bean.Input;
import org.vector.jvoice.flow.bean.Prompt;
import org.vector.jvoice.flow.render.Renderer;

public class FlowProcessorTest {
	public static final String INPUT_CODE = "INPUT_FAKE_CODE";
	public static final String PROMPT_CODE = "PROMPT_FAKE_CODE";
	public static final String FLOW_URL = "FLOW_FAKE_URL";
	
	@Test
	public void testRender() {
 
		// Given
		Renderer rendererMock = mock(Renderer.class);
		Input inputMock = mock(Input.class);
		Prompt promptMock = mock(Prompt.class);
		
		// Esto no nos sirve para nada. Sólo queremos probar que se renderiza una sola vez cada componente
		when(rendererMock.render(promptMock, FLOW_URL)).thenReturn(PROMPT_CODE);
		when(rendererMock.render(inputMock, FLOW_URL)).thenReturn(PROMPT_CODE);
		//given(rendererMock.render(isA(Input.class), anyString())).willReturn(anyString());
		
		FlowProcessor flowProcessor = new FlowProcessor();
		flowProcessor.setRenderer(rendererMock);
		flowProcessor.setStates(new ArrayList());
		flowProcessor.process(inputMock);
		flowProcessor.process(promptMock);
		
		// When
		flowProcessor.render(FLOW_URL);
		
		// Then
		verify(rendererMock, times(1)).render(inputMock, FLOW_URL);
		verify(rendererMock, times(1)).render(promptMock, FLOW_URL);
	}
}
