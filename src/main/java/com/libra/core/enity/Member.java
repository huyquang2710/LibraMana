package com.libra.core.enity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "member")
public class Member implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8168125831658828544L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fullname", columnDefinition = "VARCHAR(200) NOT NULL")
	private String fullName;
	
	@Column( columnDefinition = "VARCHAR(200) NOT NULL")
	private String username;
	
	
	private String password;
	

	private String address;
	
	
	@Column(length = 200)
	private String image;
	
	@Column(length = 100)
	private int year;
	
	@Column(length = 100)
	private int phone;
	
	@Column(columnDefinition = "VARCHAR(150)")
	private String email;
	
//	@OneToMany(mappedBy = "member")
//	private List<Book> books = new ArrayList<>();
	
	@OneToMany(mappedBy = "book")
	private List<Borrow> borrow = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
}
