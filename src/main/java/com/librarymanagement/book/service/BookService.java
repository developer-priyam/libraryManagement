package com.librarymanagement.book.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@Service
public class BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private LibraryProperties properties;
	
	private DummyLibraryDataStore datastore;
	
	public BookService() {
		logger.info("Getting Singleton Instance of Datastore");
		datastore = DummyLibraryDataStore.getInstance();
	}
	
	public Map<String, Book> getBookMap(){
		Map<String, Book> bookMap = datastore.getDummyBookData();
		logger.info("Fetching in-memory book data : {}", bookMap);
		return bookMap;
	}
	
	public Map<String, Book> borrowReturnBook(List<Integer> issuedBooks, String bookname, String action) throws Exception {
		logger.info("Lib operation request started with action : {}, bookname : {}, and Existing size of issued books : ()", action, bookname, issuedBooks.size());
		return updateBookMap(issuedBooks, getBookMap(), bookname, action);
	}
	
	private Map<String, Book> updateBookMap(List<Integer> issuedBooks, Map<String, Book> bookMap, String bookname, String action) throws Exception {
		Book book = bookMap.get(bookname);
		int availableCount = book.getAvailableCopies();
		logger.info("Number of copies of book : {} available in Librarry : {}", bookname, availableCount);
		if (action.equalsIgnoreCase(properties.getRETURN())) {
			availableCount++;
			logger.info("incrementing book copy count : {}, as action request is :{}", availableCount, action);
		} else {
			if (!issuedBooks.contains(book.getId())) {
				if(availableCount > 1) {
					logger.info("More than copy available for book : {} in library. Count is : {}. Performig action : {}", bookname, availableCount, action); 
					if(properties.getBORROW().equalsIgnoreCase(action) && issuedBooks.size() < 2) {
						availableCount--;
						logger.info("Decrementing available book count : {}", availableCount);
					} else if(properties.getBORROW().equalsIgnoreCase(action) && issuedBooks.size() >= 2) {
						throw new Exception("Cannot issue new books, Book issue limit reached");
					}
					logger.info("Number of copies : {} after performing : {} for book : {}", availableCount, action, bookname); 
				} else {
					throw new Exception("Not enough copies of the book available");
				}
			} else {
				throw new Exception("Cannot issue 2 copies of same book");
			}
		}
		book.setAvailableCopies(availableCount);
		logger.info("book : {}", book); 
		bookMap.put(bookname, book);
		return bookMap;
	}
	
}
