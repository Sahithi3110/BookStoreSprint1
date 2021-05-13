package com.cg.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.IBookService;

@SpringBootTest
public class BookModuleTests extends BookstoreApplicationTests {

	@Autowired
	IBookRepository iBookRepository;

	@Autowired
	IBookService iBookService;

	/*
	 * Test to get books by title
	 */
	@Test
	void getBooksByTitle() {

		Book b = new Book(26, "Maths", "Ramanujan", new Category(11, "Math"), "Mathematics", "#102030", 450.00,
				LocalDate.now(), LocalDate.now());

		iBookRepository.save(b);
		List<Book> book1 = iBookRepository.findByTitle("Maths");
		Optional<Book> book2 = iBookRepository.findById(26);
		assertThat(book2.isPresent()).isEqualTo(!book1.isEmpty());
	}

	/*
	 * Test to get all books
	 */
	@Test
	void getAllBooks() {
		int count = iBookRepository.bookCount();
		List<Book> list = iBookRepository.findAll();
		assertEquals(count, list.size());
	}

	/*
	 * Test to delete a book
	 */
	@Test
	void delete() {
		List<Book> book1 = iBookRepository.findAll();
		iBookService.deleteBook(24);
		List<Book> book2 = iBookRepository.findAll();
		assertThat(book1.size() - 1).isEqualTo(book2.size());
	}

	/*
	 * Test to update book
	 */
	@Test
	void update() {
		Optional<Book> b = iBookRepository.findById(1);
		Book book = iBookService.editBook(b.get(), 1);
		assertThat(iBookRepository.count()).isEqualTo(iBookRepository.bookCount());
	}

}