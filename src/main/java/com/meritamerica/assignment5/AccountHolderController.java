package com.meritamerica.assignment5;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.meritamerica.models.AccountHolder;
import com.meritamerica.models.CDAccount;
import com.meritamerica.models.CheckingAccount;
import com.meritamerica.models.MeritBank;
import com.meritamerica.models.SavingsAccount;

@RestController
public class AccountHolderController {
	
	List<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
	List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
	List<CDAccount> cdAccounts = new ArrayList<CDAccount>();
	
	@PostMapping("/account-holders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder postAccountHolder(@RequestBody @Valid AccountHolder accHolder ) {
		MeritBank.addAccountHolder(accHolder);
		return accHolder;
	}
	
	@GetMapping(value = "/account-holders")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolder> getAccountHolders() {
		return MeritBank.getAccountHolders();
	}
	
	@GetMapping("/account-holders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder getAccountHolderById(@PathVariable int id) throws NoSuchResourceFoundException{
		if (0 < id && id < (MeritBank.getAccountHolders().size() - 1)) {
			throw new NoSuchResourceFoundException("Account Holder does not exist.");
		} else {
			return MeritBank.getAccountHolders().get(id-1);
		}
	}
	
	@PostMapping("/account-holders/{id}/checking-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount postCheckingAccount(
			@PathVariable int id, @RequestBody @Valid CheckingAccount checkingAccount ) throws NoSuchResourceFoundException{
		
		MeritBank.getAccountHolders().get(id-1).addCheckingAccount(checkingAccount);
		
		
		return checkingAccount;
	}
	
	@GetMapping("/account-holders/{id}/checking-accounts")
	@ResponseStatus(HttpStatus.OK)
	public List<CheckingAccount> getCheckingAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		if (0 < id && id < (MeritBank.getAccountHolders().size() - 1) && MeritBank.getAccountHolders().get(id-1).getCheckingAccounts() != null) {
			throw new NoSuchResourceFoundException("Account Holder does not exist.");
		} else {
			return MeritBank.getAccountHolders().get(id-1).getCheckingAccounts();
		}
	}
	
	@PostMapping("/account-holders/{id}/savings-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount postSavingsAccount(
			@PathVariable int id, @RequestBody @Valid SavingsAccount savingsAccount ) throws NoSuchResourceFoundException{
		
		MeritBank.getAccountHolders().get(id-1).addSavingsAccount(savingsAccount);
		
		
		return savingsAccount;
	}
	
	@GetMapping("/account-holders/{id}/savings-accounts")
	@ResponseStatus(HttpStatus.OK)
	public List<SavingsAccount> getSavingsAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		if (0 < id && id < (MeritBank.getAccountHolders().size() - 1) && MeritBank.getAccountHolders().get(id-1).getSavingsAccounts() != null) {
			throw new NoSuchResourceFoundException("Account Holder does not exist.");
		} else {
			return MeritBank.getAccountHolders().get(id-1).getSavingsAccounts();
		}
	}
	
	@PostMapping("/account-holders/{id}/cd-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount postCDAccount(
			@PathVariable int id, @RequestBody @Valid CDAccount cdAccount ) throws NoSuchResourceFoundException{
		
		MeritBank.getAccountHolders().get(id-1).addCDAccount(cdAccount);
		
		
		return cdAccount;
	}
	
	@GetMapping("/account-holders/{id}/cd-accounts")
	@ResponseStatus(HttpStatus.OK)
	public List<CDAccount> getCDAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		if (0 < id && id < (MeritBank.getAccountHolders().size() - 1) && MeritBank.getAccountHolders().get(id-1).getCDAccounts() != null) {
			throw new NoSuchResourceFoundException("Account Holder does not exist.");
		} else {
			return MeritBank.getAccountHolders().get(id-1).getCDAccounts();
		}
	}
	
	
	
	
	
	

}
