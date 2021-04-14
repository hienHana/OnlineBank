package com.silvercoinbank.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long userId;
	
	@NotEmpty
	@Size(min=3, max=50)
	private String username;
	
	
	@NotEmpty
//	@Size(min=5)
	@Column(length=250)
	private String password;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	@Digits(message = "Accept only digits up to 10", fraction = 0, integer = 10)
	private String phone;
	
	@OneToOne
	private Customer customer;
	
	@OneToMany(mappedBy = "user")
	Set<UserRole> userRole;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role", 
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id")
    )
	Set<Role>  roles = new HashSet<>();

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public User(){
	}

	public User(String username,  String password, String email,  String phone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", customer=" + customer + ", userRole=" + userRole + "]";
	}
	
	
	
}
