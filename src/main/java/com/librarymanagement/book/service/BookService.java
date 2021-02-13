package com.librarymanagement.book.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagement.book.handler.ManageBooks;
import com.librarymanagement.book.model.Book;

@Service
public class BookService {

	@Autowired
	private ManageBooks manageBooks;
	
	public Map<String, Book> getBookList(){
		return manageBooks.getBookMap();
	}
	
	public Map<String, Book> borrowReturnBook(List<Integer> issuedBooks, String bookname, String action) throws Exception {
		return manageBooks.borrowReturnBook(issuedBooks, bookname, action);
	}
	
}
