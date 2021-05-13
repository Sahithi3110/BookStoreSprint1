package com.cg.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.exception.NoBookException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.IBookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	IBookService iBookService;

	@Autowired
	IBookRepository iBookRepository;

	/*
	 * Returns all the records from DB
	 */
	@GetMapping(path = "/getall")
	public ResponseEntity<List<Book>> getAllBooks() throws NoBookException {

		List<Book> list = iBookService.listAllBooks();
		if (list.size() == 0)
			throw new NoBookException("Sorry, No Books are found");
		else
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}

	/*
	 * Create a new record
	 */
	@PostMapping(path = "/createBook")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {

		Optional<Book> opi = iBookService.createBook(book);
		return new ResponseEntity<Book>(opi.get(), HttpStatus.OK);
	}

	/*
	 * Delete particular book based on id
	 */
	@DeleteMapping(path = "/deleteBook/{id}")
	public ResponseEntity<List<Book>> deleteBook(@PathVariable Integer id) {

		List<Book> bookObj = iBookService.deleteBook(id);
		return new ResponseEntity<List<Book>>(bookObj, HttpStatus.OK);
	}

	/*
	 * Get book according to category id
	 */
	@GetMapping(path = "/getBooksByCategory/{categoryId}")
	public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Integer categoryId) throws NoBookException {

		List<Book> list = iBookRepository.findByCategory(categoryId);
		if (list.size() == 0)
			throw new NoBookException("Sorry, No books found on this category");
		else
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}

	/*
	 * Edit book details, based on id
	 */
	@PutMapping(path = "/editBook/{id}")
	public ResponseEntity<Book> editBooks(@RequestBody Book book, @PathVariable Integer id) {

		Book bookObj = iBookService.editBook(book, id);
		if (bookObj == null)
			throw new NoBookException("Sorry, No books found to edit");
		else
			return new ResponseEntity<Book>(bookObj, HttpStatus.OK);
	}

	/*
	 * Returns book by searching keywords
	 */
	@GetMapping(path = "/searchBooksByKeyWord/{keyword}")
	public ResponseEntity<List<Book>> getBooksByKeyword(@PathVariable String keyword) throws NoBookException {

		List<Book> list = iBookRepository.search(keyword);
		if (list.size() == 0)
			throw new NoBookException("Sorry, No books found on this Keyword");
		else
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}

	/*
	 * Returns book by searching title
	 */
	@GetMapping(path = "/getBooksByTitle/{title}")
	public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) throws NoBookException {

		List<Book> list = iBookService.findByTitle(title);
		if (list.size() == 0)
			throw new NoBookException("Sorry, No books found on this title");
		else
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}

	/*
	 * return recently added book
	 */
	@GetMapping(path = "/getRecentlyAddedBooks")
	public ResponseEntity<List<Book>> getBooksByTitle() throws NoBookException {

		List<Book> list = iBookRepository.listRecentlyAdded();
		if (list.size() == 0)
			throw new NoBookException("Sorry, No books found on this title");
		else
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	}

}
