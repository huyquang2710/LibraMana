package com.libra.securities.userprincal;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.libra.core.enity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserPrinciple implements UserDetails{

	private static final long serialVersionUID = 3385388741604827430L;
	private Integer id;
	private String name;
	private String username;
	private String email;
	
	@JsonIgnore
	private String password;
	private String image;
	private Collection<? extends GrantedAuthority> roles;
	
	//lưu user trên hệ thống
	public static UserPrinciple build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> 
				new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserPrinciple(
			user.getId(),
			user.getFullName(),
			user.getUsername(),
			user.getEmail(),
			user.getPassword(),
			user.getImage(),
			authorities );
	}
	// trả về danh sách các quyền của người dùng
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return roles;
	}
	 //trả về password đã dùng trong qúa trình xác thực
	@Override
	public String getPassword() {
		
		return password;
	}
	//trả về username đã dùng trong qúa trình xác thực
	@Override
	public String getUsername() {
		
		return username;
	}
	// trả về true nếu tài khoản của người dùng chưa hết hạn
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	//trả về true nếu người dùng chưa bị khóa
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	//trả về true nếu chứng thực (mật khẩu) của người dùng chưa hết hạn
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	// trả về true nếu người dùng đã được kích hoạt	
	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
