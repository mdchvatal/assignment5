package com.meritamerica.assignment5;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.meritamerica.models.AccountHolder;
import com.meritamerica.models.BankAccount;
import com.meritamerica.models.CDAccount;
import com.meritamerica.models.MeritBank;

public class CDAccountController {
	@GetMapping("account-holders/{id}/cdaccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public List<BankAccount> getCDAccounts(@PathVariable(name = "id") long id) throws NoSuchResourceFoundException {
		AccountHolder acctholder = MeritBank.getAccountHolder(id);
		if (acctholder == null)
			throw new NoSuchResourceFoundException("Invalid Id");

		return acctholder.getCdAccounts();
	}

	@PostMapping("account-holders/{id}/cdaccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CDAccount cdAccount)
			throws NotFoundException, ExceedsCombinedBalanceLimitException, NegativeAmountException {
		AccountHolder acctholder = MeritBank.getAccountHolderById(id);
		acctholder.addCDAccount(cdAccount);
		return cdAccount;
	}

}
