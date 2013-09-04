package com.vectorsf.jvoiceframework.core.log;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

/**
 * This Class inject ExtendeLocLoggerr to beans with annotation @Log, implements BeanPostProcessor
 * @see com.vectorsf.jvoiceframework.core.log.ExtendedLocLogger
 * 
 * @author mvinuesa
 * 
 */
@Component("loggerPostProcessor")
public class LoggerPostProcessor implements BeanPostProcessor {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithFields(bean.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				ReflectionUtils.makeAccessible(field);
				if (field.getAnnotation(Log.class) != null) {
					ExtendedLocLogger eLocLogger = ExtendedLocLoggerFactory.getExtendedLocLogger(bean.getClass());
					field.set(bean, eLocLogger);
				}
			}
		});

		return bean;
	}
}
