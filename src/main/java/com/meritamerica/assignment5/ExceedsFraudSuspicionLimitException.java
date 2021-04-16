package com.meritamerica.assignment5;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceedsFraudSuspicionLimitException extends Exception {

	public ExceedsFraudSuspicionLimitException(String msg) {
		super(msg);
	}
}
