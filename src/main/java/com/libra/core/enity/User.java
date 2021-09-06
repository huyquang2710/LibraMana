package com.libra.core.enity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
})
public class User implements Serializable{

	public User(String name, String username, String email, String password) {
		this.name = name; 
		this.username = username;
		this.email = email;
		this.password = password;
	}

	private static final long serialVersionUID = 8168125831658828544L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	@Size(min = 5, max = 50, message = "Độ dài tên trong khoảng từ 5 đến 50 ký tự!!")
	@NotBlank(message = "Yêu cầu nhập tên đầy đủ!!")
	private String name;
	
	@Size(min = 2, max = 20, message = "Độ dài tên trong khoảng từ 2 đến 20 ký tự!!")
	@NotBlank(message = "Yêu cầu nhập tên!!")
	private String username;
	
	@Email(message = "Định dạng email")
	@NotBlank(message = "Yêu cầu nhập email !!")
	private String email;
	
	@NotBlank(message = "Không được để trống")
	@JsonIgnore
	private String password;
	

	private String address;
	
	
	@Lob // tao ra nhung chuoi van ban dai
	private String image;
	
	@Column(length = 100) 
	private int year;
	
	private int phone;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
//	@OneToMany(mappedBy = "member")
//	private List<Book> books = new ArrayList<>();
	
//	@OneToMany(mappedBy = "book")
//	private List<Borrow> borrow = new ArrayList<>();
//	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
}
