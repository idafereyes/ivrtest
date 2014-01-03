package com.vectorsf.jvoiceframework.isban.logger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Record;
import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;

@Component
@Aspect
public class RecordLogger extends AudioItemsLogger {
		
	@Log
	private ExtendedLocLogger logger;
	
	public ExtendedLocLogger getLogger() {
		return logger;
	}

	public void setLogger(ExtendedLocLogger logger) {
		this.logger = logger;
	}

	@Pointcut("execution(* com.vectorsf.jvoiceframework.flow.processor.SpringWebFlowProcessor.process(com.vectorsf.jvoiceframework.core.bean.Record))")
	public void process(){}
	
	@Before("process()")
	public void beforeMethod(JoinPoint joinPoint){	
		
		logger.debug(RecordLoggerMessages.DEBUG_ASPECT_RECORD_LOGGER_INIT, this.getClass().getName(), joinPoint.getSignature());

		Record record =  (Record) joinPoint.getArgs()[0];

		if (record.getAudioItems() != null){
        	logAudioItems(record.getAudioItems());
        }

		logger.debug(RecordLoggerMessages.DEBUG_ASPECT_RECORD_LOGGER_END, this.getClass().getName());
	
	}

}
