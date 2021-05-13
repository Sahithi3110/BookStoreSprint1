package com.cg.bookstore.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.exception.CustomerNotFoundException;
import com.cg.bookstore.exception.OrderDetailsEmptyException;
import com.cg.bookstore.exception.OrderDetailsNotFoundException;
import com.cg.bookstore.exception.OrderNotFoundForAdminException;
import com.cg.bookstore.exception.OrderNotFoundForCustomerException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IOrderRepository;
import com.cg.bookstore.service.IOrderService;

@Service
public class OrderServiceImplementation implements IOrderService {

	@Autowired
	private IOrderRepository iOrderRepository;

	@Autowired
	private IBookRepository iBookRepository;

	
	/*
	 * This method return the best selling book, by finding the book has been sold
	 * out maximum number of times.
	 */
	@Override
	@Transactional
	public Book listBestSellingBook() throws OrderDetailsEmptyException {

		Book book = iBookRepository.findById(iOrderRepository.listBestSellingBook()).get();
		
		if(book != null) {
			return book;
		}
		else {
			throw new OrderDetailsEmptyException("Sorry, Till now no book is ordered...!");
		}
	}

	/*
	 * This method returns all the order details.
	 */
	@Override
	@Transactional
	public List<OrderDetails> listAllOrders() throws OrderDetailsEmptyException {

		List<OrderDetails> orderDetails = iOrderRepository.findAll(); // return all records

		if (orderDetails.isEmpty()) {
			throw new OrderDetailsEmptyException("Sorry, Order Details Is Empty...!");
		} else {
			return orderDetails;
		}
	}

	/*
	 * This method add a particular order details into database.
	 */
	@Override
	@Transactional
	public OrderDetails addOrder(OrderDetails od) {
		return iOrderRepository.save(od);
	}

	/*
	 * This method return all the order details, that has been done by a particular
	 * customer.
	 */
	@Override
	@Transactional
	public List<OrderDetails> listOrderByCustomer(Customer cs) throws CustomerNotFoundException {
		List<OrderDetails> temp = iOrderRepository.findAll();
		List<OrderDetails> orderDetails = new ArrayList<>();
		for (OrderDetails tp : temp) {
			if (tp.getBookOrder().getCustomer().getCustomerId() == cs.getCustomerId()) {
				orderDetails.add(tp);
			}
		}
		if(orderDetails.isEmpty()) {
			throw new CustomerNotFoundException("Sorry, You have not ordered till now...!");
		}
		else {
			return orderDetails;
		}
	}

	/*
	 * This method update the order details, if order details is not present in
	 * database then it will throw an exception.
	 */
	@Override
	@Transactional
	public OrderDetails updateOrder(OrderDetails od) throws OrderDetailsNotFoundException {
		Optional<OrderDetails> orderdDetails = iOrderRepository.findById(od.getOrderDetailsId());
		if (orderdDetails.isPresent())
			return iOrderRepository.save(od);
		else
			throw new OrderDetailsNotFoundException("Sorry, Order Not Found...!");
	}

	/*
	 * This method return all the order details, that has been done by the Admin.
	 */
	@Override
	@Transactional
	public List<OrderDetails> viewOrderForAdmin() throws OrderNotFoundForAdminException {

		List<OrderDetails> temporary = iOrderRepository.findAll();
		List<OrderDetails> orderDetails = new ArrayList<>();
		String string = "admin";

		for (OrderDetails temp : temporary) {
			if (temp.getBookOrder().getCustomer().getUserClass().getRole().toLowerCase().equals(string)) {
				orderDetails.add(temp);
			}
		}
		if (orderDetails.isEmpty()) {
			throw new OrderNotFoundForAdminException("Soory, Order Details Not Found For Admin...!");
		} else {
			return orderDetails;
		}
	}

	/*
	 * This method return all the order details, that has been done by the Customer.
	 */
	@Override
	@Transactional
	public List<OrderDetails> viewOrderForCustomer() throws OrderNotFoundForCustomerException {
		List<OrderDetails> temporary = iOrderRepository.findAll();
		List<OrderDetails> orderDetails = new ArrayList<>();
		String string = "customer";

		for (OrderDetails temp : temporary) {
			if (temp.getBookOrder().getCustomer().getUserClass().getRole().toLowerCase().equals(string)) {
				orderDetails.add(temp);
			}
		}
		if (orderDetails.isEmpty()) {
			throw new OrderNotFoundForCustomerException("Soory, Order Details Not Found For Customer...!");
		} else {
			return orderDetails;
		}
	}

	/*
	 * This method return all the books, ORDER BY Book.
	 */
	@Override
	@Transactional
	public List<OrderDetails> viewOrderByBook() throws OrderDetailsEmptyException {

		List<OrderDetails> orderDetails = iOrderRepository.findByOrderByBook();

		if (orderDetails.isEmpty()) {
			throw new OrderDetailsEmptyException("Sorry, Order Details Is Empty...!");
		} else {
			return orderDetails;
		}
	}

	/*
	 * This method cancel the order details if exist, if order details not exist in
	 * database then it will throw an exception.
	 */
	@Override
	@Transactional
	public OrderDetails cancelOrder(OrderDetails od) throws OrderDetailsNotFoundException {
		OrderDetails orderDetails = iOrderRepository.findById(od.getOrderDetailsId()).orElse(null); // return a
																									// particular record
		if (orderDetails == null) {
			throw new OrderDetailsNotFoundException(
					"Sorry, You have entered wrong order details to cancel the order...!");
		} else {
			orderDetails.getBookOrder().setStatus("Canceled");
			orderDetails.getBookOrder().setOrderDate(null);
			orderDetails.getBookOrder().setOrderTotal(0);
			orderDetails.getBookOrder().setPaymentMethod(null);
			orderDetails.getBookOrder().setRecipientName(null);
			orderDetails.getBookOrder().setRecipientPhone(null);
			orderDetails.getBookOrder().setCustomer(null);
			orderDetails.getBookOrder().setShippingAddress(null);
			orderDetails.setBook(null);
			orderDetails.setQuantity(0);
			orderDetails.setSubtotal(0);
			iOrderRepository.save(orderDetails); // create new record

			return iOrderRepository.findById(od.getOrderDetailsId()).orElse(null);
		}
	}

	public IOrderRepository getiOrderRepository() {
		return iOrderRepository;
	}

	public void setiOrderRepository(IOrderRepository iOrderRepository) {
		this.iOrderRepository = iOrderRepository;
	}

}
