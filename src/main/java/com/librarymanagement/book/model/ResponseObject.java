package com.librarymanagement.book.model;

import java.util.Collection;

public class ResponseObject {

	private User user;
	private Collection<Book> book;
	private String status;
	
	public ResponseObject(User user, Collection<Book> book, String status) {
		this.user = user;
		this.book = book;
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Book> getBook() {
		return book;
	}

	public void setBook(Collection<Book> book) {
		this.book = book;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
