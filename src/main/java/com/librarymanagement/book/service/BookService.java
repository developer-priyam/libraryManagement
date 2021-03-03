package com.librarymanagement.book.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.librarymanagement.book.model.Book;

@Component
public interface BookService {

	public Map<String, Book> getBookMap();
}
