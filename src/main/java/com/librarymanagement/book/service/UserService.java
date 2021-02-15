package com.librarymanagement.book.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.exception.UserNotFoundException;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private LibraryProperties properties;
	
	private DummyLibraryDataStore datastore;
	
	public UserService() {
		logger.info("Getting Singleton Instance of Datastore");
		datastore = DummyLibraryDataStore.getInstance();
	}
	
	public User getUserDetails(String username) throws Exception {
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(username);
		logger.info("User Object : {} for username : {}", user, username);
		if(user == null) throw new UserNotFoundException("User not found");
		return user;
	}
	
	public User updateUserDetails(String username, String bookname, String action) {
		Map<String, User> userMap = datastore.getDummyUserData();
		logger.info("UserData", userMap);
		User user = userMap.get(username);
		List<Integer> issuedBooks = user.getIssuedBooks();
		logger.info("User id : {} for username : {}. Currently issued books : {}", user.getId(), username, issuedBooks);
		logger.info("Performing action : {}", action);
		int bookId =  datastore.getDummyBookData().get(bookname).getId();
		if(properties.getBORROW().equalsIgnoreCase(action)) {
			if(!issuedBooks.contains(bookId)) {
				issuedBooks.add(bookId);
			} 
		} else {
			Integer element = 0;
			for(Integer id : issuedBooks) {
				if(bookId == id) element = id;
			}
			issuedBooks.remove(element);
		}
		logger.info("Issued books after operation  : {}", issuedBooks);
		user.setIssuedBooks(issuedBooks);
		userMap.put(username, user);
		datastore.setDummyUserData(userMap);
		return user;
	}
}
