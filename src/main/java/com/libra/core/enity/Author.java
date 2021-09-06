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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1244004654841927334L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
	private String name;

	private String address;
	
	@Column(length = 200)
	private String image;


	@Column(length = 100)
	private int year;

	@Column(columnDefinition = "VARCHAR(100)")
	private int phone;
	
	
	@Column(columnDefinition = "VARCHAR(150)")
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
		if (image == null || id == null)
			return null;

		return "/images/author-avatars/" + id + "/" + image;
	}
}
