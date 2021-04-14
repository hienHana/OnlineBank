package com.silvercoinbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.domain.Role;
import com.silvercoinbank.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired RoleRepository roleRepository;
	
	@Override
	public String findRoleNameByRoleId(long roleId) {
		return roleRepository.findById(roleId).get().getName();
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role findRoleByName(String name) {
		return roleRepository.findRoleByName(name);
	}
	

}
