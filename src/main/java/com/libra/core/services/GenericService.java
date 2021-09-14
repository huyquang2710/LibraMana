package com.libra.core.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;
import com.libra.exception.ResourceNotFoundException;

public interface GenericService<T> {
	//CRUD
	List<T> findAll();

	Optional<T> findById(Integer id) throws ResourceNotFoundException;
	
	T save(T t) throws BadResourceException, ResourceAlreadyExistsException;
	
	void update(T t) throws BadResourceException, ResourceNotFoundException;
	
	void delete(Integer id) throws ResourceNotFoundException;
	
	//phân trang
	Page<T> findByPageable(int pageNo);
	
	//tìm kiếm
	List<T> findByNameContaining(String name);
}
