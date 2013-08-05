package org.isb.ivr.flow.user;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("userBean")
@Scope("flow")
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1741623950907638655L;

		private String aField;

		public String getaField() {
			return aField;
		}


		public void setaField(String aField) {
			this.aField = aField;
		}
		
		
}
