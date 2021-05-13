package com.cg.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.UserClass;
import com.cg.bookstore.service.ILoginService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	ILoginService iLoginService;

	/*
	 * Add user
	 */
	@PostMapping(path = "/addUser")
	public ResponseEntity<UserClass> addUser(@RequestBody UserClass login) {
		Optional<UserClass> optional = iLoginService.addUser(login);
		return new ResponseEntity<UserClass>(optional.get(), HttpStatus.OK);
	}

	/*
	 * Remove user
	 */
	@DeleteMapping(path = "/removeUser")
	public ResponseEntity<List<UserClass>> removeUser(@RequestBody UserClass login) {
		List<UserClass> list = iLoginService.removeUser(login);
		return new ResponseEntity<List<UserClass>>(list, HttpStatus.OK);
	}

	/*
	 * Validate user by username and password
	 */
	@GetMapping("/validateUser/{userId}/{password}")
	public UserClass validateUser(@PathVariable Integer userId, @PathVariable String password) {
		return iLoginService.validateUser(userId, password);
	}

}
