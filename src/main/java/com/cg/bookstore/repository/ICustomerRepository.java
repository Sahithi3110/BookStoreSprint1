package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "select count(customer_id) from customer", nativeQuery = true)
	public int customerCount();

	@Query(value = "Select c from Customer c")
	public List<Customer> listCustomers();

	/*
	 * @Query(value = "select * from Customer WHERE customer_id=:c.customerId",
	 * nativeQuery = true) public Customer viewCustomer(Customer c);
	 */
	/*
	 * public Customer createCustomer(Customer c); 
	 * public List<Customer> listCustomers();
	 * public Customer deleteCustomer(Customer c); 
	 * public Customer updateCustomer(Customer c); 
	 * public Customer viewCustomer(Customer c);
	 */

}
