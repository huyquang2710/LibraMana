package com.libra.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;  
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthorDTO {
	private Integer id;
	
    @NotBlank(message = "Tên tác giả không được trống!!!")
	private String name;
    
	private String address;
	private String image;
	private int year;
	private int phone;
	
	@Email(message = "Đúng định dạng email")
	private String email;
	
}
