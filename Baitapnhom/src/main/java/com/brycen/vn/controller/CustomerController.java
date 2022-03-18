package com.brycen.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brycen.vn.dto.CustomerDTO;
import com.brycen.vn.entity.Customer;
import com.brycen.vn.service.iml.CustomerServiceImpl;

@RestController
@RequestMapping("/congthongtin")
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	
	@GetMapping("/customer")
	public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
		CustomerDTO customerDTO = customerServiceImpl.getCustomerById(id);
		if (customerDTO != null) {
			return new ResponseEntity<Object>(customerDTO, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Not Found customer", HttpStatus.NO_CONTENT);
	}

//	@PostMapping("/customers")
//	public ResponseEntity<String> createOrUpdate(@RequestBody Customer customer) {
//		if (customer.getId() != null) {
//			customerServiceImpl.createOrUpdateCustomer(customer);
//			return new ResponseEntity<String>("Update!", HttpStatus.OK);
//		} else {
//			customerServiceImpl.createOrUpdateCustomer(customer);
//			return new ResponseEntity<String>("Create!", HttpStatus.CREATED);
//		}
//
//	}

}
