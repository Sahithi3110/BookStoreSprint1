package com.cg.bookstore.serviceImp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.exception.NoBookException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.IBookService;

@Service
public class BookServiceImplementation implements IBookService{

	@Autowired
	IBookRepository iBookRepo;
	
	@Override
	@Transactional
	public List<Book> findByTitle(String title) {
		return iBookRepo.findByTitle(title);
	}
	
	@Override
	@Transactional
	public Optional<Book> createBook(Book b)
	{
		iBookRepo.save(b);             // create new record
		return Optional.of(b);         // if object found then it will return object else  return null 
	}
	
	@Override
	@Transactional
	public List<Book> listAllBooks()
	{
		return iBookRepo.findAll();    // return all records
	}
	
	@Override
	@Transactional
	public List<Book> deleteBook(Integer id) throws NoBookException
	{
		Optional<Book> book=iBookRepo.findById(id);        // return a particular record
		if(book.isEmpty())
			throw new NoBookException("Entered ID is not found");
		else {
		iBookRepo.deleteById(id);							// delete a particular record
		return iBookRepo.findAll();	
		}
	}
	
	@Override
	@Transactional
	public Book editBook(Book b,Integer id) throws NoBookException{
		Optional<Book> book=iBookRepo.findById(id);
		if(book.isEmpty())
			throw new NoBookException("Entered ID is not found");
		else {
		iBookRepo.save(b);
		return b;
		}
	}
	
	@Override
	@Transactional
	public List<Book> listBooksByCategory(String cat){
		return iBookRepo.findAll();
	}
}
