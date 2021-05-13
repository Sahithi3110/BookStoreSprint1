package com.cg.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.UserClass;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.ILoginRepository;
import com.cg.bookstore.service.ICustomerService;

@SpringBootTest
public class CustomerControllerTest extends BookstoreApplicationTests {

	@Autowired
	ICustomerRepository iCustomerRepository;

	@Autowired
	ICustomerService iCustomerService;

	@Autowired
	ILoginRepository iLoginRepository;

	/*
	 * Test to get all customers
	 */
	@Test
	void getallCustomers() {
		int count = iCustomerRepository.customerCount();
		List<Customer> list = iCustomerRepository.findAll();
		assertEquals(count, list.size());

	}

	/*
	 * Test for view particular customer
	 */
	@Test
	public void testViewCustomers() {
		Customer customer = iCustomerRepository.findById(75).get();
		Customer customer2 = iCustomerService.viewCustomer(customer);

		assertThat(customer.getCustomerId()).isEqualTo(customer2.getCustomerId());
	}

	/*
	 * Test for update customer
	 */
	@Test
	public void testUpdate() {
		Customer customer = iCustomerRepository.findById(75).get();
		customer.setFullName("Raghav Kumar");
		iCustomerService.updateCustomer(customer);
		Customer customer2 = iCustomerRepository.findById(75).get();

		assertThat(customer2.getFullName()).isEqualTo("Raghav Kumar");
	}

	/*
	 * Test for create customer
	 */
	@Test
	public void testCreateCustomer() {
		List<Customer> beforeCreation = iCustomerRepository.findAll();
		UserClass userClass = iLoginRepository.findById(100008).get();
		Address address = new Address(9, "House No 236", "Jaipur", "India", "400123");
		Customer customer = new Customer(7, "tanu@gmail.com", "Tanu Kumari", "tanu@456", address, "7878965412",
				LocalDate.now(), userClass);
		iCustomerService.createCustomer(customer);
		List<Customer> afterCreation = iCustomerRepository.findAll();

		assertThat(beforeCreation.size() + 1).isEqualTo(afterCreation.size());
	}

	/*
	 * Test for delete customer
	 */
	@Test
	public void testDeleteCustomer() {
		List<Customer> beforeDelete = iCustomerRepository.findAll();
		iCustomerService.deleteCustomer(iCustomerRepository.findById(7).get());
		List<Customer> afterDelete = iCustomerRepository.findAll();

		assertThat(beforeDelete.size() - 1).isEqualTo(afterDelete.size());
	}

}
