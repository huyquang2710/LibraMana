package com.libra.core.services;

import java.util.List;
import java.util.Optional;

import com.libra.exception.BadResourceException;
import com.libra.exception.ResourceAlreadyExistsException;

public interface GenericService<T> {
	
	List<T> findAll();

	Optional<T> findById(Integer id);
	
	T save(T t) throws BadResourceException, ResourceAlreadyExistsException;
	
	void delete(Integer id);

}
