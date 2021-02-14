package com.librarymanagement.book.model;

public class Book {
	private String name;
	private int id;
	private int availableCopies;
	private int totalCopies;
	
	public Book(String name, int id, int availableCopies, int totalCopies) {
		this.name = name;
		this.id = id;
		this.availableCopies = availableCopies;
		this.totalCopies = totalCopies;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public int getTotalCopies() {
		return totalCopies;
	}
}
