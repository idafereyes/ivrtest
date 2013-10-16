package com.vectorsf.jvoiceframework.flow.processor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;

import com.vectorsf.jvoiceframework.core.bean.Input;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.flow.render.Renderer;

public class FlowProcessorTest {
	public static final String RENDER_CODE = "OUTPUT_FAKE_CODE";
	public static final String FLOW_URL = "FLOW_FAKE_URL";
	
	@Test
	public void testRender() {
 
		// Given
		Renderer rendererMock = mock(Renderer.class);
		Output outputMock = mock(Output.class);
		Input inputMock = mock(Input.class);
		
		FlowProcessor flowProcessor = new FlowProcessor();
		flowProcessor.setRenderer(rendererMock);
		flowProcessor.setStates(new ArrayList());
		flowProcessor.process(inputMock);
		flowProcessor.process(outputMock);
		
		// When
		flowProcessor.render(FLOW_URL);
		
		// Then
		verify(rendererMock, times(1)).render(flowProcessor.getStates(), FLOW_URL);
	}
}
