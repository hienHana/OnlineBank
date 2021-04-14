package com.silvercoinbank.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long customerId;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	private Address adrress;
	
	private String gender;

	@DateTimeFormat(iso = ISO.DATE)
	private String dob;
	
	@Email
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Digits(message = "Accept only digits up to 10", fraction = 0, integer = 10)
	private String phone;
	 
	@NotEmpty
	@Digits(message = "Please provide only digits", fraction = 0, integer = 15)
	private String ssn;
	
	@OneToMany 
	@JoinColumn(name="accountId")
	private List<Account> accounts = new ArrayList<>();
	
	//for login
	@OneToOne
	private User user;
	
	public Customer() {
		
	}

	

	public String getSsn() {
		return ssn;
	}



	public void setSsn(String ssn) {
		this.ssn = ssn;
	}



	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", adrress=" + adrress + ", gender=" + gender + ", dob=" + dob + ", email=" + email + ", phone="
				+ phone + ", ssn=" + ssn + ", user " + user + "]";
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAdrress() {
		return adrress;
	}

	public void setAdrress(Address adrress) {
		this.adrress = adrress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
