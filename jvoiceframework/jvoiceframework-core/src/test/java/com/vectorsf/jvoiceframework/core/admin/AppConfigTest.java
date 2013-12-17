package com.vectorsf.jvoiceframework.core.admin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Esta clase para probar la lectura de los ficheros de configuración
 * @author mvinuesa
 *
 */
public class AppConfigTest {
    

	@Test
	public void testConfigAudiosFormatSuffsix(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/vectorsf/jvoiceframework/core/admin/test-config-context.xml");
		applicationContext.refresh();
		AppConfig appConfig = (AppConfig)applicationContext.getBean("app");
		assertEquals("Must be equals",appConfig.getValue("max"),"25");
		applicationContext.close();
	}
    
}
