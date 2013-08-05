package org.isb.ivr.flow.user;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("userAction")
@Scope("singleton") // Seguro que este es el scope? Se puede elegir?
public class UserAction implements Serializable {

	private static final long serialVersionUID = 774947628774407409L;

		public void doit() throws Exception{
			throw new Exception("Fake Exception");
		}
}
