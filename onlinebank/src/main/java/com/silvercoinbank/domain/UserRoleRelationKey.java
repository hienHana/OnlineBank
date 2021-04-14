package com.silvercoinbank.domain;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRoleRelationKey implements Serializable{
	
	@Column(name="user_id")
	long userId;
	
	@Column(name="role_id")
	long roleId;
	
	UserRoleRelationKey(){}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
}
