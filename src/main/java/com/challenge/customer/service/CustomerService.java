package com.challenge.customer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

import com.challenge.customer.exception.CustomerException;
import com.challenge.customer.vo.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerService {

	Logger logger = Logger.getLogger(CustomerService.class.getName());

	private static final double LATITUDE_INTERCOM = 53.339428;
	private static final double LONGITUDE_INTERCOM = -6.257664;
	private static final double DISTANCE_LIMIT = 100;
	private static final ObjectMapper mapper = new ObjectMapper();

	public void process(Scanner scan, String filePath) throws CustomerException {

		List<Customer> customers = getAndFilterCustomers(scan, filePath);

		customers.stream().forEach(customer -> System.out.printf("Customer userId: %d name: %s%n", customer.getUserId(),
				customer.getName()));
	}

	public List<Customer> getAndFilterCustomers(Scanner scan, String filePath) throws CustomerException {

		List<Customer> customers = new ArrayList<>();
		Integer lineCounter = 1;

		if(Objects.isNull(scan)) {
			scan = FileService.getFileScanner(filePath);
		}
		
		try {
			while (scan.hasNextLine()) {
				
				String line = scan.nextLine();
				if(line.trim().isEmpty()) {
					break;
				}
				
				Customer customer = getCustomer(line, mapper, lineCounter);

				if (Objects.nonNull(customer)) {
					double distance = DistanceCalcService.distanceInKilometers(customer.getLatitude(),
							customer.getLongitude(), LATITUDE_INTERCOM, LONGITUDE_INTERCOM);

					if (DistanceCalcService.isDistanceLessOrEqualsThan(distance, DISTANCE_LIMIT)) {
						customers.add(customer);
					}
				}

				lineCounter++;
			}
		} finally {
			scan.close();
		}

		sortingListByCustomerId(customers);

		return customers;
	}

	private void sortingListByCustomerId(List<Customer> customers) {
		customers.sort(Comparator.comparing(Customer::getUserId));
	}

	public Customer getCustomer(String customerText, ObjectMapper mapper, Integer line) throws CustomerException {

		try {

			Customer customer = mapper.readValue(customerText, Customer.class);

			if (Objects.isNull(customer.getUserId())) {
				throw new CustomerException("user_id is null");
			}

			if (Objects.isNull(customer.getLatitude())) {
				throw new CustomerException("Latitude is null");
			}

			if (Objects.isNull(customer.getLongitude())) {
				throw new CustomerException("Longitude is null");
			}

			return mapper.readValue(customerText, Customer.class);
		} catch (CustomerException e) {
			logger.severe(String.format("Fail to process text on line %d %s", line, e.getMessage()));
			throw e;
		} catch (IOException e) {
			throw new CustomerException(String.format("Fail to process text on line %d %s", line, e.getMessage()), e);
		}
	}
}
