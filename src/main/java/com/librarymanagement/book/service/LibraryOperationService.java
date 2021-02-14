package com.librarymanagement.book.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.ResponseObject;
import com.librarymanagement.book.model.User;

@Service
public class LibraryOperationService {

	private static final Logger logger = LoggerFactory.getLogger(LibraryOperationService.class);
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	public Collection<Book> getBooks() {
		logger.info("fetching books using BookService");
		return bookService.getBookMap().values();
	}
	
	public User getUserDetails(String username) {
		logger.info("Checking if user : {} is valid user", username);
		User user = new User(-1, "User not Found", new ArrayList<>());
		try {
			user = userService.getUserDetails(username);
			logger.info("Fetched user : {}", user);
		} catch (Exception e) {
			logger.error("Error in fecthing User Details. Fetch operation failed with error : {}", e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	public ResponseObject bookOperations(String username, String bookname, String action) {
		User user = getUserDetails(username);
		logger.info("Validating user : {}", user);
		String status = "";
		Map<String, Book> bookMap = new HashMap<>();
		Collection<Book> bookList = null;
		if (user.getId() > 0) {
			List<Integer> issuedBooks = user.getIssuedBooks();
			try {
				bookMap = bookService.borrowReturnBook(issuedBooks, bookname, action);
				user = userService.updateUserDetails(username, bookname, action);
				bookList = bookMap.values();
				status = "operation successful!!";
			} catch(Exception ex) {
				bookList = getBooks();
				status = ex.getMessage();
				logger.error("Request book operation failed with error : {}", status);
			}
		}
		ResponseObject responseObj = new ResponseObject(user, bookList, status);
		logger.info("Operation Complete : {}", responseObj);
		return responseObj;
	}
}
