package com.revature.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ecommerce.repository.CustomerRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/g-corp/customer")
public class CustomerController {


	
	@Autowired
	private CustomerRepository  customerRepository;
	
	
    
}
