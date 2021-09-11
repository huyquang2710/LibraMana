package com.libra.web.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SignUpDTO {
	private String name;
	private String username;
	private String email;
	private String password;
	private Set<String> roles;
}
