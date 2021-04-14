package com.silvercoinbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.domain.Customer;
import com.silvercoinbank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired CustomerRepository customerRepository;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findCustomerById(long id) {
		if (customerRepository.existsById(id)) {
			return customerRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public long findCustomerIdByUserId(long userId) {
		return customerRepository.findCustomerIdByUserId(userId);
	}

}
