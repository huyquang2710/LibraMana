package com.libra.core.repositoies;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.libra.core.common.RoleEnum;
import com.libra.core.enity.Role;

@Repository
public interface RoleRepository {
	//param name
	// return Role
	Optional<Role> findByName(RoleEnum name);
}
