package com.librarymanagement.book.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.service.BookManageService;
import com.librarymanagement.book.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	private BookManageService bookService;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private BookController controller;
	
	private List<Integer> issuedBooks;
	
	@Before
	public void setup() throws Exception {
		Collection<Book> bookList = new ArrayList<>();
		Collection<Book> bookList1 = new ArrayList<>();
		Map<String, Book> bookMap = new HashMap<>();
		issuedBooks = new ArrayList<>();
		
		Book book = new Book("test-book", 1, 5, 5);
		Book book2 = new Book("test-book2", 2, 4, 5);
		Book book3 = new Book("test-book3", 3, 2, 2);
		
		User user = new User(1, "test", issuedBooks);
		
		bookList.add(book);
		bookList.add(book2);
		bookList.add(book3);
		
		bookList1.add(book);
		bookList1.add(book2);
		bookList1.add(book3);
		
		bookMap.put("test-book", book);
		bookMap.put("test-book2", book2);
		bookMap.put("test-book3", book3);
		issuedBooks.add(1);
		
		Mockito.when(bookService.getBookMap()).thenReturn(bookMap);
		Mockito.when(userService.getUserDetails("test")).thenReturn(user);
	} 
	
	@Test
	public void testGetBooksEndpoint() {
		Collection<Book> bookList = controller.getAllBooks();
		assertEquals(3, bookList.size());
	}
	
	@Test
	public void testGetUserEndpoint() throws Exception {
		User user = controller.getUserDetailss("test");
		assertEquals("test", user.getName());
	}
	
	@Test
	public void testLibManageBorrowEndpoint() throws Exception {
		User user = new User(2, "test2", issuedBooks);
		Mockito.when(userService.updateUserDetails("test", "test-book", "borrow")).thenReturn(user);
		assertEquals(1, controller.manageBookLibrary("borrow", "test", "test-book").getIssuedBooks().size());
	}
	
	@Test
	public void testLibManageReturnEndpoint() throws Exception {
		User user = new User(2, "test2", issuedBooks);
		Mockito.when(userService.updateUserDetails("test", "test-book", "return")).thenReturn(user);
		assertEquals(1, controller.manageBookLibrary("return", "test", "test-book").getIssuedBooks().size());
	}
}
