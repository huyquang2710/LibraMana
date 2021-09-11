package com.libra.core.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.core.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	//@param name
	// return Role
	Role findByName(String roles);
}
