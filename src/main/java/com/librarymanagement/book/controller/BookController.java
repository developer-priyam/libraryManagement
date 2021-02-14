package com.librarymanagement.book.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.LibManageReqObj;
import com.librarymanagement.book.model.ResponseObject;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.model.UserObj;
import com.librarymanagement.book.service.LibraryOperationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private LibraryOperationService libService;
	
	@GetMapping("/books")
	public Collection<Book> getAllBooks() {
		logger.info("Request received for book list");
		return libService.getBooks();
	}
	
	@PostMapping("/user")
	public User getUserDetailss(@RequestBody UserObj user) {
		logger.info("Request received for user : {}", user.getUsername());
		return libService.getUserDetails(user.getUsername());
	}
	
	@PostMapping("/libManage")
	public ResponseObject manageBookLibrary(@RequestBody LibManageReqObj reeqObj) {
		logger.info("Request received lib operation. obj is : {}", reeqObj);
		return libService.bookOperations(reeqObj.getUsername(), reeqObj.getBookname(), reeqObj.getAction());
	}
}
