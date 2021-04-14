package com.silvercoinbank.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity(name = "user_role")
public class UserRole {

	@EmbeddedId
	private UserRoleRelationKey id;
	
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name="user_id")
	private User user;
	
	
	@ManyToOne
	@MapsId("role_id")
	@JoinColumn(name = "role_id")
	private Role role;
	
	
	UserRole(){}

	public UserRoleRelationKey getId() {
		return id;
	}

	public void setId(UserRoleRelationKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
