package com.silvercoinbank.service;

import java.util.List;

import com.silvercoinbank.domain.User;

public interface UserService {
	public User saveUser(User user);
	public User findUserByUsername(String name);
	public boolean existById(long id);
	
//	public long findCustomerIdByUserId(long userId);
}
