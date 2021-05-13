package com.cg.bookstore.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IOrderRepository;
import com.cg.bookstore.service.ICustomerService;

@Service
public class CustomerServiceImplementation implements ICustomerService {

	@Autowired
	ICustomerRepository iCustomerRepository;
	
	@Autowired 
	IBookRepository iBookRepository;
	
	@Autowired 
	IOrderRepository iOrderRepository;

	@Override
	public Customer createCustomer(Customer c) {
		return iCustomerRepository.save(c);          // create new record
	}

	public Customer deleteCustomer(Customer customer) {
		iCustomerRepository.deleteById(customer.getCustomerId());        // delete a particular record
		return  customer;
	}

	
	public Customer viewCustomer(Customer c) {
		return iCustomerRepository.findById(c.getCustomerId()).get();			// return a particular record
	}

	
	@Override
	public List<Customer> listCustomers() {
		return iCustomerRepository.listCustomers();
	}

	
	@Override
	public Customer updateCustomer(Customer c) {
		return iCustomerRepository.save(c);
	}
	
	
	public List<Customer> listAllCustomersByBook(Book book) {
		List<OrderDetails> orderDetails = iOrderRepository.findAll();              // return all records
		List<Customer> customers = new ArrayList<>();
		for(OrderDetails od:orderDetails) {
			if(od.getBook().getBookId() == book.getBookId()) {
				customers.add(od.getBookOrder().getCustomer());
			}
		}
		return customers;
	}

}
