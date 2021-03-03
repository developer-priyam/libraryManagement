package com.librarymanagement.book.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.librarymanagement.book.constant.LibAction;

@Component
public class UserManageUtil {
	
	public static List<Integer> updateIssuedBookList(int bookId, List<Integer> issuedBooks, String bookname, String action) {
		List<Integer> updatedList = issuedBooks;
		if(LibAction.BORROW.getValue().equalsIgnoreCase(action)) {
			if(!updatedList.contains(bookId)) {
				updatedList.add(bookId);
			} 
		} else {
			Integer element = 0;
			for(Integer id : updatedList) {
				if(bookId == id) element = id;
			}
			updatedList.remove(element);
		}
		return updatedList;
	}
}
