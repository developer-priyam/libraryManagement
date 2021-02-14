package com.librarymanagement.book.controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.LibManageReqObj;
import com.librarymanagement.book.model.ResponseObject;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.model.UserObj;
import com.librarymanagement.book.service.LibraryOperationService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	@Mock
	private LibraryOperationService service;
	
	@InjectMocks
	private BookController controller;
	
	private ResponseObject responseObj;
	private ResponseObject responseObj1;
	
	@Before
	public void setup() {
		Collection<Book> bookList = new ArrayList<>();
		Collection<Book> bookList1 = new ArrayList<>();
		
		List<Integer> issuedBooks = new ArrayList<>();
		List<Integer> issuedBooks1 = new ArrayList<>();
		
		UserObj requestUser = new UserObj();
		requestUser.setUsername("test");
		
		Book book = new Book("test-book", 1, 5, 5);
		Book book2 = new Book("test-book", 1, 4, 5);
		Book book1 = new Book("test-book1", 2, 2, 2);
		
		User user = new User(1, "test", issuedBooks);
		User user1 = new User(1, "test", issuedBooks1);
		
		bookList.add(book);
		bookList.add(book1);
		
		bookList1.add(book);
		bookList1.add(book2);
		
		issuedBooks.add(1);
		
		responseObj = new ResponseObject(user, bookList, "borrow done");
		responseObj1 = new ResponseObject(user1, bookList1, "return done");
		
		Mockito.when(service.getBooks()).thenReturn(bookList);
		Mockito.when(service.getUserDetails(requestUser.getUsername())).thenReturn(user);
	} 
	
	@Test
	public void testGetBooksEndpoint() {
		Collection<Book> bookList = controller.getAllBooks();
		assertEquals(2, bookList.size());
	}
	
	@Test
	public void testGetUserEndpoint() {
		UserObj requestUser = new UserObj();
		requestUser.setUsername("test");
		User user = controller.getUserDetailss(requestUser);
		assertEquals("test", user.getName());
	}
	
	@Test
	public void testLibManageBorrowEndpoint() {
		LibManageReqObj reeqObj = new LibManageReqObj();
		reeqObj.setAction("borrow");
		reeqObj.setUsername("test");
		reeqObj.setBookname("test-book");
		Mockito.when(service.bookOperations(reeqObj.getUsername(), reeqObj.getBookname(), reeqObj.getAction())).thenReturn(responseObj);
		ResponseObject resObj = controller.manageBookLibrary(reeqObj);
		assertEquals(2, resObj.getBook().size());
	}
	
	@Test
	public void testLibManageReturnEndpoint() {
		LibManageReqObj reeqObj1 = new LibManageReqObj();
		reeqObj1.setAction("return");
		reeqObj1.setUsername("test");
		reeqObj1.setBookname("test-book");
		Mockito.when(service.bookOperations(reeqObj1.getUsername(), reeqObj1.getBookname(), reeqObj1.getAction())).thenReturn(responseObj1);
		ResponseObject resObj = controller.manageBookLibrary(reeqObj1);
		assertEquals(2, resObj.getBook().size());
	}
	
	@Test
	public void testResponseObjectVals() {
		assertTrue(responseObj.getStatus().equalsIgnoreCase("borrow done"));
		assertTrue(responseObj.getUser().getId() == 1);
	}
	
	
}
