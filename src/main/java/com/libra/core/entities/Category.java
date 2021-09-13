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
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Table(name = "category")
public class Category implements Serializable{

	private static final long serialVersionUID = 1639666791302990590L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Thể loại không được trống")
	private String name;
	
	private String code;
	
	@OneToMany(mappedBy = "category")
	private List<Book> books = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
	
}
