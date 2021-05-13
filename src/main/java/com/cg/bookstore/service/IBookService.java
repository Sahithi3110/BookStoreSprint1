package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.cg.bookstore.entities.Book;

public interface IBookService {

	public List<Book> listAllBooks();
	public List<Book> deleteBook(Integer id);
	public Book editBook(Book b,Integer id);
	public List<Book> listBooksByCategory(String cat);
	public Optional<Book> createBook(Book b);
	public List<Book> findByTitle(String title);
	
	
	
	
	
/*	public Book createBook(Book b);
	public List<Book> listAllBooks();
	public Book deleteBook(Book b);
	public Book editBook(Book b);
	public Book viewBook(Book b);
	public List<Book> listBooksByCategory(String cat);
	
	*/
}
