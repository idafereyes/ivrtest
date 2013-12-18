package com.vectorsf.jvoiceframework.isban.logger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Output;

@Component
@Aspect
public class OutputLogger extends AudioItemsLogger {
	
	@Pointcut("execution(* com.vectorsf.jvoiceframework.flow.processor.SpringWebFlowProcessor.process(com.vectorsf.jvoiceframework.core.bean.Output))")
	public void processAtFlowProcessor(){}
	
	@Before("processAtFlowProcessor()")
	public void beforeMethod(JoinPoint joinPoint){	
		
		Output output =  (Output) joinPoint.getArgs()[0];
		        
        if (output.getAudioItems() != null){
        	logAudioItems(output.getAudioItems());
        }
        
	}	

}
