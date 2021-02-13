package com.librarymanagement.book.model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	private User user;

	@Before
	public void setup() {
		user = new User(1, "test-user", new ArrayList<>());
	}

	@Test
	public void testUserIInitialState() {
		assertTrue(user.getId() == 1);
		assertTrue(user.getIssuedBooks().isEmpty());
		assertTrue(user.getName().equalsIgnoreCase("test-user"));
	}
}
