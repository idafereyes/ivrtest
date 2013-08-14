package org.vector.jvoice.flow;

import java.util.ArrayList;













import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;
import org.vector.jvoice.flow.FlowProcessor;
import org.vector.jvoice.flow.bean.Prompt;
import org.vector.jvoice.flow.render.HTMLRenderer;

public class FlowProcesorITest extends AbstractXmlFlowExecutionTests {
	

	

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
		// Esto deberíamos poder configurarlo y no hacerlo programáticamente
		FlowProcessor flowProcessor = new FlowProcessor();
		flowProcessor.setRenderer(new HTMLRenderer());
		flowProcessor.setStates(new ArrayList());
		builderContext.registerBean("flowProcessor", flowProcessor); 
		builderContext.registerBean("prompt", new Prompt()); 

	}
	
	@Override
	protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
		FlowDefinitionResource resource = resourceFactory.createFileResource("src/itest/resources/org/vector/jvoice/flow/test-flow.xml");
		Assert.notNull(resource);
		return resource;
	}
}
