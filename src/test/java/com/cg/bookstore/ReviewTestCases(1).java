package com.cg.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.IReviewRepository;
import com.cg.bookstore.service.IReviewService;

@SpringBootTest
public class ReviewTestCases extends BookstoreApplicationTests {

	@Autowired
	IReviewRepository iReviewRepository;

	@Autowired
	IReviewService iReviewService;

	@Autowired
	IBookRepository iBookRepository;

	@Autowired
	ICustomerRepository iCustomerRepository;

	/*
	 * Test to list all reviews
	 */
	@Test
	public void testListAllReviews() {
		List<Review> reviews1 = iReviewService.listAllReviews();
		List<Review> reviews2 = iReviewRepository.findAll();

		assertThat(reviews1.size()).isEqualTo(reviews2.size());
	}

	/*
	 * Test to view particular review
	 */
	@Test
	public void testViewReview() {
		Review rev = iReviewRepository.findById(500).get();
		Review review = iReviewService.viewReview(rev);
		Review review2 = iReviewRepository.findById(rev.getReviewId()).get();

		assertThat(review.getReviewId()).isEqualTo(review2.getReviewId());
	}

	/*
	 * Test for update review
	 */
	@Test
	public void testUpdateReview() {
		Review rev = iReviewRepository.findById(500).get();
		rev.setHeadLine("Awesome Yrr");
		iReviewService.updateReview(rev);

		assertThat(rev.getHeadLine()).isEqualTo("Awesome Yrr");
	}

	/*
	 * Test to list all reviews for a particular book
	 */
	@Test
	public void testListAllReviewsByBook() {
		Book book = iBookRepository.findById(1).get();
		List<Review> reviews = iReviewService.listAllReviewsByBook(book);

		assertThat(reviews.size()).isEqualTo(2);
	}

	/*
	 * Test to list reviews for a particular customer
	 */
	@Test
	public void testListAllReviewsByCustomer() {
		Customer customer = iCustomerRepository.findById(75).get();
		List<Review> reviews = iReviewService.listAllReviewsByCustomer(customer);

		assertThat(reviews.size()).isEqualTo(2);
	}

	/*
	 * Test for most sold out book
	 */
	@Test
	public void testListMostFavoredBooks() {

		Book book = iReviewService.listMostFavoredBooks();

		assertThat(book.getBookId()).isEqualTo(1);
	}

	/*
	 * Test for add review
	 */
	@Test
	public void testAddReview() {
		List<Review> beforeCreation = iReviewRepository.findAll();
		Book book = iBookRepository.findById(8).get();
		Customer customer = iCustomerRepository.findById(80).get();
		Review newReview = new Review(5, book, customer, "Lot's of nice things", "Amazing one", 6.4, LocalDate.now());
		iReviewService.addReview(newReview);
		List<Review> afterCreation = iReviewRepository.findAll();

		assertThat(afterCreation.size()).isEqualTo(beforeCreation.size() + 1);
	}

	/*
	 * Test to delete review
	 */
	@Test
	void testDeleteReview() {
		List<Review> beforeDelete = iReviewRepository.findAll();
		iReviewService.deleteReview(iReviewRepository.findById(4).get());
		List<Review> afterDelete = iReviewRepository.findAll();

		assertThat(beforeDelete.size() - 1).isEqualTo(afterDelete.size());
	}

}
