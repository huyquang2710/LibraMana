package com.libra.core.services;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.libra.core.entities.User;
import com.libra.web.dto.SignUpDTO;

public interface IUserService {
		
		User getUsernameByUsername(@Param("username") String username);
		
		// param username
		// return boolean
		Optional<User> existsByUsername(String username);
		boolean userExists(String user);
		
		// param email
		// return boolean
		Optional<User> existsByEmail(String email);
		boolean emailExists(String email);
		
		//register
		User register(SignUpDTO user);
}
