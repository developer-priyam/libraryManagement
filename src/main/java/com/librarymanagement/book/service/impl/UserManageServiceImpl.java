package com.librarymanagement.book.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;
import com.librarymanagement.book.service.UserManageService;
import com.librarymanagement.book.util.UserManageUtil;

@Component
public class UserManageServiceImpl implements UserManageService {

	private static final Logger logger = LoggerFactory.getLogger(UserManageServiceImpl.class);

	private DummyLibraryDataStore datastore;

	public UserManageServiceImpl() {
		datastore = DummyLibraryDataStore.getInstance();
	}

	@Override
	public User updateUserDetails(String username, String bookname, String action) {
		Map<String, User> userMap = datastore.getDummyUserData();
		User user = userMap.get(username);
		List<Integer> issuedBooks = user.getIssuedBooks();
		int bookId =  datastore.getDummyBookData().get(bookname).getId();
		logger.info("Action : {}, User id : {} for username : {}. Currently issued books : {}", action, user.getId(), username, issuedBooks);
		issuedBooks = UserManageUtil.updateIssuedBookList(bookId, issuedBooks, bookname, action);
		logger.info("Issued books after operation  : {}", issuedBooks);
		return updateUserDataStore(userMap, issuedBooks, user);
	}

	private User updateUserDataStore(Map<String, User> userMap, List<Integer> issuedBooks, User user) {
		user.setIssuedBooks(issuedBooks);
		userMap.put(user.getName(), user);
		datastore.setDummyUserData(userMap);
		return user;
	}

}
