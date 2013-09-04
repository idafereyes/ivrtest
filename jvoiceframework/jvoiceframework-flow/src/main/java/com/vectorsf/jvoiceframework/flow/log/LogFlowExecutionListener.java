package com.vectorsf.jvoiceframework.flow.log;

import java.io.Serializable;
import java.util.List;

import org.springframework.binding.mapping.MappingResult;
import org.springframework.stereotype.Component;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.engine.FlowAttributeMappingException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.RequestContext;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;

/**
 * Listener to log the spring states flow
 * @author mvinuesa
 *
 */
@Component("logFlowExecutionListener")
public class LogFlowExecutionListener extends FlowExecutionListenerAdapter implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1671376170775795641L;
	
	@Log
	private ExtendedLocLogger logger;
 
    @Override
    public void stateEntering(RequestContext context, StateDefinition state) throws org.springframework.webflow.execution.EnterStateVetoException {
        logger.info(LogFlowExecutionListenerMessages.INFO_START_STATE_EXECUTION,  state.getId());
    }
    
    @Override
    public void stateEntered(RequestContext context, StateDefinition previousState, StateDefinition newState) {
		logger.info(LogFlowExecutionListenerMessages.INFO_END_STATE_EXECUTION,  newState.getId());
    }
    
    @Override
    public void exceptionThrown(RequestContext context, FlowExecutionException exception) {
        if (exception instanceof FlowAttributeMappingException) {
            List<MappingResult> errors = ((FlowAttributeMappingException)exception).getMappingResults().getErrorResults();
            for (MappingResult error : errors) {
                if (error.getErrorCause() != null) {
                    logger.error(LogFlowExecutionListenerMessages.ERROR_FLOW_EXECUTION_EXCEPTION, error.getErrorCause());
                }
            }
        }
    }
    
    @Override
    public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
		logger.info(LogFlowExecutionListenerMessages.INFO_EVENT_TRANSITION,  transition.getId(), transition.getTargetStateId());
		Event event = context.getCurrentEvent();
		StateDefinition sd = context.getCurrentState();
    }
}