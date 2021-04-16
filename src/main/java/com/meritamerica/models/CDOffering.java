package com.meritamerica.models;

public class CDOffering implements Comparable<CDOffering>{
	
	private int term;
	private double interestRate;
	
	public CDOffering () {
		
	}
	
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
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
