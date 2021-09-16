package com.libra.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
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
	
	public String getPhotosImagePath() {
        if (image == null || id == null) return null;
         
        return "/avatar/author/" + id + "/" + image;
    }
	
}
