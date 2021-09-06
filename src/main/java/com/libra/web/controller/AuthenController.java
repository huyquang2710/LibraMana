package com.libra.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libra.core.common.RoleEnum;
import com.libra.core.enity.Role;
import com.libra.core.enity.User;
import com.libra.core.services.IRoleService;
import com.libra.core.services.IUserService;
import com.libra.securities.jwt.JwtProvider;
import com.libra.securities.userprincal.UserPrinciple;
import com.libra.web.dto.request.SignInForm;
import com.libra.web.dto.request.SignUpForm;
import com.libra.web.dto.response.JwtResponse;
import com.libra.web.dto.response.MessageResponse;

@RequestMapping("/api/auth")
@RestController
public class AuthenController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;         
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/signup")
	public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
		//check truong hop bi trung user email
		if(userService.existsByUsername(signUpForm.getUsername())) {
			return new ResponseEntity<>(new MessageResponse("Username đã đồn tại! Vui lòng đổi tên khác"), HttpStatus.OK);
		}
		if(userService.existsByEmail(signUpForm.getEmail())) {
			return new ResponseEntity<>(new MessageResponse("Email đã đồn tại! Vui lòng đổi tên khác"), HttpStatus.OK);
		}
		User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
		Set<String> strRoles = signUpForm.getRoles();
		
		//lay gia tri role
		Set<Role> roles = new HashSet<>();
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleService.findByName(RoleEnum.ADMIN).orElseThrow( () -> new RuntimeException("Không tìm thấy vai trò"));
				roles.add(adminRole);
				break;
			default:
				Role userRole = roleService.findByName(RoleEnum.USER).orElseThrow( () -> new RuntimeException("Không tìm thấy vai trò"));
				roles.add(userRole);
				break;
			}
		});
		user.setRoles(roles);
		
		// save vao database
		userService.save(user);
		
		return new ResponseEntity<>(new MessageResponse("Thêm Tài khoản thành công"), HttpStatus.OK);
	}
	@PostMapping("/signin")
	public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
		
		Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.creteToken(authentication);
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
	}
}
