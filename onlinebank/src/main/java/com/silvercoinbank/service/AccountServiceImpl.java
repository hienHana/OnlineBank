package com.silvercoinbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.dao.AccountDao;
import com.silvercoinbank.domain.Account;
import com.silvercoinbank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountDao accountDao;

	@Override
	public void saveAccountUsingJpaRepo(Account a) {
		accountRepository.save(a);
	}

	@Override
	public List<Account> findAllAccountUsingJpaRepo() {
		return accountRepository.findAll();
	}

	@Override
	public Account findAccountByIdUsingJpaRepo(long id) {
		boolean existed = accountRepository.existsById(id);
		if(existed) {
			return accountRepository.findById(id).get();
		}
		return null;
		
		//or do as below		
//		Optional<Account> optAccount = accountRepository.findById(id);
//		if(optAccount.isPresent()) {
//			Account account = new Account();
//			account = optAccount.get();			
//			return account;
//		}
//		return null;
	}

	@Override
	public List<Account> findAccountByCustomerId(long id) {
		return accountRepository.findAccountByCustomerId(id);
	}

	@Override
	public boolean existedByAccountId(long accountId) {
		return accountRepository.existsById(accountId);
	}

	/******************* Using JdbcTemplate ******************/
	@Override
	public List<Account> findAllAccountsUsingJdbcTemplate() {		
		return accountDao.findAllAccountsUsingJdbcTemplate();
	}

	@Override
	public void saveAccountUsingJdbcTemplate(Account account) {
		accountDao.saveAccountUsingJdbcTemplate(account);
	}

	@Override
	public void deleteAccountByIdUsingJdbcTemplate(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountUsingJdbcTemplate(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccountByIdUsingJdbcTemplate(long id) {
		return accountDao.getAccountByIdUsingJdbcTemplate(id);
	}

	@Override
	public void insertAccountsUsingJdbcTemplate(List<Account> accounts) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTableUsingJdbcTemplate() {
		// TODO Auto-generated method stub
		
	}
	

}
