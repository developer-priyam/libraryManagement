package com.librarymanagement.book.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.service.BookManageService;
import com.librarymanagement.book.service.BookService;
import com.librarymanagement.book.service.UserManageService;
import com.librarymanagement.book.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	private BookManageService bookManagerService;
	private UserManageService userManagerService;
	private UserService userService;
	private BookService bookService;
	
	public BookController(BookManageService bookManagerService, BookService bookService, UserService userService, UserManageService userManagerService) {
		this.bookService = bookService;
		this.bookManagerService = bookManagerService;
		this.userService = userService;
		this.userManagerService = userManagerService;
	}
	
	@GetMapping("/books")
	public Collection<Book> getAllBooks() {
		return bookService.getBookMap().values();
	}
	
	@PostMapping("/user/{username}")
	public User getUserDetailss(@PathVariable String username) throws Exception {
		logger.info("Request received for user : {}", username);
		return userService.getUserDetails(username);
	}
	
	@PostMapping("/libManage/{action}/{username}/{bookname}")
	public User manageBookLibrary(@PathVariable String action, @PathVariable String username, @PathVariable String bookname) throws Exception {
		logger.info("Request received action : {}, username : {}, bookname : {} ", action, username, bookname);
		User user = getUserDetailss(username);
		if (user.getId() > 0) {
			List<Integer> issuedBooks = user.getIssuedBooks();
			bookManagerService.borrowReturnBook(issuedBooks, bookname, action);
			user = userManagerService.updateUserDetails(username, bookname, action);
		}
		return user;
	}
}
