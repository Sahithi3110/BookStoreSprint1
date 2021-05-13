package com.cg.bookstore.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGloabalExceptionHandler  extends ResponseEntityExceptionHandler{ 
	
	@ExceptionHandler(OrderDetailsNotFoundException.class)
	public ResponseEntity<Object> handleOrderDetailsNotFoundException(OrderDetailsNotFoundException ex, WebRequest webRequest) {
		String bodyOfResponse = ex.getLocalizedMessage();
		return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OrderDetailsEmptyException.class)
	public ResponseEntity<Object> handleOrderDetailsEmptyException(OrderDetailsEmptyException ex, WebRequest webRequest) {
		String bodyOfResponse = ex.getLocalizedMessage();
		return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OrderNotFoundForCustomerException.class)
	public ResponseEntity<Object> handleOrderNotFoundForCustomerException(OrderNotFoundForCustomerException ex, WebRequest webRequest) {
		String bodyOfResponse = ex.getLocalizedMessage();
		return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest webRequest) {
		String bodyOfResponse = ex.getLocalizedMessage();
		return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(OrderNotFoundForAdminException.class)
	public ResponseEntity<Object> handleOrderNotFoundForAdminException(OrderNotFoundForAdminException ex, WebRequest webRequest) {
		String bodyOfResponse = ex.getLocalizedMessage();
		return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	
	// Sahithi
	
	@ExceptionHandler(value = { NoBookException.class})
	  public ResponseEntity<Object> handleBookException(NoBookException ex, WebRequest request)
	  {
	      String bodyOfResponse = ex.getLocalizedMessage();
		  return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	
	// Aditya 
	
	@ExceptionHandler(value = { UserNotFoundException.class})
	  public ResponseEntity<Object> handleBookException(UserNotFoundException ex, WebRequest request)
	  {
	      String bodyOfResponse = ex.getLocalizedMessage();
		  return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	
	
	//   Aneeket
	
	  @ExceptionHandler(value = { CategoryNotFoundException.class})
	  public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request)
	  {
	      String bodyOfResponse = ex.getLocalizedMessage();
		  return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	  
	  
	  //   Nagalakshmi
	  
	  @ExceptionHandler(value = { ReviewException.class})
	  public ResponseEntity<Object> handleBookException(ReviewException ex, WebRequest request)
	  {
	      String bodyOfResponse = ex.getLocalizedMessage();
		  return new ResponseEntity(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	  
	  
	  //   Anusha
	  
	  

}




