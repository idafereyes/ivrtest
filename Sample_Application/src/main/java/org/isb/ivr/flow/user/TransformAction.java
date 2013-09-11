package org.isb.ivr.flow.user;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.log.Log;
import com.vectorsf.jvoiceframework.core.log.Logger;

@Component("transformAction")
@Scope("singleton")  // Seguro que este es el scope?
public class TransformAction implements Serializable {

	private static final long serialVersionUID = 1958667010894202324L;
	
	@Log
	private Logger log;

	public UserBean transform(UserBean bean) throws Exception {
		bean.setaField(bean.getaField() + " ACTION TRANSFORMED");
		return bean;
	}
	
	public String transform(String data) throws Exception {
		log.info(TransformActionMessages.PRUEBA);
		return data + " ACTION TRANSFORMED";
	}
}
