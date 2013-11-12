package com.vectorsf.jvoiceframework.testapp.service.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service("portfolio")
public class Portfolio {

	public List<Account> retrieve(String ssn) throws Exception {
		List<Account> accounts = new ArrayList<Account>();
		Random random = new Random();
		for (int index = 0; index < random.nextInt(10) + 2; index ++) {
			SavingAccount account = new SavingAccount();
			account.setBalance(Float.toString(random.nextFloat() * 1000));
			account.setId(UUID.randomUUID().toString());
			accounts.add(account);
		}
			
		return accounts;
	}

}
