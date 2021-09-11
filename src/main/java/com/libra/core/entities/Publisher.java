package com.libra.core.entities;
//package com.libra.core.enity;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
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
//@Table(name = "publisher")
//public class Publisher implements Serializable{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -6952884252230958653L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
//	private String name;
//	
//	
//	private String address;
//	
//	@Column(columnDefinition = "VARCHAR(100)")
//	private int phone;
//	
//	@Column(columnDefinition = "VARCHAR(150)")
//	private String email;
//	
//	@OneToMany(mappedBy = "publisher")
//	private List<Book> books = new ArrayList<>();
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "createdat")
//	private Date createdAt;
//	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "modifiedat")
//	private Date modifiedAt;
//}
//
