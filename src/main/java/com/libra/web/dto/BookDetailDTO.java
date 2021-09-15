package com.libra.web.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.libra.core.entities.Author;
import com.libra.core.entities.Category;
import com.libra.core.entities.Publisher;
import com.libra.core.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter  @Getter @ToString
public class BookDetailDTO {

	private Integer id;
	
	@NotEmpty(message="Tên Sách không được trống !!!")
	private String name;
	
	private int isbn;
	
	private String image;
	
	private String description;
	
	private User user;
	
	private Category category;
	
	private Publisher publisher;

	private Author author;
	
	private Date createdAt;
	
	private Date modifiedAt;
}
