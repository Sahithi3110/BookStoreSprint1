package com.cg.bookstore.exception;

public class OrderDetailsEmptyException extends RuntimeException{
	
	public OrderDetailsEmptyException(String message) {
		
		super(message);
	}

}
