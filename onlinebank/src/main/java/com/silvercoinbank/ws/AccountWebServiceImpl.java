package com.silvercoinbank.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silvercoinbank.domain.Account;
import com.silvercoinbank.repository.AccountRepository;

@Service
public class AccountWebServiceImpl implements AccountWebService {

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	@Transactional
	public AccountResponse getAllAccounts() {
		AccountResponse response = new AccountResponse();
		List<Account> accounts = accountRepository.findAll();
		if(!accounts.isEmpty()) {
			response.setMessageCode("FOUND");
			response.setMessage("Accounts are listed below: ");
			response.setAccounts(accounts);
		}else {
			response.setMessageCode("NOT FOUND");
			response.setMessage("No account found");
		}
		return response;
	}


	@Override
	public AccountResponse getAccountById(AccountRequestById request) {
		AccountResponse response = new AccountResponse();
		Account account = accountRepository.findById(request.getAccountId()).get();
		if(account != null) {
			List<Account> accounts = new ArrayList<>();
			accounts.add(account);
			response.setMessageCode("FOUND");
			response.setMessage("Account detail is below: ");
			response.setAccounts(accounts);
		}else {
			response.setMessageCode("NOT FOUND");
			response.setMessage("No account found");
		}
		return response;
	}

}
