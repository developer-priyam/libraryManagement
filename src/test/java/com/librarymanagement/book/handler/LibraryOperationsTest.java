package com.librarymanagement.book.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.service.BookService;
import com.librarymanagement.book.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class LibraryOperationsTest {

	@Mock
	private BookService bookService;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private LibraryOperations libOps;
	
	private List<Integer> issuedBooks;
	
	@Before
	public void setup() throws Exception {
		issuedBooks = new ArrayList<>();
		issuedBooks.add(2);
		User user = new User(1, "test-user", issuedBooks);
		Map<String, User> userMap = new HashMap<>();
		userMap.put("test-user", user);
		
		Map<String, Book> bookMap = new HashMap<>();
		Book book = new Book("test-book1", 1, 2, 2);
		Book book2 = new Book("test-book", 2, 11, 11);
		bookMap.put("test-book", book);
		bookMap.put("test-book1", book2);

		Mockito.when(userService.getUserDetails(any(String.class))).thenReturn(user);
		
		Mockito.when(bookService.borrowReturnBook(any(), any(String.class), any(String.class))).thenReturn(bookMap);
		Mockito.when(bookService.getBookList()).thenReturn(bookMap);
	}
	
	@Test
	public void testGetBookList() {
		assertEquals(2, bookService.getBookList().size());
	}
	
	@Test
	public void testGetUserDetails() throws Exception {
		assertEquals(1, userService.getUserDetails("test-user").getIssuedBooks().size());
	}
	
	@Test
	public void testBorrowBook() throws Exception {
		Map<String, Book> bookMap = new HashMap<>();
		bookMap.put("test-book", new Book("test-book", 2, 3, 4));
		Mockito.when(bookService.borrowReturnBook(any(), any(String.class), any(String.class))).thenReturn(bookMap);
		assertEquals(3, bookService.borrowReturnBook(issuedBooks, "test-book", "borrow").get("test-book").getAvailableCopies());
		
	}
	
	@Test
	public void testReturnBook() throws Exception {
		assertEquals(2, bookService.borrowReturnBook(issuedBooks, "test-book", "return").get("test-book").getAvailableCopies());
		
	}
	
}
