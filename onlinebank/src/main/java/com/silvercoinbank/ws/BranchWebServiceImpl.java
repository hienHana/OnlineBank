package com.silvercoinbank.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.domain.Branch;
import com.silvercoinbank.repository.BranchRepository;

@Service
public class BranchWebServiceImpl implements BranchWebService {

	@Autowired
	BranchRepository branchRepository;
	
	@Override
	public BranchResponse createBranch(BranchCreateRequest branchRequest) {
		Branch branch = new Branch();
		branch.setBranchId(branchRequest.getBranchId());
		branch.setBranchName(branchRequest.getBranchName());
		branch.setBranchCity(branchRequest.getBranchCity());
		branch.setBranchState(branchRequest.getBranchState());
		branch.setBranchCountry(branchRequest.getBranchCountry());
		branchRepository.save(branch);
		
		List<Branch> branches = new ArrayList<>();
		branches.add(branch);
		
		BranchResponse response = new BranchResponse();
		response.setMessageCode("CREATED");
		response.setMessage("Branch created as follow: ");
		response.setBranches(branches);
		return response;
	}

	@Override
	public BranchResponse getAllBranches() {
		List<Branch> branches = branchRepository.findAll();
		
		BranchResponse response = new BranchResponse();
		if(!branches.isEmpty()) {
			response.setMessageCode("FOUND");
			response.setMessage("Branches are listed below: ");
			response.setBranches(branches);
		}else {
			response.setMessageCode("NOT FOUND");
			response.setMessage("No branch found");
		}
		return response;
	}

	@Override
	public BranchResponse getBranchById(BranchRequestById branchRequest) {
		BranchResponse response = new BranchResponse();
		Branch branch = branchRepository.findById(branchRequest.getBranchId()).get();
		if(branch != null){
			List<Branch> branches = new ArrayList<>();
			branches.add(branch);
			
			response.setMessageCode("FOUND branch ID " + branch.getBranchId());
			response.setMessage("Branch detail below:");
			response.setBranches(branches);
		}else {
			response.setMessageCode("NOT FOUND");
			response.setMessage("There is no branch with id = " + branchRequest.getBranchId());
		}
		
		return response;
	}

	@Override
	public BranchResponse deleteBranchById(BranchRequestById branchRequest) {
		BranchResponse response = new BranchResponse();
		boolean existed = branchRepository.existsById(branchRequest.getBranchId());
		if(existed) {
			response.setMessageCode("DELETED");
			response.setMessage("Branch with id = " +branchRequest.getBranchId() + " has been deleted" );
		}else {
			response.setMessageCode("NOT FOUND");
			response.setMessage("There is no branch with id = " + branchRequest.getBranchId());
		}
		return response;
	}

	@Override
	public BranchResponse updateBranchById(BranchCreateRequest branchCreateRequest) {
		BranchResponse response = new BranchResponse();
		Branch branch = branchRepository.findById(branchCreateRequest.getBranchId()).get();
		if(branch != null) {
			branch.setBranchId(branchCreateRequest.getBranchId());
			branch.setBranchName(branchCreateRequest.getBranchName());
			branch.setBranchCity(branchCreateRequest.getBranchCity());
			branch.setBranchState(branchCreateRequest.getBranchState());
			branch.setBranchCountry(branchCreateRequest.getBranchCountry());
			branchRepository.save(branch);
			
			response.setMessageCode("UPDATED");
			response.setMessage("Branch with id = " +branchCreateRequest.getBranchId() + " has been updated" );			
		}else {
			response.setMessageCode("NOT FOUND");
			response.setMessage("There is no branch with id = " + branchCreateRequest.getBranchId());
		}
		
	
		return response;
	}

}
