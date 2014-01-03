package com.vectorsf.jvoiceframework.isban.logger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Transfer;
import com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger;
import com.vectorsf.jvoiceframework.core.log.Log;

@Component
@Aspect
public class TransferLogger extends AudioItemsLogger {
		
	@Log
	private ExtendedLocLogger logger;
	
	public ExtendedLocLogger getLogger() {
		return logger;
	}

	public void setLogger(ExtendedLocLogger logger) {
		this.logger = logger;
	}

	@Pointcut("execution(* com.vectorsf.jvoiceframework.flow.processor.SpringWebFlowProcessor.process(com.vectorsf.jvoiceframework.core.bean.Transfer))")
	public void process(){}
	
	@Before("process()")
	public void beforeMethod(JoinPoint joinPoint){	
		
		logger.debug(TransferLoggerMessages.DEBUG_ASPECT_TRANSFER_LOGGER_INIT, this.getClass().getName(), joinPoint.getSignature());

		Transfer transfer =  (Transfer) joinPoint.getArgs()[0];

        if (transfer.getAudioItems() != null){
        	logAudioItems(transfer.getAudioItems());
        }
	
        logger.debug(TransferLoggerMessages.DEBUG_ASPECT_TRANSFER_LOGGER_END, this.getClass().getName());

	}
}
