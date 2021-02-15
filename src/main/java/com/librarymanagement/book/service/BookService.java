package com.librarymanagement.book.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.constant.LibraryProperties;
import com.librarymanagement.book.exception.DuplicateCopyIssueException;
import com.librarymanagement.book.exception.IssueLimitReachedException;
import com.librarymanagement.book.exception.NotEnoughCopyException;
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
	
	public void borrowReturnBook(List<Integer> issuedBooks, String bookname, String action) throws Exception {
		logger.info("Lib operation request started with action : {}, bookname : {}, and Existing size of issued books : ()", action, bookname, issuedBooks.size());
		updateBookMap(issuedBooks, getBookMap(), bookname, action);
	}
	
	private void updateBookMap(List<Integer> issuedBooks, Map<String, Book> bookMap, String bookname, String action) throws Exception {
		Book book = bookMap.get(bookname);
		int availableCount = book.getAvailableCopies();
		
		logger.info("Number of copies of book : {} available in Librarry : {}", bookname, availableCount);
		
		if (action.equalsIgnoreCase(properties.getRETURN())) {
			
			availableCount++;
			logger.info("incrementing book copy count : {}, as action request is :{}", availableCount, action);
			
		} else if (issuedBooks.size() >= 2) {
			throw new IssueLimitReachedException("Cannot issue new books, Book issue limit reached");
		} else if (issuedBooks.contains(book.getId())) {
			throw new DuplicateCopyIssueException("Cannot issue 2 copies of same book");
		} else if(availableCount == 1) {
			throw new NotEnoughCopyException("Not enough copies of the book available");
		} else {
			
			logger.info("More than 1 copy available for book : {} in library. Count is : {}. Performig action : {}", bookname, availableCount, action); 
			availableCount--;
			
			logger.info("Decrementing available book count : {}", availableCount);
		}
		logger.info("Number of copies : {} after performing : {} for book : {}", availableCount, action, bookname);
		book.setAvailableCopies(availableCount);
		logger.info("On Action : {} book : {}", action, book); 
		bookMap.put(bookname, book);
		datastore.setDummyBookData(bookMap);
	}
}
