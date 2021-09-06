package com.libra.core.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.core.common.RoleEnum;
import com.libra.core.enity.Role;
import com.libra.core.repositoies.RoleRepository;
import com.libra.core.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public Optional<Role> findByName(RoleEnum name) {
		
		return roleRepo.findByName(name);
	}

}
