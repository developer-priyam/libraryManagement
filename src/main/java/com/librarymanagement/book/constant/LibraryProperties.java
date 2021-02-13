package com.librarymanagement.book.constant;

import org.springframework.stereotype.Component;

@Component
public class LibraryProperties {

	private String BORROW = "borrow";
	private String RETURN = "return";

	public String getBORROW() {
		return BORROW;
	}
	
	public String getRETURN() {
		return RETURN;
	}
}
