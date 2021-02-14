package com.librarymanagement.book.model;

public class LibManageReqObj {
	private String username;
	private String bookname;
	private String action;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "LibManageReqObj [username=" + username + ", bookname=" + bookname + ", action=" + action + "]";
	}
	
}
