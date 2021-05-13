package com.cg.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.exception.CategoryNotFoundException;
import com.cg.bookstore.service.ICategoryService;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
	ICategoryService iCategoryService;

	// Adding the Category
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@Valid @RequestBody Category category) {
		Category categorySaved = iCategoryService.addCategory(category);
		return new ResponseEntity<Category>(categorySaved, HttpStatus.CREATED);
	}

	// Updating the Category
	@PutMapping(path = "/updateCategory")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws CategoryNotFoundException {

		Category obj = iCategoryService.editCategory(category);
		if (obj == null) {
			throw new ServiceException("The category is not present");
		}
		return new ResponseEntity<Category>(obj, HttpStatus.OK);
	}

	// Deleting the Category
	@DeleteMapping(path = "/deleteCategory")
	public ResponseEntity<List<Category>> deleteCategory(@RequestBody Category category)
			throws CategoryNotFoundException {
		List<Category> list = iCategoryService.removeCategory(category);
		if (list.size() == 0) {
			throw new ServiceException("Deletion is not done as the List is empty");
		}
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}

	// View all the categories
	@GetMapping(path = "/viewAllCategory")
	public ResponseEntity<List<Category>> viewCategory() throws CategoryNotFoundException {

		List<Category> list = iCategoryService.getAllCategories();
		if (list.size() == 0) {
			throw new ServiceException("No category data is present in the List !");
		}
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}

}
