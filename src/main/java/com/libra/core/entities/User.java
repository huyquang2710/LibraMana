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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
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
	
	@ManyToMany(mappedBy = "user")	
	private Set<Borrow> borrow; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User(Integer id, String name, String username, String email, String password, String address,
			boolean enabled, String description, String image, int year, int phone, Set<Role> roles, Date createdAt,
			Date modifiedAt, Set<Borrow> borrow) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.enabled = enabled;
		this.description = description;
		this.image = image;
		this.year = year;
		this.phone = phone;
		this.roles = roles;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.borrow = borrow;
	}

	public User() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Borrow> getBorrow() {
		return borrow;
	}

	public void setBorrow(Set<Borrow> borrow) {
		this.borrow = borrow;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
				+ password + ", address=" + address + ", enabled=" + enabled + ", description=" + description
				+ ", image=" + image + ", year=" + year + ", phone=" + phone + ", roles=" + roles + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + ", borrow=" + borrow + "]";
	}


}
