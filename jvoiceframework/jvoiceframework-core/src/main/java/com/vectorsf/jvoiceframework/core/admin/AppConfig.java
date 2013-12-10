package com.vectorsf.jvoiceframework.core.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Esta clase se usa para la configuración de una aplicación IVR, 
 * la configuración, viene inyectado en un Bean Configuration
 * @author mvinuesa
 *
 */
@Component("appConfig")
public class AppConfig {
    
    @Autowired 
    private Configuration fileConfig;
    
    private Map<String, String> config = new HashMap<String, String>();
    
    @PostConstruct
    public void config() {
		loadFromFile();
    }
	
    /**
     * Carga la configuración general de la aplicación
     */
	private void loadFromFile() {
		Iterator<String> it = fileConfig.getKeys();
		while (it.hasNext()) {
			String key = it.next();
			if (!config.containsKey(key)) {
				config.put(key, fileConfig.getString(key));
			}
		}
	}
	
	/**
	 * Recupera el valor de una propiedad por su key
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return config.get(key);
	}
    
}
