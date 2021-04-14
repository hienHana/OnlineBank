package com.silvercoinbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.silvercoinbank.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c.customerId from Customer c where c.user.userId=?1")
	public long findCustomerIdByUserId(long userId);
}
