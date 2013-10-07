package com.vectorsf.jvoiceframework.testapp.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vectorsf.jvoiceframework.core.service.locution.LocutionProvider;
import com.vectorsf.jvoiceframework.testapp.service.portfolio.Account;

@Component("accountsLocutions")
public class AccountsLocutions {
	
	@Autowired
	LocutionProvider locutionProvider;
				 
	public String getAccountSelectionLocution(List<Account> accounts, Enum<?> key, Enum<?> remainingKey, int offset) {
		
		final int ACCOUNT_NUMBER = 5;
		
		String locution = "";		
		Account account = null;
		int index  = offset;
		
		for (index = offset; index < offset +  ACCOUNT_NUMBER; index++) {
			
			if (index < accounts.size()) {			
				account = accounts.get(index);
				locution += " " + locutionProvider.getWording(key, account.getIdEnding(), index + 1);
			}
			else {
				break;
			}
		}
		if (index >= offset +  ACCOUNT_NUMBER) {
			locution += " "  + locutionProvider.getWording(remainingKey);
		}	
		return locution;
	}

}
