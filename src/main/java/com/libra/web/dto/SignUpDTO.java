package com.libra.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SignUpDTO {
	

	@NotBlank(message = "Yêu cầu nhập tên đầy đủ!!")
	@Size(min = 2, max = 30, message = "Độ dài từ 2 đến 30 ký tự!!")
	private String name;
	
	@NotBlank(message = "Yêu cầu nhập tên!!")
	@Size(min = 2, max = 20, message = "Độ dài từ 2 đến 20 ký tự!!")
	private String username;
	
	@Email(message = "Định dạng email")
	@NotEmpty(message = "Nhập email")
	private String email;
	
	@NotBlank(message = "Không được để trống")			
	private String password;
	//private Set<String> roles;
}
