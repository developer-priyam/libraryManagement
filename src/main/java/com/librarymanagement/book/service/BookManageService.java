package com.librarymanagement.book.service;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface BookManageService {
	
	public void borrowReturnBook(List<Integer> issuedBooks, String bookname, String action) throws Exception;
}
