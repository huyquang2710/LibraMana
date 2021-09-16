package com.libra.core.entities;

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
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author implements Serializable {

	private static final long serialVersionUID = 1244004654841927334L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;

	private String address;
	
	@Column(length = 200)
	private String image;


	@Column(length = 100)
	private int year;

	
	private int phone;
	
	
	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;

	@Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;
         
        return "/avatar/author/" + id + "/" + image;
    }
}
