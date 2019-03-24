package com.challenge.customer;

import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.challenge.customer.exception.CustomerException;
import com.challenge.customer.service.CustomerService;
import com.challenge.customer.service.FileService;
import com.challenge.customer.vo.Customer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Customer Tests
 */
public class CustomerTest {

	private CustomerService customerService = new CustomerService();
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void getCustomer() throws CustomerException {
		Scanner scan = FileService.getFileScanner("src/test/resources/getCustomer.txt");
		Customer customer = customerService.getCustomer(scan.nextLine(), mapper, 1);
	
		Assert.assertNotNull(customer);
		Assert.assertEquals("Christina McArdle", customer.getName());
	}
	
	@Test
	public void userIdNull() throws CustomerException {
		expectedEx.expect(CustomerException.class);
		expectedEx.expectMessage("user_id is null");
		
		Scanner scan = FileService.getFileScanner("src/test/resources/userIdNull.txt");
		customerService.getCustomer(scan.nextLine(), mapper, 1);
	}
	
	@Test
	public void latitudeNull() throws CustomerException {
		expectedEx.expect(CustomerException.class);
		expectedEx.expectMessage("Latitude is null");
		
		Scanner scan = FileService.getFileScanner("src/test/resources/latitudeNull.txt");
		customerService.getCustomer(scan.nextLine(), mapper, 1);
	}
	
	@Test
	public void longitudeNull() throws CustomerException {
		expectedEx.expect(CustomerException.class);
		expectedEx.expectMessage("Longitude is null");
		
		Scanner scan = FileService.getFileScanner("src/test/resources/longitudeNull.txt");
		customerService.getCustomer(scan.nextLine(), mapper, 1);
	}
	
	@Test
	public void getCustomers() throws CustomerException {
		List<Customer> customers = customerService.getAndFilterCustomers(null, "src/test/resources/customers.txt");
	
		Assert.assertNotNull(customers);
		Assert.assertEquals(4, customers.size());
	}
	
}
