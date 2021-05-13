package com.cg.bookstore.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IReviewRepository;
import com.cg.bookstore.service.IReviewService;

@Service
public class ReviewServiceImplementation implements IReviewService{

	@Autowired
	IReviewRepository iReviewRepository;
	
	@Autowired
	IBookRepository iBookRepository;

	
	@Override
	public List<Review> listAllReviewsByBook(Book book) {
		
		List<Review> reviews = iReviewRepository.findAll(); 				// return all records
		List<Review> list = new ArrayList<>();
		
		for(Review rev:reviews) {
			if(rev.getBook().getBookId() == book.getBookId()) {
				list.add(rev);
			}
		}
		return list;
	}
	
	@Override
	public List<Review> listAllReviewsByCustomer(Customer c) {
		
		List<Review> reviews = iReviewRepository.findAll();
		List<Review> list = new ArrayList<>();
		
		for(Review rev:reviews) {
			if(rev.getCustomer().getCustomerId() == c.getCustomerId()) {
				list.add(rev);
			}
		}
		return list;
	}
	
	@Override
	public Book listMostFavoredBooks() {
		Book book = iBookRepository.findById(iReviewRepository.mostSellingBook()).get();          // return a particular record
		return book;
	}
		

	@Override
	public List<Review> deleteReview(Review review){
		
		iReviewRepository.delete(review);                          // delete a particular record
		return iReviewRepository.findAll();
	}
	
	@Override
	public Optional<Review> addReview(Review review){
		
		iReviewRepository.save(review);                  // create new record
		return Optional.of(review);                      // if object found then it will return object else  return null
	}
	
	
	@Override
	public List<Review> listAllReviews(){
		
		return iReviewRepository.listAllReviews();
	}

	@Override
	public Review updateReview(Review review) {
		
		iReviewRepository.save(review);
		return review;
	}

	@Override
	public Review viewReview(Review review) {
		
		return iReviewRepository.findById(review.getReviewId()).get();
		
	}
	
}
