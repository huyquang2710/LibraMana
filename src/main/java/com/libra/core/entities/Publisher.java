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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
@Table(name = "publisher")
public class Publisher implements Serializable{
	private static final long serialVersionUID = -6952884252230958653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Tên nhà xuất bản không được trống")
	private String name;
	
	private String address;
	
	@Range(min = 10,max= 10, message = "định dạng 10 số" )
	private int phone;
	
	@Email(message = "Nhập đúng định dạng email")
	private String email;
	
	@OneToMany(mappedBy = "publisher")
	private List<Book> books = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
}

