package com.cg.bookstore.exception;

public class OrderDetailsNotFoundException extends RuntimeException{
	
	public OrderDetailsNotFoundException(String message) {
	
		super(message);
	}
	
}
