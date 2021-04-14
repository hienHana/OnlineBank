package com.silvercoinbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.silvercoinbank.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query("select transId from Transaction t where t.transFromAccountNo=?1")
	public List<Long> getTransIdByTransFromAccountId(long accountId);
	
	@Query("select transId from Transaction t where t.transToAccountNo=?1")
	public List<Long> getTransIdByTransToAccountId(long accountId);
}
