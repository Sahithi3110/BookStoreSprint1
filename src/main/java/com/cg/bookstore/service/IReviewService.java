package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;

public interface IReviewService {

	public List<Review> listAllReviews();
	public Optional<Review> addReview(Review review);
	public List<Review> deleteReview(Review review);
	public Review updateReview(Review review);
	public Review viewReview(Review review);
	public List<Review> listAllReviewsByBook(Book book);
	public List<Review> listAllReviewsByCustomer(Customer c);
	public Book listMostFavoredBooks();

}
