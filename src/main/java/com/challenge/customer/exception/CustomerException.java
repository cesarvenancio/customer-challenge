package com.challenge.customer.exception;

public class CustomerException extends Exception {

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(String message, Exception e) {
		super(message, e);
	}
}
