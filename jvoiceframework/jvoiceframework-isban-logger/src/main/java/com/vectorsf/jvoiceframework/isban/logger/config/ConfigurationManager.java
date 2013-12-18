package com.vectorsf.jvoiceframework.isban.logger.config;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationUtils;

import com.vectorsf.jvoiceframework.isban.logger.enums.EventType;

/**
 * This class contains all methods needed to read the <b>CommonLogger</b> configuration parameters.
 * 
 * 
 * @author sergio.milla@servexternos.isban.es and idafe.reyes@servexternos.isban.es
 *
 */
public class ConfigurationManager implements Serializable {

	private static final long serialVersionUID = 5712820720248024711L;

//	public static final String CONTEXT_KEY = "SANTANDER_CONFIGURATION";

	private final Configuration configuration;

	private ConfigurationManager(Configuration configuration) {
		this.configuration = configuration;
	}

	public static ConfigurationManager buildInstance() {
		return new ConfigurationManager(ConfigurationWrapper.getConfiguration());
	}
	
	public boolean isEventActive(EventType eventType) {
				
		String active = null;
		String type = null;
		if (eventType == EventType.INIT_CALL || eventType == EventType.END_CALL)
			type = "CALL";
		else if (eventType == EventType.INIT_SERVICE || eventType == EventType.END_SERVICE)
			type = "SERVICE";
		else if (eventType != null)
			type = eventType.toString();
		
		if (eventType != null) {
			active = configuration.getString("logSystem/"+ "events/event[@name='" + type + "']/@active");
		}
			
		return "y".equalsIgnoreCase(active);
	}
	
	public List<Object> getWriteLogClasses() {
		return configuration.getList("logSystem/writeLogClasses/writeLogClass/@className");
	}

		
	@Override
	public String toString() {
		return ConfigurationUtils.toString(configuration);
	}

}
