package com.challenge.customer;

import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.challenge.customer.exception.CustomerException;
import com.challenge.customer.service.CustomerService;

public class AppMain {

	static Logger logger = Logger.getLogger(AppMain.class.getName());

	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		String filePath = null;
		Scanner scan = null;
		
		try {
			if (args.length > 0) {
				filePath = args[0];
			} else {
				scan = new Scanner(System.in);
			}

			customerService.process(scan, filePath);
		} catch (CustomerException e) {
			logger.log(Level.SEVERE, String.format("Error processing file %s. Detail: %s", filePath, e.getMessage()), e);
			e.printStackTrace();
		}finally {
			if(Objects.nonNull(scan)) {
				scan.close();
			}
		}
	}
}
