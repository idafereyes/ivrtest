package com.vectorsf.jvoiceframework.core.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Esta clase prueba la lectura de los ficheros de configuración
 * @author mvinuesa
 *
 */
public class AppConfigTest {
    
	private static ClassPathXmlApplicationContext applicationContext = null;
	
	private static AppConfig appConfig = null;

	@BeforeClass
	public static void startContext() {
		applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/admin/test-config-context.xml");
		applicationContext.refresh();
		appConfig = (AppConfig)applicationContext.getBean("appTest");
	}
	
	@Test
	public void testConfigValue(){
		assertEquals("Must be equals",appConfig.getValue("max"),"25");
	}
	
	@Test
	public void testConfigValueNull(){
		assertNull("Must be null",appConfig.getValue("noValue"));
	}
	
	@AfterClass
	public static void stopContext() {
		applicationContext.close();
	}
    
}
