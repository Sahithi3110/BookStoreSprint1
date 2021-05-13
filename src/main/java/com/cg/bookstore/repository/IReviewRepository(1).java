package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Review;

@Repository
public interface IReviewRepository extends  JpaRepository<Review, Integer>{

	
	@Query(value="select * from Review", nativeQuery=true)
	public List<Review> listAllReviews();
	
	
	@Query(value="select * from Reviews", nativeQuery=true)
	public Review viewReview(Review review);
	
	
	@Query(value="select count(review_id) from review", nativeQuery=true)
	public int reviewCount();
	
	@Query(value = "SELECT book_id FROM review GROUP BY book_id ORDER BY count(*) desc LIMIT 1", nativeQuery = true)
	public int mostSellingBook();
	
	//public List<Book> listMostFavoredBooks();
	
	
	
	
	
	
	
	
	
	/*public List<Review> listAllReviews();
	public Review addReview(Review review);
	public Review deleteReview(Review review);
	public Review updateReview(Review review);
	public Review viewReview(Review review);
	public List<Review> listAllReviewsByBook(Book book);
	public List<Review> listAllReviewsByCustomer(Customer c);
	public List<Book> listMostFavoredBooks();*/
}
