package com.librarymanagement.book.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@Service
public class UserService {
	
	@Autowired
	private LibraryProperties properties;
	
	private DummyLibraryDataStore datastore;
	
	public UserService() {
		datastore = DummyLibraryDataStore.getInstance();
	}
	
	public User getUserDetails(String username) throws Exception {
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(username);
		if(user == null) throw new Exception("User not found");
		System.out.println("Retrived username===="+userMap.get(username));
		return user;
	}
	
	public User updateUserDetails(String username, String bookname, String action) {
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(username);
		List<Integer> issuedBooks = user.getIssuedBooks();
		if(properties.getBORROW().equalsIgnoreCase(action)) {
			addBookToUserAccount(issuedBooks, datastore.getDummyBookData().get(bookname).getId());
		} else {
			removeFromUserAccount(issuedBooks, datastore.getDummyBookData().get(bookname).getId());
		}
		user.setIssuedBooks(issuedBooks);
		userMap.put(username, user);
		datastore.setDummyUserData(userMap);
		return user;
	}

	private void removeFromUserAccount(List<Integer> issuedBooks, Integer id) {
		Integer element = 0;
		for(Integer bookId : issuedBooks) {
			if(bookId == id) element = bookId;
		}
		issuedBooks.remove(element);
	}
	
	private void addBookToUserAccount(List<Integer> issuedBooks, Integer id) {
		if(!issuedBooks.contains(id)) {
			issuedBooks.add(id);
		}
	}
}
