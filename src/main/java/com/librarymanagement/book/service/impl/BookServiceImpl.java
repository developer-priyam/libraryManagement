package com.librarymanagement.book.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.librarymanagement.book.model.Book;
import com.librarymanagement.book.repository.DummyLibraryDataStore;
import com.librarymanagement.book.service.BookService;

/**
 * 
 * @author Priyam Bhatnagar
 *
 */
@Service
public class BookServiceImpl implements BookService {
	private DummyLibraryDataStore datastore;

	public BookServiceImpl() {
		datastore = DummyLibraryDataStore.getInstance();
	}
	
	@Override
	public Map<String, Book> getBookMap(){
		return datastore.getDummyBookData();
	}
}
