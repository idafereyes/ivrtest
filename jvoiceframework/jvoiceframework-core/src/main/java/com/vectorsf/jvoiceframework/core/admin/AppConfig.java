package com.vectorsf.jvoiceframework.core.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;

/**
 * Esta clase se usa para la configuración de una aplicación IVR, 
 * la configuración, viene inyectado en un Bean Configuration
 * @author mvinuesa
 *
 */
public class AppConfig {
    
    private Configuration configBean;
    
    private Map<String, String> configMap = new HashMap<String, String>();
    
    @PostConstruct
    public void config() {
		loadFromFile();
    }
    
    /** Constructor */
    public AppConfig(Configuration configBean) {
    	this.configBean = configBean;
    }
	
    /**
     * Carga la configuración general de la aplicación
     */
	private void loadFromFile() {
		Iterator<String> it = configBean.getKeys();
		while (it.hasNext()) {
			String key = it.next();
			if (!configMap.containsKey(key)) {
				configMap.put(key, configBean.getString(key));
			}
		}
	}
	
	/**
	 * Recupera el valor de una propiedad por su key
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return configMap.get(key);
	}
    
}
