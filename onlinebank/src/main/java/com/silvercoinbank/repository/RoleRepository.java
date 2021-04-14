package com.silvercoinbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.silvercoinbank.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findRoleByName(String name);
}
