package com.cg.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.entities.UserClass;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IOrderRepository;
import com.cg.bookstore.service.IOrderService;

@SpringBootTest
public class OrderDetailsModuleTests {
	
	
	
	@Autowired
	private IOrderRepository iOrderRepository;
	
	@Autowired
	private IBookRepository iBookRepository;
	
	@Autowired
	private IOrderService iOrderService;
	
	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	
	
	/*          Woorking Fine     */
	
	@Test
	public void testListAllOrders() {
		List<OrderDetails> beforeInsertion = iOrderRepository.findAll();
		Book b1 = iBookRepository.getOne(4);
		Book b2 = iBookRepository.findById(1).get();
		OrderDetails orderDetails1 = new OrderDetails(10006, new BookOrder(1001, new Customer(510, "shambhawi@gmail.com", "Shambhawi Bhardwaj", "sam@bhardwaj", new Address(6, "House No - 0013", "Muzafarpur", "India", "901205"), "9463521078", LocalDate.now(), new UserClass(100003, "shambhawi@gmail.com", "sam@bhardwaj", "Admin")), LocalDate.now(), 468.20, "Delivered", new Address(6, "House No - 0013", "Muzafarpur", "India", "901205"), "Online", "Shambhawi Bhardwaj", "9463521078"), b1, 4, 984.56);
		OrderDetails orderDetails2 = new OrderDetails(10007, new BookOrder(1003, new Customer(732, "bhardwaj@gmail.com", "Nitin Nishant", "nitin@bhardwaj", new Address(3, "House No - 0071", "Khagaria", "India", "851204"), "8051326547", LocalDate.now(), new UserClass(100002, "bhardwaj@gmail.com", "nitin@bhardwaj", "Admin")), LocalDate.now(), 960.20, "Pending", new Address(3, "House No - 0071", "Khagaria", "India", "851204"), "Online", "Nitin Nishant", "8051326547"), b2, 6, 1375.56);
		
		iOrderRepository.save(orderDetails1);
		iOrderRepository.save(orderDetails2);
		
		Iterable<OrderDetails> allOrderDetailsFromDb = iOrderRepository.findAll();
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		for(OrderDetails orderDetails:allOrderDetailsFromDb) {
			orderDetailsList.add(orderDetails);
		}
		
		assertThat(orderDetailsList.size()).isEqualTo(beforeInsertion.size() + 2);
		
	}   
	
	
	/*    WORKING FINE		*/
	
	@Test
	public void testUpdateOrder() {
		OrderDetails orderDetails = iOrderRepository.findById(31).get();
		orderDetails.getBook().setBookId(1);
		iOrderService.updateOrder(orderDetails);
		
		OrderDetails getFromDb = iOrderRepository.findById(31).get();
		
		assertThat(getFromDb.getBook().getBookId()).isEqualTo(1);
	}     
	
	
	
	/*   NOT WORKING   
	
	
	@Test
	public void testCancelOrder() {
		
		OrderDetails beforeCanceling = iOrderRepository.findById(10001).get();
		iOrderService.cancelOrder(beforeCanceling);
		OrderDetails afterCanceling = iOrderRepository.findById(10001).get();
		
		assertThat(afterCanceling.getBookOrder().getStatus()).isEqualTo("Canceled");
	}     
	*/
	
	
	
	/*      WORKING FINE			*/
	
	
	@Test
	public void testListOrderByCustomer() {
		
		Customer customer = iCustomerRepository.findById(75).get();
		List<OrderDetails> listOrderByCustomer = iOrderService.listOrderByCustomer(customer);
		
		List<OrderDetails> orderDetails = iOrderRepository.findAll();
		List<OrderDetails> orderDetails2 = new ArrayList<>();
		for(OrderDetails od:orderDetails) {
			if(od.getBookOrder().getCustomer().getCustomerId() == customer.getCustomerId()) {
				orderDetails2.add(od);
			}
		}
		
		assertThat(listOrderByCustomer.size()).isEqualTo(orderDetails2.size());
	}
	
							
	
	
	
	/*      WORKING FINE			*/
	
	
	@Test
	public void testViewOrderByBook() {
		
		List<OrderDetails> orderDetails1 = iOrderService.viewOrderByBook();
		List<OrderDetails> orderDetails2 = iOrderRepository.findByOrderByBook();
		
		assertThat(orderDetails1.size()).isEqualTo(orderDetails2.size());
	}
	         
	
	
	
	/*      WORKING FINE		*/
	 
	 
	
	@Test
	public void testListBestSellingBook() {
		
		int bestSellingBookId = iOrderService.listBestSellingBook().getBookId();
		
		assertThat(bestSellingBookId).isEqualTo(1);
	}
	
	             
	
	
	
	
	/*   WORKING FINE		 */
	
	@Test
	public void testViewOrderForCustomer() {
		
		List<OrderDetails> beforeInsertion = iOrderService.viewOrderForCustomer();
		int beforeCount = 0;
		for(OrderDetails od:beforeInsertion)
			beforeCount++;
		// In this order has been done by Admin
		OrderDetails orderDetails1 = new OrderDetails(10101, new BookOrder(1003, new Customer(732, "nishant@gmail.com", "Nishant", "nishant@bhardwaj", new Address(1, "House No - 0071", "Khagaria", "India", "851204"), "8051323800", LocalDate.now(), new UserClass(100003, "bhardwaj@gmail.com", "Bhardwaj@123", "Admin")), LocalDate.now(), 900.20, "Delivered", new Address(1, "House No - 0071", "Khagaria", "India", "851204"), "Online", "Nishant Bhardwaj", "8051323800"), new Book(3, "Gem of Life", "H.J.Kanaya", new Category(11, "Fiction"), "Based on some life story", "#789654", 964.12, LocalDate.now(), LocalDate.now()), 3, 220.80);
		// In this order has been done by Customer
		OrderDetails orderDetails2 = new OrderDetails(10101, new BookOrder(1008, new Customer(75, "Swati@gmail.com", "Swati kumari", "swati@bhardwaj", new Address(4, "House No - 20", "Patna", "India", "900100"), "4563210987", LocalDate.now(), new UserClass(100005, "kumkum@gmail.com", "Kumkum@bhardwaj", "customer")), LocalDate.now(), 400.30, "Pending", new Address(4, "House No - 20", "Patna", "India", "900100"), "Cash on delivery", "Swati Rani", "4563210987"), new Book(4, "Gem of Life", "H.J.Kanaya", new Category(6, "Fiction"), "Based on some life story", "#789654", 964.12, LocalDate.now(), LocalDate.now()), 6, 875.20);
		iOrderRepository.save(orderDetails1);
		iOrderRepository.save(orderDetails2);
		
		List<OrderDetails> afterInsertion = iOrderService.viewOrderForCustomer();
		int afterCount = 0;
		for(OrderDetails od:afterInsertion)
			afterCount++;
		
		assertThat(afterCount).isEqualTo(beforeCount+1);
	}    
	
	
	
	/* WORKING FINE		*/
	
	@Test
	public void testViewOrderForAdmin() {
		
		List<OrderDetails> beforeInsertion = iOrderService.viewOrderForAdmin();
		int beforeCount = 0;
		for(OrderDetails od:beforeInsertion)
			beforeCount++;
		
		// In this order has been done by Admin
		OrderDetails orderDetails1 = new OrderDetails(10101, new BookOrder(1003, new Customer(732, "nishant@gmail.com", "Nishant", "nishant@bhardwaj", new Address(1, "House No - 0071", "Khagaria", "India", "851204"), "8051323800", LocalDate.now(), new UserClass(100003, "bhardwaj@gmail.com", "Bhardwaj@123", "Admin")), LocalDate.now(), 900.20, "Delivered", new Address(1, "House No - 0071", "Khagaria", "India", "851204"), "Online", "Nishant Bhardwaj", "8051323800"), new Book(3, "Gem of Life", "H.J.Kanaya", new Category(11, "Fiction"), "Based on some life story", "#789654", 964.12, LocalDate.now(), LocalDate.now()), 3, 220.80);
		// In this order has been done by Customer
		OrderDetails orderDetails2 = new OrderDetails(10101, new BookOrder(1008, new Customer(75, "Swati@gmail.com", "Swati kumari", "swati@bhardwaj", new Address(4, "House No - 20", "Patna", "India", "900100"), "4563210987", LocalDate.now(), new UserClass(100005, "kumkum@gmail.com", "Kumkum@bhardwaj", "customer")), LocalDate.now(), 400.30, "Pending", new Address(4, "House No - 20", "Patna", "India", "900100"), "Cash on delivery", "Swati Rani", "4563210987"), new Book(4, "Gem of Life", "H.J.Kanaya", new Category(6, "Fiction"), "Based on some life story", "#789654", 964.12, LocalDate.now(), LocalDate.now()), 6, 875.20);
		iOrderRepository.save(orderDetails1);
		iOrderRepository.save(orderDetails2);
		
		List<OrderDetails> afterInsertion = iOrderService.viewOrderForAdmin();
		int afterCount = 0;
		for(OrderDetails od:afterInsertion)
			afterCount++;
		
		assertThat(afterCount).isEqualTo(beforeCount+1);
	}     
	
	
	
	/*      WORKING FINE		*/
	
	@Test
	public void testAddOrder() {
		
		List<OrderDetails> beforeInsertion = iOrderRepository.findAll();
		int beforeCount = 0;
		for(OrderDetails od:beforeInsertion)
			beforeCount++;
		
		OrderDetails orderDetails2 = new OrderDetails(10101, new BookOrder(1008, new Customer(75, "Swati@gmail.com", "Swati kumari", "swati@bhardwaj", new Address(4, "House No - 20", "Patna", "India", "900100"), "4563210987", LocalDate.now(), new UserClass(100005, "kumkum@gmail.com", "Kumkum@bhardwaj", "customer")), LocalDate.now(), 400.30, "Pending", new Address(4, "House No - 20", "Patna", "India", "900100"), "Cash on delivery", "Swati Rani", "4563210987"), new Book(4, "Gem of Life", "H.J.Kanaya", new Category(6, "Fiction"), "Based on some life story", "#789654", 964.12, LocalDate.now(), LocalDate.now()), 6, 875.20);
		iOrderService.addOrder(orderDetails2);
		
		List<OrderDetails> afterInsertion = iOrderRepository.findAll();
		int afterCount = 0;
		for(OrderDetails od:afterInsertion)
			afterCount++;
		
		assertThat(afterCount).isEqualTo(beforeCount+1);
	}    

}
