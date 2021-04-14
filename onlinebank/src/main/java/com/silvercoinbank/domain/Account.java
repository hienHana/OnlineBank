package com.silvercoinbank.domain;



import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long accountId;
	
	private String accountHolderName;
	
//	@NotEmpty
//	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate accountDateOpened;
	
	private double accountCurrentBalance;
	
	
	@ManyToOne
	@JoinColumn(name="branchId")
	@JsonBackReference
	private Branch accountBranch;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	@JsonBackReference
	private Customer accountCustomer;
	
	@XmlTransient
//	@JsonIgnore
	@JsonBackReference
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="account_transaction", 
	joinColumns=@JoinColumn(name="accountId"), 
	inverseJoinColumns = @JoinColumn(name="transId"))
	private Set<Transaction> accountTransactions = new HashSet<>();
	
	public Account() {
		
	}
	
	
	public Account(long accountId, String accountHolderName, AccountType accountType, LocalDate accountDateOpened,
			double accountCurrentBalance, Branch accountBranch, Customer accountCustomer,
			Set<Transaction> accountTransactions) {
		super();
		this.accountId = accountId;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.accountDateOpened = accountDateOpened;
		this.accountCurrentBalance = accountCurrentBalance;
		this.accountBranch = accountBranch;
		this.accountCustomer = accountCustomer;
		this.accountTransactions = accountTransactions;
	}

	@XmlTransient
	public Set<Transaction> getAccountTransactions() {
		return accountTransactions;
	}

	public void setAccountTransactions(Set<Transaction> accountTransactions) {
		this.accountTransactions = accountTransactions;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public LocalDate getAccountDateOpened() {
		return accountDateOpened;
	}

	public void setAccountDateOpened(LocalDate accountDateOpened) {
		this.accountDateOpened = accountDateOpened;
	}

	public double getAccountCurrentBalance() {
		return accountCurrentBalance;
	}

	public void setAccountCurrentBalance(double accountCurrentBalance) {
		this.accountCurrentBalance = accountCurrentBalance;
	}

	public Branch getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(Branch accountBranch) {
		this.accountBranch = accountBranch;
	}

	@XmlTransient
	public Customer getAccountCustomer() {
		return accountCustomer;
	}

	public void setAccountCustomer(Customer accountCustomer) {
		this.accountCustomer = accountCustomer;
	}



	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountHolderName=" + accountHolderName + ", accountType="
				+ accountType + ", accountDateOpened=" + accountDateOpened + ", accountCurrentBalance="
				+ accountCurrentBalance 
				+ ", accountCustomer=" + accountCustomer + "]";
	}

	
	
	
}
