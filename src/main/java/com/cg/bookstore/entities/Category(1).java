package com.cg.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Category {

	/*
	 * Field Declaration
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	
	@Size(min = 3, max = 25, message = "Category Name must Contain Atleast 3 Characters")
	@NotEmpty(message = "Category must contains some name")
	private String categoryName;
	
	/*
	 * Default Constructor
	 */
	
	public Category() {
		
	}

	/*
	 * Parameterized Constructor
	 */
	
	public Category(int categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	/*
	 * Getter, Setter For All Fields
	 */
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
