package com.librarymanagement.book.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.librarymanagement.book.constant.LibAction;
import com.librarymanagement.book.exception.DuplicateCopyIssueException;
import com.librarymanagement.book.exception.IssueLimitReachedException;
import com.librarymanagement.book.exception.NotEnoughCopyException;
import com.librarymanagement.book.model.Book;

@Component
public class BookIssueValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(BookIssueValidator.class);
	
	public static int validateIssueProcess(Book book, String action, List<Integer> issuedBooks, int availableCount) {
		int newBookCount = availableCount;
		if (action.equalsIgnoreCase(LibAction.RETURN.getValue())) {
			newBookCount++;
			logger.info("incrementing book copy count : {}, as action request is :{}", newBookCount, action);
		} else if (issuedBooks.size() >= 2) {
			throwIssueLimitReachException();
		} else if (issuedBooks.contains(book.getId())) {
			throwDuplicateCopyException();
		} else if(newBookCount == 1) {
			throwNotEnoughCopyException();
		} else {
			logger.info("More than 1 copy available for book : {} in library. Count is : {}. Performig action : {}", book.getName(), newBookCount, action); 
			newBookCount--;
			logger.info("Decrementing available book count : {}", newBookCount);
		}
		return newBookCount;
	}
	
	private static void throwIssueLimitReachException() {
		throw new IssueLimitReachedException("Cannot issue new books, Book issue limit reached");
	}

	private static void throwDuplicateCopyException() { 
		throw new DuplicateCopyIssueException("Cannot issue 2 copies of same book");
	}

	private static void throwNotEnoughCopyException() {
		throw new NotEnoughCopyException("Not enough copies of the book available");
	}
}
