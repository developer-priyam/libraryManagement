package com.librarymanagement.book.service;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.librarymanagement.book.handler.ManageBooks;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@Mock
	private ManageBooks managebooks;
	
	@InjectMocks
	private BookService bookService;
	
	@Test
	public void testGetBookList() {
		bookService.getBookList();
		Mockito.verify(managebooks, Mockito.times(1)).getBookMap();
	}
	
	@Test
	public void testBorrowReturnBook() throws Exception {
		bookService.borrowReturnBook(new ArrayList<Integer>(), "test", "test");
		Mockito.verify(managebooks, Mockito.times(1)).borrowReturnBook(new ArrayList<Integer>(), "test", "test");
	}
}
