package com.meritamerica.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CDAccountCreator {
	
	private CDAccount cdAccount = new CDAccount();
	
	@NotNull
	private CDOffering cdOffering;
	
	@Positive
	@NotNull
	private int balance;
	
	public CDAccountCreator(CDOffering cdOffering, int balance) {
		this.cdOffering = cdOffering;
		this.balance = balance;
		this.cdAccount.setBalance(balance);
		this.cdAccount.setInterestRate(cdOffering.getInterestRate());
		this.cdAccount.setTerm(cdOffering.getTerm());
		this.cdOffering.setId(cdOffering.getId());
	}

	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public CDAccount getCdAccount() {
		return cdAccount;
	}

	public void setCdAccount(CDAccount cdAccount) {
		this.cdAccount = cdAccount;
	}
	
}
