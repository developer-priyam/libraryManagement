package com.librarymanagement.book.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.exception.UserNotFoundException;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;
import com.librarymanagement.book.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private DummyLibraryDataStore datastore;
	
	public UserServiceImpl() {
		datastore = DummyLibraryDataStore.getInstance();
	}
	
	@Override
	public User getUserDetails(String username) throws Exception {
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(username);
		logger.info("User Object : {} for username : {}", user, username);
		if(user == null) throw new UserNotFoundException("User not found");
		return user;
	}
}
