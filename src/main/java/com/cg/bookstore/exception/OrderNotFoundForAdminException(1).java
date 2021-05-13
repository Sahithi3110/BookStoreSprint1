package com.cg.bookstore.exception;

public class OrderNotFoundForAdminException extends RuntimeException {
	
	public OrderNotFoundForAdminException(String message) {
		
		super(message);
	}

}
