package com.vectorsf.jvoiceframework.testapp.service.authentication;

import org.springframework.stereotype.Service;

@Service("authentication")
public class Authentication {
	
	public void login(String user, String password) throws AuthenticationException {
		final String USER = "123456789";
		final String PASSWORD = "1234";
		if (!user.equals(USER) || !password.equals(PASSWORD)) {
			throw new AuthenticationException();
		}
	}
}
