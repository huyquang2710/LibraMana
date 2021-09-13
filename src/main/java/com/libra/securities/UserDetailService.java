package com.libra.securities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.libra.core.entities.Role;
import com.libra.core.entities.User;
import com.libra.core.services.IUserService;

@Service
public class UserDetailService implements UserDetailsService{
	
	// tìm kiếm username password trong DB
	@Autowired
	private IUserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUsernameByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy user!!");
		}
//		return new MyUserDetails(user);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<Role> roles = user.getRoles();
		for(Role role: roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	
	}
}
