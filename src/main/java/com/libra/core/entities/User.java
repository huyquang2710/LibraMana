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
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
public class User implements Serializable{
	private static final long serialVersionUID = 8168125831658828544L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@Column(unique=true)
	private String username;
	
	@Column(unique=true)
	private String email;
	
	@JsonIgnore
	private String password;

	private String address;
	
	private boolean enabled;
	
	private String description;

	private String image;
	
	@Column(length = 100) 
	private int year;
	
	private int phone;
	
	@Column(name = "resetpasswordtoken")
	private String resetPasswordToken;
	
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
	
	@ManyToMany(mappedBy = "user")	
	private Set<Borrow> borrow;

}
