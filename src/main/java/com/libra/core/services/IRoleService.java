package com.libra.core.services;

import com.libra.core.entities.Role;

public interface IRoleService {
	//@param name
	// return Role
	Role findByNameRole(String name);
}
