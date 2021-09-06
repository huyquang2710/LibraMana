package com.libra.core.repositoies;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.core.enity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	// param username
	// return User
	Optional<User> findByUsername(String username);
	
	// param username
	// return boolean
	Boolean existsByUsername(String username);
	
	// param email
	// return boolean
	Boolean existsByEmail(String email);
}
