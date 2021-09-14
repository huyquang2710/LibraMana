package com.libra.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
public class PublisherDTO implements Serializable{
	private static final long serialVersionUID = -6952884252230958653L;

	private Integer id;	
	@NotEmpty(message="Tên nhà xuất bản không được trống")
	private String name;
	private String address;
	private int phone;
	@Email(message = "Nhập đúng định dạng email")
	private String email;
	private String image;
}

