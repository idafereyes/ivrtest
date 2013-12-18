package com.vectorsf.jvoiceframework.isban.logger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Record;

@Component
@Aspect
public class RecordLogger extends AudioItemsLogger {
		
	@Pointcut("execution(* com.vectorsf.jvoiceframework.flow.processor.SpringWebFlowProcessor.process(com.vectorsf.jvoiceframework.core.bean.Record))")
	public void process(){}
	
	@Before("process()")
	public void beforeMethod(JoinPoint joinPoint){	
		
		Record record =  (Record) joinPoint.getArgs()[0];

		if (record.getAudioItems() != null){
        	logAudioItems(record.getAudioItems());
        }
	}

}
