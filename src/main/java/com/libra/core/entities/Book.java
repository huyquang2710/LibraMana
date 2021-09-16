 package com.libra.core.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Table(name = "book")
public class Book implements Serializable{

	private static final long serialVersionUID = -1176413046749324010L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(length = 100)
	private int isbn;
	
	@Lob
	private String image;
	
	private String description;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "userid", insertable = false, updatable = false)
//	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryid")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisherid")
	private Publisher publisher;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorid")
	private Author author;
	
	@OneToMany(mappedBy = "book")
	private List<Borrow> borrow = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
	
	@Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;
         
        return "/avatar/book/" + id + "/" + image;
    }
}
 