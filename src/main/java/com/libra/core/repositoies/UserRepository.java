package com.libra.core.repositoies;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.username =:username")
	User getUsernameByUsername(@Param("username") String username);
	
	
	// param username
	// return boolean
	Optional<User> existsByUsername(String username);
	
	// param email
	// return boolean
	Optional<User> existsByEmail(String email);
}
