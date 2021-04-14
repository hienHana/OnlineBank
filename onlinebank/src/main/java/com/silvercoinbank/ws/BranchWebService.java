package com.silvercoinbank.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface BranchWebService {
	@WebMethod
	public @WebResult BranchResponse createBranch(@WebParam BranchCreateRequest branchRequest);
	
	@WebMethod
	public @WebResult BranchResponse getAllBranches();
	
	@WebMethod
	public @WebResult BranchResponse getBranchById(@WebParam BranchRequestById branchRequest);
	
	@WebMethod
	public @WebResult BranchResponse deleteBranchById(@WebParam BranchRequestById branchRequest);
	
	@WebMethod
	public @WebResult BranchResponse updateBranchById(@WebParam BranchCreateRequest branchCreateRequest);
}
