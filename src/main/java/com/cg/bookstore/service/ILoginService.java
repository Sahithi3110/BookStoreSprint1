package com.cg.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.cg.bookstore.entities.UserClass;
import com.cg.bookstore.exception.UserNotFoundException;

public interface ILoginService {

	 public Optional<UserClass> addUser(UserClass user);
	 public List<UserClass> removeUser(UserClass user);
	 //public Optional<Login> validateUser(Login user);
	 public UserClass validateUser(Integer userId, String password) throws UserNotFoundException;
	
}
