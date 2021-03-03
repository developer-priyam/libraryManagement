package com.librarymanagement.book.constant;

public enum LibAction {
	BORROW("borrow"),
	RETURN("return");
	
	private String action;
	
	private LibAction(String action) {
		this.action = action;
	}
	
	public String getValue() {
		return action;
	}
}
