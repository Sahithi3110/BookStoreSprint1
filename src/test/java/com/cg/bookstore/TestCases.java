package com.cg.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.Category;
import com.cg.bookstore.repository.ICategoryRepository;
import com.cg.bookstore.service.ICategoryService;

@SpringBootTest
public class TestCases extends BookstoreApplicationTests {

	@Autowired
	ICategoryRepository iCategoryRepository;

	@Autowired
	ICategoryService iCategoryService;

	/*
	 * Test to gat all categories
	 */
	@Test
	public void testGetAllCategories() {
		List<Category> categories = iCategoryRepository.getAllCategories();
		List<Category> categories1 = iCategoryRepository.findAll();

		assertThat(categories.size()).isEqualTo(categories1.size());

	}

	/*
	 * Test to add category
	 */
	@Test
	public void testAddCategory() {
		Category category = new Category(25, "Java");
		List<Category> categories = iCategoryRepository.findAll();
		iCategoryService.addCategory(category);
		List<Category> categories1 = iCategoryRepository.findAll();

		assertThat(categories1.size()).isEqualTo(categories.size() + 1);
	}

	/*
	 * Test to edit Cateogry
	 */
	@Test
	public void tsetEditCategory() {
		Category category = iCategoryRepository.findById(12).get();
		category.setCategoryName("C++");
		iCategoryService.editCategory(category);

		Category afterUpdation = iCategoryRepository.findById(12).get();
		assertThat(afterUpdation.getCategoryName()).isEqualTo("C++");

	}

	/*
	 * Test to delete Category
	 */
	@Test
	public void testRemoveCategory() {
		List<Category> beforeRemove = iCategoryRepository.findAll();
		Category category = iCategoryRepository.findById(20).get();
		iCategoryService.removeCategory(category);

		List<Category> afterRemove = iCategoryRepository.findAll();

		assertThat(afterRemove.size()).isEqualTo(beforeRemove.size() - 1);
	}

}
