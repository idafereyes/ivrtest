package com.vectorsf.jvoiceframework.core.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Esta clase prueba la lectura de los ficheros de configuración
 * @author mvinuesa
 *
 */
public class AppConfigTest {
    
	ClassPathXmlApplicationContext applicationContext = null;

	@Before
	public void startContext() {
		applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/admin/test-config-context.xml");
		applicationContext.refresh();
	}
	
	@Test
	public void testConfigValue(){
		AppConfig appConfig = (AppConfig)applicationContext.getBean("app");
		assertEquals("Must be equals",appConfig.getValue("max"),"25");
	}
	
	@Test
	public void testConfigValueNull(){
		AppConfig appConfig = (AppConfig)applicationContext.getBean("app");
		assertNull("Must be null",appConfig.getValue("noValue"));
	}
	
	@After
	public void stopContext() {
		applicationContext.close();
	}
    
}
