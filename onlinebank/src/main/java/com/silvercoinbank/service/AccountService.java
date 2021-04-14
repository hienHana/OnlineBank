package com.silvercoinbank.service;

import java.util.List;

import com.silvercoinbank.domain.Account;


public interface AccountService {
	public void saveAccountUsingJpaRepo(Account a);
	
	public List<Account> findAllAccountUsingJpaRepo();
	
	public Account findAccountByIdUsingJpaRepo(long id);
	
	public List<Account> findAccountByCustomerId(long id);

	public boolean existedByAccountId(long accountId);
	
	/*********** Using JdbcTemplate *********/
	public List<Account> findAllAccountsUsingJdbcTemplate();
	public void saveAccountUsingJdbcTemplate(Account account);
	public void deleteAccountByIdUsingJdbcTemplate(long id);
	public void updateAccountUsingJdbcTemplate(Account account);
	public Account getAccountByIdUsingJdbcTemplate(long id);
	public void insertAccountsUsingJdbcTemplate(List<Account> accounts);
	public void createTableUsingJdbcTemplate();
}
