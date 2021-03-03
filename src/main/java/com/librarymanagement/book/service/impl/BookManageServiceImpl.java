package com.librarymanagement.book.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.repository.DummyLibraryDataStore;
import com.librarymanagement.book.service.BookManageService;
import com.librarymanagement.book.util.BookIssueValidator;

/**
 * 
 * @author Priyam Bhatnagar
 *
 */
@Component
public class BookManageServiceImpl implements BookManageService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookManageServiceImpl.class);
	
	private DummyLibraryDataStore datastore;

	public BookManageServiceImpl() {
		datastore = DummyLibraryDataStore.getInstance();
	}
	
	@Override
	public void borrowReturnBook(List<Integer> issuedBooks, String bookname, String action) throws Exception {
		Map<String, Book> bookMap = datastore.getDummyBookData();
		Book book = bookMap.get(bookname);
		int availableCount = book.getAvailableCopies();
		logger.info("Number of copies of book : {} available in Librarry : {}", bookname, availableCount);
		availableCount = BookIssueValidator.validateIssueProcess(book, action, issuedBooks, availableCount);
		logger.info("Number of copies : {} after performing : {} for book : {}", availableCount, action, bookname);
		updateBookMap(book, bookMap, availableCount);
	}

	private void updateBookMap(Book book, Map<String, Book> bookMap, int availableCount) throws Exception {
		book.setAvailableCopies(availableCount);
		bookMap.put(book.getName(), book);
		datastore.setDummyBookData(bookMap);
	}

}
