package com.challenge.customer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.challenge.customer.exception.CustomerException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileService {

	public static Scanner getFileScanner(String path) throws CustomerException {
		File file = new File(path);

		try {
			if (!file.exists()) {
				throw new CustomerException("File not found! "+path);
			}
			
			if (file.length() == 0) {
				throw new CustomerException("File is empty! "+path);
			}

			return new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new CustomerException("File not found", e);
		}
	}
}
