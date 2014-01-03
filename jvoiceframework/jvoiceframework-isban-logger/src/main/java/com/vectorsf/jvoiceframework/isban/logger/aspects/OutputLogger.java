package com.vectorsf.jvoiceframework.isban.logger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Output;
import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;

@Component
@Aspect
public class OutputLogger extends AudioItemsLogger {
	
	@Log
	private ExtendedLocLogger logger;
	
	public ExtendedLocLogger getLogger() {
		return logger;
	}

	public void setLogger(ExtendedLocLogger logger) {
		this.logger = logger;
	}

	@Pointcut("execution(* com.vectorsf.jvoiceframework.flow.processor.SpringWebFlowProcessor.process(com.vectorsf.jvoiceframework.core.bean.Output))")
	public void processAtFlowProcessor(){}
	
	@Before("processAtFlowProcessor()")
	public void beforeMethod(JoinPoint joinPoint){	
		
		logger.debug(OutputLoggerMessages.DEBUG_ASPECT_OUTPUT_LOGGER_INIT, this.getClass().getName(), joinPoint.getSignature());
				
		Output output =  (Output) joinPoint.getArgs()[0];
		        
        if (output.getAudioItems() != null){
        	logAudioItems(output.getAudioItems());
        }
		
        logger.debug(OutputLoggerMessages.DEBUG_ASPECT_OUTPUT_LOGGER_END, this.getClass().getName());
        
	}	

}
