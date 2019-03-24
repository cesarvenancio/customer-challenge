package com.challenge.customer;

import java.io.IOException;
import java.util.logging.Logger;

import com.challenge.customer.service.CustomerService;

public class AppMain {
	
	static Logger logger = Logger.getLogger(AppMain.class.getName());
	
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		try {
			customerService.process("src/main/resources/customers.txt");
		} catch (IOException e) {
			logger.severe(e.getMessage());
			if(args.length > 0 && args[0].equals("printStack")) {
				e.printStackTrace();
			}
		}
	}
}
