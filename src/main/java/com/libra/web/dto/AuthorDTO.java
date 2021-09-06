package com.libra.web.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AuthorDTO {
	private Integer id;
	private String name;
	private String address;
	private String image;
	private int year;
	private int phone;
	private String email;
	private Date createdAt;
	private Date modifiedAt;
	
}
