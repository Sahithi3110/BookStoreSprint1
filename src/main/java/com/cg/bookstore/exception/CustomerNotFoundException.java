package com.cg.bookstore.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String message) {
		
		super(message);
	}

}
