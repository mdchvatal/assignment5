package com.meritamerica.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SavingsAccount extends BankAccount {
	//private double interestRate = 0.01;

	public SavingsAccount() {
		this.setInterestRate(MeritBank.getSavingsInterest());
	}

	public SavingsAccount(double openingBalance) {
		super(openingBalance);
		this.setInterestRate(MeritBank.getSavingsInterest());
	}

	public SavingsAccount(double balance, double interestRate) {
		super(balance, interestRate);
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(long accountNumber, double balance, double interestRate) {
		super(accountNumber, balance, interestRate);
		// TODO Auto-generated constructor stub
	}

	public SavingsAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
		// TODO Auto-generated constructor stub
	}
	
	public static SavingsAccount readFromString(String accountData) throws ParseException{
		SavingsAccount fromStringAccount = new SavingsAccount();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] accountDataFormatter = accountData.split(",");
		fromStringAccount.setAccountNumber(Long.parseLong(accountDataFormatter[0]));
		fromStringAccount.setBalance(Double.parseDouble(accountDataFormatter[1]));
		fromStringAccount.setInterestRate(Double.parseDouble(accountDataFormatter[2]));
		System.out.println(fromStringAccount.getInterestRate());
		fromStringAccount.accountOpenedOn = dateFormatter.parse(accountDataFormatter[3]);	
		return fromStringAccount;
	}
	

}

