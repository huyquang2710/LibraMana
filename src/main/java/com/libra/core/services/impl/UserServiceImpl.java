package com.libra.core.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.core.enity.User;
import com.libra.core.repositoies.UserRepository;
import com.libra.core.services.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Optional<User> findByUsername(String username) {
		
		return userRepo.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
	
		return userRepo.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}

	@Override
	public User save(User user) {
	
		return userRepo.save(user);
	}

}
