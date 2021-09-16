package com.libra.core.services;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.libra.core.entities.User;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceNotFoundException;
import com.libra.web.dto.SignUpDTO;

public interface IUserService {
		
		User getUsernameByUsername(@Param("username") String username);	
		
		boolean userExists(String username);
		boolean emailExists(String email);
		
		//register
		User registerUser(SignUpDTO user);
		User registerAdmin(SignUpDTO user);
		
		Optional<User> findById(Integer id) throws ResourceNotFoundException;
		
		void update(User user) throws BadResourceException, ResourceNotFoundException;
}
