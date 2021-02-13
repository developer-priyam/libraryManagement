package com.librarymanagement.book;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.librarymanagement.book.controller.BookController;

@SpringBootTest
class BookApplicationTests {

	@Autowired
	private BookController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
