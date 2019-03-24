package com.challenge.customer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

import com.challenge.customer.vo.Customer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerService {

	Logger logger = Logger.getLogger(CustomerService.class.getName());

	// Intercom Distance;
	private static final double LATITUDE_INTERCOM = 53.339428;
	private static final double LONGITUDE_INTERCOM = -6.257664;

	private static final double DISTANCE_LIMIT = 100;

	public void process(String filePath) throws JsonParseException, JsonMappingException, IOException {

		List<Customer> customers = getAndFilterCustomers(filePath);

		for (Customer customer : customers) {
			System.out.printf("Customer userId: %d name: %s%n", customer.getUserId(), customer.getName());
		}
	}

	public List<Customer> getAndFilterCustomers(String filePath) throws IOException {

		List<Customer> customers = new ArrayList<Customer>();
		Scanner scan = getFileScanner(filePath);
		ObjectMapper mapper = new ObjectMapper();
		Integer lineCounter = 1;
		
		while (scan.hasNextLine()) {
			Customer customer = getCustomer(scan.nextLine(), mapper, lineCounter);
			
			if(Objects.nonNull(customer)) {
				double distance = DistanceCalcService.distanceInKilometers(customer.getLatitude(), customer.getLongitude(),
						LATITUDE_INTERCOM, LONGITUDE_INTERCOM);
				
				if (DistanceCalcService.isDistanceLessOrEqualsThan(distance, DISTANCE_LIMIT)) {
					customers.add(customer);
				}
			}
			
			lineCounter++;
		}

		sortingListByCustomerId(customers);

		return customers;
	}

	private void sortingListByCustomerId(List<Customer> customers) {
		customers.sort(Comparator.comparing(Customer::getUserId));
	}
	
	public Scanner getFileScanner(String path) throws IOException {
		
		File file = new File(path);
		try {
			
			if(file.length() == 0) {
				throw new IOException("File is empty!");
			}
			
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			logger.severe("File not found");
			throw e;
		}
	}
	
	public Customer getCustomer(String customerText, ObjectMapper mapper, Integer line) throws IOException {
			try {
				
				Customer customer = mapper.readValue(customerText, Customer.class);
				
				if(Objects.isNull(customer.getUserId())) {
					throw new JsonParseException(null, "user_id is null");
				}
				
				if(Objects.isNull(customer.getLatitude())) {
					throw new JsonParseException(null, "Latitude is null");
				}
				
				if(Objects.isNull(customer.getLongitude())) {
					throw new JsonParseException(null, "Longitude is null");
				}
				
				return mapper.readValue(customerText, Customer.class);
			} catch (JsonParseException e) {
				logger.severe(String.format("Fail to process text on line %d %s", line, e.getMessage()));
				throw e;
			} catch (IOException e) {
				throw e;
			}
	}
}
