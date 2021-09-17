package com.libra.web.dto;

import java.util.Date;
import java.util.Set;

import com.libra.core.entities.Borrow;
import com.libra.core.entities.Role;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class UserDTO {
	private Integer id;
	
	private String name;

	private String username;

	private String email;
	
	private String address;
	
	private boolean enabled;
	
	private String description;
	
	private String image;
	
	private int year;
	
	private int phone;

	private Set<Role> roles;
	
//	@OneToMany(mappedBy = "member")
//	private List<Book> books = new ArrayList<>();
	
//	@OneToMany(mappedBy = "book")
//	private List<Borrow> borrow = new ArrayList<>();
//	

	private Date createdAt;
	

	private Date modifiedAt;
	
	private Set<Borrow> borrow; 
	
	
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;
         
        return "/avatar/author/" + id + "/" + image;
    }
}
