package com.librarymanagement.book.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@Component
public class LoadDummyData implements CommandLineRunner {
	
	@Autowired
	private DummyLibraryDataStore dataStore;
	
	@Override
	public void run(String... args) throws Exception {
		loadBooksData();
		loadUserData();
	}

	private void loadBooksData() {
		Book book1 = new Book("Maths", 1, 5, 5);
		Book book2 = new Book("Science", 2, 4, 4);
		Book book3 = new Book("Literature", 3, 10, 10);
		Book book4 = new Book("Computers", 4, 3, 3);
		Book book5 = new Book("History", 5, 5, 5);
		Book book6 = new Book("Economics", 6, 2, 2);
		Book book7 = new Book("Comics", 7, 15, 15);
		Book book8 = new Book("Politics", 8, 11, 11);
		Book book9 = new Book("Accounts", 9, 1, 1);
		Book book10 = new Book("CrimeNovels", 10, 6, 6);
		
		Map<String, Book> bookMap = new HashMap<>();
		bookMap.put(book10.getName(), book10);
		bookMap.put(book9.getName(), book9);
		bookMap.put(book8.getName(), book8);
		bookMap.put(book7.getName(), book7);
		bookMap.put(book6.getName(), book6);
		bookMap.put(book5.getName(), book5);
		bookMap.put(book4.getName(), book4);
		bookMap.put(book3.getName(), book3);
		bookMap.put(book2.getName(), book2);
		bookMap.put(book1.getName(), book1);
		System.out.println(dataStore);
		System.out.println(bookMap);
		dataStore.setDummyBookData(bookMap);
	}

	private void loadUserData() {
		User user1 = new User(1, "Priyam", new ArrayList<>());
		User user2 = new User(2, "Bhatnagar", new ArrayList<>());
		
		Map<String, User> userMap = new HashMap<>();
		userMap.put(user2.getName(), user2);
		userMap.put(user1.getName(), user1);
		
		dataStore.setDummyUserData(userMap);
	}
}