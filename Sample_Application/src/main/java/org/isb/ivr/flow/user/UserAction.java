package org.isb.ivr.flow.user;

import java.io.Serializable;

public class UserAction implements Serializable {

	private static final long serialVersionUID = 774947628774407409L;

		public void doit() throws Exception{
			throw new Exception("Fake Exception");
		}
}
