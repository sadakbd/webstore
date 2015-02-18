package edu.mum.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.web.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerServie;
	
	
	@RequestMapping("/customers")
	public String list(Model model){		
		model.addAttribute("customers",customerServie.getAllCustomers());		
		return "customers";
	}
	
	
	


}
