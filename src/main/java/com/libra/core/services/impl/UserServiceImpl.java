package com.libra.core.services.impl;

import java.util.HashSet;
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


//	//check user 
////	@Override
////	@Transactional
////	public Optional<User> findByUsername(String username) {
////	
////		return userRepo.findByUsername(username);
////	}	
//	@Override
//	public boolean userExists(String username) {
//		return userRepo.findByUsername(username);
//	}
//	//check email ton tai
//	@Override
//	@Transactional
//	public Optional<User> findByEmail(String email) {
//		return userRepo.findUser
//	}
//	@Override
//	public boolean emailExists(String email) {
//		return findUserByEmail(email).isPresent();
//	}
	
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


//	@Override
//	public User findByUsername(String username) {
//		return userRepo.findByUsername(username);
//	}
//
//
//	@Override
//	public User findByEmail(String email) {
//		return userRepo.findByEmail(email);
//	}

	//check username va email
	@Override
	public boolean userExists(String username) {
		return userRepo.findExistByname(username);
	}
	@Override
	public boolean emailExists(String email) {
		return userRepo.findExistByemail(email);
	}

}
