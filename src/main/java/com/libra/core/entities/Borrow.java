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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Table(name = "borrow")
public class Borrow implements Serializable{
	
	private static final long serialVersionUID = 965899214290922861L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "borrow_user", joinColumns = @JoinColumn(name = "borrow_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookid")
	private Book book;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "borrowdate")
	private Date borrowDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "returndate")
	private Date returndate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
	
	
}