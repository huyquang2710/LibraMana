//package com.libra.core.enity;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "book")
//public class Book implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -1176413046749324010L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
//	private String name;
//	
//	@Column(length = 100)
//	private int isbn;
//	
//	@Column(length = 200)
//	private String image;
//	
//	@Column(columnDefinition = "VARCHAR(300)")
//	private String description;
//	
////	@ManyToOne(fetch = FetchType.LAZY)
////	@JoinColumn(name = "memberid", insertable = false, updatable = false)
////	private Member member;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "categoryid", insertable = false, updatable = false)
//	private Category category;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "publisherid", insertable = false, updatable = false)
//	private Publisher publisher;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "authorid", insertable = false, updatable = false)
//	private Author author;
//	
//	@OneToMany(mappedBy = "book")
//	private List<Borrow> borrow = new ArrayList<>();
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "createdat")
//	private Date createdAt;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "modifiedat")
//	private Date modifiedAt;
//}
