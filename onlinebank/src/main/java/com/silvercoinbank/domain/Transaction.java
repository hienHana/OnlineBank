package com.silvercoinbank.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long transId;
	
	private long transFromAccountNo;
	
	private long transToAccountNo;
	
	private double transAmount;
	
	private LocalDateTime transDateTime;
	
	//deposit, transfer, withdrawal
	private String transType;
	
	private String transComments;
	
	private String whoMakesTrans;
	
	@XmlTransient
	@ManyToMany(mappedBy="accountTransactions")
	private Set<Account> transAccount = new HashSet<>();
	
	public Transaction() {
		
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public long getTransFromAccountNo() {
		return transFromAccountNo;
	}

	public void setTransFromAccountNo(long transFromAccountNo) {
		this.transFromAccountNo = transFromAccountNo;
	}

	public long getTransToAccountNo() {
		return transToAccountNo;
	}

	public void setTransToAccountNo(long transToAccountNo) {
		this.transToAccountNo = transToAccountNo;
	}

	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}

	public LocalDateTime getTransDateTime() {
		return transDateTime;
	}

	public void setTransDateTime(LocalDateTime transDateTime) {
		this.transDateTime = transDateTime;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransComments() {
		return transComments;
	}

	public void setTransComments(String transComments) {
		this.transComments = transComments;
	}

	
	@XmlTransient
	public Set<Account> getTransAccount() {
		return transAccount;
	}

	public void setTransAccount(Set<Account> transAccount) {
		this.transAccount = transAccount;
	}

	public String getWhoMakesTrans() {
		return whoMakesTrans;
	}

	public void setWhoMakesTrans(String whoMakesTrans) {
		this.whoMakesTrans = whoMakesTrans;
	}
	
	
	
}
