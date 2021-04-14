package com.silvercoinbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.silvercoinbank.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query("select a from Account a where a.accountCustomer.customerId=?1")
	public List<Account> findAccountByCustomerId(long id);
	
}
