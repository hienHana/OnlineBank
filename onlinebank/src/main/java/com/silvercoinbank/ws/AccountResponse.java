package com.silvercoinbank.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.silvercoinbank.domain.Account;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountResponse {
	
	@XmlElement
	private String message;
	
	@XmlElement
	private String messageCode;
	
	@XmlElement
	private List<Account> accounts;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
