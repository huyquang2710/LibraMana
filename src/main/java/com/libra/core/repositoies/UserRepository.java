package com.libra.core.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.username =:username")
	User getUsernameByUsername(@Param("username") String username);
	
	@Query("SELECT c FROM User c WHERE c.email = ?1")
	User findByEmail(String emmail);
	
	User findByUsername(String username);
	// param username
	// return boolean
	//Optional<User> findByUsername(String username);
	//User findByUsername(String username);
	
	// param email
	// return boolean
	//Optional<User> findByEmail(String email);
	//User findByEmail(String email);
	
	@Query("select count(p) = 1 from User p where username = ?1")
	boolean findExistByname(String name);
	@Query("select count(p) = 1 from User p where email = ?1")
	boolean findExistByemail(String email);
	
	// quen pass
	User findByResetPasswordToken(String token);
}
