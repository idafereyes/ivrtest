package org.isb.ivr.flow.user;

import java.io.Serializable;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;

@Component
@Aspect
public class TransformActionAspect implements Serializable {

	private static final long serialVersionUID = -7587012738483550111L;

	@Pointcut("execution(* org.isb.ivr.flow.user.TransformAction.*(..))")
	public void transformActionMethods() {
	}
	
	@Log
	private Logger logger;

	@Before("transformActionMethods()")
	public void beforeMethod(JoinPoint joinPoint) {
		logger.info(TransformActionAspectMessages.INFO_BEFORE_METHOD, joinPoint.getSignature().getName() , joinPoint.getArgs());
	}

	@After("transformActionMethods()")
	public void afterMethod(JoinPoint joinPoint) {
		logger.info(TransformActionAspectMessages.INFO_AFTER_METHOD, joinPoint.getSignature().getName() , joinPoint.getArgs());
	}
}