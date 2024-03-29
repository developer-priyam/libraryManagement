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
import com.librarymanagement.book.exception.DuplicateCopyIssueException;
import com.librarymanagement.book.exception.IssueLimitReachedException;
import com.librarymanagement.book.exception.NotEnoughCopyException;
import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@Mock
	private LibraryProperties props;

	@Mock
	private DummyLibraryDataStore datastore;

	@InjectMocks
	private BookManageService bookService;

	private List<Integer> issuedBooks;
	private List<Integer> issuedBooks1;

	@Before
	public void setup() {
		Map<String, User> userMap = new HashMap<>();
		issuedBooks = new ArrayList<>();
		issuedBooks.add(3);
		issuedBooks.add(2);
		issuedBooks1 = new ArrayList<>();
		issuedBooks1.add(2);
		User user = new User(1, "test-user", new ArrayList<>());
		User user2 = new User(2, "test-user2", issuedBooks);
		Book book2 = new Book("test-book2", 2, 2, 2);
		Book book3 = new Book("test-book3",3 , 1, 1);
		Book book = new Book("test-book", 1, 11, 11);
		Map<String, Book> bookMap = new HashMap<>();
		userMap.put("test-user", user);
		userMap.put("test-user2", user2);
		bookMap.put("test-book", book);
		bookMap.put("test-book2", book2);
		bookMap.put("test-book3", book3);
		Mockito.when(props.getRETURN()).thenReturn("return");
		Mockito.when(datastore.getDummyBookData()).thenReturn(bookMap);
	}

	@Test
	public void testGetBookMap() throws Exception {
		Map<String, Book> bookMap = bookService.getBookMap();
		assertEquals(3, bookMap.size());
	}

	@Test(expected = IssueLimitReachedException.class)
	public void testBorrowReturnBookIssueLimitReached() throws Exception {
		bookService.borrowReturnBook(issuedBooks, "test-book", "borrow");
	}

	@Test(expected = NotEnoughCopyException.class)
	public void testBorrowReturnBookNotEnoughCopies() throws Exception {
		bookService.borrowReturnBook(new ArrayList<>(), "test-book3", "borrow");
	}
	
	@Test(expected = DuplicateCopyIssueException.class)
	public void testBorrowReturnBookSameCopies() throws Exception {
		bookService.borrowReturnBook(issuedBooks1, "test-book2", "borrow");
	}

	@Test
	public void testBorrow() throws Exception {
		bookService.borrowReturnBook(new ArrayList<>(), "test-book2", "borrow");
		Map<String, Book> bookMap = bookService.getBookMap();
		assertEquals(1, bookMap.get("test-book2").getAvailableCopies());
	}

	@Test
	public void testReturnBook() throws Exception {
		bookService.borrowReturnBook(issuedBooks1, "test-book", "borrow");
		bookService.borrowReturnBook(issuedBooks1, "test-book", "return");
		Map<String, Book> bookMap = bookService.getBookMap();
		assertEquals(11, bookMap.get("test-book").getAvailableCopies());
	}
}
