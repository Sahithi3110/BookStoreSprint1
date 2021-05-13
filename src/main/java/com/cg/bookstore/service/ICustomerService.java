package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;

public interface ICustomerService {

	
	public  Customer createCustomer(Customer c);
	public List<Customer> listCustomers();
	public Customer deleteCustomer(Customer customer);
	public Customer updateCustomer(Customer c);
	public Customer viewCustomer(Customer c);
	public List<Customer> listAllCustomersByBook(Book book);
	
	
	
	
	
	
	
	
	
	/*
	 * public Customer createCustomer(Customer c); 
	 * public List<Customer> listCustomers(); 
	 * public Customer deleteCustomer(Customer c); 
	 * public Customer updateCustomer(Customer c); 
	 * public Customer viewCustomer(Customer c); 
	 * public List<Customer> listAllCustomersByBook(Book book);
	 * 
	 */
	
}
