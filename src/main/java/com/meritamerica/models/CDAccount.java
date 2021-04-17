package com.meritamerica.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount {
	protected double futureBalance;
	private int term;
	private int tempTerm;

	public CDAccount() {
	}

	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate());
		this.term = offering.getTerm();
		this.tempTerm = term;
		super.setAccountNumber(MeritBank.getNextAccountNumber());
		this.accountOpenedOn = new Date();
	}

	public int getTerm() {
		return term;
	}
	
	public void setTerm(int i) {
		this.term = i;
		this.tempTerm = term;
	}
	
	@Override
	public boolean withdraw(double amount) {
			return false;
	}
	
	@Override
	public boolean deposit(double amount) {
			return false;
	}
	
	public double futureValue() {
	    if (tempTerm == 0) {
	    	double tempBalance;
	    	tempBalance = futureBalance;
	    	resetFutureBalance();
	        return tempBalance;
	    } else {
	        futureBalance = futureBalance * (1 + getInterestRate());
	        --tempTerm;
	        return futureValue();
	    }
	  }
	
	public double getFutureBalance() {
		this.futureBalance = this.balance;
		return futureBalance;
	}
	
	public void resetFutureBalance() {
		this.futureBalance = getBalance();
	}
	
	public void resetTempTerm() {
		this.tempTerm = this.term;
	}
	
	
	
	public static CDAccount readFromString(String accountData) throws ParseException{
		CDAccount fromStringAccount = new CDAccount();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] accountDataFormatter = accountData.split(",");
		if (accountDataFormatter.length != 5) {
			throw new NumberFormatException();
		} else {
			fromStringAccount.setAccountNumber(Long.parseLong(accountDataFormatter[0]));
			fromStringAccount.setBalance(Double.parseDouble(accountDataFormatter[1]));
			fromStringAccount.setInterestRate(Double.parseDouble(accountDataFormatter[2]));
			System.out.println(fromStringAccount.getInterestRate());
			fromStringAccount.accountOpenedOn = dateFormatter.parse(accountDataFormatter[3]);
			fromStringAccount.setTerm(Integer.parseInt(accountDataFormatter[4]));
			return fromStringAccount;
		}
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormatter.format(getOpenedOn());
		return getAccountNumber() + "," + getBalance() + "," + getInterestRate()
					+ "," + dateString + "," + term;
	}
}