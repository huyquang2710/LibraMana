package com.libra.core.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
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
	private static final long serialVersionUID = 8168125831658828544L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
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
	@JsonIgnore
	private String password;

	private String address;
	
	private boolean enabled;
	
	
	@Lob // tao ra nhung chuoi van ban dai
	private String image;
	
	@Column(length = 100) 
	private int year;
	
	private int phone;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
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
