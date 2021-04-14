package com.silvercoinbank.service;

import java.util.List;

import com.silvercoinbank.domain.Customer;

public interface CustomerService {
	public Customer saveCustomer(Customer customer);
	public List<Customer> findAllCustomers();
	public Customer findCustomerById(long id);
	public long findCustomerIdByUserId(long userId);
}
