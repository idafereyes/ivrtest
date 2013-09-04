package com.vectorsf.jvoiceframework.core.log;

import java.lang.reflect.Field;
import java.util.Locale;

import org.slf4j.cal10n.LocLogger;
import org.slf4j.cal10n.LocLoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import ch.qos.cal10n.IMessageConveyor;
import ch.qos.cal10n.MessageConveyor;

/**
 * This Class put logger to beans with annotation @Log, implements
 * BeanPostProcessor
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
					IMessageConveyor messageConveyor = new MessageConveyor(Locale.getDefault());
					LocLoggerFactory locFactory = new LocLoggerFactory(messageConveyor);
					LocLogger locLogger = locFactory.getLocLogger(bean.getClass());
					ExtendedLocLogger eLocLogger = new ExtendedLocLogger(locLogger);
					field.set(bean, eLocLogger);
				}
			}
		});

		return bean;
	}
}
