package com.libra.core.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role") 
public class Role implements Serializable{

	private static final long serialVersionUID = 1817680817197571719L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 20)
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")	
	private Set<User> users;

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Role(Integer id, String name, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public Role() {
		super();
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
	
	
}
