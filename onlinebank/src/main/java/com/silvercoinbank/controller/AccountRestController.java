package com.silvercoinbank.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silvercoinbank.domain.Account;
import com.silvercoinbank.domain.Transaction;
import com.silvercoinbank.service.AccountService;
import com.silvercoinbank.service.CustomerService;
import com.silvercoinbank.service.TransactionService;
import com.silvercoinbank.service.UserService;

@RestController
@RequestMapping (value="r")
public class AccountRestController {
	
	@Autowired AccountService accountService;
	@Autowired UserService userService;
	@Autowired CustomerService customerService;
	@Autowired TransactionService transService;
	
	
	@RequestMapping (value="/accountAll")
	public ResponseEntity<?> getAllAccount(){
		List<Account> accounts = accountService.findAllAccountUsingJpaRepo();
		if(accounts.isEmpty()) {
			return new ResponseEntity<String>("Find no accounts", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/getAccountSummaryByCustomerUsername")
	public ResponseEntity<?> getAccountSummaryByCustomerUserName(@RequestParam String username){
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		if(accounts.isEmpty()) {
			return new ResponseEntity<String>("Find no accounts", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.FOUND);
	}
	
	@GetMapping (value="/getAccountDetailByCustomerUsername")
	public ResponseEntity<?> getAccountDetailsByCustomerUserName(@RequestParam String username){
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		if(!accounts.isEmpty()) {
			List<Transaction> transactionsPerUser = new ArrayList<>();
			for(Account a:accounts) {
				List<Long> transIdList = new ArrayList<>();
				transIdList.addAll(transService.getTransIdByTransFromAccountId(a.getAccountId()));
				transIdList.addAll(transService.getTransIdByTransToAccountId(a.getAccountId()));
				if(!transIdList.isEmpty()) {
					for(Long t:transIdList) {
						transactionsPerUser.add(transService.findTransactionById(t));
					}
					//if find transactions, display them
					return new ResponseEntity<List<Transaction>>(transactionsPerUser, HttpStatus.FOUND);
				}
				//if no transaction, display find no transactions
				return new ResponseEntity<String>("Find no transactions", HttpStatus.NO_CONTENT);
			}
			//if there is no transaction but has an account, display the account
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.FOUND);
		}
		//if no account, display find no accounts
		return new ResponseEntity<String>("Find no accounts", HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping (value="/createAccount", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount (@RequestBody Account account){
		if (accountService.existedByAccountId(account.getAccountId())) {
			return new ResponseEntity<String>("Account id " +account.getAccountId() + " already exist", HttpStatus.FOUND);
		}
		accountService.saveAccountUsingJpaRepo(account);
		return new ResponseEntity <Account>(account, HttpStatus.CREATED); 
	}
	
	@GetMapping(value="/deposit")
	public ResponseEntity<?> deposit(@RequestParam long accountId, @RequestParam double amount){
		Account accountFromDB = accountService.findAccountByIdUsingJpaRepo(accountId);
		if(accountFromDB != null) {
			accountFromDB.setAccountCurrentBalance(accountFromDB.getAccountCurrentBalance()+amount);
			
//			String transComments = "deposit to accountId " + accountId;
//			Transaction trans = transService.depositTransaction(accountId, amount, transComments);
//			Set<Transaction> accountTransactions = new HashSet<>();
//			accountTransactions.add(trans);
//			accountFromDB.setAccountTransactions(accountTransactions);
			
			accountService.saveAccountUsingJpaRepo(accountFromDB);
			return new ResponseEntity <Account>(accountFromDB, HttpStatus.ACCEPTED); 
		}
		return new ResponseEntity<String>("Find no account", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/withdraw")
	public ResponseEntity<?> withdraw(@RequestParam long accountId, @RequestParam double amount){
		Account accountFromDB = accountService.findAccountByIdUsingJpaRepo(accountId);
		if(accountFromDB != null) {
			if(amount <= accountFromDB.getAccountCurrentBalance()) {
				accountFromDB.setAccountCurrentBalance(accountFromDB.getAccountCurrentBalance()-amount);
				
//				String transComments = "withdraw from accountId " + accountId;
//				Transaction trans = transService.withdrawTransaction(accountId, amount, transComments);
//				Set<Transaction> accountTransactions = new HashSet<>();
//				accountTransactions.add(trans);
//				accountFromDB.setAccountTransactions(accountTransactions);
				
				accountService.saveAccountUsingJpaRepo(accountFromDB);
				return new ResponseEntity <Account>(accountFromDB, HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<String>("Transaction Fail. You withdraw more than your balance", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("Find no account", HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/transfer")
	public ResponseEntity<?> transfer(@RequestParam long fromAccountId, 
									  @RequestParam long toAccountId, 
									  @RequestParam double amount){
		Account fromAccount = accountService.findAccountByIdUsingJpaRepo(fromAccountId);
		Account toAccount = accountService.findAccountByIdUsingJpaRepo(toAccountId);
		if(fromAccount != null && toAccount != null) {
			if(amount <= fromAccount.getAccountCurrentBalance()) {
				fromAccount.setAccountCurrentBalance(fromAccount.getAccountCurrentBalance()-amount);
				toAccount.setAccountCurrentBalance(toAccount.getAccountCurrentBalance()+amount);
				
//				String transComments = "transfer from accountId " + fromAccountId + " to accountId " + toAccountId;
//				Transaction trans = transService.transferTransaction(fromAccountId, toAccountId, amount, transComments);
//				Set<Transaction> accountTransactions = new HashSet<>();
//				accountTransactions.add(trans);
//				fromAccount.setAccountTransactions(accountTransactions);
//				toAccount.setAccountTransactions(accountTransactions);
				
				accountService.saveAccountUsingJpaRepo(fromAccount);
				accountService.saveAccountUsingJpaRepo(toAccount);
				
				List<Account> accounts = new ArrayList<>();
				accounts.add(fromAccount);
				accounts.add(toAccount);
				
				return new ResponseEntity <List<Account>>(accounts, HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<String>("Transaction Fail. You withdraw more than your balance", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>("Either fromAccount, or toAccount, or both accounts are not exist", HttpStatus.NO_CONTENT);
	}
}
