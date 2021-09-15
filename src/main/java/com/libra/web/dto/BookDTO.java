package com.libra.web.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter  @Getter @ToString
public class BookDTO {
	@NotEmpty(message="Tên tác giả không được trống !!!")
	private String name;
	
	private int isbn;
}
