package com.librarymanagement.book.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.model.User;

@Component
public class DummyLibraryDataStore {
	private static DummyLibraryDataStore dummyDataStore; 
	private static Map<String, Book> DummyBookData = new HashMap<>();
	private static Map<String, User> DummyUserData = new HashMap<>();
	
	private DummyLibraryDataStore() {}
	
	public Map<String, Book> getDummyBookData() {
		return DummyBookData;
	}
	
	public void setDummyBookData(Map<String, Book> dummyBookData) {
		DummyBookData = dummyBookData;
	}
	
	public Map<String, User> getDummyUserData() {
		return DummyUserData;
	}

	public void setDummyUserData(Map<String, User> dummyUserData) {
		DummyUserData = dummyUserData;
	}
	
	public static DummyLibraryDataStore getInstance() {
		if(dummyDataStore == null) {
			synchronized(DummyLibraryDataStore.class) {
				dummyDataStore = new DummyLibraryDataStore();
			}
		}
		return dummyDataStore;
	}
	
}
