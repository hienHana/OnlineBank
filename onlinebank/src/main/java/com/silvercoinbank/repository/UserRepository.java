package com.silvercoinbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.silvercoinbank.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findUserByUsername(String name);
	
//	@Query("select u.customer.customerId from User u where u.userId=?1")
//	public long findCustomerIdByUserId(long userId);
}
