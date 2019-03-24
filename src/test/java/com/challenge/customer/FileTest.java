package com.challenge.customer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.challenge.customer.exception.CustomerException;
import com.challenge.customer.service.FileService;

public class FileTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void emptyFile() throws CustomerException {
		expectedEx.expect(CustomerException.class);
		expectedEx.expectMessage("File is empty");
		FileService.getFileScanner("src/test/resources/emptyFile.txt");
	}
}
