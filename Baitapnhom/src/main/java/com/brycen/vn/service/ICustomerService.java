package com.brycen.vn.service;

import com.brycen.vn.dto.CustomerDTO;
import com.brycen.vn.entity.Customer;

public interface ICustomerService {
	
	CustomerDTO getCustomerById(Long id);
	
	boolean createOrUpdateCustomer(Customer customer);

}
