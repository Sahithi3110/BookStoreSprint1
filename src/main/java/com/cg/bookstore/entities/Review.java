package com.cg.bookstore.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Review {

	/*
	 * Field Declaration
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	
	@OneToOne
	@JoinColumn(name = "book_id", referencedColumnName = "bookId")
	private Book book;
	
	@OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customerSecond;
	
	private String headLine;
	private String comment;
	private double rating;
	private LocalDate reviewOn;
	
	/*
	 * Default Constructor
	 */
	
	public Review() {
		
	}
	
	/*
	 * Parameterized Constructor
	 */
	
	public Review(int reviewId, Book book, Customer customerSecond, String headLine, String comment, double rating,
			LocalDate reviewOn) {
		super();
		this.reviewId = reviewId;
		this.book = book;
		this.customerSecond = customerSecond;
		this.headLine = headLine;
		this.comment = comment;
		this.rating = rating;
		this.reviewOn = reviewOn;
	}

	/*
	 * Getter, Setter For All Fields
	 */
	
	public int getReviewId() {
		return reviewId;
	}
	
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Customer getCustomer() {
		return customerSecond;
	}
	
	public void setCustomer(Customer customerSecond) {
		this.customerSecond = customerSecond;
	}
	
	public String getHeadLine() {
		return headLine;
	}
	
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public LocalDate getReviewOn() {
		return reviewOn;
	}
	
	public void setReviewOn(LocalDate reviewOn) {
		this.reviewOn = reviewOn;
	}

	public Customer getCustomerSecond() {
		return customerSecond;
	}

	public void setCustomerSecond(Customer customerSecond) {
		this.customerSecond = customerSecond;
	}
	
}
