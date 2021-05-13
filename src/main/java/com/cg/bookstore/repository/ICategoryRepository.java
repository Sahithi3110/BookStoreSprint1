package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.bookstore.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{

	
	    //Viewing All the data present in the category
		@Query(value="select * from category",nativeQuery=true)
		public List<Category> getAllCategories();

		//Count the Category ID
		@Query(value="SELECT count(category_id) from category",nativeQuery=true)
		public int categoryCount();
	
	
	
	/*public Category addCategory(String categoryName);
	public Category editCategory(Category cat);
	public Category viewAllCategories();
	public Category removeCategory(Category cat); */
}
