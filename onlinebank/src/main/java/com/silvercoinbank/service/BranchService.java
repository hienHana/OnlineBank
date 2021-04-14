package com.silvercoinbank.service;

import java.util.List;

import com.silvercoinbank.domain.Branch;

public interface BranchService {
	public void saveBranchUsingHibernate(Branch b);
	
	public List<Branch> findAllBranchUsingHibernate();
	
	public Branch findBranchByIdUsingHibernate(long id);
	
	public void deleteBranchUsingHibernate(long id);
	
	public void updateBranchUsingHibernate(Branch b);
	
}
