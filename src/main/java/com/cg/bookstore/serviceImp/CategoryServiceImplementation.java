package com.cg.bookstore.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.ICategoryRepository;
import com.cg.bookstore.service.ICategoryService;

@Service
public class CategoryServiceImplementation implements ICategoryService {

	 @Autowired
	 private ICategoryRepository iCategoryRepository;

	 
	 //Method For adding a category
	 @Override
	 public Category addCategory(Category category) {
		 Category categorySaved = iCategoryRepository.save(category);          // create new record
			return categorySaved;
	 }

	
	 //Method For editing a category
		@Override
		public Category editCategory(Category category) {
			iCategoryRepository.save(category);
			return category;
		}
		
		
		
		//Method for Getting All the categories

		@Override
		public List<Category> getAllCategories() {
			List<Category> catList=null;
			catList=iCategoryRepository.findAll();                      // return all records
			return catList ;
		}

		
		
		//Method for deleting all the categories
		@Override
		public List<Category> removeCategory(Category cat) {
			iCategoryRepository.delete(cat);							// delete a particular record
		return iCategoryRepository.findAll();
		}
}
