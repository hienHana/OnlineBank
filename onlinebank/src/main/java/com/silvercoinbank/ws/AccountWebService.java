package com.silvercoinbank.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface AccountWebService {
	@WebMethod
	public @WebResult AccountResponse getAllAccounts ();
	
	@WebMethod
	public @WebResult AccountResponse getAccountById (@WebParam AccountRequestById request);
	
	
}
