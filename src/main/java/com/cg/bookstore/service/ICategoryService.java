package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.entities.Category;

public interface ICategoryService {

	public Category addCategory(Category category);
	public Category editCategory(Category category);
	public List<Category> getAllCategories();
	public List<Category> removeCategory(Category cat);
}
