package com.cg.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bookstore.entities.UserClass;
import com.cg.bookstore.repository.ILoginRepository;
import com.cg.bookstore.service.ILoginService;

@SpringBootTest
public class UserModuleTests extends BookstoreApplicationTests {

	@Autowired
	ILoginRepository iLoginRepository;

	@Autowired
	ILoginService iLoginService;

	/*
	 * Test to get all users
	 */
	@Test
	void testGetAllUsers() {
		int count = iLoginRepository.userCount();
		List<UserClass> list = iLoginRepository.findAll();
		assertEquals(count, list.size());
	}

	/*
	 * Test to add user
	 */
	@Test
	void testAddUser() {
		int beforeCreating = iLoginRepository.userCount();
		UserClass userClass = new UserClass(2, "aditya@gmail.com", "aditya@123", "customer");
		iLoginRepository.save(userClass);
		int afterCreating = iLoginRepository.userCount();
		assertThat(afterCreating).isEqualTo(beforeCreating + 1);
	}

	/*
	 * Test to validate user
	 */
	@Test
	void testValidateUsers() {
		Optional<UserClass> ol = iLoginRepository.findById(100002);
		UserClass l = iLoginService.validateUser(ol.get().getUserId(), ol.get().getPassword());
		assertThat(ol.isPresent()).isEqualTo(!(l == null));
	}

	/*
	 * Test to delete user
	 */
	@Test
	void testRemoveUsers() {
		int count = iLoginRepository.userCount();
		Optional<UserClass> ol = iLoginRepository.findById(100006);
		List<UserClass> list = iLoginService.removeUser(ol.get());
		assertThat(count - 1).isEqualTo(list.size());
	}

}
