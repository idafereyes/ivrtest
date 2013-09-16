package com.vectorsf.jvoiceframework.flow.render;


import java.util.ArrayList;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.flow.processor.FlowProcessor;

public class VXIRendererTest {
	
	static final String SCAN_BASE_PACKAGE = "com.vectorsf.jvoiceframework.core.bean";
	static final String FLOW_EXECUTION_URL = "http://flowExecutionUrl/";


	@Test
	public void testOutputRenderer(){
		
		//Given	
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();		
		context.scan(SCAN_BASE_PACKAGE);
		context.refresh();

		Output output = (Output)context.getBean(Output.class);

		AudioItem audioItem1 = new AudioItem();
		audioItem1.setSrc("SAN-WELCOME");
		audioItem1.setWording("bienvenido");
		output.getAudioItemsList().add(audioItem1);
		
		AudioItem audioItem2 = new AudioItem();
		audioItem2.setSrc("SAN-WELCOME-B");
		output.getAudioItemsList().add(audioItem2);
		
		AudioItem audioItem3 = new AudioItem();
		audioItem3.setWording("Gracias por llamar.");
		output.getAudioItemsList().add(audioItem3);
				
		output.getProperties().put("other.property","newValue");
		
		output.setBargein(true);
		output.setCatchHangup(true);
		output.setFlush(true);
		
		//Al no estar anotados no se puede utilizar el AnnotationConfigApplicationContext
		FlowProcessor flowProcessor = new FlowProcessor();
		VXIRenderer vxiRenderer = new VXIRenderer();
		flowProcessor.setRenderer(vxiRenderer);
		flowProcessor.setStates(new ArrayList());

		flowProcessor.process(output);
		
		//When
		String vxmlCode = flowProcessor.render(FLOW_EXECUTION_URL);
		
		//Then
		System.out.println(vxmlCode);
		
		//Finally
		context.close();

	}

}
