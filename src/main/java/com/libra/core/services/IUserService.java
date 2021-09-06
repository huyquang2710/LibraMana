package com.libra.core.services;

import java.util.Optional;

import com.libra.core.enity.User;

public interface IUserService {
	// param username
		// return User
		Optional<User> findByUsername(String username);
		
		// param username
		// return boolean
		Boolean existsByUsername(String username);
		
		// param email
		// return boolean
		Boolean existsByEmail(String email);
		
		//Save
		User save(User user);
}
