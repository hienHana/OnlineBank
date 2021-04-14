package com.silvercoinbank.validation;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.silvercoinbank.domain.Account;
import com.silvercoinbank.service.AccountService;
import com.silvercoinbank.service.CustomerService;
import com.silvercoinbank.service.UserService;

@Component
public class AccountValidator implements Validator {

	@Autowired AccountService accountService;
	@Autowired UserService userService;
	@Autowired CustomerService customerService;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account)target;
		
		//check if amount of money is negative or zero
		if(account.getAccountCurrentBalance()<= 0) {
			errors.rejectValue("accountCurrentBalance","account.accountCurrentBalance" ,"Amount should be greater than 0");
		}

		
		//check if user deposit, withdraw, or transfer from their account, not from other's
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}else {
			username = principal.toString();
		}			
		long userId = userService.findUserByUsername(username).getUserId();
		long customerId = customerService.findCustomerIdByUserId(userId);
		List<Account> accounts = accountService.findAccountByCustomerId(customerId);
		long loginAccountId = accounts.get(0).getAccountId();
		if(account.getAccountId() != loginAccountId ) {
			errors.rejectValue("accountId", "account.accountId", "You only can enter your account number");
		}
	}

}
