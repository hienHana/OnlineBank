package com.silvercoinbank.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.silvercoinbank.domain.User;
import com.silvercoinbank.service.UserService;

@Component
public class UserValidator implements Validator{
	@Autowired UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;				
			
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username", "username cannot be null");

		if(user.getUsername() != null   && userService.findUserByUsername(user.getUsername()) != null){			
			errors.rejectValue("username", "user.username", "The username is existed");
		}		
	}
}
