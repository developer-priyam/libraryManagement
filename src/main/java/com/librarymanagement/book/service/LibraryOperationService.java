package com.librarymanagement.book.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.handler.LibraryOperations;
import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.ResponseObject;
import com.librarymanagement.book.model.User;

@Service
public class LibraryOperationService {

	@Autowired
	private LibraryOperations libOps;
	
	public Collection<Book> getBookList() {
		return libOps.getAllBooksInLib();
	}
	
	public User getUserDetails(String username) {
		return libOps.getUserDetails(username);
	}
	
	public ResponseObject bookOperations(String username, String bookname, String action) {
		return libOps.bookOps(bookname, username, action);
	}
}
