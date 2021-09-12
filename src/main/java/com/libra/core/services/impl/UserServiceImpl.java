package com.libra.core.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.libra.core.entities.Role;
import com.libra.core.entities.User;
import com.libra.core.repositoies.RoleRepository;
import com.libra.core.repositoies.UserRepository;
import com.libra.core.services.IUserService;
import com.libra.web.dto.SignUpDTO;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	public User getUsernameByUsername(String username) {
		return userRepo.getUsernameByUsername(username);
	}


	//check user 
	@Override
	@Transactional
	public Optional<User> existsByUsername(String username) {
	
		return userRepo.existsByUsername(username);
	}	
	public boolean userExists(String user) {
		return existsByUsername(user).isPresent();
	}
	//check email ton tai
	@Override
	@Transactional
	public Optional<User> existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	public boolean emailExists(String email) {
		return existsByEmail(email).isPresent();
	}
	
	//dang ky moi
	@Transactional
	public User save(User user) {
		return userRepo.save(user);
	}
	@Override
	public User register(SignUpDTO userDTO) {
		// ma hoa pass
		String password = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(password);
		
		User user = new User();
		user.setImage("avatar.png");
		user.setEnabled(true);
		
		//set role
		Set<Role> role = new HashSet<>();
		role.add(roleRepo.findByName("USER"));
		user.setRoles(role);
		
		//chuyen Dto sang entity
		modelMapper.map(userDTO, user);
		
		return save(user);
	}

}
