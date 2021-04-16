package com.meritamerica.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AccountHolder implements Comparable<AccountHolder>{
	private static int nextId = 1;
	private int id;

	private double combinedBalance;
	private List<CDAccount> cdAccounts = new ArrayList<CDAccount>();
	private List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
	private List<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
	
	@NotBlank
	@NotNull
	private String firstName;
	private String middleName;
	@NotBlank
	@NotNull
	private String lastName;
	@NotBlank
	@NotNull
	private String ssn;
	private int numberOfCheckingAccounts = 0;
	private int numberOfSavingsAccounts = 0;
	private int numberOfCDAccounts = 0;
	//private long accountID;
	
	public AccountHolder() {
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.ssn = "";
		this.id = nextId++;
	}
	
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.id = nextId++;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsFraudSuspicionLimitException {
		if (openingBalance >= 1000) {
			throw new ExceedsFraudSuspicionLimitException();
		} else {
			CheckingAccount account = new CheckingAccount(openingBalance);
			account.setInterestRate(MeritBank.getCheckingInterest());
			if (((this.getCheckingBalance() + this.getSavingsBalance()) + openingBalance < 250000)) {
				checkingAccounts.add(account);
				numberOfCheckingAccounts++;
				return account;
			} else {
				return account;
			}
		}
	}
	
	public CheckingAccount addCheckingAccount(BankAccount checkingAccount) {
		if ((this.getCheckingBalance() + this.getSavingsBalance()) + checkingAccount.getBalance() < 250000) {
			checkingAccounts.add((CheckingAccount) checkingAccount);
			numberOfCheckingAccounts++;
			return (CheckingAccount) checkingAccount;
		} else {
			return (CheckingAccount) checkingAccount;
		}
	}
		
	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}
		
	public int getNumberOfCheckingAccounts() {
		return numberOfCheckingAccounts;
	}
		
	public double getCheckingBalance() {
		double tempBalance = 0;
		for (CheckingAccount checkingAccount : checkingAccounts) {
			tempBalance += checkingAccount.getBalance();
		}
		return tempBalance;
	}
		
	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsFraudSuspicionLimitException {
		if (openingBalance >= 1000) {
			throw new ExceedsFraudSuspicionLimitException();
		}
		SavingsAccount account = new SavingsAccount(openingBalance);
		if (((this.getCheckingBalance() + this.getSavingsBalance()) + openingBalance <= 250000)) {
			savingsAccounts.add(account);
			numberOfSavingsAccounts++;
			return account;
		} else {
			return  account;
		}
	}
	
	public SavingsAccount addSavingsAccount(BankAccount savingsAccount) {
		if ((this.getCheckingBalance() + this.getSavingsBalance()) + savingsAccount.getBalance() < 250000) {
			savingsAccounts.add((SavingsAccount) savingsAccount);
			numberOfSavingsAccounts++;
			return (SavingsAccount) savingsAccount;
		} else {
			return (SavingsAccount) savingsAccount;
		}
	}
	
	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}
	
	public int getNumberOfSavingsAccounts() {
		return numberOfSavingsAccounts;
	}
		
	public double getSavingsBalance() {
		double tempBalance = 0;
		for (SavingsAccount savingsAccount : savingsAccounts) {
			tempBalance += savingsAccount.getBalance();
		}
		return tempBalance;
	}
		
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) throws ExceedsFraudSuspicionLimitException {
		if (openingBalance >= 1000) {
			throw new ExceedsFraudSuspicionLimitException();
		}
		CDAccount cdAccount = new CDAccount(offering, openingBalance);
		cdAccounts.add(cdAccount);
		numberOfCDAccounts++;
		return cdAccount;
		
	}
		
	public CDAccount addCDAccount(BankAccount cdAccount) {
		cdAccounts.add((CDAccount) cdAccount);
		numberOfCDAccounts++;
		return (CDAccount) cdAccount;
	}
		
	public List<CDAccount> getCDAccounts() {
		return cdAccounts;
	}
		
	public int getNumberOfCDAccounts() {
		return numberOfCDAccounts;
	}
		
	public double getCDBalance() {
		double tempBalance = 0;
		for (CDAccount cdAccount : cdAccounts) {
			tempBalance += cdAccount.getBalance();
		}
		return tempBalance;
	}
		
	public double getCombinedBalance() {
		combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return combinedBalance;
	}

	@Override
	public int compareTo(AccountHolder accHold) {
		if (this.getCombinedBalance() > accHold.getCombinedBalance()) {
			return 1;
		} else if (this.getCombinedBalance() < accHold.getCombinedBalance()) {
			return -1;
		} else {
		return 0;
		}
	}
	
	static AccountHolder readFromString(String accountHolderData) throws ParseException{
		AccountHolder fromStringAccount = new AccountHolder();
		try {
			String[] accountDataFormatter = accountHolderData.split(",", -1);
				fromStringAccount.lastName = accountDataFormatter[0];
				fromStringAccount.middleName = accountDataFormatter[1];
				fromStringAccount.firstName = accountDataFormatter[2];
				fromStringAccount.ssn = accountDataFormatter[3];	
		} catch (NumberFormatException e) {
			System.out.println("That's not valid data input");
		}
		return fromStringAccount;
	}

	@Override
	public String toString() {
		return lastName + "," + middleName + "," + firstName + "," + ssn;
	}
	
}
