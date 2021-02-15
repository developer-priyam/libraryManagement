package com.librarymanagement.book.exception;

public class IssueLimitReachedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IssueLimitReachedException(String s) {
		super(s);
	}
	
}
