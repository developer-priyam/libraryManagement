package com.librarymanagement.book.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.librarymanagement.book.handler.LibraryOperations;

@RunWith(MockitoJUnitRunner.class)
public class LibraryOperationServiceTest {

	@Mock
	private LibraryOperations libOps;
	
	@InjectMocks
	private LibraryOperationService service;
	
	@Test
	public void testGetBookList() {
		service.getUserDetails("test");
		Mockito.verify(libOps, Mockito.times(1)).getUserDetails("test");
	}
	
	@Test
	public void testGetUserDetails() {
		service.getBookList();
		Mockito.verify(libOps, Mockito.times(1)).getAllBooksInLib();
	}
	
	@Test
	public void testBookOperations() {
		service.bookOperations("test", "test", "test");
		Mockito.verify(libOps, Mockito.times(1)).bookOps("test", "test", "test");
	}
}
