package com.vectorsf.statistics.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

/**
 * This class loads the <b>CommonLogger</b> configuration parameter from the configuration file.
 * 
 * 
 * @author sergio.milla@servexternos.isban.es and idafe.reyes@servexternos.isban.es
 *
 */

public class ConfigurationWrapper {
	
	private static final String COMMON_LOGGER_CONFIG_FILE = "common-logger-config.xml";
	
	private static final Configuration configuration;
	
	static {
		XMLConfiguration loggerConf;
		
//		 try to load application config file
		loggerConf = new XMLConfiguration();
	
		try {
			loggerConf.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(COMMON_LOGGER_CONFIG_FILE));
			//TODO ver si dejamos el cambio en caliente
			loggerConf.setReloadingStrategy(new FileChangedReloadingStrategy());
			
		} catch (ConfigurationException e) {
			throw new RuntimeException("Error loading CommonLogger configuration parameters", e);
		}
				
		loggerConf.setExpressionEngine(new XPathExpressionEngine());
	
		configuration = loggerConf;
	}
	
	public static Configuration getConfiguration() {
		return configuration;
	}
}
