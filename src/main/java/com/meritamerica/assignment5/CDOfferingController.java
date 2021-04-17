package com.meritamerica.assignment5;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.models.CDOffering;
import com.meritamerica.models.MeritBank;

@RestController
public class CDOfferingController {
	
	
	@PostMapping("/cd-offerings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering postCDOffering(@RequestBody @Valid CDOffering cdOffering) { 
			MeritBank.addCDOffering(cdOffering);
			return cdOffering;
	}
	
	@GetMapping("/cd-offerings")
	@ResponseStatus(HttpStatus.OK)
	public List<CDOffering> getCDOfferings() {
		return MeritBank.getCDOfferings();
	}
	
}
