package com.vectorsf.jvoiceframework.core.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.vectorsf.jvoiceframework.core.log.ExtendedLocLoggerFactory;
import com.vectorsf.jvoiceframework.core.log.Logger;

/**
 * Esta clase se usa para la configuración de una aplicación IVR, 
 * la configuración, viene inyectado en un Bean Configuration
 * @author mvinuesa
 *
 */
public class AppConfig {
    
	private Logger logger = ExtendedLocLoggerFactory.getLogger(AppConfig.class);
	
    private String fileName;
  
	private Map<String, String> configMap = new HashMap<String, String>();
    
    @PostConstruct
    public void config() {
		loadFromFile();
    }
    
    /** Constructor */
    public AppConfig() {
    }
	
    /**
     * Carga la configuración general de la aplicación
     */
	private void loadFromFile() {
		if (fileName != null) {
			try {
				PropertiesConfiguration pc = new PropertiesConfiguration(fileName);
				Iterator<String> it = pc.getKeys();
				while (it.hasNext()) {
					String key = it.next();
					configMap.put(key, pc.getString(key));
				}
			} catch (ConfigurationException e) {
				logger.error(AppConfigMessages.ERROR_APP_CONFIG, fileName);
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
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
