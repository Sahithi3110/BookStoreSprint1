package com.cg.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderDetails {

	/*
	 * Field Declaration
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderDetailsId;

	@OneToOne
	@JoinColumn(name = "order_id", referencedColumnName = "orderId")
	private BookOrder bookOrder;

	@OneToOne
	@JoinColumn(name = "bookId")
	private Book book;

	private int quantity;
	private double subtotal;

	/*
	 * Default Constructor
	 */

	public OrderDetails() {

	}

	/*
	 * Parameterized Constructor
	 */

	public OrderDetails(int orderDetailsId, BookOrder bookOrder, Book book, int quantity, double subtotal) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.bookOrder = bookOrder;
		this.book = book;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	/*
	 * Getter, Setter For All Fields
	 */

	public int getOrderDetailsId() {
		return orderDetailsId;
	}

	public void setOrderDetailsId(int orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}

	public BookOrder getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

}
