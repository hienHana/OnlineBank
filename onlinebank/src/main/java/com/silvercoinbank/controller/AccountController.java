package com.silvercoinbank.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.silvercoinbank.domain.Account;
import com.silvercoinbank.domain.Branch;
import com.silvercoinbank.domain.Customer;
import com.silvercoinbank.domain.Transaction;
import com.silvercoinbank.service.AccountService;
import com.silvercoinbank.service.BranchService;
import com.silvercoinbank.service.CustomerService;
import com.silvercoinbank.service.TransactionService;
import com.silvercoinbank.service.UserService;
import com.silvercoinbank.validation.AccountValidator;

@Controller
public class AccountController {	
	
	@Autowired AccountService accountService;
	@Autowired TransactionService transService;
	@Autowired BranchService branchService;
	@Autowired UserService userService;
	@Autowired CustomerService customerService;
	@Autowired AccountValidator accountValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(accountValidator);
	}
	
	@RequestMapping (value="/accountAll")
	public String getAllAccount(Account account, Model model) {	
		List<Account> accounts = accountService.findAllAccountUsingJpaRepo();
		model.addAttribute("accounts", accounts);
		return "accountAll";
	}
	
	@RequestMapping (value="/accountForm")
	public String getAccountForm(@ModelAttribute Account account, Model model) {
		List<Branch> branches = new ArrayList<>();
		branches = branchService.findAllBranchUsingHibernate();
		Map<Long, String> branchList = new LinkedHashMap<>();
		for(Branch br:branches) {
			branchList.put(br.getBranchId(),br.getBranchName());
		}
		model.addAttribute("branches", branchList);
		return "accountForm";
	}
	
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST )
	public String createAccount(@Valid @ModelAttribute Account account, BindingResult br, Model model) {
		Customer customer = customerService.findCustomerById(account.getAccountCustomer().getCustomerId());		
		account.setAccountCustomer(customer);
		if(!br.hasErrors()) {
			accountService.saveAccountUsingJpaRepo(account);
			model.addAttribute("account", account);
			return "redirect:accountAll";
		}
		return "accountForm";
	}
	
	
	
	@RequestMapping (value="/getAccountSummaryByCustomerId")
	public String getAccountSummaryByCustomerId(Account account, Model model, Principal principal) {
		String username = principal.getName();
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		
		model.addAttribute("accounts",accounts);
		return "accountSummary";
	}
	
	
	
	@RequestMapping (value="/getAccountDetailByCustomerId")
	public String getAccountDetailByCustomerId(Account account, Model model, Principal principal) {	
		String username = principal.getName();
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		System.out.println("&&&&&& customerId "+ customerId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);

		List<Transaction> transactionsPerUser = new ArrayList<>();
		for(Account a:accounts) {
			List<Long> transIdList = new ArrayList<>();
			transIdList.addAll(transService.getTransIdByTransFromAccountId(a.getAccountId()));
			transIdList.addAll(transService.getTransIdByTransToAccountId(a.getAccountId()));
			
			//display the most recent transaction first
			Collections.sort(transIdList, Collections.reverseOrder());
			for(Long t:transIdList) {
				transactionsPerUser.add(transService.findTransactionById(t));
			}
		}
	
		model.addAttribute("transactions", transactionsPerUser);
		model.addAttribute("accounts",accounts);
		return "accountDetail";
	}

	
//	@RequestMapping(value="accountUpdateForm")
//	public String getAccountForUpdate(Account account) {
//		return "accountUpdateForm";
//	}
//	
//	@RequestMapping(value="accountUpdate", method = RequestMethod.POST)
//	public String updateAccount(@Valid @ModelAttribute Account account, BindingResult br, @RequestParam long id) {
//		if(! br.hasErrors()) {
//			accountService.updateAccountByIdUsingJpaRepo(id);		
//			return "redirect:accountForm";
//		}
//		return "accountForm";
//	}
	
	@RequestMapping (value="/depositForm")
	public String depositForm(Account account) {
		return "deposit";
	}
	
	@RequestMapping (value="/deposit", method=RequestMethod.POST)
	public String deposit(@Valid @ModelAttribute Account account, BindingResult br, Model model, 
							@RequestParam long accountId, @RequestParam double accountCurrentBalance, Principal principal) {
		//get accountId from the username that login
		String username = principal.getName();
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		long loginAccountId = accounts.get(0).getAccountId();
		if(loginAccountId == accountId) {	
			Account accountFromDB = accountService.findAccountByIdUsingJpaRepo(loginAccountId);
			if (accountFromDB != null) {
				if(accountCurrentBalance>0) {
					double balance = accountFromDB.getAccountCurrentBalance();
					double newBalance = balance + accountCurrentBalance;
					accountFromDB.setAccountCurrentBalance(newBalance);
					if(!br.hasErrors()) {
						//create and save transaction record to transaction table
						String transComments = "deposit to accountId " + accountId;
						Transaction trans = transService.depositTransaction(accountId, accountCurrentBalance, transComments, principal.getName());
						
						//Add transaction to account, and save the updated account
						Set<Transaction> accountTransactions = new HashSet<>();
						accountTransactions.add(trans);
						accountFromDB.setAccountTransactions(accountTransactions);
						accountService.saveAccountUsingJpaRepo(accountFromDB);
						model.addAttribute("accounts", accountService.findAllAccountUsingJpaRepo());
						return "redirect:getAccountDetailByCustomerId";
					}
					System.out.println("br.hasErrors()" + br.hasErrors());					
					return "redirect:getAccountDetailByCustomerId";
				}
				System.out.println("Deposit amount should be greater than 0");
				return "deposit";
			}
			System.out.println("Deposit Transaction fails due to no account found");
			return "redirect:getAccountDetailByCustomerId";
		}
		System.out.println("You cannot deposit to other customer account");
		return "deposit";
	}
	
	
	@RequestMapping(value="/withdrawForm")
	public String withdrawForm(Account account) {
		return "withdraw";
	}
	
	@RequestMapping(value="/withdraw", method=RequestMethod.POST)
	public String withdraw(@Valid @ModelAttribute Account account, BindingResult br, Model model,
				@RequestParam long accountId, @RequestParam double accountCurrentBalance, Principal principal) {
		//get accountId from the username that login
		String username = principal.getName();
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		long loginAccountId = accounts.get(0).getAccountId();
		if(loginAccountId == accountId) {
			Account accountFromDB = accountService.findAccountByIdUsingJpaRepo(loginAccountId);
			if (accountFromDB != null) {
				double balance = accountFromDB.getAccountCurrentBalance();
				if(accountCurrentBalance > 0 && accountCurrentBalance <= balance){				
					double newBalance = balance - accountCurrentBalance;
					accountFromDB.setAccountCurrentBalance(newBalance);
				
					if(!br.hasErrors()) {
						//create and save transaction record to transaction table
						String transComments = "withdraw from accountId " + accountId;
						Transaction trans = transService.withdrawTransaction(accountId, accountCurrentBalance, transComments, principal.getName());
						
						//Add transaction to account, and save the updated account
						Set<Transaction> accountTransactions = new HashSet<>();
						accountTransactions.add(trans);
						accountFromDB.setAccountTransactions(accountTransactions);
						accountService.saveAccountUsingJpaRepo(accountFromDB);
						model.addAttribute("accounts", accountService.findAllAccountUsingJpaRepo());
						return "redirect:getAccountDetailByCustomerId";
					}	
				}
				System.out.println("Withdraw amount should be greater than 0 and less than or equal your balance");
				return "withdraw";
			}
			System.out.println("Withdraw Transaction fails due to no account found");
			return "redirect:getAccountDetailByCustomerId";
		}
		System.out.println("You cannot withdraw from other customer account");
		return "withdraw";
	}
	
	
	@RequestMapping(value="/transferForm")
	public String transferForm(Account account) {
		return "transfer";
	}
	
	@RequestMapping(value="/transfer", method=RequestMethod.POST)
	public String transfer(@Valid @ModelAttribute Account account, BindingResult br, Model model,
							@RequestParam long accountId, @RequestParam long toAccountId, 
							@RequestParam double accountCurrentBalance, Principal principal) {
		//get accountId from the username that login
		String username = principal.getName();
		long userId = userService.findUserByUsername(username).getUserId();
//		long customerId = userService.findCustomerIdByUserId(userId);
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		long loginAccountId = accounts.get(0).getAccountId();
		
		if(loginAccountId == accountId && accountId != toAccountId) {		
			Account fromAccount = accountService.findAccountByIdUsingJpaRepo(accountId);
			Account toAccount = accountService.findAccountByIdUsingJpaRepo(toAccountId);
			if (fromAccount != null && toAccount != null) {
				double fromAccountBalance = fromAccount.getAccountCurrentBalance();
				double toAccountBalance = toAccount.getAccountCurrentBalance();
				if(accountCurrentBalance>0) {
					double fromAccountNewBalance = fromAccountBalance - accountCurrentBalance;
					if(fromAccountNewBalance >= 0) {
						double toAccountNewBalance = toAccountBalance + accountCurrentBalance;
						fromAccount.setAccountCurrentBalance(fromAccountNewBalance);
						toAccount.setAccountCurrentBalance(toAccountNewBalance);
						if(!br.hasErrors()) {
							accountService.saveAccountUsingJpaRepo(fromAccount);
							accountService.saveAccountUsingJpaRepo(toAccount);
							String transComments = "transfer from accountId "+ accountId+" to accountId " + toAccountId;
							Transaction trans = transService.transferTransaction(accountId, toAccountId, accountCurrentBalance, transComments, principal.getName());
							
							//Add transaction to account, and save the updated account
							Set<Transaction> accountTransactions = new HashSet<>();
							accountTransactions.add(trans);
							fromAccount.setAccountTransactions(accountTransactions);
							toAccount.setAccountTransactions(accountTransactions);
							accountService.saveAccountUsingJpaRepo(fromAccount);
							accountService.saveAccountUsingJpaRepo(toAccount);
							model.addAttribute("accounts", accountService.findAllAccountUsingJpaRepo());
							return "redirect:getAccountDetailByCustomerId";
						}		
					}
					System.out.println("Transfer amount should be less than or equal your balance");
					return "redirect:getAccountDetailByCustomerId";
				}
				System.out.println("Transfer amount should be greater than 0");
				return "transfer";
			}
			System.out.println("Transfer Transaction fails due to either one of the accounts or both not found");
			return "redirect:getAccountDetailByCustomerId";
		}
		System.out.println("You cannot transfer from other customer account, or transfer to your own same account");
		return "transfer";
	}
	
	
	/******************* Using JdbcTemplate *****************/
	@RequestMapping (value="/getAllAccounts")
	public ResponseEntity<?> getAllAccounts() {	
		List<Account> accounts = accountService.findAllAccountsUsingJdbcTemplate();
		if(!accounts.isEmpty())
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
		return new ResponseEntity<String>("No account found", HttpStatus.NOT_FOUND);
	}
	
	/* 
	   {
	    "accountId": 8,
	    "accountHolderName": "Tata",
	    "accountType": "CHECKING",
	    "accountDateOpened": "2020-02-05",
	    "accountCurrentBalance": 1300.0
		}
	  */
	@RequestMapping (value="/getAccount")
	public ResponseEntity<?> getAccount(@RequestParam long id) {	
		Account account = accountService.getAccountByIdUsingJdbcTemplate(id);
		if(account != null)
			return new ResponseEntity<Account>(account, HttpStatus.OK);
		return new ResponseEntity<String>("No account found", HttpStatus.NOT_FOUND);
	}
	

	@RequestMapping (value="/saveAccount", method = RequestMethod.POST)			
	public ResponseEntity<?> saveAccount(@RequestBody Account account) {
		System.out.println(">>>>>>>>>saveAccount"+ account);
		if(accountService.existedByAccountId(account.getAccountId())) {
			return new ResponseEntity<String>("The account already exists", HttpStatus.FOUND);
		}
//		accountService.saveAccountUsingJdbcTemplate(account);
		accountService.saveAccountUsingJpaRepo(account);
		System.out.println(">>>>>> account "+ account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
}
