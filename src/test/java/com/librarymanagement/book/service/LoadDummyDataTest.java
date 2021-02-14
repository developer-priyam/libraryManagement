package com.librarymanagement.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import com.librarymanagement.book.repository.DummyLibraryDataStore;

@RunWith(MockitoJUnitRunner.class)
public class LoadDummyDataTest {
	private DummyLibraryDataStore dataStore;
	private LoadDummyData loadData;

	@Before
	public void setup() {
		loadData = new LoadDummyData();
		dataStore = DummyLibraryDataStore.getInstance();
	}
	 
	@Test
	public void testBookDataLoad() throws Exception {
		String[] args = new String[1];
		loadData.run(args);
		
		assertEquals(10, dataStore.getDummyBookData().size());
		assertEquals(2, dataStore.getDummyUserData().size());
	}
}
