package com.silvercoinbank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.silvercoinbank.domain.Customer;
import com.silvercoinbank.domain.User;
import com.silvercoinbank.service.CustomerService;

@Controller
public class CusomerController {
	
	@Autowired CustomerService customerService;
	
	@RequestMapping(value="customerForm")
	public String createCustomerForm(Customer customer) {
		return "customer";
	}
	
	@RequestMapping(value="createCustomer", method = RequestMethod.POST)
	public String createCustomer(@Valid @ModelAttribute Customer customer, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			customer = customerService.saveCustomer(customer);
			return "redirect:accountForm";
		}
		return "customer";
	}
	
}
