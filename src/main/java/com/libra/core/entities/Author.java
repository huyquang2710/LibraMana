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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	@NotBlank(message = "Yêu cầu nhập tên!!")
	@Size(min = 2, max = 20, message = "Độ dài tên trong khoảng từ 2 đến 20 ký tự!!")
	private String name;

	private String address;
	
	@Column(length = 200)
	private String image;


	@Column(length = 100)
	private int year;

	
	private int phone;
	
	
	@Column(unique = true)
	@Email(message = "Đúng định dạng email")
	private String email;

	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "createdat")
	private Date createdAt;

	@Temporal(TemporalType.DATE)
	@Column(name = "modifiedat")
	private Date modifiedAt;
}
