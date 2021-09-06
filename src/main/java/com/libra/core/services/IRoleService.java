package com.libra.core.services;

import java.util.Optional;

import com.libra.core.common.RoleEnum;
import com.libra.core.enity.Role;

public interface IRoleService {
	//@param name
	// return Role
	Optional<Role> findByName(RoleEnum name);
}
