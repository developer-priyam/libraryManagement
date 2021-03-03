package com.librarymanagement.book.service;

import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.User;

@Component
public interface UserManageService {

	public User updateUserDetails(String username, String bookname, String action);
}
