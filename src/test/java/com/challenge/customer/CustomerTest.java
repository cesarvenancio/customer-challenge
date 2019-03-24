package com.challenge.customer;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.challenge.customer.service.CustomerService;
import com.challenge.customer.vo.Customer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Customer Tests
 */
public class CustomerTest {

	private CustomerService customerService = new CustomerService();
	private ObjectMapper mapper = new ObjectMapper();
	private Scanner scan; 
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void emptyFile() throws IOException {
		expectedEx.expect(IOException.class);
		expectedEx.expectMessage("File is empty");
		customerService.getFileScanner("src/test/resources/emptyFile.txt");
	}
	
	@Test
	public void getCustomer() throws IOException {
		Scanner scan = customerService.getFileScanner("src/test/resources/getCustomer.txt");
		Customer customer = customerService.getCustomer(scan.nextLine(), mapper, 1);
	
		Assert.assertNotNull(customer);
		Assert.assertEquals("Christina McArdle", customer.getName());
	}
	
	@Test
	public void userIdNull() throws IOException {
		expectedEx.expect(JsonParseException.class);
		expectedEx.expectMessage("user_id is null");
		
		Scanner scan = customerService.getFileScanner("src/test/resources/userIdNull.txt");
		customerService.getCustomer(scan.nextLine(), mapper, 1);
	}
	
	@Test
	public void latitudeNull() throws IOException {
		expectedEx.expect(JsonParseException.class);
		expectedEx.expectMessage("Latitude is null");
		
		Scanner scan = customerService.getFileScanner("src/test/resources/latitudeNull.txt");
		customerService.getCustomer(scan.nextLine(), mapper, 1);
	}
	
	@Test
	public void longitudeNull() throws IOException {
		expectedEx.expect(JsonParseException.class);
		expectedEx.expectMessage("Longitude is null");
		
		Scanner scan = customerService.getFileScanner("src/test/resources/longitudeNull.txt");
		customerService.getCustomer(scan.nextLine(), mapper, 1);
	}
	
	@Test
	public void getCustomers() throws IOException {
		List<Customer> customers = customerService.getAndFilterCustomers("src/test/resources/customers.txt");
	
		Assert.assertNotNull(customers);
		Assert.assertEquals(4, customers.size());
	}
	
}
