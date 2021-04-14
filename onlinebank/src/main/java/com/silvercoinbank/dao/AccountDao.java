package com.silvercoinbank.dao;

import java.util.List;

import com.silvercoinbank.domain.Account;

public interface AccountDao {
	public List<Account> findAllAccountsUsingJdbcTemplate();	
	public void saveAccountUsingJdbcTemplate(Account account);	
	public void deleteAccountByIdUsingJdbcTemplate(long id);
	public void updateAccountUsingJdbcTemplate(Account account);
	public Account getAccountByIdUsingJdbcTemplate(long id);
	public void insertAccountsUsingJdbcTemplate(List<Account> accounts);
	public void createTableUsingJdbcTemplate();
}
