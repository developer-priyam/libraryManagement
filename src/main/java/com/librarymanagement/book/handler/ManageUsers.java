package com.librarymanagement.book.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@Component
public class ManageUsers {
	
	@Autowired
	private LibraryProperties properties;
	
	@Autowired
	private DummyLibraryDataStore datastore;// = DummyLibraryDataStore.getInstance();
	
	
	public User getUser(String userName) throws Exception{
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(userName);
		if(user == null) throw new Exception("User not found");
		System.out.println("Retrived username===="+userMap.get(userName));
		return user;
	}
	
	public User updateUserAccount(String userName, String bookName, String action) {
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(userName);
		List<Integer> issuedBooks = user.getIssuedBooks();
		if(properties.getBORROW().equalsIgnoreCase(action)) {
			addBookToUserAccount(issuedBooks, datastore.getDummyBookData().get(bookName).getId());
		} else {
			removeFromUserAccount(issuedBooks, datastore.getDummyBookData().get(bookName).getId());
		}
		user.setIssuedBooks(issuedBooks);
		userMap.put(userName, user);
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
