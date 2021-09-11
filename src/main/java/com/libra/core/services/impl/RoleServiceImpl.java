package com.libra.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.core.entities.Role;
import com.libra.core.repositoies.RoleRepository;
import com.libra.core.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Role findByNameRole(String name) {
		
		return roleRepo.findByName(name);
	}
	
}
