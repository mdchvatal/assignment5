package com.meritamerica.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CDAccount extends BankAccount {
	
	int tempTerm;
	protected double futureBalance;
	private int term;
	private double interestRate;
	private CDOffering cdOffering;
	
	public CDAccount() {
		this.accountNumber = MeritBank.getNextAccountNumber();
	}
	
	public CDAccount(CDOffering offering, int balance) {
		this.balance = balance;
		this.term = offering.getTerm();
		super.setAccountNumber(MeritBank.getNextAccountNumber());
		this.accountOpenedOn = new Date();
		this.setCdOffering(offering);
		this.interestRate = offering.getInterestRate();
	}
	
	public CDAccount(double balance, int id) {
		CDOffering newOffering = MeritBank.getCDOfferings().get(id-1);
		this.balance = balance;
		this.term = newOffering.getTerm();
		super.setAccountNumber(MeritBank.getNextAccountNumber());
		this.accountOpenedOn = new Date();
		this.setCdOffering(newOffering);
		this.interestRate = newOffering.getInterestRate();
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
	
	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}

	public void resetFutureBalance() {
		this.futureBalance = getBalance();
	}
	
	public void resetTempTerm() {
		this.tempTerm = this.term;
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
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