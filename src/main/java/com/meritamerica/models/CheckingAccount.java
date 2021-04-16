package com.meritamerica.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckingAccount extends BankAccount {
	//private double interestRate;
	

	public CheckingAccount() {
		this.setInterestRate(MeritBank.getCheckingInterest());
	}

	public CheckingAccount(double openingBalance) {
		super(openingBalance);
		this.setInterestRate(MeritBank.getCheckingInterest());
	}

	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(long accountNumber, double balance, double interestRate) {
		super(accountNumber, balance, interestRate);
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
		// TODO Auto-generated constructor stub
	}
	
	public static CheckingAccount readFromString(String accountData) throws ParseException{
		CheckingAccount fromStringAccount;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] accountDataFormatter = accountData.split(",");
		if (accountDataFormatter.length != 4) {
			throw new NumberFormatException();
		} else {
			fromStringAccount = new CheckingAccount();
			fromStringAccount.setAccountNumber(Long.parseLong(accountDataFormatter[0]));
			fromStringAccount.setBalance(Double.parseDouble(accountDataFormatter[1]));
			fromStringAccount.setInterestRate(Double.parseDouble(accountDataFormatter[2]));
			System.out.println(fromStringAccount.getInterestRate());
			fromStringAccount.accountOpenedOn = dateFormatter.parse(accountDataFormatter[3]);	
			return fromStringAccount;
		}
	}

}
