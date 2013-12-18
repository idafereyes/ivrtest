package com.vectorsf.jvoiceframework.isban.logger.log;

import com.vectorsf.jvoiceframework.isban.logger.events.IVREvent;

public interface WriteLogInterface {
	
	public void writeLog(IVREvent event);

}
