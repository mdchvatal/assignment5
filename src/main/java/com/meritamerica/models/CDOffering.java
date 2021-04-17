package com.meritamerica.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CDOffering implements Comparable<CDOffering>{
	
	protected double futureBalance;
	
	
	@NotNull
	@Positive
	private int term;
	
	
	@NotNull
	@Max(1)
	@Positive
	private double interestRate;
	
	private static int nextId = 1;
	private int id;
	
	public CDOffering() {
	}
	
	public CDOffering(int id) {
		this.interestRate = MeritBank.getCDOfferings().get(id-1).getInterestRate();
		this.term = MeritBank.getCDOfferings().get(id-1).getTerm();
		this.id = id;
	}
	

	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
		this.id = nextId++;
	}

	public int getTerm() {
		return term;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}

	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(CDOffering o) {
		if ((Math.pow((1+this.getInterestRate()), this.getTerm())) > (Math.pow((1+o.getInterestRate()), o.getTerm()))) {
			return 1;
		} else if ((Math.pow((1+this.getInterestRate()), this.getTerm())) == (Math.pow((1+o.getInterestRate()), o.getTerm()))) {
			return 0;
		} else {
			return -1;
		}
	}
	
	static CDOffering readFromString(String cdOfferingDataString) throws NumberFormatException{
		CDOffering fromStringAccount = new CDOffering();
		String[] accountDataFormatter = cdOfferingDataString.split(",");
		if (accountDataFormatter.length != 2) {
			throw new NumberFormatException();
		}else {
			fromStringAccount.setTerm(Integer.parseInt(accountDataFormatter[0]));
			fromStringAccount.setInterestRate(Double.parseDouble(accountDataFormatter[1]));
			return fromStringAccount;
		}
	}
	
}
