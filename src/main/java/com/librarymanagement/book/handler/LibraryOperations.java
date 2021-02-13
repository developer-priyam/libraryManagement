package com.librarymanagement.book.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.ResponseObject;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.service.BookService;
import com.librarymanagement.book.service.UserService;

@Component
public class LibraryOperations {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	public Collection<Book> getAllBooksInLib() {
		return bookService.getBookList().values();
	}
	
	public User getUserDetails(String username) {
		User user = new User(-1, "", new ArrayList<>());
		try {
			user = userService.getUserDetails(username);
		} catch (Exception e) {
			user.setName(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(user.getId());
		return user;
	}
	
	public ResponseObject bookOps(String bookname, String username, String action) {
		User user = getUserDetails(username);
		String status = "";
		Map<String, Book> bookMap = new HashMap<>();
		if (user.getId() > 0) {
			List<Integer> issuedBooks = user.getIssuedBooks();
			try {
				bookMap = bookService.borrowReturnBook(issuedBooks, bookname, action);
				user = userService.updateUserDetails(username, bookname, action);
				status = "operation successful!!";
			} catch(Exception ex) {
				status = ex.getMessage();
			}
		}
		return new ResponseObject(user, bookMap.values(), status);
	}
	
}