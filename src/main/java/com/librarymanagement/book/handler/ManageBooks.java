package com.librarymanagement.book.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@Component
public class ManageBooks {
	
	@Autowired
	private LibraryProperties properties;
	
	@Autowired
	private DummyLibraryDataStore datastore;

	public Map<String, Book> getBookMap(){
		Map<String, Book> bookMap = datastore.getDummyBookData();
		return bookMap;
	}
	
	public Map<String, Book> borrowReturnBook(List<Integer> issuedBooks, String bookName, String action) throws Exception {
		return updateBookMap(issuedBooks, getBookMap(), bookName, action);
	}
	
	private Map<String, Book> updateBookMap(List<Integer> issuedBooks, Map<String, Book> bookMap, String bookName, String action) throws Exception {
		Book book = bookMap.get(bookName);
		int availableCount = book.getAvailableCopies();
		if(availableCount > 1) {
			if(properties.getBORROW().equalsIgnoreCase(action) && issuedBooks.size() < 2) {
				availableCount--;
			} else if(properties.getBORROW().equalsIgnoreCase(action) && issuedBooks.size() >= 2) {
				throw new Exception("Cannot issue new books, Book issue limit reached");
			} else {
				availableCount++;
			}
		} else {
			throw new Exception("Not enough copies of the book available");
		}
		book.setAvailableCopies(availableCount);
		bookMap.put(bookName, book);
		return bookMap;
	}
}
