package com.librarymanagement.book.controller;

import java.util.Collection;

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

	@Autowired
	private LibraryOperationService libService;
	
	@GetMapping("/books")
	public Collection<Book> getAllBooks() {
		return libService.getBooks();
	}
	
	@PostMapping("/user")
	public User getUserDetailss(@RequestBody UserObj user) {
		System.out.println(user.getUsername() + "====username");
		return libService.getUserDetails(user.getUsername());
	}
	
	@PostMapping("/libManage")
	public ResponseObject manageBookLibrary(@RequestBody LibManageReqObj reeqObj) {
		return libService.bookOperations(reeqObj.getUsername(), reeqObj.getBookname(), reeqObj.getAction());
	}
}
