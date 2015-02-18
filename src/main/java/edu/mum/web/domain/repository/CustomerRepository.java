package edu.mum.web.domain.repository;

import java.util.List;

import edu.mum.web.domain.Customer;


public interface CustomerRepository {
	
	
	List <Customer> getAllCustomers();
	
	//Product getCustomerById(String customerID);

}
