package com.librarymanagement.book.service;

import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.User;

@Component
public interface UserService {

	public User getUserDetails(String username) throws Exception;
}
