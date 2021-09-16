package com.libra.core.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.libra.core.entities.Role;
import com.libra.core.entities.User;
import com.libra.core.repositoies.RoleRepository;
import com.libra.core.repositoies.UserRepository;
import com.libra.core.services.IUserService;
import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceNotFoundException;
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
	private PasswordEncoder passwordEncoder;


	@Override
	public User getUsernameByUsername(String username) {
		return userRepo.getUsernameByUsername(username);
	}
	private boolean existsById(Integer id) {
		return userRepo.existsById(id);
	}
	
	//dang ky moi
	@Transactional
	public User save(User user) {
		return userRepo.save(user);
	}
	@Override
	public User registerUser(SignUpDTO userDTO) {
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
	@Override
	public User registerAdmin(SignUpDTO userDTO) {
		// ma hoa pass
		String password = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(password);
		
		User user = new User();
		user.setImage("avatar.png");
		user.setEnabled(true);
		
		//set role
		Set<Role> role = new HashSet<>();
		role.add(roleRepo.findByName("ADMIN"));
		user.setRoles(role);
		
		//chuyen Dto sang entity
		modelMapper.map(userDTO, user);
		
		return save(user);
	}

	//check username va email
	@Override
	public boolean userExists(String username) {
		return userRepo.findExistByname(username);
	}
	@Override
	public boolean emailExists(String email) {
		return userRepo.findExistByemail(email);
	}

	@Override
	public Optional<User> findById(Integer id) throws ResourceNotFoundException{
		User user = userRepo.findById(id).get();
		if(user == null) {
			throw new ResourceNotFoundException("Không tìm thấy id: " + id);
		}
		Optional<User> userOpt = Optional.ofNullable(user);
		return userOpt;
	}

	//update
	@SuppressWarnings("deprecation")
	@Override
	public void update(User user) throws BadResourceException, ResourceNotFoundException {
		if(!StringUtils.isEmpty(user.getName())) {
			if(!existsById(user.getId())) {
				throw new ResourceNotFoundException("Không tìm thấy id: " + user.getId());
			}
			userRepo.save(user);
		} else {
			BadResourceException exc = new BadResourceException("Lỗi!!. Không thể cập nhật");
			exc.addErrorMessage("Tài khoản trống hoặc rỗng!!");
			throw exc;
		}
		
	}
	//forgot mat khau
	@Override
	public void updateResetPassword(String token, String email) {
		User user = userRepo.findByEmail(email);
		
		if(user != null) {
			user.setResetPasswordToken(token);
			userRepo.save(user);
		} else {
			throw new UsernameNotFoundException("Không tìm thấy email: " + email +". Vui lòng nhập lại");
		}
	}
	//reset pass
	public User get(String resetPasswordToken) {
		return userRepo.findByResetPasswordToken(resetPasswordToken);
	}
	//new pass
	public void updateNewPassword(User user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encoderPassword = passwordEncoder.encode(newPassword);
		
		user.setPassword(encoderPassword);
		user.setResetPasswordToken(null);
		
		userRepo.save(user);
	}
}
