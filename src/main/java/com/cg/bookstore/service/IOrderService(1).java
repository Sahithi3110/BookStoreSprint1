package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;

public interface IOrderService {

	public Book listBestSellingBook();
	public List<OrderDetails> listAllOrders();
	public OrderDetails addOrder(OrderDetails od);
	public List<OrderDetails> viewOrderForAdmin();
	public OrderDetails updateOrder(OrderDetails od);
	public List<OrderDetails> viewOrderForCustomer();  
	public List<OrderDetails> listOrderByCustomer(Customer cs);
	public List<OrderDetails> viewOrderByBook();
	public OrderDetails cancelOrder(OrderDetails od); 

}
