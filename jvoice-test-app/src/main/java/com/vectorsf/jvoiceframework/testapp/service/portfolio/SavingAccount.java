package com.vectorsf.jvoiceframework.testapp.service.portfolio;

import java.io.Serializable;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("savingAccount")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SavingAccount extends Account implements Serializable {

	private static final long serialVersionUID = -171527180834393295L;
	private String balance;

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
}
