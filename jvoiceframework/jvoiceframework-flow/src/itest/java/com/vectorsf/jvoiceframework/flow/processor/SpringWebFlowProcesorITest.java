package com.vectorsf.jvoiceframework.flow.processor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;


public class SpringWebFlowProcesorITest extends AbstractXmlFlowExecutionTests {
	

	@Test
	public void testBasicFlowProcessor() {
		
		MockExternalContext context = new MockExternalContext();
		startFlow(context);
		//assertCurrentStateEquals("render");
		assertFlowExecutionActive();
		context.setEventId("success");
		resumeFlow(context);
		

		assertFlowExecutionEnded();
		assertFlowExecutionOutcomeEquals("ok_end");
		
	}

	@Override
	protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/flow/processor/test-context.xml");
		builderContext.getFlowBuilderServices().setApplicationContext(applicationContext);
		
		// Tambi�n podemos hacerlo programaticamente
		/*
			FlowProcessor flowProcessor = new FlowProcessor();
			flowProcessor.setRenderer(new HTMLRenderer());
			flowProcessor.setStates(new ArrayList());
			builderContext.registerBean("flowProcessor", flowProcessor); 
			builderContext.registerBean("prompt", new Prompt()); 		
		*/
	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		FlowDefinitionResource resource = resourceFactory.createResource("com/vectorsf/jvoiceframework/flow/processor/test-flow.xml");
		Assert.notNull(resource);
		return resource;
	}
}
