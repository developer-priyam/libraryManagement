package com.librarymanagement.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.handler.ManageUsers;
import com.librarymanagement.book.model.User;

@Service
public class UserService {
	
	@Autowired
	private ManageUsers manageUsers;
	
	public User getUserDetails(String username) throws Exception {
		return manageUsers.getUser(username);
	}
	
	public User updateUserDetails(String username, String bookname, String action) {
		return manageUsers.updateUserAccount(username, bookname, action);
	}
}
