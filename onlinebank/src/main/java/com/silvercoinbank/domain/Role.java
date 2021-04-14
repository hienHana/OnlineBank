package com.silvercoinbank.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@OneToMany(mappedBy = "role")
	Set<UserRole> userRole;
	
	@ManyToMany (mappedBy="roles")
	private Set<User> users = new HashSet<>();
	
	public Set<UserRole> getUserRole() {
		return userRole;
	}


	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}


	public Role(){
		
	}
	

	public Role(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Role(String name) {
		this.name = name;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", userRole=" + userRole + "]";
	}
	

}
