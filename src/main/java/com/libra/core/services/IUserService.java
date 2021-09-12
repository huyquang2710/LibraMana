package com.libra.core.services;

import org.springframework.data.repository.query.Param;

import com.libra.core.entities.User;
import com.libra.web.dto.SignUpDTO;

public interface IUserService {
		
		User getUsernameByUsername(@Param("username") String username);
		
		// param username
		// return boolean
		//Optional<User> findByUsername(String username);
	
		//User findByUsername(String username);
		
		// param email
		// return boolean
		//Optional<User> findByEmail(String email);
		//boolean emailExists(String email);
		//User findByEmail(String email);
		
		boolean userExists(String username);
		boolean emailExists(String email);
		
		//register
		User register(SignUpDTO user);
}
