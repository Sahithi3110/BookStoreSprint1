package com.cg.bookstore.exception;

public class OrderNotFoundForCustomerException extends RuntimeException{
	
	public OrderNotFoundForCustomerException(String message) {
		
		super(message);
	}

}
