package com.librarymanagement.book.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookTest {

	private Book book;
	
	@Before
	public void setup() {
		book = new Book("test-book", 1, 10, 11);
	}
	
	@Test
	public void testBookPojo() {
		assertTrue(book.getAvailableCopies() == 10);
		assertTrue(book.getId() == 1);
		assertTrue(book.getTotalCopies() == 11);
		assertTrue(book.getName().equalsIgnoreCase("test-book"));
	}
}
