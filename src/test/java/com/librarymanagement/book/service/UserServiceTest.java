package com.librarymanagement.book.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.librarymanagement.book.handler.ManageUsers;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private ManageUsers manageUsers;
	
	@InjectMocks
	private UserService service;
	
	@Test
	public void testGetUserDetails() throws Exception {
		service.getUserDetails("test");
		Mockito.verify(manageUsers, Mockito.times(1)).getUser("test");
	}
	
	@Test
	public void testUpdateUserDetails() throws Exception {
		service.updateUserDetails("test", "test", "test");
		Mockito.verify(manageUsers, Mockito.times(1)).updateUserAccount("test", "test", "test");
	}
}
