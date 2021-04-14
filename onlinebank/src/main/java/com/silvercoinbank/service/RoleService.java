package com.silvercoinbank.service;

import java.util.List;

import com.silvercoinbank.domain.Role;

public interface RoleService {
	public void saveRole(Role role);
	public List<Role> findAllRoles();
	public String findRoleNameByRoleId(long roleId);
	public Role findRoleByName(String name);

}
