package com.vectorsf.jvoiceframework.isban.logger.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.Transfer;

@Component
@Aspect
public class TransferLogger extends AudioItemsLogger {
		
	@Pointcut("execution(* com.vectorsf.jvoiceframework.flow.processor.SpringWebFlowProcessor.process(com.vectorsf.jvoiceframework.core.bean.Transfer))")
	public void process(){}
	
	@Before("process()")
	public void beforeMethod(JoinPoint joinPoint){	
		
		Transfer transfer =  (Transfer) joinPoint.getArgs()[0];

        if (transfer.getAudioItems() != null){
        	logAudioItems(transfer.getAudioItems());
        }
	}
}
