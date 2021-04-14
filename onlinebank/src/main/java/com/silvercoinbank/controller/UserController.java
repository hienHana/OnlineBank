package com.silvercoinbank.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.silvercoinbank.domain.Role;
import com.silvercoinbank.domain.User;
import com.silvercoinbank.domain.UserRole;
import com.silvercoinbank.service.RoleService;
import com.silvercoinbank.service.UserService;
import com.silvercoinbank.validation.UserValidator;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	@Autowired BCryptPasswordEncoder bCryptEncoder;
	@Autowired UserValidator userValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	
	@RequestMapping(value= {"/bank", "/"})
	public String welcome(Principal principal) {
		if(principal != null) {
			return "redirect:getAccountSummaryByCustomerId";
		}
		return "home";
	}
	
	
	@RequestMapping(value= "/admin")
	public String adminPage(Principal principal) {	
		if(principal != null) {
			Set<UserRole> userRoles = userService.findUserByUsername(principal.getName()).getUserRole();
			Set<String> roleNames = new HashSet<>();
			for(UserRole role:userRoles) {
				roleNames.add(role.getRole().getName());
			}
			for(String roleName:roleNames) {
//				System.out.println(">>>> userName " +userService.findUserByUsername(principal.getName()).getUsername()+ ", role "+ roleName);
				if(roleName.equals("admin")){
					return "redirect:accountAll";
				}
			}
			return "redirect:accessDenied";					
		}
		return "adminLogin";
	}
	
	
	@RequestMapping(value="userSignUpForm")
	public String createUserSignUpForm(User user) {
		return "userSignUpForm";
	}
	
	@RequestMapping(value="createUserSignUp" , method = RequestMethod.POST)
	public String createUserSignUp(@Valid @ModelAttribute User user, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			String pw = bCryptEncoder.encode(user.getPassword());
			user.setPassword(pw);
			Set<Role> roles = new HashSet<>();
			roles.add(roleService.findRoleByName("user"));
			user.setRoles(roles);
			user = userService.saveUser(user);
			model.addAttribute("users", user);
			return "home";
		}
		return "userSignUpForm";
	}
	
	
	@RequestMapping(value="userForm")
	public String createUserForm(User user) {
		return "user";
	}
	
	@RequestMapping(value="createUser" , method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			String pw = bCryptEncoder.encode(user.getPassword());
			user.setPassword(pw);
			Set<Role> roles = new HashSet<>();
			roles.add(roleService.findRoleByName("user"));
			user.setRoles(roles);
			user = userService.saveUser(user);
			model.addAttribute("users", user);
			return "redirect:customerForm";
		}
		return "user";
	}
	
	
	@RequestMapping(value="/login")
	public String logIn(
			@RequestParam(value="logout", required=false) String logout,
			@RequestParam(value="error", required=false) String error,
			HttpServletRequest req, HttpServletResponse res, Model model
			
			){
		
		String errorMessage = null;
		if(error!=null){
			errorMessage = "Either the username or the password is not correct.";
		}
		if(logout !=null){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			Collection<?> authorities  = auth.getAuthorities();
			for (Object obj :  authorities){
				if (obj instanceof GrantedAuthority )
				System.out.println("****"+ ((GrantedAuthority) obj).getAuthority());
				
			}
			
			if(auth != null){
				new SecurityContextLogoutHandler().logout(req, res, auth);
			}
			System.out.println("1. auth: "+ auth);
			System.out.println("2. auth: "+ SecurityContextHolder.getContext().getAuthentication());
			return "redirect:/login";
		}
		
		model.addAttribute("errorMessage", errorMessage);
		return "login";
	}
	
	
	@RequestMapping(value="/accessDenyReturning")
	public String welcomeAdmin(Principal principal) {
		if(principal != null) {
			return "redirect:getAccountSummaryByCustomerId";
		}
		return "login";
	}
	
	
	@RequestMapping(value="accessDenied")
	public String denyAccess(Model model, Principal principal) {
		String message = "<strong> Hi " + principal.getName() + "</strong> !"
				+ "<br> You do not have access rights. Please return to your account by clicking the link below";
		model.addAttribute("message", message);
		return "accessDenied";
	}
}
