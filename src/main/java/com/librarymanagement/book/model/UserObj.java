package com.librarymanagement.book.model;

public class UserObj {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserObj [username=" + username + "]";
	}
}
