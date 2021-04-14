package com.silvercoinbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvercoinbank.domain.User;
import com.silvercoinbank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String name) {
		return userRepository.findUserByUsername(name);
	}

	@Override
	public boolean existById(long id) {
		return userRepository.existsById(id);
	}

//	@Override
//	public long findCustomerIdByUserId(long userId) {
//		return userRepository.findCustomerIdByUserId(userId);
//	}

}
