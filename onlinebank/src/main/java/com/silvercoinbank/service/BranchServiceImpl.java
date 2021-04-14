package com.silvercoinbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.dao.BranchDao;
import com.silvercoinbank.domain.Branch;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	BranchDao branchDao;

	@Override
	public void saveBranchUsingHibernate(Branch b) {
		branchDao.saveBranchUsingHibernate(b);		
	}

	@Override
	public List<Branch> findAllBranchUsingHibernate() {
		return branchDao.findAllBranchUsingHibernate();
	}

	@Override
	public Branch findBranchByIdUsingHibernate(long id) {
		return branchDao.findBranchByIdUsingHibernate(id);
	}

	@Override
	public void deleteBranchUsingHibernate(long id) {
		branchDao.deleteBranchUsingHibernate(id);		
	}

	@Override
	public void updateBranchUsingHibernate(Branch b) {
		branchDao.updateBranchUsingHibernate(b);		
	}
	
	
	
}
