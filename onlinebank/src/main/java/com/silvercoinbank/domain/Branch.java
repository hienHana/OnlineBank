package com.silvercoinbank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


@Entity
public class Branch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long branchId;
	
	@Size(min = 2)
	@NotEmpty
	private String branchName;
	
	private String branchCity;
	
	private String branchState;
	
	private String branchCountry;
	
	@OneToMany (mappedBy="accountBranch", fetch = FetchType.EAGER)
	private List<Account> branchAccounts = new ArrayList<>();
	
	public Branch() {
		
	}

	public Branch(String branchName, String branchCity, String branchState, String branchCountry) {
		super();
		this.branchName = branchName;
		this.branchCity = branchCity;
		this.branchState = branchState;
		this.branchCountry = branchCountry;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchState() {
		return branchState;
	}

	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

	public String getBranchCountry() {
		return branchCountry;
	}

	public void setBranchCountry(String branchCountry) {
		this.branchCountry = branchCountry;
	}

	@XmlTransient
	public List<Account> getBranchAccounts() {
		return branchAccounts;
	}

	public void setBranchAccounts(List<Account> branchAccounts) {
		this.branchAccounts = branchAccounts;
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", branchCity=" + branchCity
				+ ", branchState=" + branchState + ", branchCountry=" + branchCountry  + "]";
	}
	
	
	
}
