//package com.libra.core.enity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor @AllArgsConstructor
//@Table(name = "borrow")
//public class Borrow implements Serializable{
//	
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 965899214290922861L;
//
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "memberid")
//	private User member;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "bookid")
//	private Book book;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "borrowdate")
//	private Date borrowDate;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "returndate")
//	private Date returndate;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "createdat")
//	private Date createdAt;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "modifiedat")
//	private Date modifiedAt;
//	
//	
//}