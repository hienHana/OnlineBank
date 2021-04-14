package com.silvercoinbank.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.silvercoinbank.domain.Role;
import com.silvercoinbank.domain.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userService.findUserByUsername(name);
		System.out.println("username and password " + user.getUsername() + ":::"+user.getPassword());
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		for(Role role:user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			System.out.println("Role name: "+ role.getName());
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
