package com.libra.core.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

public interface GenericService<T> {
	Iterable<T> findAll();
	//Page<T> findAll(Pageable pageable);
	
//	//sort
//	List<T> findAll(Sort sort);
//	//Pgeable
//	Page<T> findAll(Pageable pageable);
	
	Optional<T> findById(Integer id);
	
	T save(T t);
	
	void remove(Integer id);
	
	//Phan trang , tim kiem
	Page<T> findPaginted(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
	Page<T> findPaginted(int pageNo, int pageSize, String sortField, String sortDirection);
	
	//search
	//List<T> findByNameContaining(String name);
}
