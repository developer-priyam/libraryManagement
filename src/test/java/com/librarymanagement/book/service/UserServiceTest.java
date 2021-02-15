package com.librarymanagement.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
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

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.exception.UserNotFoundException;
import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private LibraryProperties props;
	
	@Mock
	private DummyLibraryDataStore datastore;
	
	@InjectMocks
	private UserService service;
	
	@Before
	public void setup() {
		Map<String, User> userMap = new HashMap<>();
		List<Integer> issuedBooks = new ArrayList<>();
		issuedBooks.add(1);
		User user = new User(1, "test-user", new ArrayList<>());
		User user2 = new User(1, "test-user2", issuedBooks);
		Book book = new Book("test-book", 1, 11, 11);
		Map<String, Book> bookMap = new HashMap<>();
		userMap.put("test-user", user);
		userMap.put("test-user2", user2);
		bookMap.put("test-book", book);
		Mockito.when(props.getBORROW()).thenReturn("borrow");
		Mockito.when(datastore.getDummyUserData()).thenReturn(userMap);
		Mockito.when(datastore.getDummyBookData()).thenReturn(bookMap);
		
	}
	
	@Test
	public void testGetUser() throws Exception {
		User user = service.getUserDetails("test-user");
		assertEquals("test-user", user.getName());
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testGetUserNotFoundException() throws Exception {
		service.getUserDetails("test-user1");
	}
	
	@Test
	public void testUpdateUserAccountForBorrow() {
		User user = service.updateUserDetails("test-user", "test-book", "borrow");
		assertEquals(1, user.getIssuedBooks().size());
	}
	
	@Test
	public void testUpdateUserAccountForReturn() {
		User updatedUser = service.updateUserDetails("test-user2", "test-book", "return");
		assertEquals(0, updatedUser.getIssuedBooks().size());
	}
}
