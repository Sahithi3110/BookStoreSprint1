package com.cg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.service.IOrderService;

@RestController
@RequestMapping("/api/v1/orderdetails")
public class OrderDetailsController {

	@Autowired
	private IOrderService iOrderService;

	/*
	 * This method return the best selling book, by finding the book has been sold
	 * out maximum number of times.
	 */
	@GetMapping("/listBestSellingBook")
	public Book listBestSellingBook() {
		return iOrderService.listBestSellingBook();
	}

	/*
	 * This method returns all the order details.
	 */
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrderDetails>> listAllOrders() {
		List<OrderDetails> orderDetails = iOrderService.listAllOrders();

		if (orderDetails.isEmpty()) {
			return new ResponseEntity("Order not available", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<OrderDetails>>(orderDetails, HttpStatus.OK);
		}
	}

	/*
	 * This method add a particular order details into database.
	 */
	@PostMapping("/addOrder")
	public OrderDetails addOrder(@RequestBody OrderDetails od) {
		return iOrderService.addOrder(od);
	}

	/*
	 * This method return all the order details, that has been done by a particular
	 * customer.
	 */
	@PostMapping("/listOrderByCustomer")
	public List<OrderDetails> listOrderByCustomer(@RequestBody Customer cs) {
		return iOrderService.listOrderByCustomer(cs);
	}

	/*
	 * This method update the order details, if order details is not present in
	 * database then it will throw an exception.
	 */
	@PutMapping("/updateOrder")
	public OrderDetails updateOrder(@RequestBody OrderDetails od) {
		return iOrderService.updateOrder(od);
	}

	/*
	 * This method return all the order details, that has been done by the Admin.
	 */
	@GetMapping("/viewOrderForAdmin")
	public List<OrderDetails> viewOrderForAdmin() {
		return iOrderService.viewOrderForAdmin();
	}

	/*
	 * This method return all the order details, that has been done by the Customer.
	 */
	@GetMapping("/viewOrderForCustomer")
	public List<OrderDetails> viewOrderForCustomer() {
		return iOrderService.viewOrderForCustomer();
	}

	/*
	 * This method return all the books, ORDER BY Book.
	 */
	@GetMapping("/orderByBook")
	public ResponseEntity<List<OrderDetails>> viewOrderByBook() {
		List<OrderDetails> orderDetails = iOrderService.viewOrderByBook();
		if (orderDetails.isEmpty()) {
			return new ResponseEntity("Book is Not ordered till now", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<OrderDetails>>(orderDetails, HttpStatus.OK);
		}
	}

	/*
	 * This method cancel the order details if exist, if order details not exist in
	 * database then it will throw an exception.
	 */
	@PutMapping("/cancelOrder")
	public OrderDetails cancelOrder(@RequestBody OrderDetails od) {
		return iOrderService.cancelOrder(od);
	}

	public IOrderService getiOrderService() {
		return iOrderService;
	}

	public void setiOrderService(IOrderService iOrderService) {
		this.iOrderService = iOrderService;
	}

}
