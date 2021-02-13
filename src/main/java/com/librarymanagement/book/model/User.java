package com.librarymanagement.book.model;

import java.util.List;

public class User {
	private int id;
	private String name;
	private List<Integer> issuedBooks;
	
	public User(int id, String name, List<Integer> issuedBooks) {
		this.id = id;
		this.name = name;
		this.issuedBooks = issuedBooks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getIssuedBooks() {
		return issuedBooks;
	}

	public void setIssuedBooks(List<Integer> issuedBooks) {
		this.issuedBooks = issuedBooks;
	}
	
}
