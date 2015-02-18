package edu.mum.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.mum.web.domain.Customer;
import edu.mum.web.domain.repository.CustomerRepository;
import edu.mum.web.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomers() {
		
		return customerRepository.getAllCustomers();
	}	
}