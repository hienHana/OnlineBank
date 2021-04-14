package com.silvercoinbank.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.silvercoinbank.domain.Branch;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class BranchResponse {
	//The order of declaring these affect the order of display on SOAPui
	@XmlElement
	private String message;
	
	@XmlElement
	private String messageCode;
	
	@XmlElement
	private List<Branch> branches;

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

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	
	

}
