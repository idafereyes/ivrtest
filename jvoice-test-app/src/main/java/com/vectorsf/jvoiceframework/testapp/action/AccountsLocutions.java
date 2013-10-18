package com.vectorsf.jvoiceframework.testapp.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vectorsf.jvoiceframework.core.bean.AudioItem;
import com.vectorsf.jvoiceframework.core.bean.SayAs;
import com.vectorsf.jvoiceframework.core.bean.Wording;
import com.vectorsf.jvoiceframework.core.enums.InterpretAs;
import com.vectorsf.jvoiceframework.core.service.locution.LocutionProvider;
import com.vectorsf.jvoiceframework.testapp.locution.Accounts;
import com.vectorsf.jvoiceframework.testapp.service.portfolio.Account;

@Component("accountsLocutions")
public class AccountsLocutions {
	
	@Autowired
	LocutionProvider locutionProvider;
				 
	public List<AudioItem> getAccountSelectionLocutions(List<Account> accounts, int offset) {
		
		final int ACCOUNT_NUMBER = 5;
		
		List<AudioItem> locutions = new ArrayList<AudioItem>();		
		Account account = null;
		int index  = offset;
		
		for (index = offset; index < offset +  ACCOUNT_NUMBER; index++) {
			
			if (index < accounts.size()) {			
				account = accounts.get(index);
				
				AudioItem aiA = new AudioItem();
				aiA.setWording(locutionProvider.getWording(Accounts.ACCOUNT_SELECTION_PT1_A));
				locutions.add(aiA);
				
				AudioItem aiB = new AudioItem();
				Wording wording = new Wording();
				wording.setText(account.getIdEnding());
				wording.setSayAs(new SayAs(InterpretAs.DIGITS));
				aiB.setWording(wording);
				locutions.add(aiB);
				
				AudioItem aiC = new AudioItem();
				aiC.setWording(locutionProvider.getWording(Accounts.ACCOUNT_SELECTION_PT1_C, index + 1));
				locutions.add(aiC);
			}
			else {
				break;
			}
		}
		if (index >= offset +  ACCOUNT_NUMBER) {
			AudioItem aiRemaining = new AudioItem();
			aiRemaining.setWording(locutionProvider.getWording(Accounts.REMAINING_ACCOUNT_SELECTION));
			locutions.add(aiRemaining);
		}	
		return locutions;
	}

}
