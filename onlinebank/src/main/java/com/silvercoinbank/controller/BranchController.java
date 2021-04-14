package com.silvercoinbank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.silvercoinbank.domain.Branch;
import com.silvercoinbank.service.BranchService;

@Controller
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@RequestMapping(value = "/saveBranchWithHibernate", method = RequestMethod.GET)
	public ResponseEntity<Branch> createBranch(@RequestParam String branchName, @RequestParam String branchCity, 
								@RequestParam String branchState, @RequestParam String branchCountry) {

		Branch b = new Branch( branchName, branchCity, branchState, branchCountry);
		branchService.saveBranchUsingHibernate(b);
		return new ResponseEntity<Branch>(b, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getAllBranchWithHibernate", method = RequestMethod.GET)
	public ResponseEntity <List<Branch>> getAllBranch() {
		List<Branch> branches = branchService.findAllBranchUsingHibernate();
		return new ResponseEntity<List<Branch>>(branches, HttpStatus.CREATED);
	}
	
	@RequestMapping (value = "/getBranchByIdWithHibernate")
	public ResponseEntity<Branch>  getBranchById(@RequestParam long id) {
		Branch b = new Branch();
		b = branchService.findBranchByIdUsingHibernate(id);
		return new ResponseEntity<Branch>(b, HttpStatus.FOUND);
	}
	
	@RequestMapping(value="/deleteBranch")
	public String deleteBranch(@RequestParam long id) {
		branchService.deleteBranchUsingHibernate(id);
		return "redirect:/getBranchForm";
	}
	
	@RequestMapping(value = "/getBranchForm", method = RequestMethod.GET)
	public String getAllBranch(Branch branch, Model model) {
		model.addAttribute("branches", branchService.findAllBranchUsingHibernate());
		return "branch";
	}
	
	@RequestMapping(value = "/saveBranch", method = RequestMethod.POST)
	public String saveBranch(@Valid @ModelAttribute Branch branch, BindingResult br) {
		if(!br.hasErrors()) {
			branchService.saveBranchUsingHibernate(branch);
			return "redirect:/getBranchForm";
		}
		return "branch";
	}
	
	@RequestMapping(value = "/updateBranchForm", method = RequestMethod.GET)
	public String updateBranchForm(@ModelAttribute Branch branch,@RequestParam long id,  Model model) {
		branch = branchService.findBranchByIdUsingHibernate(id);
		model.addAttribute("branch", branch);	
		return "branchUpdate";
	}
	
	
	@RequestMapping(value = "/updateBranch", method = RequestMethod.POST)
	public String updateBranch(@ModelAttribute Branch branch, Model model, BindingResult br) {
		if(!br.hasErrors()) {
			branchService.updateBranchUsingHibernate(branch);	
			model.addAttribute("branches", branch);
			return "redirect:/getBranchForm";
		}
		System.out.println("br has error");
		return "branch";
	}
	
	
}
