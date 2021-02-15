package com.librarymanagement.book.exception;

public class NotEnoughCopyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughCopyException(String s) {
		super(s);
	}

}
